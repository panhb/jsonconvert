package com.example.jsonconvert.web.array.service.impl;

import com.example.jsonconvert.web.array.dao.ArrayDao;
import com.example.jsonconvert.web.array.model.po.ArrayPO;
import com.example.jsonconvert.web.array.service.ArrayService;
import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.moduleobject.dao.ModuleObjectDao;
import com.example.jsonconvert.web.moduleobject.service.ModuleObjectService;
import org.springframework.stereotype.Service;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class ArrayServiceImpl extends BaseService implements ArrayService {

    private final ArrayDao dao;

    public ArrayServiceImpl(ArrayDao dao) {
        this.dao = dao;
    }

    @Override
    public ArrayPO findById(Long id) {
        return dao.findById(id).get();
    }
}
