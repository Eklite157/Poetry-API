package com.example.demo.api;

import com.example.demo.model.Poem;
import com.example.demo.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/poetry")
@RestController
public class PoemController {

    @Autowired
    //instance variable (reference to actual service)
    private final PoemService poemService;

    //constructor
    public PoemController(PoemService poemService) {
        this.poemService = poemService;
    }

    @PostMapping
    //method to add Poem (reference to poemService layer)
    public void addPoem(@Valid @NonNull @RequestBody Poem poem) {
        poemService.addPoem(poem);
    }

    @GetMapping
    public List<Poem> getAllPoems() {
        return poemService.getAllPoems();
    }

    @GetMapping(path = "/{id}")
    public Poem getPoemById(@PathVariable("id") UUID id) {
        return poemService.getPoemById(id)
                .orElse(null);
    }

    @PutMapping(path = "/{id}")
    public void updatePoem(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Poem poemToUpdate) {
        poemService.updatePoem(id, poemToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePoemById(@PathVariable("id") UUID id) {

        poemService.deletePoem(id);
    }

}
