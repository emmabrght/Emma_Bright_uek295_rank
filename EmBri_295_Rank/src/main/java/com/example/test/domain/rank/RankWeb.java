package com.example.test.domain.rank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankWeb {

    private final RankService ps;

    public RankWeb(RankService ps) {
        this.ps = ps;
    }

    @GetMapping("/{rankId}")
    public ResponseEntity<Rank> findById (@PathVariable("rankId") Integer id) {
        return ResponseEntity.ok().body(ps.findById(id));
    }

    @GetMapping("/all")
    public List<Rank> findAll () {
        return ps.findAll();
    }

    @DeleteMapping("/{rankId}")
    public String deleteById (@PathVariable("rankId") Integer id) {
        ps.deleteById(id);
        return "Deleted";
    }

    @PostMapping
    public Rank createById (@RequestBody Rank rank) {
        return ps.createById(rank);
    }
}
