package com.example.jsonconvert.web.objectprop.model.po;

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
@Entity(name = "t_object_prop")
public class ObjectPropPO extends BasePO {

    /**
     * 对象id
     */
    private Long objectId;

    /**
     * 根对象id
     */
    private Long rootObjectId;

    /**
     * 属性id
     */
    private Long propId;
}
