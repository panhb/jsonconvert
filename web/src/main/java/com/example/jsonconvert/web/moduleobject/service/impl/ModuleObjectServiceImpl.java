package com.example.jsonconvert.web.moduleobject.service.impl;

import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.moduleobject.dao.ModuleObjectDao;
import com.example.jsonconvert.web.moduleobject.service.ModuleObjectService;
import org.springframework.stereotype.Service;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@Service
public class ModuleObjectServiceImpl extends BaseService implements ModuleObjectService {

    private final ModuleObjectDao dao;

    public ModuleObjectServiceImpl(ModuleObjectDao dao) {
        this.dao = dao;
    }
}
