package org.example.jsonconvert.exception;

/**
 * @author hongbo.pan
 * @date 2020/11/11
 */
public class JsonConvertException extends RuntimeException{

    public JsonConvertException(String msg) {
        super(msg);
    }

    public JsonConvertException(String msg, Throwable e) {
        super(msg, e);
    }

    public JsonConvertException(Throwable e) {
        super(e);
    }
}
