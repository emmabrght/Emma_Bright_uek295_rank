package com.example.test.domain.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductWeb {

    private final ProductService ps;

    public ProductWeb(ProductService ps) {
        this.ps = ps;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById (@PathVariable("productId") Integer id) {
        return ResponseEntity.ok().body(ps.findById(id));
    }

    @GetMapping("/all")
    public List<Product> findAll () {
        return ps.findAll();
    }

    @DeleteMapping("/{productId}")
    public String deleteById (@PathVariable("productId") Integer id) {
        ps.deleteById(id);
        return "Deleted";
    }

    @PostMapping
    public Product createById (@RequestBody Product product) {
        return ps.createById(product);
    }
}
