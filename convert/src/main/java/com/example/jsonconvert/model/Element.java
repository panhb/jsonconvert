package com.example.jsonconvert.model;

import com.example.jsonconvert.enums.DataType;
import lombok.Data;
import lombok.ToString;

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
     * 先决条件
     * 1.源数据非空
     * 2.类型为DataType.STRING
     */
    private String regularVerify;

    /**
     * 在源属性不存在时，默认值配置生效
     * 当默认值配置存在时，非空校验和正则校验无效
     */
    private String defaultValue;


}
