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
public class Password implements Serializable{

    private static final String MESSAGE = "Password must contain at least 1 uppercase, 1 lowercase, 1 digit, 1 special character and minimun 8 characters total.";
    
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = MESSAGE)
    @JsonValue
    private String data;

    @Override
    public String toString() {
        return data;
    }
}
