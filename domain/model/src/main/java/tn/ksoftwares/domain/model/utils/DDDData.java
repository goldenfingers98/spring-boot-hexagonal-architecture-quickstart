package tn.ksoftwares.domain.model.utils;

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
public abstract class DDDData {

    protected static final String REGEX = "";

    @Pattern(regexp = REGEX)
    @JsonValue
    protected String data;

    @Override
    public String toString() {
        return data;
    }
}
