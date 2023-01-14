package tn.ksoftwares.auth.domain.model.utils;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Name implements Serializable{

    @JsonValue
    @Pattern(regexp = "^[A-Z][a-z]{2,19}", message = "Name should start with uppercase, contain alphabet characters and has size between 3 and 20.")
    private String data;

    @Override
    public String toString() {
        return data;
    }

}
