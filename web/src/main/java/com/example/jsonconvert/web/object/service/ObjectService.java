package com.example.jsonconvert.web.object.service;

import com.example.jsonconvert.web.object.model.po.ObjectPO;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
public interface ObjectService {

    /**
     * findById
     * @param id
     * @return
     */
    ObjectPO findById(Long id);
}
