package com.example.jsonconvert.model;

import com.example.jsonconvert.enums.DataType;
import lombok.Data;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
@Data
public class BaseElement {

    /**
     * 属性类型
     */
    private DataType propType;

    private ArrayElement arrayElement;

    private ObjectElement objectElement;

}
