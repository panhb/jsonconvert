package com.example.jsonconvert.web;

import com.example.jsonconvert.web.object.service.ObjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

	@Autowired
	private ObjectService service;

	@Test
	void contextLoads() {
		System.out.println(service.findById(1L));
	}

}
