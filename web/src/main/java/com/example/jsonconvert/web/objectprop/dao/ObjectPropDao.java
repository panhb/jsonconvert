package com.example.jsonconvert.web.objectprop.dao;

import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.objectprop.model.po.ObjectPropPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "objectProp")
public interface ObjectPropDao extends BaseDao<ObjectPropPO> {

}
