package com.training.jwa.repository;

import com.training.jwa.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Type, and the identifier's data type's wrapper class
public interface ProductRepository extends CrudRepository<Product, Integer> {
    public List<Product> findByProductName(String productName);
    public List<Product> findByPrice(int price);
    public List<Product> findByPriceBetween(int minPrice, int maxPrice);
    public List<Product> findByQuantityOnHandBetween(int minQty, int maxQty);
}
