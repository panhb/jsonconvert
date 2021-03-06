package com.example.jsonconvert.convert;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.example.jsonconvert.enums.DataType;
import com.example.jsonconvert.model.Element;
import com.example.jsonconvert.model.ObjectElement;
import com.example.jsonconvert.model.RootElement;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
public class TestObject1 {
    public static void main(String[] args) {
//        String json = "[[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}],[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]]";
        String json = "{\"id\":\"1\"}";
//        String json = "[\"1\",\"2\"]";
//        String json = "[]";
//        String json = "{}";

        Element element = new Element();
        element.setPropName("id");
        element.setPropType(DataType.STRING);
        element.setTargetPropName("pkid");
        element.setTargetPropType(DataType.LONG);

        Element element2 = new Element();
        element2.setPropName("name");
        element2.setPropType(DataType.STRING);
        element2.setTargetPropName("haha");
        element2.setTargetPropType(DataType.STRING);
//        element2.setNullVerify(true);
//        element2.setDefaultValue("123");

        ObjectElement oe = new ObjectElement();
        oe.setElements(Lists.newArrayList(element, element2));

        RootElement root = new RootElement();
        root.setObjectElement(oe);
        root.setPropType(DataType.OBJECT);

        try {
            System.out.println(new Gson().toJson(root));
//            System.out.println(JsonConvert.convertToString(json, root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
