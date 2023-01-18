package com.example.springtestingdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CatDTO {

    private String name;

    private String breed;

    private int age;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int rescueId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String rescueName;

}
