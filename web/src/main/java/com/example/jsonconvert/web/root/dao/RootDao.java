package com.example.jsonconvert.web.root.dao;

import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.root.model.po.RootPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "root")
public interface RootDao extends BaseDao<RootPO> {

}
