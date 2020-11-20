package com.example.jsonconvert.web.propmapping.service.impl;

import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.propmapping.dao.PropMappingDao;
import com.example.jsonconvert.web.propmapping.service.PropMappingService;
import org.springframework.stereotype.Service;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class PropMappingServiceImpl extends BaseService implements PropMappingService {

    private final PropMappingDao dao;

    public PropMappingServiceImpl(PropMappingDao dao) {
        this.dao = dao;
    }
}
