package com.example.jsonconvert.web.prop.dao;

import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.prop.model.po.PropPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "prop")
public interface PropDao extends BaseDao<PropPO> {

    /**
     * findByIdIn
     * @param ids
     * @return
     */
    List<PropPO> findByIdIn(List<Long> ids);

}
