package com.example.jsonconvert.web.object.dao;

import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.object.model.po.ObjectPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "object")
public interface ObjectDao extends BaseDao<ObjectPO> {

}
