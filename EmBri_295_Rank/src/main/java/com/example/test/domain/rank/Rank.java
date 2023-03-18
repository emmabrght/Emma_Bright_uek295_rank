package com.example.test.domain.rank;

import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Entity
public class Rank {
    @NotNull
    @Column(unique = true)
    private Integer ranking;
    @PositiveOrZero
    private Integer buys;
    @PositiveOrZero
    private Integer bookId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Rank(Integer ranking, Integer buys, Integer bookId) {
        this.ranking = ranking;
        this.buys = buys;
        this.bookId = bookId;
    }

    public Rank() {

    }
}
