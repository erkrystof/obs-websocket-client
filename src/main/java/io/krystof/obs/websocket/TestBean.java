package io.krystof.obs.websocket;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.scenes.GetSceneItemListResponse;

public class TestBean {

	private static final Logger LOG = LoggerFactory.getLogger(TestBean.class);

	public String getTest(String obsUrl, String obsPassword) {

		try {
			ObsClient obsClient = new ObsClient(new URI(obsUrl), Optional.of(obsPassword),
					Optional.ofNullable(AbstractObsEventMessage.Intent.All.getValue()));

			obsClient.ui.callSetStudioModeEnabled(true);

			obsClient.ui.callSetStudioModeEnabled(false);

			GetSceneItemListResponse getSceneItemListResponse = obsClient.input.callGetSceneItemList("SCENE-AFK");

			LOG.info("What is this? :{}", getSceneItemListResponse);

			getSceneItemListResponse = obsClient.input.callGetSceneItemList("SCENE-RetroShows");

			LOG.info("What is this? :{}", getSceneItemListResponse);

			GetInputSettingsResponse response = obsClient.input
					.callGetInputSettings("http://toothless.lan:8088/test2/html/");

			LOG.info("What is this: {}", response);

			response = obsClient.input.callGetInputSettings("VLC-Show-TheAcademy");

			LOG.info("What is this: {}", response);

			response = obsClient.input.callGetInputSettings("Vasdfsafd");

			LOG.info("What is this: {}", response);

			LOG.info("{}", obsClient.input.callGetInputKindList(false));

			Thread.sleep(50000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return "Yup!";
	}

}
