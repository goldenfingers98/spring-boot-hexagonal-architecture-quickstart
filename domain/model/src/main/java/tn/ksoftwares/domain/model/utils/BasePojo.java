package tn.ksoftwares.domain.model.utils;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.ksoftwares.domain.model.exception.FailedSerializationException;

public abstract class BasePojo implements Serializable {
    
    private static final ObjectMapper objectMapper = new ObjectMapper() ;

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new FailedSerializationException(e.getMessage());
        }
    }
}
