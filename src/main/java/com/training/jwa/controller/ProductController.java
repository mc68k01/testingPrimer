package com.training.jwa.controller;

import com.training.jwa.models.Product;
import com.training.jwa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pr")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        ResponseEntity<String> responseEntity = null;
        String result = null;
        if (productService.isProductExists(product.getProductId())) {
            // failed
            responseEntity = new ResponseEntity<String>("Product already exists", HttpStatus.CONFLICT); // 409
        } else {
            result = productService.saveProduct(product);
            if (result.equals("Product saved successfully")) {
                responseEntity = new ResponseEntity<String>(result, HttpStatus.CREATED); // 201
            } else {
                responseEntity = new ResponseEntity<String>(result, HttpStatus.NOT_ACCEPTABLE); // 406
            }
        }
        // send appropriate status codes
        return responseEntity;
    }

//    @PostMapping
//    public String createProduct(@RequestBody Product product) {
//        // send appropriate status codes
//        // cheque whether the price and Quantity On Hand (QOH) must not be negative
//        // but that will violate the single responsibility principle
//        // we must create a service layer, and send the product f
//        return productService.saveProduct(product);
//    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        ResponseEntity<String> responseEntity = null;
        String result = null;
        if(!productService.isProductExists(product.getProductId())){
            // failed
            responseEntity = new ResponseEntity<String>("Product does not exist", HttpStatus.NO_CONTENT); //204
        }
        else {
            result = productService.updateProduct(product);
            if (result.equals("Product updated successfully")){
                responseEntity = new ResponseEntity<String>(result, HttpStatus.OK); //201
            }
            else {
                responseEntity = new ResponseEntity<String>(result, HttpStatus.NOT_ACCEPTABLE); //406
            }
        }
        return responseEntity;
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) {
        ResponseEntity<String> responseEntity = null;
        String result = null;
        if(!productService.isProductExists(productId)){
            // failed
            responseEntity = new ResponseEntity<String>("Product does not exist", HttpStatus.NOT_FOUND); //404
        }
        else {
            result = productService.deleteProduct(productId);
            if (result.equals("Product with product id: " + productId + " deleted successfully!!")){
                responseEntity = new ResponseEntity<String>(result, HttpStatus.OK); //200
            }
            else {
                responseEntity = new ResponseEntity<String>(result, HttpStatus.NOT_ACCEPTABLE); //406
            }
        }
        return responseEntity;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String productName) {
        ResponseEntity<List<Product>> responseEntity = null;
        List<Product> products = new ArrayList<Product>();
        if(productName==null){
            // failed
            products = productService.getProducts();
        }
        else {
            products = productService.getProductsByName(productName);
        }
        if (products.size() == 0) {
            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 204
        } else {
            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); //201
        }
        return responseEntity;
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {
        ResponseEntity<Product> responseEntity = null;
        Product product = null;

        if (!productService.isProductExists(productId))
            responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 204
        else {
            product = productService.getProduct(productId);
            responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK); // 200
        }
        return responseEntity;
    }

//    @GetMapping
//    public ResponseEntity<List<Product>> getProducts() {
//        ResponseEntity<List<Product>> responseEntity = null;
//        List<Product> products = new ArrayList<Product>();
//        products = productService.getProducts();
//        if (products.size() == 0) {
//            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); //
//
//        } else {
//            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 201
//        }
//        return responseEntity;
//    }

//    @GetMapping ("{price}")
//    public ResponseEntity<List<Product>> getProductsByPrice(@PathVariable("price") int price) {
//        ResponseEntity<List<Product>> responseEntity = null;
//        List<Product> products = new ArrayList<Product>();
//        if(price==0){
//            // failed
//            products = productService.getProductsByPrice(price);
//        }
//        else {
//            products = productService.getProductsByPrice(price);
//        }
//        if (products.size() == 0) {
//            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); //
//        } else {
//            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); //201
//        }
//        return responseEntity;
//    }

    @GetMapping("{minPrice}/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable("minPrice") int minPrice, @PathVariable("maxPrice") int maxPrice) {
        ResponseEntity<List<Product>> responseEntity = null;
        List<Product> products = new ArrayList<Product>();
        if(minPrice==0 || maxPrice==0){
            // failed
            products = productService.getProducts();
        }
        else {
            products = productService.getProductsByPriceRange(minPrice, maxPrice);
        }
        if (products.size() == 0) {
            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); //
        } else {
            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); //200
        }
        return responseEntity;
    }

    @GetMapping("{minQty}/{maxQty}")
    public ResponseEntity<List<Product>> getProductsByQuantityOnHand(@PathVariable("minQty") int minQty, @PathVariable("maxQty") int maxQty) {
        ResponseEntity<List<Product>> responseEntity = null;
        List<Product> products = new ArrayList<Product>();
        if(minQty==0 || maxQty==0){
            // failed
            products = productService.getProducts();
        }
        else {
            products = productService.getProductsByQuantityOnHand(minQty, maxQty);
        }
        if (products.size() == 0) {
            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); //
        } else {
            responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); //201
        }
        return responseEntity;
    }
}
