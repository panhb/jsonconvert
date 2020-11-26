package com.example.jsonconvert.web.array.model.po;

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
@Entity(name = "t_array")
public class ArrayPO extends BasePO {

    /**
     * 类型
     */
    private String type;

    /**
     * 对象id
     */
    private Long objectId;

    /**
     * 数组id
     */
    private Long arrayId;
}
