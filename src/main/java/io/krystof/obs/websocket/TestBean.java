package io.krystof.obs.websocket;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.obs.websocket.messages.requests.ui.SetStudioModeEnabledRequest;

public class TestBean {

	private static final Logger LOG = LoggerFactory.getLogger(TestBean.class);


	public String getTest(String obsUrl, String obsPassword) {

		try {
			ObsClient obsClient = new ObsClient(new URI(obsUrl), Optional.of(obsPassword),
					Optional.empty());

			obsClient.simpleSendRequest(new SetStudioModeEnabledRequest());

			Thread.sleep(50000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return "Yup!";
	}

}
