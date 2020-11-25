package com.example.jsonconvert.web.propmapping.service;

import com.example.jsonconvert.web.propmapping.model.po.PropMappingPO;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
public interface PropMappingService {

    /**
     * findByObjectIdAndSrcPropId
     * @param objectId
     * @param srcPropId
     * @return
     */
    PropMappingPO findByObjectIdAndSrcPropId(Long objectId, Long srcPropId);

}
