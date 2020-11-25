package com.example.jsonconvert.web.prop.service;

import com.example.jsonconvert.web.prop.model.po.PropPO;

import java.util.List;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
public interface PropService {

    /**
     * findByIdIn
     * @param ids
     * @return
     */
    List<PropPO> findByIdIn(List<Long> ids);

}
