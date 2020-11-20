package com.example.jsonconvert.web.moduleobject.dao;

import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.moduleobject.model.po.ModuleObjectPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "moduleObject")
public interface ModuleObjectDao extends BaseDao<ModuleObjectPO> {

}
