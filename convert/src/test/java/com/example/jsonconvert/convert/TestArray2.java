package com.example.jsonconvert.convert;

import com.example.jsonconvert.model.ArrayElement;
import com.example.jsonconvert.JsonConvert;
import com.example.jsonconvert.enums.DataType;
import com.example.jsonconvert.model.RootElement;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
public class TestArray2 {
    public static void main(String[] args) {
        String json = "[\"1\",\"2\"]";

        ArrayElement arrayElement = new ArrayElement();
        arrayElement.setPropType(DataType.STRING);

        RootElement root = new RootElement();
        root.setArrayElement(arrayElement);
        root.setPropType(DataType.ARRAY);

        try {
            System.out.println(JsonConvert.convertToString(json, root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
