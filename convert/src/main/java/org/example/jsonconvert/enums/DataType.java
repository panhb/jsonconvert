package org.example.jsonconvert.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author hongbo.pan
 * @date 2020/11/11
 */
public enum DataType {

    /**
     * array
     */
    ARRAY("array"),

    /**
     * object
     */
    OBJECT("object"),

    /**
     * string
     */
    STRING("string"),

    /**
     * int
     */
    INT("int"),

    /**
     * long
     */
    LONG("long"),

    /**
     * bigDecimal
     */
    BIGDECIMAL("bigDecimal"),

    /**
     * float
     */
    FLOAT("float"),

    /**
     * double
     */
    DOUBLE("double"),

    /**
     * boolean
     */
    BOOLEAN("boolean");

    private String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static Map<String, DataType> enumMap = Maps.newHashMap();

    static {
        for(DataType as : DataType.values()) {
            enumMap.put(as.getType(), as);
        }
    }

    public static DataType getByType(String type) {
        return  enumMap.get(type);
    }
}
