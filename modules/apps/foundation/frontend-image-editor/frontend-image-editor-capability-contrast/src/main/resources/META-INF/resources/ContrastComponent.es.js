<<<<<<< HEAD
import Component from 'metal-component';
import Slider from 'metal-slider';
import Soy from 'metal-soy';
import debounce from 'metal-debounce';
import { CancellablePromise } from 'metal-promise';
import { core } from 'metal';
=======
import Component from 'metal-component/src/Component';
import Soy from 'metal-soy/src/Soy';

import core from 'metal/src/core';
import debounce from 'metal-debounce/src/debounce';
import { CancellablePromise } from 'metal-promise/src/promise/Promise';
import Slider from 'metal-slider/src/Slider';
>>>>>>> compatible

import componentTemplates from './ContrastComponent.soy';
import controlsTemplates from './ContrastControls.soy';

/**
 * Contrast Component
<<<<<<< HEAD
 * @review
=======
>>>>>>> compatible
 */
class ContrastComponent extends Component {
	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	attached() {
		// Debounced version of requestImageEditorPreview
		this.requestImageEditorPreview_ = debounce(this.requestImageEditorPreview, 50);

		this.cache_ = {};
	}

	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	detached() {
		this.cache_ = {};
	}

	/**
	 * Applies a contrast filter to the image.
<<<<<<< HEAD
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
	 * @review
=======
	 *
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
>>>>>>> compatible
	 */
	preview(imageData) {
		return this.process(imageData);
	}

	/**
	 * Applies a contrast filter to the image.
<<<<<<< HEAD
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
	 * @review
=======
	 *
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
>>>>>>> compatible
	 */
	process(imageData) {
		let contrastValue = this.components.slider.value;
		let promise = this.cache_[contrastValue];

		if (!promise) {
			promise = this.spawnWorker_({
				contrastValue: contrastValue,
				imageData: imageData
			});

			this.cache_[contrastValue] = promise;
		}

		return promise;
	}

	/**
	 * Notifies the editor that this component wants to generate
	 * a different preview version of the current image. It debounces
	 * the calls
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	requestPreview() {
		this.requestImageEditorPreview_();
	}

	/**
	 * Spawns the a webworker to do the image processing in a different thread.
<<<<<<< HEAD
	 * @param  {Object} message An object with the image and contrast value.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
	 * @review
=======
	 *
	 * @param  {Object} message An object with the image and contrast value.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
>>>>>>> compatible
	 */
	spawnWorker_(message) {
		return new CancellablePromise((resolve, reject) => {
			let workerURI = this.modulePath + '/ContrastWorker.js';
			let processWorker = new Worker(workerURI);

			processWorker.onmessage = (event) => resolve(event.data);
			processWorker.postMessage(message);
		});
	}
}

/**
 * State definition.
<<<<<<< HEAD
 * @review
 * @static
 * @type {!Object}
=======
 * @type {!Object}
 * @static
>>>>>>> compatible
 */
ContrastComponent.STATE = {
	/**
	 * Path of this module
	 * @type {String}
	 */
	modulePath: {
		validator: core.isString
	},

	/**
	 * Injected method to notify the editor this component
	 * wants to generate a preview version of the image.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Function}
	 */
	requestImageEditorPreview: {
		validator: core.isFunction
	}
};

<<<<<<< HEAD
=======
// Register component
>>>>>>> compatible
Soy.register(ContrastComponent, componentTemplates);

export default ContrastComponent;