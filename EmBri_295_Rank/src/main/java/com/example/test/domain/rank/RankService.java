package com.example.test.domain.rank;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Log4j2
public class RankService {

    private RankRepository pr;

    @Autowired
    public RankService(RankRepository pr){
        this.pr = pr;
    }

    public Rank findById(Integer id){
        log.info("User searched for rank with id" + id);
        return pr.findById(id).orElseThrow(() -> new NoSuchElementException("This doesn't exist lol"));
    }
    public List<Rank> findAll(){
        log.info("User got all ranks");
        return pr.findAll();
    }
    public void deleteById(Integer id){
        log.info("User deleted rank with id" + id);
        pr.deleteById(id);
    }
    public Rank createById(Rank rank){
        log.info("User created a rank");
        return pr.save(rank);
    }
    public Rank updateById(Rank rank, Integer id) throws InstanceAlreadyExistsException {
        if (!pr.existsById(rank.getId())){
            return createById(rank);
        } else if (!Objects.equals(rank.getId(), id)) {
            throw new InstanceAlreadyExistsException("lol no");
        }
        log.info("User updated rank" + id);
        return pr.save(rank);
    }
}
