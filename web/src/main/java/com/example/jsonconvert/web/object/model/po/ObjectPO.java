package com.example.jsonconvert.web.object.model.po;

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
@Entity(name = "t_object")
public class ObjectPO extends BasePO {

    /**
     * 对象code
     */
    private String code;
}
