package com.example.jsonconvert.web.array.dao;

import com.example.jsonconvert.web.array.model.po.ArrayPO;
import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.moduleobject.model.po.ModuleObjectPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "array")
public interface ArrayDao extends BaseDao<ArrayPO> {

}
