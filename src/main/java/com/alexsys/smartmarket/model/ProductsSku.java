package com.alexsys.smartmarket.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products_skus")
@Setter
@Getter
public class ProductsSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_attribute_id")
    private ProductAttribute sizeAttribute;

    @ManyToOne
    @JoinColumn(name = "color_attribute_id")
    private ProductAttribute colorAttribute;

    private String sku;

    private Double price;

    private Integer quantity;

    // getters & setters
}
