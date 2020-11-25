package com.example.jsonconvert.web.root.model.po;

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
@Entity(name = "t_root")
public class RootPO extends BasePO {

    /**
     * 属性类型
     */
    private String type;

    /**
     * 属性对象id
     */
    private Long objectId;

    /**
     * 属性数组id
     */
    private Long arrayId;
}
