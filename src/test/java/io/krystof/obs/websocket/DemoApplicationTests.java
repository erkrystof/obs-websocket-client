package io.krystof.obs.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	public static Properties props = new Properties();

	public static ObsClient obsClient;

	@BeforeEach
	public void before() throws IOException, URISyntaxException {

		props.load(
				Thread
						.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("application.properties"));

		obsClient = new ObsClient(new URI(props.getProperty("obs.url")), Optional.of(props.getProperty("obs.password")),
				Optional.of(AbstractObsEventMessage.Intent.None.getValue()));

	}

	@Test
	public void test() {

	}

}
