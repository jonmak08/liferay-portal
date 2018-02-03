'use strict';

import EventEmitter from 'metal-events/src/EventEmitter';
<<<<<<< HEAD

=======
>>>>>>> compatible
import CompatibilityEventProxy from '../../src/main/resources/META-INF/resources/liferay/CompatibilityEventProxy.es';

describe('CompatibilityEventProxy', () => {
	function createMockedTarget(event, emitFacade) {
<<<<<<< HEAD
		const mockedTarget = {
			fire: function() {},

			_yuievt: {
				events: {},
			},
		};

		if (event) {
			mockedTarget._yuievt.events[event] = {
				emitFacade: emitFacade,
=======
		let mockedTarget = {
			fire: function() {

			},

			_yuievt: {
				events: {}
			}
		};

		if (event) {
			mockedTarget._yuievt.events[event] = {
				emitFacade: emitFacade
>>>>>>> compatible
			};
		}

		return mockedTarget;
	}

<<<<<<< HEAD
	const eventNameToEmit = 'eventToEmit';

	const eventObjectToEmit = {
		key: eventNameToEmit,
	};

	const eventFacadeObjectToEmit = {
		type: eventNameToEmit,
	};

	let host;

	beforeEach(done => {
=======
	let eventNameToEmit = 'eventToEmit';

	let eventObjectToEmit = {
		key: eventNameToEmit
	};

	let eventFacadeObjectToEmit = {
		type: eventNameToEmit
	};

	let	host;

	beforeEach((done) => {
>>>>>>> compatible
		host = new EventEmitter();
		done();
	});

<<<<<<< HEAD
	afterEach(done => {
=======
	afterEach((done) => {
>>>>>>> compatible
		host.dispose();
		done();
	});

<<<<<<< HEAD
	const namespace = 'namespace';

	it('should not emit any event when no targets have been added', done => {
		const component = new CompatibilityEventProxy({
			host: host,
		});

		const spy = jest.spyOn(component, 'emitCompatibleEvents_');

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

		expect(spy).not.toHaveBeenCalled();

		spy.mockReset();
		spy.mockRestore();
=======
	let namespace = 'namespace';

	it('should not emit any event when no targets have been added', (done) => {
		let component = new CompatibilityEventProxy({
			host: host
		});

		let spy = sinon.spy(component, 'emitCompatibleEvents_');

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

		assert.strictEqual(false, spy.called);
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should not crash if target has no method fire', done => {
		const mockedTarget = {};

		const component = new CompatibilityEventProxy({
			host: host,
		});

		const spy = jest.spyOn(component, 'emitCompatibleEvents_');

		component.addTarget(mockedTarget);

		expect(() => {
			host.emit(
				eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);
		}).not.toThrow();

		spy.mockReset();
		spy.mockRestore();
=======
	it('should not crash if target has no method fire', (done) => {
		let mockedTarget = {};

		let component = new CompatibilityEventProxy({
			host: host
		});

		let spy = sinon.spy(component, 'emitCompatibleEvents_');

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

		assert.strictEqual(undefined, spy.exceptions[0]);
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should emit adapted event with event name and event params to given targets when no namespace is specified', done => {
		const mockedTarget = createMockedTarget(eventNameToEmit);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			host: host,
=======
	it('should emit adapted event with event name and event params to given targets when no namespace is specified', (done) => {
		let mockedTarget = createMockedTarget(eventNameToEmit);

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			host: host
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy).toHaveBeenCalledWith(eventNameToEmit, eventObjectToEmit);

		spy.mockReset();
		spy.mockRestore();
=======
		assert.strictEqual(true, spy.calledWith(eventNameToEmit, eventObjectToEmit));
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should emit adapted event with event name and event params to given targets when namespace is specified', done => {
		const namespacedEventNameToEmit = namespace + ':' + eventNameToEmit;

		const mockedTarget = createMockedTarget(namespacedEventNameToEmit);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			host: host,
			namespace: namespace,
=======
	it('should emit adapted event with event name and event params to given targets when namespace is specified', (done) => {
		let namespacedEventNameToEmit = namespace + ':' + eventNameToEmit;

		let mockedTarget = createMockedTarget(namespacedEventNameToEmit);

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			host: host,
			namespace: namespace
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy).toHaveBeenCalledWith(
			namespacedEventNameToEmit,
			eventObjectToEmit
		);
=======
		assert.strictEqual(true, spy.calledWith(namespacedEventNameToEmit, eventObjectToEmit));
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should emit adapted event to given targets when target is not listening', done => {
		const mockedTarget = createMockedTarget();

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			host: host,
=======
	it('should emit adapted event to given targets when target is not listening', (done) => {
		let mockedTarget = createMockedTarget();

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			host: host
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy).toHaveBeenCalledWith(eventNameToEmit, eventObjectToEmit);
=======
		assert.strictEqual(true, spy.calledWith(eventNameToEmit, eventObjectToEmit));
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should emit adapted event to given targets when target is listening', done => {
		const mockedTarget = createMockedTarget(eventNameToEmit);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			host: host,
=======
	it('should emit adapted event to given targets when target is listening', (done) => {
		let mockedTarget = createMockedTarget(eventNameToEmit);

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			host: host
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy).toHaveBeenCalledWith(eventNameToEmit, eventObjectToEmit);
=======
		assert.strictEqual(true, spy.calledWith(eventNameToEmit, eventObjectToEmit));
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should maintain target original state of emitFacade after emiting events', done => {
		const emitFacade = false;

		const mockedTarget = createMockedTarget(eventNameToEmit, emitFacade);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			host: host,
=======
	it('should maintain target original state of emitFacade after emiting events', (done) => {
		let emitFacade = false;

		let mockedTarget = createMockedTarget(eventNameToEmit, emitFacade);

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			host: host
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy).toHaveBeenCalledWith(eventNameToEmit, eventObjectToEmit);
		expect(emitFacade).toEqual(
			mockedTarget._yuievt.events[eventNameToEmit].emitFacade
		);
=======
		assert.strictEqual(true, spy.calledWith(eventNameToEmit, eventObjectToEmit));
		assert.strictEqual(emitFacade, mockedTarget._yuievt.events[eventNameToEmit].emitFacade);
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should maintain target original state of emitFacade after emiting events when component emitFacade is true', done => {
		const emitFacade = false;

		const mockedTarget = createMockedTarget(eventNameToEmit, emitFacade);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			emitFacade: true,
			host: host,
=======
	it('should maintain target original state of emitFacade after emiting events when component emitFacade is true', (done) => {
		let emitFacade = false;

		let mockedTarget = createMockedTarget(eventNameToEmit, emitFacade);

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			emitFacade: true,
			host: host
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy).toHaveBeenCalledWith(eventNameToEmit, eventObjectToEmit);
		expect(emitFacade).toEqual(
			mockedTarget._yuievt.events[eventNameToEmit].emitFacade
		);
=======
		assert.strictEqual(true, spy.calledWith(eventNameToEmit, eventObjectToEmit));
		assert.strictEqual(emitFacade, mockedTarget._yuievt.events[eventNameToEmit].emitFacade);
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should adapt the events according to specified RegExp', done => {
		const eventNameToEmit = 'eventChanged';

		const eventObjectToEmit = {
			key: eventNameToEmit,
		};

		const eventFacadeObjectToEmit = {
			type: eventNameToEmit,
		};

		const adaptedEventNameToEmit = 'eventChange';

		const mockedTarget = createMockedTarget(eventNameToEmit);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			adaptedEvents: {
				match: /(.*)(Changed)$/,
				replace: '$1Change',
			},
			host: host,
=======
	it('should adapt the events according to specified RegExp', (done) => {
		let eventNameToEmit = 'eventChanged';

		let eventObjectToEmit = {
			key: eventNameToEmit
		};

		let eventFacadeObjectToEmit = {
			type: eventNameToEmit
		};

		let adaptedEventNameToEmit = 'eventChange';

		let mockedTarget = createMockedTarget(eventNameToEmit);

		let spy = sinon.spy(mockedTarget, 'fire');

		let component = new CompatibilityEventProxy({
			adaptedEvents: {
				match: /(.*)(Changed)$/,
				replace: '$1Change'
			},
			host: host
>>>>>>> compatible
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

<<<<<<< HEAD
		expect(spy.mock.calls[0][0]).toEqual(adaptedEventNameToEmit);
=======
		assert.strictEqual(true, spy.getCall(0).args[0] === adaptedEventNameToEmit);
>>>>>>> compatible

		done();
	});

<<<<<<< HEAD
	it('should emit events even if the event does not have a key property', done => {
		const eventObjectToEmit = {};

		const mockedTarget = createMockedTarget(eventNameToEmit);

		const spy = jest.spyOn(mockedTarget, 'fire');

		const component = new CompatibilityEventProxy({
			host: host,
		});

		component.addTarget(mockedTarget);

		host.emit(eventNameToEmit, eventObjectToEmit, eventFacadeObjectToEmit);

		expect(spy).toHaveBeenCalledWith(eventNameToEmit, eventObjectToEmit);

		done();
	});
});
=======
});
>>>>>>> compatible
