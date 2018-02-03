<<<<<<< HEAD
import Component from 'metal-component';
import Soy from 'metal-soy';
import { CancellablePromise } from 'metal-promise';
import { async, core } from 'metal';
=======
import Component from 'metal-component/src/Component';
import Soy from 'metal-soy/src/Soy';

import async from 'metal/src/async/async';
import core from 'metal/src/core';
import { CancellablePromise } from 'metal-promise/src/promise/Promise';
>>>>>>> compatible

import componentTemplates from './EffectsComponent.soy';
import controlsTemplates from './EffectsControls.soy';

/**
 * Effects Component
<<<<<<< HEAD
 * @review
=======
>>>>>>> compatible
 */
class EffectsComponent extends Component {
	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	attached() {
		this.cache_ = {};

		async.nextTick(() => {
			this.getImageEditorImageData()
				.then((imageData) => CancellablePromise.resolve(this.generateThumbnailImageData_(imageData)))
				.then((previewImageData) => this.generateThumbnails_(previewImageData))
				.then(() => this.prefetchEffects_());
		});
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
<<<<<<< HEAD
	 * Returns whether the carousel can be scrolled towards the right
	 * @private
	 * @return {boolean} True if the carousel can be scrolled, false otherwise.
	 * @review
	 */
	canScrollForward_() {
		const carousel = this.refs.carousel;
		const continer = this.refs.carouselContainer;
		const offset = Math.abs(parseInt(carousel.style.marginLeft || 0, 10));
		const viewportWidth = parseInt(continer.offsetWidth, 10);
		const maxContentWidth = parseInt(carousel.offsetWidth, 10);

		return offset + viewportWidth < maxContentWidth;
	}

	/**
	 * Generates a specific thumbnail for a given effect.
=======
	 * Generates a specific thumbnail for a given effect.
	 *
>>>>>>> compatible
	 * @param  {String} effect The effect to generate the thumbnail for.
	 * @param  {ImageData} imageData The image data to apply the effect to.
	 * @return {CancellablePromise} A promise to be fullfilled when the
	 * thumbnail has been generated.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	generateThumbnail_(effect, imageData) {
		let promise = this.spawnWorker_({
			effect: effect,
			imageData: imageData
		});

		promise.then((imageData) => {
<<<<<<< HEAD
			let canvas = this.element.querySelector('#' + this.ref + effect + ' canvas');
=======
			let canvas = this.element.querySelector('#' + this.key + effect + ' canvas');
>>>>>>> compatible
			canvas.getContext('2d').putImageData(imageData, 0, 0);
		});

		return promise;
	}

	/**
	 * Generates the complete set of thumbnails for the component effects.
<<<<<<< HEAD
	 * @param  {ImageData} imageData The thumbnail image data (small version)
	 * @return {CancellablePromise} A promise to be fullfilled when all thumbnails
	 * have been generated.
	 * @review
=======
	 *
	 * @param  {ImageData} imageData The thumbnail image data (small version)
	 * @return {CancellablePromise} A promise to be fullfilled when all thumbnails
	 * have been generated.
>>>>>>> compatible
	 */
	generateThumbnails_(imageData) {
		return CancellablePromise.all(
			this.effects.map(effect => this.generateThumbnail_(effect, imageData))
		);
	}

	/**
	 * Generates a resized version of the image data to generate the
	 * thumbnails more efficiently.
<<<<<<< HEAD
	 * @param  {ImageData} imageData The original image data
	 * @return {ImageData} The resized image data
	 * @review
=======
	 *
	 * @param  {ImageData} imageData The original image data
	 * @return {ImageData} The resized image data
>>>>>>> compatible
	 */
	generateThumbnailImageData_(imageData) {
		let thumbnailSize = this.thumbnailSize;
		let imageWidth = imageData.width;
		let imageHeight = imageData.height;

		let rawCanvas = document.createElement('canvas');
		rawCanvas.width = imageWidth;
		rawCanvas.height = imageHeight;
		rawCanvas.getContext('2d').putImageData(imageData, 0, 0);

		let commonSize = imageWidth > imageHeight ? imageHeight : imageWidth;

		let canvas = document.createElement('canvas');
		canvas.width = thumbnailSize;
		canvas.height = thumbnailSize;

		let context = canvas.getContext('2d');
		context.drawImage(rawCanvas, imageWidth - commonSize, imageHeight - commonSize, commonSize, commonSize, 0, 0, thumbnailSize, thumbnailSize);

		return context.getImageData(0, 0, thumbnailSize, thumbnailSize);
	}

	/**
	 * Starts optimistically prefetching all the effect results.
<<<<<<< HEAD
	 * @return {CancellablePromise} A promise to be fullfilled when all
	 * the effects have been prefetched
	 * @review
=======
	 *
	 * @return {CancellablePromise} A promise to be fullfilled when all
	 * the effects have been prefetched
>>>>>>> compatible
	 */
	prefetchEffects_() {
		return new CancellablePromise((resolve, reject) => {
			if (!this.isDisposed()) {
				let missingEffects = this.effects.filter((effect) => !this.cache_[effect]);

				if (!missingEffects.length) {
					resolve();
				} else {
					this.getImageEditorImageData()
						.then((imageData) => this.process(imageData, missingEffects[0]))
						.then(() => this.prefetchEffects_());
				}
			}
		});
	}

	/**
	 * Applies the selected effect to the image.
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
	 * Notifies the editor that the component wants to generate a new
	 * preview of the current image.
<<<<<<< HEAD
	 * @param  {MouseEvent} event
	 * @review
=======
	 *
	 * @param  {MouseEvent} event
>>>>>>> compatible
	 */
	previewEffect(event) {
		this.currentEffect_ = event.delegateTarget.getAttribute('data-effect');
		this.requestImageEditorPreview();
	}

	/**
	 * Applies the selected effect to the image.
<<<<<<< HEAD
=======
	 *
>>>>>>> compatible
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @param {String} effectName The effect to apply to the image.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	process(imageData, effectName) {
		let effect = effectName || this.currentEffect_;
		let promise = this.cache_[effect];

		if (!promise) {
			promise = this.spawnWorker_({
				effect: effect,
				imageData: imageData
			});

			this.cache_[effect] = promise;
		}

		return promise;
	}

	/**
<<<<<<< HEAD
	 * Makes the carousel scroll left to reveal options off the visible area
	 * @return void
	 * @review
	 */
	scrollLeft() {
		const carousel = this.refs.carousel;
		const itemWidth = this.refs.carouselFirstItem.offsetWidth || 0;
		const marginLeft = parseInt(carousel.style.marginLeft || 0, 10);

		if (marginLeft < 0) {
			const newMarginValue = Math.min(marginLeft + itemWidth, 0);

			this.carouselOffset =  newMarginValue + 'px';
		}
	}

	/**
	 * Makes the caousel scroll right to reveal options off the visible area
	 * @return void
	 * @review
	 */
	scrollRight() {
		if (this.canScrollForward_()) {
			const carousel = this.refs.carousel;
			const itemWidth = this.refs.carouselFirstItem.offsetWidth || 0;
			const marginLeft = parseInt(carousel.style.marginLeft || 0, 10);

			this.carouselOffset = (marginLeft - itemWidth) + 'px';
		}
	}

	/**
	 * Spawns the a webworker to do the image processing in a different thread.
=======
	 * Spawns the a webworker to do the image processing in a different thread.
	 *
>>>>>>> compatible
	 * @param  {String} workerURI URI of the worker to spawn.
	 * @param  {Object} message An object with the image and effect preset.
	 * @return {CancellablePromise} A promise that will resolve when the webworker
	 * finishes processing the image.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	spawnWorker_(message) {
		return new CancellablePromise((resolve, reject) => {
			let processWorker = new Worker(this.modulePath + '/EffectsWorker.js');

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
 */
EffectsComponent.STATE = {
	/**
	 * Offset to the carousel item with the 'px' postfix
	 * @review
	 * @type {String}
	 */
	carouselOffset: {
		validator: core.isString,
		value: '0'
	},

	/**
	 * Array of available effects
	 * @review
=======
 * @type {!Object}
 * @static
 */
EffectsComponent.STATE = {
	/**
	 * Array of available effects
>>>>>>> compatible
	 * @type {Object}
	 */
	effects: {
		validator: core.isArray,
		value: ['none', 'ruby', 'absinthe', 'chroma', 'atari', 'tripel', 'ailis', 'flatfoot', 'pyrexia', 'umbra', 'rouge', 'idyll', 'glimmer', 'elysium', 'nucleus', 'amber', 'paella', 'aureus', 'expanse', 'orchid']
	},

	/**
	 * Injected helper to get the editor image data
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Function}
	 */
	getImageEditorImageData: {
		validator: core.isFunction
	},

	/**
	 * Path of this module
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Function}
	 */
	modulePath: {
		validator: core.isString
	},

	/**
	 * Injected helper to get the editor image data
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Function}
	 */
	requestImageEditorPreview: {
		validator: core.isFunction
	},

	/**
	 * Size of the thumbnails. (size x size)
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Number}
	 */
	thumbnailSize: {
		validator: core.isNumber,
		value: 55
	}
};

<<<<<<< HEAD
=======
// Register component
>>>>>>> compatible
Soy.register(EffectsComponent, componentTemplates);

export default EffectsComponent;