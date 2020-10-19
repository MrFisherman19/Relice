package com.mrfisherman.relice.Entity.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color {

    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
    private String hexColor = "#222222";
}
