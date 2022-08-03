package io.krystof.obs.websocket.messages.events;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;

@AutoProperty
public abstract class AbstractObsEventMessage extends ObsMessage {

	public AbstractObsEventMessage() {
		super.setOperationCode(OperationCode.Event);
	}

	@AutoProperty
	public static class Data extends AbstractObsDataTransferObject {

		private EventType eventType;
		private Intent eventIntent;

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

	}

	public enum EventType {
		// General
		SceneItemSelected(SceneItemSelectedEvent.class);

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

		public int getValue() {
			return value;
		}
	}

}
