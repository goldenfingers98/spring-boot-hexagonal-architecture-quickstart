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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Username implements Serializable {

    private static final String MESSAGE = "Username must contain at least one uppercase, one lowecase, one number and one special character.";

    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = MESSAGE)
    @JsonValue
    private String data;

    @Override
    public String toString() {
        return data;
    }
}
