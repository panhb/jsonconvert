package com.example.jsonconvert.web.root.service.impl;

import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.root.dao.RootDao;
import com.example.jsonconvert.web.root.model.po.RootPO;
import com.example.jsonconvert.web.root.service.RootService;
import org.springframework.stereotype.Service;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class RootServiceImpl extends BaseService implements RootService {

    private final RootDao dao;

    public RootServiceImpl(RootDao dao) {
        this.dao = dao;
    }

    @Override
    public RootPO findById(Long id) {
        return dao.findById(id).get();
    }
}
