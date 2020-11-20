package com.example.jsonconvert.web.object.service.impl;

import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.object.dao.ObjectDao;
import com.example.jsonconvert.web.object.model.po.ObjectPO;
import com.example.jsonconvert.web.object.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class ObjectServiceImpl extends BaseService implements ObjectService {

    private final ObjectDao dao;

    public ObjectServiceImpl(ObjectDao dao) {
        this.dao = dao;
    }

    @Override
    public ObjectPO findById(Long id) {
        Optional<ObjectPO> optional = dao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
