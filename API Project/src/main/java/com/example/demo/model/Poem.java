package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Poem {

    //instance variables initialized
    private final UUID id;

    @NotBlank
    private final String name;

    private final String poet;

    private final String poemText;

    //parameterized constructor, initializes object with instance variables when object created
    public Poem (@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("poet") String poet,
                   @JsonProperty ("poemText") String poemText) {
        this.id = id;
        this.name = name;
        this.poet = poet;
        this.poemText = poemText;
    }

    //getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getPoet() {
        return poet;
    }
    public String getPoemText() {return poemText;
    }
}
