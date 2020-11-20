package com.example.jsonconvert.web.objectprop.service.impl;

import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.objectprop.dao.ObjectPropDao;
import com.example.jsonconvert.web.objectprop.service.ObjectPropService;
import org.springframework.stereotype.Service;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class ObjectPropServiceImpl extends BaseService implements ObjectPropService {

    private final ObjectPropDao dao;

    public ObjectPropServiceImpl(ObjectPropDao dao) {
        this.dao = dao;
    }
}
