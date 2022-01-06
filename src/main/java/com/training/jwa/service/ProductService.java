package com.training.jwa.service;

import com.training.jwa.models.Product;

import java.util.List;

public interface ProductService {

    public String saveProduct(Product product);
    public String updateProduct(Product product);
    public String deleteProduct(int productId);
    public Product getProduct(int productId);
    public List<Product> getProducts();
    public boolean isProductExists(int productId);
    public List<Product> getProductsByName(String productName);
    public List<Product> getProductsByPrice(int price);
    public List<Product> getProductsByPriceRange(int minPrice, int maxPrice);
    public List<Product> getProductsByQuantityOnHand(int minQty, int maxQty);
}
