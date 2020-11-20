package com.example.jsonconvert.web.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author hongbo.pan
 * @date 2020/11/19
 */
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {


}
