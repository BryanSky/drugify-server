package ch.drugify.server.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Converter {

    public static String convertToJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

    public static Object convertFromJson(String json, Class c){
        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;
        try {
            obj = mapper.readValue(json, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static long getLong(String key, String body) {
        return 0;
    }
}
