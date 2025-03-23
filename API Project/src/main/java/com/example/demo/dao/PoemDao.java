package com.example.demo.dao;

import com.example.demo.model.Poem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PoemDao {

    //HTTP ADD
    //abstract method to insert a poem with a given ID to the database
    int insertPoem(UUID id, Poem poem);

    //default method to add a poem with a randomly generated ID to the database
    default int addPoem(Poem poem) {
        UUID id = UUID.randomUUID();
        return insertPoem(id, poem);
    }

    //HTTP GET
    //method to retrieve all poems; returns a list of poems
    List<Poem> selectAllPoems();

    //method to select a poem
    Optional<Poem> selectPoemById(UUID id);

    //HTTP UPDATE
    //method to update poem at an id with a new poem
    int updatePoemById(UUID id, Poem poem);

    //HTTP DELETE
    //method to delete a poem
    int deletePoemById(UUID id);
}
