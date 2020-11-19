package org.example.jsonconvert.enums;

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
}
