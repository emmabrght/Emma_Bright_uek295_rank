package com.example.test.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository pr;

    @Autowired
    public ProductService(ProductRepository pr){
        this.pr = pr;
    }

    public Product findById(Integer id){
        return pr.findById(id).orElseThrow();
    }
    public List<Product> findAll(){
        return pr.findAll();
    }
    public void deleteById(Integer id){
        pr.deleteById(id);
    }
    public Product createById(Product product){
        return pr.save(product);
    }
}
