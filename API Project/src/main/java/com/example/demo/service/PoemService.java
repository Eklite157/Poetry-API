package com.example.demo.service;

import com.example.demo.dao.PoemDao;
import com.example.demo.model.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//class will serve as service layer between API and poemDao
public class PoemService {

    //instance variable that CANNOT be reassigned
    private final PoemDao poemDao;

    //constructor
    @Autowired
    public PoemService(@Qualifier("postgres") PoemDao poemDao) {
        this.poemDao = poemDao;
    }

    //method delegates task of adding poem to the DAO class (references DAO)
    public int addPoem(Poem Poem) {
        return poemDao.addPoem(Poem);
    }

    public List<Poem> getAllPoems() {
        return poemDao.selectAllPoems();
    }

    public Optional<Poem> getPoemById(UUID id) {
        return poemDao.selectPoemById(id);
    }

    public int updatePoem(UUID id, Poem newPoem) {
        return poemDao.updatePoemById(id, newPoem);
    }

    public int deletePoem(UUID id) {
        return poemDao.deletePoemById(id);
    }
}