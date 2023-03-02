package io.krystof.obs.websocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.obs.websocket.messages.requests.inputs.PressInputPropertiesButtonRequest;
import io.krystof.obs.websocket.messages.requests.media_inputs.TriggerMediaInputActionRequest;
import io.krystof.obs.websocket.messages.responses.inputs.InputSettingsVlcSourcePlayListItem;
import io.krystof.obs.websocket.messages.responses.media_inputs.GetMediaInputStatusResponse;
import io.krystof.obs.websocket.messages.responses.scene_items.GetSceneItemListResponse;
import io.krystof.obs.websocket.messages.responses.scene_items.SceneItem;

/**
 * This is more of a 'service' wrapper for clients. It simply wraps common
 * obsClient commands, with set/update calls utilizing an internal delay. This
 * was made to wrap common looping and lookup logic, as well as the delay piece
 * being necessary so OBS responds properly, I just can't seem to do rapid fire
 * communications and get consistent results.
 *
 */
public class ObsClientService {

	private static final Logger LOG = LoggerFactory.getLogger(ObsClientService.class);

	public ObsClientService(ObsClient obsClient) {
		super();
		this.obsClient = obsClient;
	}

	private ObsClient obsClient;

	public void addEventListener(ObsEventListener obsEventListener) {
		obsClient.addEventListener(obsEventListener);
	}

	public void pauseMediaOrVlcSourceOnlyIfPlaying(String mediaSourceName) {
		if (isMediaOrVlcSourcePlaying(mediaSourceName)) {
			obsClient.mediaInputs.triggerMediaInputAction(mediaSourceName,
					TriggerMediaInputActionRequest.OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSE);
			LOG.info("PAUSE: {}", mediaSourceName);
			internalCommandDelay();
		}
	}

	public void pauseMediaOrVlcSourceRegardlessOfState(String mediaSourceName) {
		obsClient.mediaInputs.triggerMediaInputAction(mediaSourceName,
				TriggerMediaInputActionRequest.OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSE);
		LOG.info("PAUSE: {}", mediaSourceName);
		internalCommandDelay();
	}

	public void playMediaOrVlcSource(String mediaSourceName) {
		obsClient.mediaInputs.triggerMediaInputAction(mediaSourceName,
				TriggerMediaInputActionRequest.OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAY);
		LOG.info("PLAY: {}", mediaSourceName);
		internalCommandDelay();
	}

	public boolean isMediaOrVlcSourcePlaying(String vlcSourceName) {
		return StringUtils.equals(GetMediaInputStatusResponse.OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAYING,
				obsClient.mediaInputs.getMediaInputStatus(vlcSourceName).getSpecificResponseData()
						.getMediaState());
	}

	public boolean isMediaOrVlcSourcePlayingOrPaused(String vlcSourceName) {
		String mediaState = obsClient.mediaInputs.getMediaInputStatus(vlcSourceName).getSpecificResponseData()
				.getMediaState();
		return StringUtils.equalsAny(mediaState, GetMediaInputStatusResponse.OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAYING,
				GetMediaInputStatusResponse.OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSED);
	}

	public void setTextValue(String sourceName, String newText) {
		obsClient.inputs.setInputSettings(sourceName,
				Map.of("text", newText),
				true);
		internalCommandDelay();
	}

	public void setSourceVisibility(String sceneName, String sourceName, boolean visible) {
		setSourceVisibility(sceneName, sourceName, visible, false);
	}

	public void setInputVolumesTo(List<String> inputSourceNames, long inputVolume) {
		for (String inputSource: inputSourceNames) {
			obsClient.inputs.setInputVolume(inputSource, inputVolume);
			internalCommandDelay();
		}
	}

	public void setSourceVisibility(String sceneName, String sourceName, boolean visible, boolean quiet) {
		int sceneSourceId = obsClient.sceneItems.getSceneItemList(sceneName)
				.getSpecificResponseData()
				.getSceneItems().stream().filter(item -> item.getSourceName().equals(sourceName))
				.findFirst().get().getSceneItemId();

		obsClient.sceneItems.setSceneItemEnabled(sceneName, sceneSourceId, visible);
		if (!quiet) {
			LOG.info("{}: {}/{}", visible ? "SHOW" : "HIDE", sceneName, sourceName);
		}
		internalCommandDelay();

	}

	/**
	 * This has a wonderful tendency to start the source playback just when setting
	 * the filename...
	 */
	public void setVlcVideoPlaylist(String vlcSourceName, String... fileNames) {
		Map<String, Object> newInputSettings = new HashMap<>();
		List<InputSettingsVlcSourcePlayListItem> playlistItems = new ArrayList<>();
		for (String fileName : fileNames) {
			InputSettingsVlcSourcePlayListItem playListDto = new InputSettingsVlcSourcePlayListItem();
			playListDto.setValue(fileName);
			playlistItems.add(playListDto);
		}
		newInputSettings.put("playlist", playlistItems);
		obsClient.inputs.setInputSettings(vlcSourceName, newInputSettings, true);
		LOG.info("Set VLC Playlist on source: {}, {}", vlcSourceName, Arrays.toString(fileNames));
		internalCommandDelay();
	}

