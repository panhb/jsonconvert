package com.example.jsonconvert.web.element;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import com.example.jsonconvert.JsonConvert;
import com.example.jsonconvert.model.RootElement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author hongbo.pan
 * @date 2020/11/26
 */
@SpringBootTest
class ElementServiceTest {

    @Autowired
    private ElementService service;

    @SneakyThrows
    @Test
    void buildRoot() throws IOException {
        String json = "{\"id\":\"1\",\"name2\":\"999\"}";
//        System.out.println(new Gson().toJson(service.buildRoot(1L)));
//        System.out.println(JsonConvert.convertToString(json, service.buildRoot(1L)));
        String root = IOUtils.toString(ElementServiceTest.class.getResourceAsStream("/convert/test.json"));
        System.out.println(root);
        System.out.println(JsonConvert.convertToString(json, new Gson().fromJson(root, RootElement.class)));

    }

}