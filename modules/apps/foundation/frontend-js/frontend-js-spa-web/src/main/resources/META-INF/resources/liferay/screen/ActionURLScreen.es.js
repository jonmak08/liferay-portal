'use strict';

import EventScreen from './EventScreen.es';
import Uri from 'metal-uri/src/Uri';
import utils from 'senna/src/utils/utils';

<<<<<<< HEAD
/**
 * ActionURLScreen
 *
 * This class inherits from EventScreen. It's the screen used for all
 * requests made to ActionURLs.
 * @review
 */
class ActionURLScreen extends EventScreen {
	/**
	 * @inheritDoc
	 * @review
	 */
=======
class ActionURLScreen extends EventScreen {
>>>>>>> compatible
	constructor() {
		super();

		this.httpMethod = 'POST';
	}

<<<<<<< HEAD
	/**
	 * @inheritDoc
	 * When an action request (form submission) redirects, we make
	 * sure that the final url has the lifecycle RENDER (p_p_lifecycle=0).
	 * @return {!String} The request path
	 * @review
	 */
=======
>>>>>>> compatible
	getRequestPath() {
		var request = this.getRequest();

		if (request) {
			let uri = new Uri(super.getRequestPath());

			if (uri.getParameterValue('p_p_lifecycle') === '1') {
				uri.setParameterValue('p_p_lifecycle', '0');
			}

			return utils.getUrlPath(uri.toString());
		}

		return null;
	}
}

export default ActionURLScreen;