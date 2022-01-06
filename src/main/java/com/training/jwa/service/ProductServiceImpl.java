package com.training.jwa.service;
import com.training.jwa.models.Product;
import com.training.jwa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public String saveProduct(Product product) {
        if (product.getPrice() < 0 || product.getQuantityOnHand() < 0)
            // check whether the price and qoh must not be negative
            return "Product price or qoh cannot be negative";
        else {
            productRepository.save(product);
            return "Product saved successfully";
        }
    }
    public String updateProduct(Product product){
        if (product.getPrice() < 0 || product.getQuantityOnHand() < 0) return "Product price or QOH cannot be negative";
        else {
            productRepository.save(product);
            return "Product updated successfully";
        }
    }
    public String deleteProduct(int productId){
        productRepository.deleteById(productId);
        return "Product with product id: " + productId + " deleted successfully!!";
    }
    public Product getProduct(int productId){
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }
    public List<Product> getProducts(){
        return (List<Product>) productRepository.findAll();
    }


    @Override
    public boolean isProductExists(int productId) {
        Optional <Product> product = productRepository.findById(productId);
        return product.isPresent();
    }

    @Override
    public List<Product> getProductsByName(String productName){
        return productRepository.findByProductName(productName);
    }
    @Override
    public List<Product> getProductsByPrice(int price){
        return productRepository.findByPrice(price);
    }
    @Override
    public List<Product> getProductsByPriceRange(int minPrice, int maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    @Override
    public List<Product> getProductsByQuantityOnHand(int minQty, int maxQty) {
        return productRepository.findByQuantityOnHandBetween(minQty, maxQty);
    }
}
