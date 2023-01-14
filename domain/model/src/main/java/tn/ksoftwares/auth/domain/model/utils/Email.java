package tn.ksoftwares.auth.domain.model.utils;

import java.io.Serializable;

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
public class Email implements Serializable {

    @javax.validation.constraints.Email
    @JsonValue
    private String data;

    @Override
    public String toString() {
        return data;
    }

}
