package com.tranthyanh.showroom.services;

import java.util.List;
import java.util.Optional;

public interface Repo {
    boolean checkLogin(String username, String password);
    CompanyName loadCompanyName();
    void saveCompanyName(CompanyName companyName);
    String loadAboutContent();
    void saveAboutContent(String aboutContent);

    // Part 5 additions
    List<Product> loadProducts();
    int insertProduct(String name);
    boolean isProductNameExists(String name);

    Optional<String> loadProductName(int id);
    UpdateProductResult updateProduct(int id, String name);
    void deleteProduct(int id);
}