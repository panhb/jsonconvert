package com.example.jsonconvert;

import cn.hutool.core.util.StrUtil;
import com.example.jsonconvert.enums.DataType;
import com.example.jsonconvert.exception.JsonConvertException;
import com.example.jsonconvert.model.ArrayElement;
import com.example.jsonconvert.model.BaseElement;
import com.example.jsonconvert.model.Element;
import com.example.jsonconvert.utils.CommonUtil;
import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.example.jsonconvert.model.ObjectElement;
import com.example.jsonconvert.model.RootElement;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
@Slf4j
public class JsonConvert {

    /**
     * 转换成目标json
     * @param json 源json
     * @param rootElement 源json结构和目标转换关系
     * @return
     */
    public static String convertToString(String json, RootElement rootElement) {
        return convertToString(json, rootElement, null);
    }

    /**
     * 转换成目标json
     * @param json 源json
     * @param rootElement 源json结构和目标转换关系
     * @param dataPropName 源json 数据属性名
     * @return
     */
    public static String convertToString(String json, RootElement rootElement, String dataPropName) {
        log.debug("待转换json:{},映射关系:{},数据属性:{}", json, CommonUtil.toJson(rootElement), dataPropName);
        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseString(json);
        } catch (Exception e) {
            throw new JsonConvertException("error json");
        }
        JsonElement element = convertToElement(jsonElement, rootElement, dataPropName);
        return CommonUtil.toJson(element);
    }

    private static JsonElement convertToElement(JsonElement jsonElement, RootElement rootElement, String dataPropName) {
        JsonElement resultElement;
        List<String> dataPropNameList = StrUtil.split(dataPropName,".");
        for (String propName : dataPropNameList) {
            jsonElement = getDataElement(jsonElement, propName);
            rootElement = getRootElement(rootElement, propName);
        }
        switch (rootElement.getPropType()) {
            case OBJECT:
                resultElement = convertObject(jsonElement.getAsJsonObject(), rootElement.getObjectElement());
                break;
            case ARRAY:
                //支持Object转Array
                resultElement = convertArray(toJsonArray(jsonElement), toArrayElement(jsonElement, rootElement).getArrayElement());
                break;
            default:
                throw new JsonConvertException("error root element");
        }
        return resultElement;
    }

    /**
     * 支持object转array
     * @param jsonElement
     * @return
     */
    private static JsonArray toJsonArray(JsonElement jsonElement) {
        JsonArray jsonArray = new JsonArray();
        if (jsonElement.isJsonArray()) {
            jsonArray = jsonElement.getAsJsonArray();
        } else {
            jsonArray.add(jsonElement);
        }
        return jsonArray;
    }

    /**
     * 支持object转array
     * @param jsonElement
     * @param baseElement
     * @return
     */
    private static ArrayElement toArrayElement(JsonElement jsonElement, BaseElement baseElement) {
        ArrayElement arrayElement = new ArrayElement();
        if (baseElement == null) {
            arrayElement.setPropType(DataType.ARRAY);
        } else {
            arrayElement.setPropType(baseElement.getPropType());
            arrayElement.setObjectElement(baseElement.getObjectElement());
            arrayElement.setArrayElement(baseElement.getArrayElement());
        }
        ArrayElement newArrayElement = new ArrayElement();
        if (jsonElement.isJsonObject()) {
            newArrayElement.setObjectElement(arrayElement.getObjectElement());
            newArrayElement.setPropType(DataType.OBJECT);
            arrayElement.setArrayElement(newArrayElement);
        } else if (jsonElement.isJsonPrimitive()) {
            newArrayElement.setPropType(CommonUtil.getDataTypeByJsonPrimitive(jsonElement.getAsJsonPrimitive()));
            arrayElement.setArrayElement(newArrayElement);
        }
        return arrayElement;
    }

    /**
     * 获取数据属性
     * 一般会包装
     * {
     *     code：code,
     *     data: data
     * }
     * @param jsonElement
     * @param dataPropName
     * @return
     */
    private static JsonElement getDataElement(JsonElement jsonElement, String dataPropName) {
        JsonElement dataElement = jsonElement;
        if (!Strings.isNullOrEmpty(dataPropName)) {
            //包装的类型必须是对象
            if (!jsonElement.isJsonObject()) {
                throw new JsonConvertException("json type is not object");
            } else {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                dataElement = jsonObject.get(dataPropName);
            }
        }
        return dataElement;
    }

    /**
     * 获取数据结构
     * @param rootElement
     * @param dataPropName
     * @return
     */
    private static RootElement getRootElement(RootElement rootElement, String dataPropName) {
        RootElement dataRootElement = rootElement;
        if (!Strings.isNullOrEmpty(dataPropName)) {
            Element dataElement = rootElement.getObjectElement().getElements().stream().filter(element ->
                    element.getPropName().equals(dataPropName)).findFirst().get();
            rootElement.setArrayElement(dataElement.getArrayElement());
            rootElement.setObjectElement(dataElement.getObjectElement());
            rootElement.setPropType(dataElement.getPropType());
        }
        return dataRootElement;
    }

    /**
     * 解析数组
     * @param jsonArray
     * @param arrayElement
     * @return
     */
    private static JsonArray convertArray(JsonArray jsonArray, ArrayElement arrayElement) {
        JsonArray array = new JsonArray();
        for (JsonElement jsonElement : jsonArray) {
            switch (arrayElement.getPropType()){
                case OBJECT:
                    JsonObject jsonObject = convertObject(jsonElement.getAsJsonObject(), arrayElement.getObjectElement());
                    array.add(jsonObject);
                    break;
                case ARRAY:
                    //支持Object转Array
                    JsonArray array1 = convertArray(toJsonArray(jsonElement), toArrayElement(jsonElement, arrayElement).getArrayElement());
                    array.add(array1);
                    break;
                default :
                    array.add(jsonElement);
                    break;
            }
        }
        return array;
    }

    /**
     * 解析对象
     * @param jsonObject
     * @param objectElement
     * @return
     */
    private static JsonObject convertObject(JsonObject jsonObject, ObjectElement objectElement) {
        JsonObject jsonObj = new JsonObject();
        objectElement.getElements().forEach(element -> {
            JsonElement jsonElement = getValue(element, jsonObject);
            if (Objects.nonNull(jsonElement) && !jsonElement.isJsonNull()) {
                setValue(element, jsonElement, jsonObj);
            } else {
                setValue(element, JsonNull.INSTANCE, jsonObj);
            }
        });
        return jsonObj;
    }

    /**
     * 设置值
     * @param element
     * @param jsonElement
     * @param jsonObject
     */
    private static void setValue(Element element, JsonElement jsonElement, JsonObject jsonObject) {
        if (Objects.nonNull(jsonElement) && !Strings.isNullOrEmpty(element.getTargetPropName())) {
            //有targetPropName一定需要有对应的targetPropType
            if (Objects.isNull(element.getTargetPropType())) {
                StringBuilder errMsg = new StringBuilder(element.getTargetPropName());
                errMsg.append(" targetPropType is null");
                throw new JsonConvertException(errMsg.toString());
            }
            switch (element.getTargetPropType()){
                case OBJECT:
                case ARRAY:
                    jsonObject.add(element.getTargetPropName(), jsonElement);
                    break;
                case NULL:
                    jsonObject.add(element.getTargetPropName(), JsonNull.INSTANCE);
                    break;
                case STRING:
                    jsonObject.addProperty(element.getTargetPropName(), jsonElement.getAsString());
                    break;
                case BOOLEAN:
                    jsonObject.addProperty(element.getTargetPropName(), Boolean.valueOf(jsonElement.getAsString()));
                    break;
                case INT:
                    jsonObject.addProperty(element.getTargetPropName(), Integer.valueOf(jsonElement.getAsString()));
                    break;
                case LONG:
                    jsonObject.addProperty(element.getTargetPropName(), Long.valueOf(jsonElement.getAsString()));
                    break;
                case FLOAT:
                    jsonObject.addProperty(element.getTargetPropName(), Float.valueOf(jsonElement.getAsString()));
                    break;
                case DOUBLE:
                    jsonObject.addProperty(element.getTargetPropName(), Double.valueOf(jsonElement.getAsString()));
                    break;
                case BIGDECIMAL:
                    jsonObject.addProperty(element.getTargetPropName(), new BigDecimal(jsonElement.getAsString()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 取值
     * @param element
     * @param jsonObject
     * @return
     */
    private static JsonElement getValue(Element element, JsonObject jsonObject) {
        JsonElement dataElement = null;
        if (jsonObject.has(element.getPropName())) {
            dataElement = jsonObject.get(element.getPropName());
            checkNullVerify(element, dataElement);
            if (!Strings.isNullOrEmpty(element.getRegularVerify())) {
                checkNull(element, dataElement);
                checkDataType(element, DataType.STRING);
                checkReg(element, dataElement);
            }
        } else {
            if (!Strings.isNullOrEmpty(element.getDefaultValue())) {
                //不包含源属性且有默认值
                dataElement = new JsonPrimitive(element.getDefaultValue());
            }
        }
        JsonElement jsonElement;
        switch (element.getPropType()) {
            case OBJECT:
                jsonElement = convertObject(dataElement.getAsJsonObject(), element.getObjectElement());
                break;
            case ARRAY:
                jsonElement = convertArray(dataElement.getAsJsonArray(), element.getArrayElement());
                break;
            default:
                jsonElement = dataElement;
                break;
        }
        return jsonElement;
    }

    /**
     * 校验数据类型
     * @param element
     * @param dataType
     */
    private static void checkDataType(Element element, DataType dataType) {
        StringBuilder errMsg = new StringBuilder(element.getPropName());
        if (!dataType.equals(element.getPropType())) {
            errMsg.append(" datatype is not ").append(dataType.getType());
            throw new JsonConvertException(errMsg.toString());
        }
    }

    /**
     * 根据非空校验配置校验属性值非空
     * @param element
     * @param jsonElement
     */
    private static void checkNullVerify(Element element, JsonElement jsonElement) {
        if (Objects.nonNull(element.getNullVerify()) && element.getNullVerify()) {
            checkNull(element, jsonElement);
        }
    }

    /**
     * 校验属性值非空
     * @param element
     * @param jsonElement
     */
    private static void checkNull(Element element, JsonElement jsonElement) {
        StringBuilder errMsg = new StringBuilder(element.getPropName());
        if (Objects.isNull(jsonElement) || jsonElement.isJsonNull()) {
            errMsg.append(" is null");
            throw new JsonConvertException(errMsg.toString());
        }
    }

    /**
     * 正则校验
     * @param element
     * @param jsonElement
     */
    private static void checkReg(Element element, JsonElement jsonElement) {
        StringBuilder errMsg = new StringBuilder(element.getPropName());
        boolean isMatch = Pattern.matches(element.getRegularVerify(), jsonElement.getAsString());
        if (!isMatch) {
            errMsg.append(" value not match, value is ").append(jsonElement.getAsString())
                    .append(" reg is ").append(element.getRegularVerify());
            throw new JsonConvertException(errMsg.toString());
        }
    }
}
