package com.example.test.domain.product;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Product {

    private Integer rank;
    private Integer buys;
    private Integer bookId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Product(Integer rank, Integer buys, Integer bookId){
        this.rank = rank;
        this.buys = buys;
        this.bookId = bookId;
    }

    public Product() {

    }
}
