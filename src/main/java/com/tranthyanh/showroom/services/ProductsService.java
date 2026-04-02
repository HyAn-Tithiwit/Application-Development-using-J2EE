package com.tranthyanh.showroom.services;

import com.tranthyanh.showroom.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ProductsService {
    private final Repo repo;
    private final Storage storage;

    public ProductsService(Repo repo, Storage storage) {
        this.repo = repo;
        this.storage = storage;
    }

    public List<Product> getProducts() {
        return repo.loadProducts();
    }

    public void addProduct(String name, MultipartFile image) throws Exception {
        // DIY - Task 1: Trim leading and trailing whitespace
        if (name != null) {
            name = name.trim();
        }

        if (StringUtils.isNullOrBlank(name)) {
            throw new Exception("Product name cannot be null or blank");
        }

        // DIY - Task 3: Ensure each product name is unique
        if (repo.isProductNameExists(name)) {
            throw new Exception("A product with the name '" + name + "' already exists");
        }

        if (image == null || image.isEmpty()) {
            throw new Exception("Product image cannot be null or empty");
        }

        var contentType = image.getContentType();
        if (!"image/jpeg".equals(contentType)) {
            throw new Exception("Product image must be in JPG format");
        }

        if (image.getSize() > 1024 * 1024) {
            throw new Exception("Product image cannot be bigger than 1MB");
        }

        var id = repo.insertProduct(name);
        storage.saveFile(image, "products/" + id + ".jpg");
    }
}