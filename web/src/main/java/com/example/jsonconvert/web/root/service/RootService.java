package com.example.jsonconvert.web.root.service;

import com.example.jsonconvert.web.root.model.po.RootPO;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
public interface RootService {

    /**
     * findById
     * @param id
     * @return
     */
    RootPO findById(Long id);

}
