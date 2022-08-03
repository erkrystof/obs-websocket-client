package io.krystof.obs.websocket;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	public static Properties props = new Properties();

	TestBean bean = new TestBean();

	@BeforeEach
	public void before() throws IOException {
		props.load(
				Thread
						.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("application.properties"));

	}



	@Test
	void contextLoads() {
		bean.getTest(props.getProperty("obs.url"), props.getProperty("obs.password"));
	}

}
