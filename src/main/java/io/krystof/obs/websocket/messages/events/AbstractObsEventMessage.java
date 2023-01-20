package io.krystof.obs.websocket.messages.events;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.events.media_inputs.MediaInputActionTriggered;
import io.krystof.obs.websocket.messages.events.media_inputs.MediaInputPlaybackEnded;
import io.krystof.obs.websocket.messages.events.media_inputs.MediaInputPlaybackStarted;
import io.krystof.obs.websocket.messages.events.scene_items.SceneItemEnableStateChanged;
import io.krystof.obs.websocket.messages.events.scene_items.SceneItemSelectedEvent;
import io.krystof.obs.websocket.messages.events.scenes.CurrentPreviewSceneChanged;
import io.krystof.obs.websocket.messages.events.scenes.CurrentProgramSceneChanged;
import io.krystof.obs.websocket.messages.events.scenes.SceneCreated;
import io.krystof.obs.websocket.messages.events.scenes.SceneNameChanged;
import io.krystof.obs.websocket.messages.events.scenes.SceneRemoved;
import io.krystof.obs.websocket.messages.events.transitions.SceneTransitionEnded;
import io.krystof.obs.websocket.messages.events.transitions.SceneTransitionStarted;
import io.krystof.obs.websocket.messages.events.transitions.SceneTransitionVideoEnded;

@AutoProperty
public abstract class AbstractObsEventMessage extends ObsMessage {

	public AbstractObsEventMessage(EventType eventType, AbstractEventSpecificDataObject eventData) {
		super.setOperationCode(OperationCode.Event);
		this.payload = new Payload();
		payload.eventType = eventType;
		setEventData(eventData);
	}

	@JsonProperty("d")
	protected Payload payload;

	@AutoProperty
	public static class Payload extends AbstractObsDataTransferObject {

		private EventType eventType;
		private Intent eventIntent;
		private AbstractEventSpecificDataObject eventData;

		public EventType getEventType() {
			return eventType;
		}

		public void setEventType(EventType eventType) {
			this.eventType = eventType;
		}

		public Intent getEventIntent() {
			return eventIntent;
		}

		public void setEventIntent(Intent eventIntent) {
			this.eventIntent = eventIntent;
		}

		public Object getEventData() {
			return eventData;
		}

		public void setEventData(AbstractEventSpecificDataObject eventData) {
			this.eventData = eventData;
		}

	}

	public void setEventData(AbstractEventSpecificDataObject eventData) {
		payload.eventData = eventData;
	}

	public enum EventType {
		// Transititions
		SceneTransitionEnded(SceneTransitionEnded.class),
		SceneTransitionVideoEnded(SceneTransitionVideoEnded.class),
		SceneTransitionStarted(SceneTransitionStarted.class),
		// Scenes
		SceneRemoved(SceneRemoved.class),
		SceneCreated(SceneCreated.class),
		SceneNameChanged(SceneNameChanged.class),
		CurrentProgramSceneChanged(CurrentProgramSceneChanged.class),
		CurrentPreviewSceneChanged(CurrentPreviewSceneChanged.class),
		// Scene Items
		SceneItemSelected(SceneItemSelectedEvent.class),
		SceneItemEnableStateChanged(SceneItemEnableStateChanged.class),
		// Media Inputs
		MediaInputPlaybackEnded(MediaInputPlaybackEnded.class),
		MediaInputPlaybackStarted(MediaInputPlaybackStarted.class),
		MediaInputActionTriggered(MediaInputActionTriggered.class);

		private final Class<? extends AbstractObsEventMessage> eventClass;

		EventType(Class<? extends AbstractObsEventMessage> eventClass) {
			this.eventClass = eventClass;
		}

		public Class<? extends AbstractObsEventMessage> getEventClass() {
			return eventClass;
		}
	}

	public enum Intent {
		/**
		 * Set subscriptions to 0 to disable all events
		 */
		None(0),
		/**
		 * Receive events in the `General` category
		 */
		General(1),
		/**
		 * Receive events in the `Config` category
		 */
		Config(1 << 1),
		/**
		 * Receive events in the `Scenes` category
		 */
		Scenes(1 << 2),
		/**
		 * Receive events in the `Inputs` category
		 */
		Inputs(1 << 3),
		/**
		 * Receive events in the `Transitions` category
		 */
		Transitions(1 << 4),
		/**
		 * Receive events in the `Filters` category
		 */
		Filters(1 << 5),
		/**
		 * Receive events in the `Outputs` category
		 */
		Outputs(1 << 6),
		/**
		 * Receive events in the `Scene Items` category
		 */
		SceneItems(1 << 7),
		/**
		 * Receive events in the `MediaInputs` category
		 */
		MediaInputs(1 << 8),
		/**
		 * Receive all event categories (default subscription setting)
		 */
		All(General.value | Config.value | Scenes.value | Inputs.value | Transitions.value
				| Filters.value | Outputs.value | SceneItems.value | MediaInputs.value),
		/**
		 * InputVolumeMeters event (high-volume)
		 */
		InputVolumeMeters(1 << 9),
		/**
		 * InputActiveStateChanged event (high-volume)
		 */
		InputActiveStateChanged(1 << 10),
		/**
		 * InputShowStateChanged event (high-volume)
		 */
		InputShowStateChanged(1 << 11),
		;

		private final int value;

		Intent(int value) {
			this.value = value;
		}

		@JsonValue
		public int getValue() {
			return value;
		}

		private static final Map<Integer, Intent> mMap = Collections.unmodifiableMap(initializeMapping());

		public static Intent getByValue(Integer value) {
			return mMap.get(value);
		}

		private static Map<Integer, Intent> initializeMapping() {
			Map<Integer, Intent> mMap = new HashMap<>();
			for (Intent s : Intent.values()) {
				mMap.put(s.getValue(), s);
			}
			return mMap;
		}

	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

}
