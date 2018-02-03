'use strict';

const MAX_TIMEOUT = Math.pow(2, 31) - 1;

<<<<<<< HEAD
/**
 * Utils
 *
 * Collection of utilities used by this module.
 * @review
 */
class Utils {
	/**
	 * Returns the biggest number allowed by the setTimeout function.
	 * @return {!Number} The number.
	 * @review
	 */
=======
class Utils {
>>>>>>> compatible
	static getMaxTimeout() {
		return MAX_TIMEOUT;
	}

<<<<<<< HEAD
	/**
	 * Given a portletId, returns the id of it's portlet boundary DOM element.
	 * @param  {!String} portletId The portlet id.
	 * @return {!String} The portlet boundary id.
	 * @review
	 */
=======
>>>>>>> compatible
	static getPortletBoundaryId(portletId) {
		return 'p_p_id_' + portletId + '_';
	}

<<<<<<< HEAD
	/**
	 * Given an array of portlet ids, returns an array portlet boundary ids.
	 * @param  {!Array} The collection of portletIds.
	 * @return {!Array} The collection of portlet boundary ids.
	 * @review
	 */
=======
>>>>>>> compatible
	static getPortletBoundaryIds(portletIds) {
		return portletIds.map(
			function(portletId) {
				return Utils.getPortletBoundaryId(portletId);
			}
		);
	}

<<<<<<< HEAD
	/**
	 * Calls the destructor of every portlet rendered on the page.
	 * @review
	 */
=======
>>>>>>> compatible
	static resetAllPortlets() {
		Utils.getPortletBoundaryIds(Liferay.Portlet.list).forEach(
			function(value, index, collection) {
				var portlet = document.querySelector('#' + value);

				if (portlet) {
					Liferay.Portlet.destroy(portlet);

					portlet.portletProcessed = false;
				}
			}
		);

		Liferay.Portlet.readyCounter = 0;
	}
}

export default Utils;