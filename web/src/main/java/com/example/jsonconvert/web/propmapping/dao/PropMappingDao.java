package com.example.jsonconvert.web.propmapping.dao;

import com.example.jsonconvert.web.base.dao.BaseDao;
import com.example.jsonconvert.web.propmapping.model.po.PropMappingPO;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@RepositoryRestResource(path = "propMapping")
public interface PropMappingDao extends BaseDao<PropMappingPO> {

    /**
     * findByObjectIdAndSrcPropId
     * @param objectId
     * @param srcPropId
     * @return
     */
    PropMappingPO findByObjectIdAndSrcPropId(Long objectId, Long srcPropId);

}
