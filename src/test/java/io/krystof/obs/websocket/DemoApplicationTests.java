package io.krystof.obs.websocket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	TestBean bean;

	@Value("${obs.url")
	String obsUrl;

	@Value("${obs.password}")
	String obsPassword;

	@Test
	void contextLoads() {
		assertEquals("Yup!", bean.getTest(obsUrl, obsPassword));
	}

}
