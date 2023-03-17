package com.example.test.domain.rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    private RankRepository pr;

    @Autowired
    public RankService(RankRepository pr){
        this.pr = pr;
    }

    public Rank findById(Integer id){
        return pr.findById(id).orElseThrow();
    }
    public List<Rank> findAll(){
        return pr.findAll();
    }
    public void deleteById(Integer id){
        pr.deleteById(id);
    }
    public Rank createById(Rank rank){
        return pr.save(rank);
    }
}
