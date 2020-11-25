package com.example.jsonconvert.web.prop.service.impl;

import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.prop.dao.PropDao;
import com.example.jsonconvert.web.prop.model.po.PropPO;
import com.example.jsonconvert.web.prop.service.PropService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class PropServiceImpl extends BaseService implements PropService {

    private final PropDao dao;

    public PropServiceImpl(PropDao dao) {
        this.dao = dao;
    }

    @Override
    public List<PropPO> findByIdIn(List<Long> ids) {
        return dao.findByIdIn(ids);
    }
}
