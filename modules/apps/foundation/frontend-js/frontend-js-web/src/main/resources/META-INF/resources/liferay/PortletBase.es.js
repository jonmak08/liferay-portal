import core from 'metal';
import dom from 'metal-dom';
import Component from 'metal-component';

/**
 * PortletBase provides some helper functions that simplify querying the DOM
 * for elements related to a specific portlet.
<<<<<<< HEAD
 * @abstract
 * @extends {Component}
 * @review
=======
 *
 * @abstract
 * @extends {Component}
>>>>>>> compatible
 */
class PortletBase extends Component {
	/**
	 * Returns a NodeList containing all of the matching Element nodes within
	 * the subtrees of the root object, in tree order. If there are no matching
	 * nodes, the method returns an empty NodeList.
<<<<<<< HEAD
=======
	 *
>>>>>>> compatible
	 * @param {string} selectors List of one or more CSS relative selectors
	 * @param {(string|Element|Document)=} root Root node of the search. If not
	 * specified, the element search will start in the portlet's root node or in
	 * the document
	 * @return {NodeList<Element>} List of Elements matching the selectors in
	 * tree order
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	all(selectors, root) {
		root = dom.toElement(root) || this.rootNode || document;

<<<<<<< HEAD
		return root.querySelectorAll(
			this.namespaceSelectors_(this.namespace, selectors)
		);
=======
		return root.querySelectorAll(this.namespaceSelectors_(this.namespace, selectors));
>>>>>>> compatible
	}

	/**
	 * Namespaces the list of selectors appending the portlet namespace to the
	 * selectors of type id. Selectors of other types remain unaltered.
<<<<<<< HEAD
	 * @param {string} namespace The portlet's namespace
	 * @param {string} selectors List of one or more CSS relative selectors
	 * @protected
	 * @return {string} Namespaced id selectors
	 * @review
	 */
	namespaceSelectors_(namespace, selectors) {
		return selectors.replace(
			new RegExp('(#|\\[id=(\\"|\\\'))(?!' + namespace + ')', 'g'),
			'$1' + namespace
		);
=======
	 *
	 * @protected
	 * @param {string} namespace The portlet's namespace
	 * @param {string} selectors List of one or more CSS relative selectors
	 * @return {string} Namespaced id selectors
	 */
	namespaceSelectors_(namespace, selectors) {
		return selectors.replace(new RegExp('(#|\\[id=(\\\"|\\\'))(?!' + namespace + ')', 'g'), '$1' + namespace);
>>>>>>> compatible
	}

	/**
	 * Appends the portlet's namespace to the given string or object properties.
<<<<<<< HEAD
	 * @param {!Object|string} obj The object or string to be namespaced
	 * @return {Object|string} An object with its properties namespaced using
	 * the portlet namespace or a namespaced string
	 * @review
=======
	 *
	 * @param {!Object|string} The object or string to be namespaced
	 * @return {Object|string} An object with its properties namespaced using
	 * the portlet namespace or a namespaced string
>>>>>>> compatible
	 */
	ns(obj) {
		return Liferay.Util.ns(this.namespace, obj);
	}

	/**
	 * Returns the first matching Element node within the subtrees of the
	 * root object. If there is no matching Element, the method returns null.
<<<<<<< HEAD
=======
	 *
>>>>>>> compatible
	 * @param {string} selectors List of one or more CSS relative selectors
	 * @param {(string|Element|Document)=} root Root node of the search. If not
	 * specified, the element search will start in the portlet's root node or in
	 * the document
	 * @return {Element|null} List of First Element matching the selectors or null
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	one(selectors, root) {
		root = dom.toElement(root) || this.rootNode || document;

<<<<<<< HEAD
		return root.querySelector(
			this.namespaceSelectors_(this.namespace, selectors)
		);
=======
		return root.querySelector(this.namespaceSelectors_(this.namespace, selectors));
>>>>>>> compatible
	}

	/**
	 * Returns the default portlet root node element. By default, this is the
	 * element with id "p_p_id{portletNamespace}".
<<<<<<< HEAD
	 * @protected
	 * @return {Element} The portlet's default root node element
	 * @review
=======
	 *
	 * @protected
	 * @return {Element} The portlet's default root node element
>>>>>>> compatible
	 */
	rootNodeValueFn_() {
		return dom.toElement('#p_p_id' + this.namespace);
	}
}

/**
 * State definition.
 * @ignore
<<<<<<< HEAD
 * @review
=======
>>>>>>> compatible
 * @static
 * @type {!Object}
 */
PortletBase.STATE = {
	/**
	 * Portlet's namespace
	 * @instance
	 * @memberof PortletBase
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	namespace: {
		validator: core.isString,
=======
	 * @type {string}
	 */
	namespace: {
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * Portlet's root node element
	 * @instance
	 * @memberof PortletBase
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Element}
	 */
	rootNode: {
		setter: dom.toElement,
<<<<<<< HEAD
		valueFn: 'rootNodeValueFn_',
	},
};

export default PortletBase;
=======
		valueFn: 'rootNodeValueFn_'
	}
};

export default PortletBase;
>>>>>>> compatible
