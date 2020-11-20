package com.example.jsonconvert.web.propmapping.model.po;

import com.example.jsonconvert.web.base.model.po.BasePO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@ToString(callSuper = true)
@Data
@Entity(name = "t_prop_mapping")
public class PropMappingPO extends BasePO {

    /**
     * 源属性
     */
    private Long srcPropId;

    /**
     * 目标属性
     */
    private Long targetPropId;

    /**
     * 源属性值非空校验
     */
    private Boolean nullVerify;

    /**
     * 源属性值正则校验
     */
    private String regularVerify;

    /**
     * 无源属性时默认值
     */
    private String defaultValue;
}
