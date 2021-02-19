package com.example.jsonconvert.web.element;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author hongbo.pan
 * @date 2021/2/9
 */
public class PaixuTest {

    static Gson gson = new Gson();

    @SneakyThrows
    public static void main(String[] args) {
        String json = IOUtils.toString(PaixuTest.class.getResourceAsStream("/convert/多选题.json"));
//        System.out.println(json);
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonArray jsonArray = jsonElement.getAsJsonObject().get("data").getAsJsonObject().get("questionList").getAsJsonArray();
//        System.out.println(jsonArray.size());
        Map<Integer, List<JsonObject>> map = Maps.newHashMap();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            int length = jsonObject.get("answer").getAsString().length();
            if (map.containsKey(length)) {
                map.getOrDefault(length, Lists.newArrayList()).add(jsonObject);
            } else {
                map.put(length, Lists.newArrayList(jsonObject));
            }
        }

        int k = 1;
        for (Integer i : map.keySet()) {
            System.out.println("=============================="+i+"==============================");
            for (JsonObject jsonObject : map.get(i)) {
                System.out.println("题目"+(k++)+":"+jsonObject.get("question").getAsString());
                JsonArray option = jsonObject.getAsJsonArray("optionList");
                for (int j = 0; j < option.size(); j++) {
                    JsonObject op = option.get(j).getAsJsonObject();
                    System.out.println(op.get("sort").getAsString()+"."+op.get("option").getAsString());
                }
                System.out.println("答案:"+jsonObject.get("answer").getAsString());
                System.out.println();
            }
            System.out.println("=============================="+i+"==============================");
        }
    }
}
