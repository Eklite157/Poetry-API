package com.example.Poetry_API.api;

import com.example.Poetry_API.model.Poem;
import com.example.Poetry_API.service.PoemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//handles HTTP requests and talks directly with user, calls service
//we reject bad input right in the controller layer before even hitting the inner layers with @Valid
@RequestMapping("/api/v1/poem")
@RestController
public class PoemController {

    //not instantiated yet
    private final PoemService poemService;

    //constructor
    @Autowired//so you don't have to manually create a new poemService class (use bean instead)
    public PoemController(PoemService poemService) {
        this.poemService = poemService;
    }

    //methods
    @PostMapping //maps HTTP 'POST' to following method
    public ResponseEntity<?> addPoem(@Valid @RequestBody Poem poem) {
        Poem addedPoem = poemService.addPoem(poem); //returns the ID of inserted poem

        //Spring handles the mandatory non-null field validations

        //handle duplicate
        if (addedPoem == null) {
            return ResponseEntity.status(409).body("Attempting to add duplicate poem!");
        }

        return ResponseEntity.status(201).body(addedPoem); //poem created
    }


    @GetMapping
    public List<Poem> getAllPoems () {
        return poemService.getAllPoems();
    }


    @GetMapping(path = "/{id}")
    public Poem getPoemByid (@PathVariable("id") int id) {
        Optional<Poem> optionalPoem = poemService.getPoemById(id);

        if (optionalPoem.isPresent()) {
            return optionalPoem.get(); //returns value inside Optional if not empty
        } else {
            return null;
        }
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deletePoemById (@PathVariable("id") int id) {

        int result = poemService.deletePoemById(id); //performs the task

        if (result == 1) {
            return ResponseEntity.noContent().build(); // 204 = success
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<?> updatePoemById (@PathVariable("id") int id, @Valid @RequestBody Poem updatedPoem) {

        Poem savedPoem = poemService.updatePoemById(id, updatedPoem); //performs the task

        //Spring handles the mandatory non-null field validations

        //handle duplicate
        if (poemService.updatePoemById(id, updatedPoem) == null) {
            return ResponseEntity.status(409).body("Updated poem is a duplicate!");
        }

        return ResponseEntity.ok(savedPoem); //send JSON of updated poem back
    }

}
