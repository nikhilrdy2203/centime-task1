package com.assignment.serviceone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheJson {
    @JsonProperty("Name")
    @NotNull(
            message = "Name field cannot be null."
    )
    @NotBlank(
            message = "Name field cannot be empty."
    )
    @Size(
            min = 3,
            message = "Name Should have Minimum of 3 letters"
    )
    private String Name;
    @JsonProperty("Surname")
    @NotNull(
            message = "Surname field cannot be null."
    )
    @NotBlank(
            message = "Surname field cannot be empty."
    )
    @Size(
            min = 3,
            message = "Surname Should have Minimum of 3 letters"
    )
    private String Surname;
}
