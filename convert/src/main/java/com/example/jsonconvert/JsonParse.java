package com.example.jsonconvert;

import cn.hutool.core.util.StrUtil;
import com.example.jsonconvert.enums.DataType;
import com.example.jsonconvert.exception.JsonParseException;
import com.example.jsonconvert.model.ArrayElement;
import com.example.jsonconvert.model.Element;
import com.example.jsonconvert.utils.CommonUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.example.jsonconvert.model.ObjectElement;
import com.example.jsonconvert.model.RootElement;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author hongbo.pan
 * @date 2020/11/11
 */
@Slf4j
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
        log.debug("待解析json:{},数据属性:{}", json, dataPropName);
        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseString(json);
        } catch (Exception e) {
            throw new JsonParseException("error json");
        }
        RootElement rootElement = parse(jsonElement);
        if (!Strings.isNullOrEmpty(dataPropName)) {
            if (DataType.OBJECT.equals(rootElement.getPropType())) {
                List<String> dataPropNameList = StrUtil.split(dataPropName,".");
                for (String propName : dataPropNameList) {
                    ObjectElement objectElement = rootElement.getObjectElement();
                    Optional<Element> elementOptional = objectElement.getElements().stream().filter(
                            e -> propName.equals(e.getPropName())).findFirst();
                    if (elementOptional.isPresent()) {
                        rootElement.setArrayElement(elementOptional.get().getArrayElement());
                        rootElement.setObjectElement(elementOptional.get().getObjectElement());
                        rootElement.setPropType(elementOptional.get().getPropType());
                    } else {
                        throw new JsonParseException("object is not exist");
                    }
                }
            } else {
                throw new JsonParseException("datatype is not object");
            }
        }
        return rootElement;
    }

    /**
     * 解析json对象结构
     * @param jsonElement
     * @return
     */
    private static RootElement parse(JsonElement jsonElement) {
        RootElement rootElement = new RootElement();
        rootElement.setPropType(CommonUtil.getDataTypeByJsonElement(jsonElement));
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
            DataType dataType = CommonUtil.getDataTypeByJsonElement(value);
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
        arrayElement.setPropType(CommonUtil.getDataTypeByJsonElement(element));
        if (element.isJsonArray()) {
            arrayElement.setArrayElement(parseArray(element));
        } else if (element.isJsonObject()) {
            arrayElement.setObjectElement(parseObject(element));
        }
        return arrayElement;
    }

}
