<<<<<<< HEAD
import { CancellablePromise } from 'metal-promise';
=======
import { CancellablePromise } from 'metal-promise/src/promise/Promise';
>>>>>>> compatible

/**
 * Image Editor History Entry
 *
 * This class models a step in the image edition process. It stores the
 * Image data at a given point in time so it can be later recovered for
 * undo/redo purposes or other visualization needs.
<<<<<<< HEAD
 * @review
=======
>>>>>>> compatible
 */
class ImageEditorHistoryEntry {
	/**
	 * Constructor
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	constructor(image) {
		this.dataPromise_ = new CancellablePromise((resolve, reject) => {
			// Preemtively fetch the imageData when all we have is the image url
			if (image.url && !image.data) {
				this.loadData_(image.url)
					.then((imageData) => resolve(imageData));
			}
			else {
				resolve(image.data);
			}
		});
	}

	/**
	 * Fetches an ImageData for a given image url
<<<<<<< HEAD
	 * @param  {String} imageURL The image url to load
	 * @protected
	 * @review
=======
	 *
	 * @param  {String} imageURL The image url to load
	 * @protected
>>>>>>> compatible
	 */
	loadData_(imageURL) {
		return new CancellablePromise((resolve, reject) => {
			let bufferImage = new Image();

			bufferImage.onload = () => {
				let bufferCanvas = document.createElement('canvas');
				let bufferContext = bufferCanvas.getContext('2d');

				let height = bufferImage.height;
				let width = bufferImage.width;

				bufferCanvas.width = width;
				bufferCanvas.height = height;

				bufferContext.drawImage(bufferImage, 0, 0, width, height);

				resolve(bufferContext.getImageData(0, 0, width, height));
			};

			bufferImage.src = imageURL;
		});
	}

	/**
	 * Fetches the stored ImageData of this history entry
<<<<<<< HEAD
	 * @return {CancellablePromise} A promise that will resolve with the stored ImageData value
	 * @review
=======
	 *
	 * @return {CancellablePromise} A promise that will resolve with the stored ImageData value
>>>>>>> compatible
	 */
	getImageData() {
		return this.dataPromise_;
	}
}

export default ImageEditorHistoryEntry;