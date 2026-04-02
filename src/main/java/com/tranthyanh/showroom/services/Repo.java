package com.tranthyanh.showroom.services;

import java.util.List;

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
}