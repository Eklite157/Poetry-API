package com.example.Poetry_API.dao;

import com.example.Poetry_API.model.Poem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DAO talks directly to database
@Repository
public class DataAccessServiceOld {
    private static List<Poem> DB = new ArrayList<>();

    //check to ensure no duplicate poems. DAO layer because actually interacts with database
    public boolean poemExists (String title, String content) {
        for (Poem p : DB) {
            if (p.getTitle().equals(title) && p.getContent().equals(content)) {
                return true;
            }
        }
        return false;
    }

    public int insertPoem (String title, String author, String dynasty, String content) {

        // Reject null or blank title/content
        if (title == null || author == null || content == null) {
            System.out.println("Title author and content are required.");
            return 0; // Do nothing
        }


        //NEED TO FIX HERE
        DB.add(new Poem (title, author, dynasty, content));
        return 1;

    }


    public List<Poem> selectAllPoems() {
        return DB;
    }

    public Optional<Poem> selectPoemById (int id) {
        for (Poem p: DB) {
            if (p.getId() == id) {
                //if found
                return Optional.of(p);
            }
        }
        //if not found
        return Optional.empty();
    }

    public int deletePoemById (int id) {
        Optional<Poem> result = selectPoemById(id);
        if(result.isEmpty()) {
            return 0;
        }
        DB.remove(result.get());
        return 1;
    }


    public int updatePoemById (int id, Poem poem) {

        for (int i = 0; i < DB.size(); ++i) {
            Poem p = DB.get(i);
            if (p.getId() == id) {
                poem.setId(id); //keep old id
                DB.set(i, poem); //update poem at that location
                return 1;
            }
        };
        return 0; //not found
    }
}


