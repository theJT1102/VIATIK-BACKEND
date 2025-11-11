package com.viatik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class JsonService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode listToJsonNode(List<?> list) {
        return objectMapper.valueToTree(list);
    }

    public <T> T jsonNodeToObject(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error al mapear JsonNode a objeto: " + e.getMessage());
        }
    }

    public ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }
    
    // Método para agregar un objeto a un ArrayNode (útil para Transacciones de Fondos)
    public ArrayNode addToArray(JsonNode currentArray, Object objectToAdd) {
        ArrayNode array = (currentArray != null && currentArray.isArray()) 
                         ? (ArrayNode) currentArray 
                         : createArrayNode();
        array.add(objectMapper.valueToTree(objectToAdd));
        return array;
    }
}