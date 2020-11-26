package com.example.jsonconvert.web.element;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hongbo.pan
 * @date 2020/11/26
 */
@SpringBootTest
class ElementServiceTest {

    @Autowired
    private ElementService service;

    @Test
    void buildRoot() {
        System.out.println(new Gson().toJson(service.buildRoot(1L)));
    }

}