package com.spotify.rewrapped.utils;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJSON(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Map<String, Object> parsedJson = new Gson().fromJson(jsonObject, Map.class);
        return parsedJson;
    }

    public static JsonObject toJSON(Map<String, Object> map) {
        JsonObject jsonObject = new Gson().toJsonTree(map).getAsJsonObject();
        return jsonObject;
    }
}
