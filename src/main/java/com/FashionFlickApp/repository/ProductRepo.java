package com.FashionFlickApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FashionFlickApp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
 List<Product> findByCategory(String category);
}
