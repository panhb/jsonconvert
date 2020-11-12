package org.example.jsonconvert.model;

import lombok.Data;
import org.example.jsonconvert.enums.DataType;

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
