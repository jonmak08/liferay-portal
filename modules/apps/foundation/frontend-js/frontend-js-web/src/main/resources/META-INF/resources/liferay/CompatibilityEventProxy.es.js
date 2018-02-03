import State from 'metal-state';
<<<<<<< HEAD
import {core} from 'metal';
=======
import { core } from 'metal';
>>>>>>> compatible

/**
 * CompatibilityEventProxy
 *
 * This class adds compatibility for YUI events, re-emitting events
 * according to YUI naming and adding the capability of adding targets
 * to bubble events to them.
<<<<<<< HEAD
 * @review
=======
 *
>>>>>>> compatible
 */
class CompatibilityEventProxy extends State {
	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
	 */
	constructor(config, element) {
		super(config, element);

		this.eventTargets_ = [];
		this.host = config.host;
		this.namespace = config.namespace;
=======
	 */
	constructor(opt_config, opt_element) {
		super(opt_config, opt_element);

		this.eventTargets_ = [];
		this.host = opt_config.host;
		this.namespace = opt_config.namespace;
>>>>>>> compatible

		this.startCompatibility_();
	}

	/**
	 * Registers another EventTarget as a bubble target.
<<<<<<< HEAD
	 * @param  {!Object} target YUI component where events will be emited to
	 * @private
	 * @review
=======
	 *
	 * @param  {!Object} target YUI component where events will be emited to
	 * @private
>>>>>>> compatible
	 */
	addTarget(target) {
		this.eventTargets_.push(target);
	}

	/**
	 * Check if the event is an attribute modification event and addapt
	 * the eventName.
<<<<<<< HEAD
	 * @param  {!String} eventName
	 * @private
	 * @return {String} Adapted event name
	 * @review
	 */
	checkAttributeEvent_(eventName) {
		return eventName.replace(
			this.adaptedEvents.match,
			this.adaptedEvents.replace
		);
=======
	 *
	 * @param  {!String} eventName
	 * @private
	 */
	checkAttributeEvent_(eventName) {
		return eventName.replace(this.adaptedEvents.match, this.adaptedEvents.replace);
>>>>>>> compatible
	}

	/**
	 * Emit the event adapted to yui
<<<<<<< HEAD
	 * @param  {!String} eventName
	 * @param  {!Event} event
	 * @private
	 * @review
	 */
	emitCompatibleEvents_(eventName, event) {
		this.eventTargets_.forEach(target => {
			if (target.fire) {
				let prefixedEventName = this.namespace
					? this.namespace + ':' + eventName
					: eventName;
=======
	 *
	 * @param  {!String} eventName
	 * @param  {!Event} event
	 * @private
	 */
	emitCompatibleEvents_(eventName, event) {
		this.eventTargets_.forEach((target) => {
			if (target.fire) {
				let prefixedEventName = this.namespace ? this.namespace + ':' + eventName : eventName;
>>>>>>> compatible
				let yuiEvent = target._yuievt.events[prefixedEventName];

				if (core.isObject(event)) {
					try {
						event.target = this.host;
<<<<<<< HEAD
					} catch (err) {}
=======
					} catch(err) {}
>>>>>>> compatible
				}

				let emitFacadeReference;

				if (!this.emitFacade && yuiEvent) {
					emitFacadeReference = yuiEvent.emitFacade;
					yuiEvent.emitFacade = false;
				}

				target.fire(prefixedEventName, event);

				if (!this.emitFacade && yuiEvent) {
					yuiEvent.emitFacade = emitFacadeReference;
				}
			}
		});
	}

	/**
	 * Configuration to emit yui-based events to maintain
	 * backwards compatibility.
<<<<<<< HEAD
	 * @private
	 * @review
	 */
	startCompatibility_() {
		this.host.on('*', (event, eventFacade) => {
			if (!eventFacade) {
				eventFacade = event;
			}

			let compatibleEvent = this.checkAttributeEvent_(eventFacade.type);

			if (compatibleEvent !== eventFacade.type) {
				eventFacade.type = compatibleEvent;
				this.host.emit(compatibleEvent, event, eventFacade);
			} else if (this.eventTargets_.length > 0) {
				this.emitCompatibleEvents_(compatibleEvent, event);
			}
		});
=======
	 *
	 * @private
	 */
	startCompatibility_() {
		this.host.on(
			'*',
			(event, eventFacade) => {
				if (!eventFacade) {
					eventFacade = event;
				}

				let compatibleEvent = this.checkAttributeEvent_(eventFacade.type);

				if (compatibleEvent !== eventFacade.type) {
					eventFacade.type = compatibleEvent;
					this.host.emit(compatibleEvent, event, eventFacade);
				}
				else if (this.eventTargets_.length > 0 && event.key) {
					this.emitCompatibleEvents_(compatibleEvent, event);
				}
			}
		);
>>>>>>> compatible
	}
}

/**
 * State definition.
 * @ignore
<<<<<<< HEAD
 * @review
 * @static
 * @type {!Object}
=======
 * @type {!Object}
 * @static
>>>>>>> compatible
 */
CompatibilityEventProxy.STATE = {
	/**
	 * Regex for replace event names to YUI adapted names.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Object}
	 */
	adaptedEvents: {
		value: {
			match: /(.*)(Changed)$/,
<<<<<<< HEAD
			replace: '$1Change',
		},
=======
			replace: '$1Change'
		}
>>>>>>> compatible
	},

	/**
	 * Indicates if event facade should be emited to the target
<<<<<<< HEAD
	 * @review
	 * @type {String}
	 */
	emitFacade: {
		value: false,
	},
};

export default CompatibilityEventProxy;
=======
	 * @type {String}
	 */
	emitFacade: {
		value: false
	}
};

export default CompatibilityEventProxy;
>>>>>>> compatible
