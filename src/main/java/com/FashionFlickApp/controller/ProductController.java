package com.FashionFlickApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FashionFlickApp.model.Product;
import com.FashionFlickApp.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("")
	public ResponseEntity<List<Product>> addProduct(@RequestBody List<Product> newProduct) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(newProduct));
	}
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProduct() {
		return ResponseEntity.ok(productService.getAllProduct());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product singleProduct = productService.getProductById(id);
		if(singleProduct != null) {
			return new ResponseEntity<>(singleProduct, HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
		List<Product> products = productService.getProductByCategory(category);
	   if(products != null) {
		   return new ResponseEntity<>(products, HttpStatus.OK);
	   }
	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id , @RequestBody Product product) {
		try {
			Product updateProduct = productService.updateProduct(id, product);
			return ResponseEntity.ok(updateProduct);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
	}
}
