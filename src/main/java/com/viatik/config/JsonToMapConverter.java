package com.viatik.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JsonToMapConverter implements AttributeConverter<JsonNode, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        try {
            if (jsonNode == null) {
                return null;
            }
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException ex) {
            // Manejar error o lanzar una excepción de runtime
            throw new RuntimeException("Error al convertir JSON a String en DB", ex);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty()) {
                // Devolver un nodo vacío o null si no hay datos.
                return objectMapper.createObjectNode();
            }
            return objectMapper.readTree(dbData);
        } catch (JsonProcessingException ex) {
            // Manejar error o lanzar una excepción de runtime
            throw new RuntimeException("Error al convertir String de DB a JSON", ex);
        }
    }
}