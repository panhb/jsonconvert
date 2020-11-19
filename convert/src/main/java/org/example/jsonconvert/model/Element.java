package org.example.jsonconvert.model;

import lombok.Data;
import lombok.ToString;
import org.example.jsonconvert.enums.DataType;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
@Data
@ToString(callSuper = true)
public class Element extends BaseElement {

    /**
     * 源属性名
     */
    private String propName;

    /**
     * 目标属性名
     */
    private String targetPropName;

    /**
     * 目标属性类型
     */
    private DataType targetPropType;

    /**
     * 校验源属性值是否可为空(优先级高于正则)
     */
    private Boolean nullVerify;

    /**
     * 正则校验源属性值
     */
    private String regularVerify;

    /**
     * 在源属性不存在且有目标属性时，使用默认值
     * 默认值优先级高于非空校验和正则校验
     */
    private String defaultValue;


}
