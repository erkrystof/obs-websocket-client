package io.krystof.obs.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.krystof.obs.websocket.TestBean;

@Configuration
public class GeneralConfig {

	@Bean
	TestBean textBean() {
		return new TestBean();
	}

}
