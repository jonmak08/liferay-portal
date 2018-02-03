'use strict';

import EventScreen from './EventScreen.es';

<<<<<<< HEAD
/**
 * RenderURLScreen
 *
 * This class inherits from EventScreen. It's the screen used for all
 * requests made to RenderURLs.
 * @review
 */
class RenderURLScreen extends EventScreen {
	/**
	 * @inheritDoc
	 * @review
	 */
=======
class RenderURLScreen extends EventScreen {
>>>>>>> compatible
	constructor() {
		super();

		this.cacheable = true;
	}
}

export default RenderURLScreen;