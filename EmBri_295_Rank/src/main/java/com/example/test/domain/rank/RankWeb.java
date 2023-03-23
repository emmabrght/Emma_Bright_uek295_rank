package com.example.test.domain.rank;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@Log4j2
@RequestMapping("/rank")
public class RankWeb {

    private final RankService ps;

    public RankWeb(RankService ps) {
        this.ps = ps;
    }

    @GetMapping("/{rankId}")
    @PreAuthorize("hasAuthority('DEFAULT')")
    @Operation(summary = "Get ranks via id", description = "Fetches singular ranked product by its id and returns a JSON with the status code 200")
    public ResponseEntity<Rank> findById (@PathVariable("rankId") Integer id) {
        return ResponseEntity.ok().body(ps.findById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('DEFAULT')")
    @Operation(summary = "Get all ranks", description = "Fetches all ranked products and returns a JSON with the status code 200")
    public List<Rank> findAll () {
        return ps.findAll();
    }

    @DeleteMapping("/{rankId}")
    @PreAuthorize("hasAuthority('DEFAULT')")
    @Operation(summary = "Delete a rank via id", description = "Deletes singular rank via its id and returns a string saying \"Deleted\"")
    public String deleteById (@PathVariable("rankId") Integer id) {
        ps.deleteById(id);
        return "Deleted";
    }

    @PutMapping("/{rankId}")
    @PreAuthorize("hasAuthority('DEFAULT')")
    @Operation(summary = "Updates rank via id", description = "Updates data of selected rank via its id, returns JSON with the updated data")
    public ResponseEntity<Rank> updateById (@PathVariable("rankId") Integer id, @RequestBody Rank rank) throws InstanceAlreadyExistsException {
        return ResponseEntity.status(200).body(ps.updateById(rank, id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('DEFAULT')")
    @Operation(summary = "Creates new rank", description = "Will create a new rank and give it an auto generated id")
    public ResponseEntity<Rank> createById (@Valid @RequestBody Rank rank) {
        return ResponseEntity.status(201).body(ps.createById(rank));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> rankNoSuchElementException(NoSuchElementException nsee){
        log.error("This Rank doesn't exist.");
        return ResponseEntity.status(404).body(nsee.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //for valid numbers
    public ResponseEntity<String> rankMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        log.error("Invalid Input, please try again.");
        return ResponseEntity.status(400).body(Objects.requireNonNull(manve.getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> rankAlreadyExists(InstanceAlreadyExistsException iaee){
        log.error("This already exists, try again.");
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(iaee.getMessage());
    }
}
