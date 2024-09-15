package com.shoppingcart.shop.service.product;

import com.shoppingcart.shop.model.Product;
import com.shoppingcart.shop.request.AddProductRequest;
import com.shoppingcart.shop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    Product addProduct(AddProductRequest request);

    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
