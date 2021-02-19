package com.example.jsonconvert.web.element;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author hongbo.pan
 * @date 2021/2/9
 */
public class Test {

    static Gson gson = new Gson();

    @SneakyThrows
    public static void main(String[] args) {
        String name = "多选题";
        String json = IOUtils.toString(Test.class.getResourceAsStream("/convert/"+name+".json"));
//        System.out.println(json);
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonArray jsonArray = jsonElement.getAsJsonObject().get("data").getAsJsonObject().get("questionList").getAsJsonArray();
//        System.out.println(jsonArray.size());
        List<List<String>> rows = Lists.newArrayList();
        List<String> head = Lists.newArrayList("题号","题干","正确选项","错误选项","全部选项");
        rows.add(head);
        for (int i = 0; i < jsonArray.size(); i++) {
            List<String> row = Lists.newArrayList();
            JsonElement jsonElement1 = jsonArray.get(i);
            JsonObject jsonObject = jsonElement1.getAsJsonObject();
            System.out.println("题目"+(i+1)+":"+jsonObject.get("question").getAsString());
            row.add("题目"+(i+1));
            row.add(jsonObject.get("question").getAsString());
            JsonArray option = jsonObject.getAsJsonArray("optionList");
            StringBuilder right = new StringBuilder();
            StringBuilder error = new StringBuilder();
            StringBuilder all = new StringBuilder();
            String answer = jsonObject.get("answer").getAsString();
            for (int j = 0; j < option.size(); j++) {
                JsonObject op = option.get(j).getAsJsonObject();
                all.append(op.get("sort").getAsString() + "." + op.get("option").getAsString()).append("\r\n");
                if (answer.contains(op.get("sort").getAsString())) {
                    right.append(op.get("sort").getAsString() + "." + op.get("option").getAsString()).append("\r\n");
                } else {
                    error.append(op.get("sort").getAsString() + "." + op.get("option").getAsString()).append("\r\n");
                }
                System.out.println(op.get("sort").getAsString()+"."+op.get("option").getAsString());
            }
            row.add(right.toString());
            row.add(error.toString());
            row.add(all.toString());
            rows.add(row);
            System.out.println("答案:"+jsonObject.get("answer").getAsString());
            System.out.println();
        }
        ExcelWriter writer = ExcelUtil.getWriter("d:/"+name+".xlsx");
        writer.write(rows, true);
        writer.close();
    }
}
