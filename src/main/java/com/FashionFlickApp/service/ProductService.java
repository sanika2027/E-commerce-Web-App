package com.FashionFlickApp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FashionFlickApp.model.Product;
import com.FashionFlickApp.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;
	
	public List<Product> addProduct(List<Product> products) {
		return productRepo.saveAll(products); 
		 
	}
	
	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}
	
	public Product getProductById(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	public List<Product> getProductByCategory(String category) {
		return productRepo.findByCategory(category);
	}
	
	public Product updateProduct(Long id, Product updateProduct) throws Exception{
		Product product = productRepo.findById(id)
	            .orElseThrow(() -> new Exception("Product not found"));
		
		product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());
        product.setCategory(updateProduct.getCategory());
        product.setImageUrl(updateProduct.getImageUrl());
        
        return productRepo.save(product);

	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
}
