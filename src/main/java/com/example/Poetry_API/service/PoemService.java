package com.example.Poetry_API.service;

import com.example.Poetry_API.dao.DataAccessService;
import com.example.Poetry_API.model.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//handles application logic, calls DAO
@Service
public class PoemService {

    private final DataAccessService dataAccessService ;

    //constructor
    @Autowired //so you don't have to manually create a new dataAccessService class instance (use bean instead)
    public PoemService(DataAccessService dataAccessService) {
        this.dataAccessService = dataAccessService;
    }

    //method
    public Poem addPoem (Poem poem) {

        //insertPoem already returns an int
        return dataAccessService.insertPoem(
                poem.getTitle(),
                poem.getPoet(),
                poem.getPoetEn(),
                poem.getDynasty(),
                poem.getContent()
        );
    }

    public List<Poem> getAllPoems (){
        return dataAccessService.selectAllPoems();
    }

    public Optional<Poem> getPoemById(int id) {
        return dataAccessService.selectPoemById(id);
    }

    public int deletePoemById (int id) {
        return dataAccessService.deletePoemById(id);
    }

    public Poem updatePoemById(int id, Poem poem) {
        return dataAccessService.updatePoemById(id, poem);
    }

    public boolean isDBAlive() {
        return dataAccessService.isDBAlive();
    }
}
