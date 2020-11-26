package com.example.jsonconvert.web.array.service;

import com.example.jsonconvert.web.array.model.po.ArrayPO;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
public interface ArrayService {

    /**
     * findById
     * @param id
     * @return
     */
    ArrayPO findById(Long id);

}
