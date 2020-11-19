package org.example.jsonconvert.convert;

import org.example.jsonconvert.JsonConvert;
import org.example.jsonconvert.enums.DataType;
import org.example.jsonconvert.model.ArrayElement;
import org.example.jsonconvert.model.RootElement;

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
