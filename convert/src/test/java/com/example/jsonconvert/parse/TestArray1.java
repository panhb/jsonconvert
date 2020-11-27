package com.example.jsonconvert.parse;

import com.example.jsonconvert.JsonConvert;
import com.example.jsonconvert.JsonParse;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
public class TestArray1 {
    public static void main(String[] args) {
        String json = "[[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}],[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]]";
        try {
            System.out.println(JsonParse.parse(json));
            System.out.println(JsonConvert.convertToString(json, JsonParse.parse(json)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
