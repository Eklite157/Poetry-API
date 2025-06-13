package com.example.Poetry_API.dao;

import com.example.Poetry_API.model.Poem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//DAO talks directly to database
@Repository
public class DataAccessService {

    private final PoemRepository poemRepository;

    //constructor
    public DataAccessService(PoemRepository poemRepository) {
        this.poemRepository = poemRepository;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }


    //methods
    public int insertPoem (String title, String poet, String dynasty, String content) {

        // Reject null or blank title/content
        if ( isBlank(title) || isBlank(poet)  || (isBlank(content))) {
            System.out.println("Title author and content are required.");
            return 0; // Do nothing
        }

        // Reject duplicates
        if (poemRepository.existsByTitleAndContent(title, content)) {
            System.out.println("Poem already exists!");
            return 0;
        }

        Poem newPoem = new Poem(title, poet, dynasty, content);
        Poem savedPoem = poemRepository.save(newPoem);
        return savedPoem.getId(); // return generated DB ID
    }


    public List<Poem> selectAllPoems() {
        return poemRepository.findAll();
    }


    public Optional<Poem> selectPoemById (int id) {
        return poemRepository.findById(id);
    }


    public int deletePoemById (int id) {
        Optional<Poem> result = selectPoemById(id);
        if(result.isEmpty()) {
            return 0;
        }

        poemRepository.deleteById(id);
        return 1;
    }


    public Poem updatePoemById (int id, Poem poem) {
        //find the poem in database
        Optional<Poem> poemToUpdate = poemRepository.findById(id);

        if (poemToUpdate.isEmpty()) {
            return null;
        }

        //obtain Java object
        Poem selectedPoem = poemToUpdate.get();

        //now update the poem
        selectedPoem.setTitle(poem.getTitle());
        selectedPoem.setPoet(poem.getPoet());
        selectedPoem.setDynasty(poem.getDynasty());
        selectedPoem.setContent(poem.getContent());

        poemRepository.save(selectedPoem);
        return selectedPoem;
    }
}