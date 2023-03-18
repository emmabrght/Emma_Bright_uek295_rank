package com.example.test.domain.rank;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rank")
public class RankWeb {

    private final RankService ps;

    public RankWeb(RankService ps) {
        this.ps = ps;
    }

    @GetMapping("/{rankId}")
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Get ranks via id", description = "Fetches singular ranked product by its id and returns a JSON with the status code 200")
    public ResponseEntity<Rank> findById (@PathVariable("rankId") Integer id) {
        return ResponseEntity.ok().body(ps.findById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all ranks", description = "Fetches all ranked products and returns a JSON with the status code 200")
    public List<Rank> findAll () {
        return ps.findAll();
    }

    @DeleteMapping("/{rankId}")
    public String deleteById (@PathVariable("rankId") Integer id) {
        ps.deleteById(id);
        return "Deleted";
    }

    @PutMapping("/{rankId}")
    public ResponseEntity<Rank> updateById (@PathVariable("rankId") Integer id, @RequestBody Rank rank) throws InstanceAlreadyExistsException {
        return ResponseEntity.status(200).body(ps.updateById(rank, id));
    }

    @PostMapping
    public ResponseEntity<Rank> createById (@Valid @RequestBody Rank rank) {
        return ResponseEntity.status(201).body(ps.createById(rank));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> rankNoSuchElementException(NoSuchElementException nsee){
        return ResponseEntity.status(404).body("suck my balls lol");
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class) //for valid numbers
    public ResponseEntity<String> rankMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        return ResponseEntity.status(400).body(manve.getFieldError().getDefaultMessage());
    }

     */

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> rankAlreadyExists(InstanceAlreadyExistsException iaee){
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(iaee.getMessage());
    }
}
