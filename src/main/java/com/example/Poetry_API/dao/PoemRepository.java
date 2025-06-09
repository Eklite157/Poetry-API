package com.example.Poetry_API.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Poetry_API.model.Poem;

//this interface allows you to inherit methods from JpaRepository like save(), findById() etc.
@Repository
public interface PoemRepository extends JpaRepository<Poem, Integer> {

    //all CRUD methods from DataAccessService are already included in JpaRepository

    //additional custom methods
    boolean existsByTitleAndContent(String title, String content);
    //generates 'SELECT COUNT(*) > 0 FROM poem WHERE title = ? AND content = ?'

}