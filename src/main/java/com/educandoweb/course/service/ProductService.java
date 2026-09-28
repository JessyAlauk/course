package com.educandoweb.course.service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository ProductRepository;

    public ProductService(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    public List<Product> findAll(){
        return ProductRepository.findAll();
    }

    public Product findById (Long id){
        var obj = ProductRepository.findById(id);
        return obj.get();
    }
}
