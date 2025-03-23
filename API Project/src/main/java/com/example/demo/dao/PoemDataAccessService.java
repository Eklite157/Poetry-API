package com.example.demo.dao;

import com.example.demo.model.Poem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PoemDataAccessService implements PoemDao{

    private final JdbcTemplate jdbcTemplate;

    //constructor for the JdbcTemplate
    @Autowired
    public PoemDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPoem(UUID id, Poem poem) {

        // SQL query to insert the poem into the database
        String sql = "INSERT INTO poetry (id, name, poet, poemText) VALUES (?, ?, ?, ?)";

        // Execute the query using jdbcTemplate
        return jdbcTemplate.update(
                sql,                    // SQL query
                id,                     // The randomly generated UUID
                poem.getName(),       // The poem's name
                poem.getPoet(),
                poem.getPoemText()
        );

    }


    @Override
    public List<Poem> selectAllPoems() {
        final String sql = "SELECT id, name, poet, poemText FROM poetry";
        //row mapper
        //resultSet is the current row of database result, and turns it into a Java object
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            //convert id from string to UUID
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String poet = resultSet.getString("poet");
            String poemText = resultSet.getString("poemText");
            return new Poem(id, name, poet, poemText);
        });
    }

    @Override
    public Optional<Poem> selectPoemById(UUID id) {
        final String sql = "SELECT id, name, poet, poemText FROM poetry WHERE id = ?";
        Poem poem = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                //row mapper
                (resultSet, i) -> {
                    UUID poemId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String poet = resultSet.getString("poet");
                    String poemText = resultSet.getString("poemText");
                    return new Poem(poemId, name, poet, poemText);
                });

        return Optional.ofNullable(poem);
    }


    @Override
    public int updatePoemById(UUID id, Poem poem) {
        final String sql = "UPDATE poetry SET name = ?, poet = ?, poemText = ? WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                poem.getName(),
                poem.getPoet(),
                poem.getPoemText(),
                id
        );
    }

    @Override
    public int deletePoemById(UUID id) {
        final String sql = "DELETE FROM poetry WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}
