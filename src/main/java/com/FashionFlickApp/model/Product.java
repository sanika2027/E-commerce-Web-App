package com.FashionFlickApp.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String description;
    private Double price;
    private Double originalPrice;
    private Integer discountPercent;
    private String fabric;
    private String sleeveLength;
    private String pattern;
    private String category;

    @ElementCollection // To store a list of sizes
    private List<String> sizes;

    @Column(length = 10000) // For storing large URLs
    private String imageUrl;

    @ElementCollection
    @Column(length = 1000)  // Adjust the length to suit the maximum URL length
    private List<String> imageGallery;



    public Product(String name, String description, Double price, Double originalPrice, Integer discountPercent,
                   String fabric, String sleeveLength, String pattern, String category, List<String> sizes, 
                   String imageUrl, List<String> imageGallery) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.originalPrice = originalPrice;
        this.discountPercent = discountPercent;
        this.fabric = fabric;
        this.sleeveLength = sleeveLength;
        this.pattern = pattern;
        this.category = category;
        this.sizes = sizes;
        this.imageUrl = imageUrl;
        this.imageGallery = imageGallery;
    }
}
