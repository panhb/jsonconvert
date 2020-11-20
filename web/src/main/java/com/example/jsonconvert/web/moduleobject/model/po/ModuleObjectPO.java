package com.example.jsonconvert.web.moduleobject.model.po;

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
@Entity(name = "t_module_object")
public class ModuleObjectPO extends BasePO {

    /**
     * 对象id
     */
    private Long objectId;

    /**
     * 模块id
     */
    private Long moduleId;

    /**
     * 系统code
     */
    private String sysCode;
}
