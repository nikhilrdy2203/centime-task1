package com.assignment.servicethere.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheJson {
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Surname")
    private String Surname;
}
