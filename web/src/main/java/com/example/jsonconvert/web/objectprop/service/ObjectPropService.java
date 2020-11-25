package com.example.jsonconvert.web.objectprop.service;

import com.example.jsonconvert.web.objectprop.model.po.ObjectPropPO;

import java.util.List;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
public interface ObjectPropService {

    /**
     * findByObjectId
     * @param objectId
     * @return
     */
    List<ObjectPropPO> findByObjectId(Long objectId);

}
