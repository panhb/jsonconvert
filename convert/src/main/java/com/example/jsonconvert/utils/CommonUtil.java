package com.example.jsonconvert.utils;

import com.example.jsonconvert.enums.DataType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.Objects;

/**
 * @author hongbo.pan
 * @date 2021/4/25
 */
public class CommonUtil {

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        return gson.toJson(object);
    }

    /**
     * 获取类型
     * @param jsonElement
     * @return
     */
    public static DataType getDataTypeByJsonElement(JsonElement jsonElement) {
        if (Objects.isNull(jsonElement) || jsonElement.isJsonNull()) {
            return DataType.NULL;
        } else if (jsonElement.isJsonArray()) {
            return DataType.ARRAY;
        } else if (jsonElement.isJsonObject()) {
            return DataType.OBJECT;
        } else {
            return getDataTypeByJsonPrimitive(jsonElement.getAsJsonPrimitive());
        }
    }

    /**
     *
     * @param jsonPrimitive
     * @return
     */
    public static DataType getDataTypeByJsonPrimitive(JsonPrimitive jsonPrimitive) {
        if (Objects.isNull(jsonPrimitive) || jsonPrimitive.isJsonNull()) {
            return DataType.NULL;
        } else if (jsonPrimitive.isNumber()) {
            return DataType.BIGDECIMAL;
        } else if (jsonPrimitive.isBoolean()) {
            return DataType.BOOLEAN;
        } else {
            return DataType.STRING;
        }
    }
}
