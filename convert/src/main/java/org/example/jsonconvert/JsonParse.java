package org.example.jsonconvert;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.example.jsonconvert.enums.DataType;
import org.example.jsonconvert.exception.JsonParseException;
import org.example.jsonconvert.model.ArrayElement;
import org.example.jsonconvert.model.Element;
import org.example.jsonconvert.model.ObjectElement;
import org.example.jsonconvert.model.RootElement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author hongbo.pan
 * @date 2020/11/11
 */
public class JsonParse {

    /**
     * 解析json串结构
     * @param json
     * @return
     */
    public static RootElement parse(String json) {
        return parse(json, null);
    }

    /**
     * 解析json对象结构
     * @param jsonElement
     * @return
     */
    public static RootElement parse(JsonElement jsonElement) {
        RootElement rootElement = new RootElement();
        rootElement.setPropType(getDataType(jsonElement));
        if (jsonElement.isJsonObject()) {
            rootElement.setObjectElement(parseObject(jsonElement));
        } else if (jsonElement.isJsonArray()) {
            rootElement.setArrayElement(parseArray(jsonElement));
        } else {
            throw new JsonParseException("error type");
        }
        return rootElement;
    }

    /**
     * 解析json串结构
     * 从data属性开始
     * 例如
     * {
     *     code:0,
     *     data:{
     *     }
     * }
     * @param json
     * @return
     */
    public static RootElement parse(String json, String dataPropName) {
        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseString(json);
        } catch (Exception e) {
            throw new JsonParseException("error json");
        }
        RootElement rootElement = parse(jsonElement);
        if (!Strings.isNullOrEmpty(dataPropName)) {
            if (DataType.OBJECT.equals(rootElement.getPropType())) {
                ObjectElement objectElement = rootElement.getObjectElement();
                Optional<Element> elementOptional = objectElement.getElements().stream().filter(
                        e -> dataPropName.equals(e.getPropName())).findFirst();
                if (elementOptional.isPresent()) {
                    rootElement.setObjectElement(elementOptional.get().getObjectElement());
                } else {
                    throw new JsonParseException("object is not exist");
                }
            } else {
                throw new JsonParseException("datatype is not object");
            }
        }
        return rootElement;
    }

    /**
     * 解析对象
     * @param jsonElement
     * @return
     */
    private static ObjectElement parseObject(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return null;
        }
        ObjectElement objectElement = new ObjectElement();
        List<Element> elementList = Lists.newArrayList();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            Element element = new Element();
            element.setPropName(entry.getKey());
            element.setTargetPropName(entry.getKey());
            JsonElement value = entry.getValue();
            DataType dataType = getDataType(value);
            element.setPropType(dataType);
            element.setTargetPropType(dataType);
            if (value.isJsonArray()) {
                element.setArrayElement(parseArray(value));
            } else if (value.isJsonObject()){
                element.setObjectElement(parseObject(value));
            }
            elementList.add(element);
        }
        objectElement.setElements(elementList);
        return objectElement;
    }

    /**
     * 解析数组
     * @param jsonElement
     * @return
     */
    private static ArrayElement parseArray(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return null;
        }
        ArrayElement arrayElement = new ArrayElement();
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        JsonElement element = jsonArray.get(0);
        arrayElement.setPropType(getDataType(element));
        if (element.isJsonArray()) {
            arrayElement.setArrayElement(parseArray(element));
        } else if (element.isJsonObject()) {
            arrayElement.setObjectElement(parseObject(element));
        }
        return arrayElement;
    }

    /**
     * 获取类型
     * @param jsonElement
     * @return
     */
    private static DataType getDataType(JsonElement jsonElement) {
        if (jsonElement.isJsonArray()) {
            return DataType.ARRAY;
        } else if (jsonElement.isJsonObject()) {
            return DataType.OBJECT;
        } else {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (jsonPrimitive.isNumber()) {
                return DataType.BIGDECIMAL;
            } else if (jsonPrimitive.isBoolean()) {
                return DataType.BOOLEAN;
            } else {
                return DataType.STRING;
            }
        }
    }

}