	@SuppressWarnings("unchecked")
	public Optional<String> getVlcVideoPlaylistFirstFile(String vlcSourceName) {
		List<Map<String, String>> playListItems = new ArrayList<>();
		playListItems = (List<Map<String, String>>) obsClient.inputs.getInputSettings(vlcSourceName)
				.getSpecificResponseData().getInputSettings().get("playlist");
		if (CollectionUtils.isEmpty(playListItems)) {
			return Optional.empty();
		} else {
			return Optional.of(playListItems.get(0).get("value"));
		}
	}

	public boolean isSourceVisible(String sceneName, String sourceName) {
		int sceneSourceId = obsClient.sceneItems.getSceneItemList(sceneName)
				.getSpecificResponseData()
				.getSceneItems().stream().filter(item -> item.getSourceName().equals(sourceName))
				.findFirst().get().getSceneItemId();

		return obsClient.sceneItems.getSceneItemEnabled(sceneName, sceneSourceId).getSpecificResponseData()
				.isSceneItemEnabled();
	}

	public void setSourceInvisibleAndPauseMedia(String sceneName, String sourceName) {
		pauseMediaOrVlcSourceOnlyIfPlaying(sourceName);
		setSourceVisibility(sceneName,
				sourceName, false);
		internalCommandDelay();
	}

	public void setSourceVisibleAndPlayMedia(String sceneName, String sourceName) {
		setSourceVisibility(sceneName,
				sourceName, true);
		playMediaOrVlcSource(sourceName);
	}

	public void setMediaSourceLocalFile(String sourceName, String fileName) {
		Map<String, Object> newInputSettings = new HashMap<>();
		newInputSettings.put("local_file", fileName);
		obsClient.inputs.setInputSettings(sourceName, newInputSettings, true);
		internalCommandDelay();
		LOG.info("Set Media on source: {}, {}", sourceName, fileName);
	}

	public String getMediaSourceLocalFile(String sourceName) {
		return (String) obsClient.inputs.getInputSettings(sourceName).getSpecificResponseData().getInputSettings()
				.get("local_file");
	}

	public void setMediaInputCursorInMsIfPlayingOrPaused(String sourceName, int newCursor) {
		if (isMediaOrVlcSourcePlayingOrPaused(sourceName)) {
			Map<String, Object> newSettings = new HashMap<>();
			newSettings.put("mediaCursor", newCursor);
			obsClient.mediaInputs.setMediaInputCursor(sourceName, newCursor);
			internalCommandDelay();
			LOG.info("SET CURSOR: {} on {}", newCursor, sourceName);
		}
	}

	public GetMediaInputStatusResponse getStatusOfMediaSource(String sourceName) {
		return obsClient.mediaInputs
				.getMediaInputStatus(sourceName);
	}

	public void setCurrentProgramScene(String sceneName) {
		obsClient.scenes.setCurrentProgramScene(sceneName);
		internalCommandDelay();
	}

	public String getCurrentProgramScene() {
		return obsClient.scenes.getCurrentProgramScene().getSpecificResponseData().getCurrentProgramSceneName();
	}

	public Map<String, SceneItem> getAllVisibleSceneItemsBySceneName(String scene) {
		GetSceneItemListResponse sceneItemsForHolder = obsClient.sceneItems.getSceneItemList(
				scene);
		return sceneItemsForHolder.getSpecificResponseData()
				.getSceneItems().stream()
				.filter(item -> item.isSceneItemEnabled())
				.collect(Collectors.toMap(item -> item.getSourceName(), Function.identity()));
	}

	private void internalCommandDelay() {
		try {// 250 works
			Thread.sleep(250);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Interrupted.", e);
		}

	}

	public String getBrowserSourceUrl(String sourceName) {
		return (String) obsClient.inputs.getInputSettings(sourceName).getSpecificResponseData().getInputSettings()
				.get("url");
	}

	public void setBrowserSourceUrl(String sourceName, String newUrl) {
		Map<String, Object> newInputSettings = new HashMap<>();
		newInputSettings.put("url", newUrl);
		obsClient.inputs.setInputSettings(sourceName, newInputSettings, true);
		internalCommandDelay();
		LOG.info("setBrowserSourceUrl: {}, {}", sourceName, newUrl);
	}

	public void refreshBrowser(String browserSourceName) {
		obsClient.inputs.pressInputPropertiesButton(browserSourceName,
				PressInputPropertiesButtonRequest.PROPERTY_NAME_REFRESH_BROWSER_NO_CACHE);
		internalCommandDelay();
	}

}
