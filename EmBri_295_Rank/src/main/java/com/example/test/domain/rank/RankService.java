package com.example.test.domain.rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Objects;

@Service
public class RankService {

    private RankRepository pr;

    @Autowired
    public RankService(RankRepository pr){
        this.pr = pr;
    }

    public Rank findById(Integer id){
        return pr.findById(id).orElseThrow(/*RankNotFoundException::new*/);
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
    public Rank updateById(Rank rank, Integer id) throws InstanceAlreadyExistsException {
        if (!pr.existsById(rank.getId())){
            return createById(rank);
        } else if (!Objects.equals(rank.getId(), id)) {
            throw new InstanceAlreadyExistsException();
        }
        return pr.save(rank);
    }
}
