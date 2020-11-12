package org.example.jsonconvert.exception;

/**
 * @author hongbo.pan
 * @date 2020/11/11
 */
public class JsonParseException extends RuntimeException{

    public JsonParseException(String msg) {
        super(msg);
    }

    public JsonParseException(String msg, Throwable e) {
        super(msg, e);
    }

    public JsonParseException(Throwable e) {
        super(e);
    }
}
