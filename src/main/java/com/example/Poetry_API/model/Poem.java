package com.example.Poetry_API.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity //mark as model; maps to table and stores each row as Java Poem object
@Table(name = "all_poems") //match existing table name

//outlines the fields needed (in accordance with columns of database table)
public class Poem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA will handle id generation based on DB
    private int id;

    @NotBlank
    private String title;
    @NotBlank
    private String poet;
    @NotBlank
    private String poet_en;
    private String dynasty;
    @NotBlank
    private String content;
    //nullable
    private String translation;

    //constructor allowing other classes to create and use Poem objects
    public Poem (String title, String poet, String poet_en, String dynasty, String content){
        this.title = title;
        this.poet = poet;
        this.poet_en = poet_en;
        this.dynasty = dynasty;
        this.content = content;
        // translation defaults to null
    }

    public Poem() {
        // No-args constructor required by JPA
    }


    //getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoet() {
        return poet;
    }

    public void setPoet(String poet) {
        this.poet = poet;
    }

    //Jackson automatically infers property names by removing get prefix
    //need to tell Jackson what json key maps to this field, since different (poetEn vs poet_en)
    @JsonProperty("poet_en")
    public String getPoetEn() {
        return poet_en;
    }

    public void setPoetEn(String poet_en) {
        this.poet_en = poet_en;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTranslation() { return translation; }

    public void setTranslation(String translation) { this.translation = translation; }

}
