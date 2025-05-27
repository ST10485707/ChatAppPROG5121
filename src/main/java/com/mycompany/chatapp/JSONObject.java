/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mila
 */
class JSONObject {
  private final Map<String, String> data;

    public JSONObject() {
        data = new HashMap<>();
    }

    public void put(String key, String value) {
        data.put(key, value);
    }

  @Override
    public String toString() {
        StringBuilder json = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            json.append("  \"").append(entry.getKey()).append("\": \"")
                .append(entry.getValue()).append("\",\n");
        }
        // Remove comma
        if (!data.isEmpty()) {
            json.setLength(json.length() - 2);
            json.append("\n");
        }
        json.append("}");
        return json.toString();
    }

    String toJSONString() {
    StringBuilder json = new StringBuilder("{\n");
    for (Map.Entry<String, String> entry : data.entrySet()) {
        json.append("  \"").append(entry.getKey()).append("\": \"")
            .append(entry.getValue()).append("\",\n");
    }
    if (!data.isEmpty()) {
        json.setLength(json.length() - 2); // Remove comma
        json.append("\n");
    }
    json.append("}");
    return json.toString();
    }
}

