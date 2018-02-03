import core from 'metal';
import dom from 'metal-dom';
<<<<<<< HEAD
import {EventHandler} from 'metal-events';

=======
import { EventHandler } from 'metal-events';
>>>>>>> compatible
import PortletBase from './PortletBase.es';

/**
 * DynamicInlineScroll appends list item elements to dropdown menus with inline-scrollers
 * on scroll events to improve page load performance.
 *
 * @extends {Component}
<<<<<<< HEAD
 * @review
=======
>>>>>>> compatible
 */
class DynamicInlineScroll extends PortletBase {
	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	created() {
		this.eventHandler_ = new EventHandler();
	}

	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	attached() {
		let {rootNode} = this;

		rootNode = rootNode || document;

<<<<<<< HEAD
		this.eventHandler_.add(
			dom.delegate(
				rootNode,
				'scroll',
				'ul.pagination ul.inline-scroller',
				this.onScroll_.bind(this)
			)
		);
=======
		this.eventHandler_.add(dom.delegate(rootNode, 'scroll', 'ul.pagination ul.inline-scroller', this.onScroll_.bind(this)));
>>>>>>> compatible
	}

	/**
	 * @inheritDoc
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 */
	detached() {
		super.detached();

		this.eventHandler_.removeAllListeners();
	}

	/**
	 * Dynamically adds list item elements to the dropdown menu.
<<<<<<< HEAD
	 * @param {element} listElement Dom node of the list element.
	 * @param {number} pageIndex Index of page with an inline-scroller.
	 * @protected
	 * @review
=======
	 *
	 * @param {element} listElement Dom node of the list element.
	 * @param {number} pageIndex Index of page with an inline-scroller.
	 * @protected
>>>>>>> compatible
	 */
	addListItem_(listElement, pageIndex) {
		const listItem = document.createElement('li');

<<<<<<< HEAD
		dom.append(
			listItem,
			`<a href="${this.getHREF_(pageIndex)}">${pageIndex}</a>`
		);
=======
		dom.append(listItem, `<a href="${this.getHREF_(pageIndex)}">${pageIndex}</a>`);
>>>>>>> compatible

		pageIndex++;

		listElement.appendChild(listItem);
		listElement.setAttribute('data-page-index', pageIndex);

<<<<<<< HEAD
		this.eventHandler_.add(
			dom.on(listItem, 'click', this.handleListItemClick_.bind(this))
		);
=======
		this.eventHandler_.add(dom.on(listItem, 'click', this.handleListItemClick_.bind(this)));
>>>>>>> compatible
	}

	/**
	 * Returns the href attribute value for each
<<<<<<< HEAD
	 * @param {number} pageIndex Index of page
	 * @protected
	 * @return {string} String value of href
	 * @review
=======
	 *
	 * @param {number} pageIndex Index of page
	 * @protected
	 * @return {string} String value of href
>>>>>>> compatible
	 */
	getHREF_(pageIndex) {
		const {curParam, formName, jsCall, namespace, url, urlAnchor} = this;

		if (this.url !== null) {
			return `${url}${namespace}${curParam}=${pageIndex}${urlAnchor}`;
		}

<<<<<<< HEAD
		return `javascript:document.${formName}.${namespace}${curParam}.value = "${
			pageIndex
		}; ${jsCall}`;
=======
		return `javascript:document.${formName}.${namespace}${curParam}.value = "${pageIndex}; ${jsCall}`;
>>>>>>> compatible
	}

	/**
	 * Returns the value of the parameter passed in as a Number object
<<<<<<< HEAD
	 * @param {string|!Object} val The string or Object to be converted to a number
	 * @protected
	 * @return {number} Number value of parameter
	 * @review
=======
	 *
	 * @param {string|!Object} val The string or Object to be converted to a number
	 * @protected
	 * @return {number} Number value of parameter
>>>>>>> compatible
	 */
	getNumber_(val) {
		return Number(val);
	}

	/**
	 * Handles click event of dynmaically added list item and submits search
	 * container form.
<<<<<<< HEAD
	 * @param {Event} event The click event of the dynamically added list item.
	 * @protected
	 * @review
=======
	 *
	 * @param {Event} event The click event of the dynamically added list item.
	 * @protected
>>>>>>> compatible
	 */
	handleListItemClick_(event) {
		if (this.forcePost) {
			event.preventDefault();

			const {curParam, namespace, randomNamespace} = this;

<<<<<<< HEAD
			const form = document.getElementById(
				randomNamespace + namespace + 'pageIteratorFm'
			);

			form.elements[namespace + curParam].value =
				event.currentTarget.textContent;
=======
			const form = document.getElementById(randomNamespace + namespace + 'pageIteratorFm');

			form.elements[namespace + curParam].value = event.currentTarget.textContent;
>>>>>>> compatible

			form.submit();
		}
	}

	/**
	 * An event triggered when a dropdown menu with an inline-scroller is scrolled.
	 * Dynamically adds list item elements to the dropdown menu as it is scrolled down.
<<<<<<< HEAD
	 * @param {Event} event The scroll event triggered by scrolling a dropdown menu
	 * with an inline-scroller
	 * @protected
	 * @review
=======
	 *
	 * @param {Event} event The scroll event triggered by scrolling a dropdown menu
	 * with an inline-scroller
	 * @protected
>>>>>>> compatible
	 */
	onScroll_(event) {
		const {cur, initialPages, pages} = this;
		const {target} = event;

		let pageIndex = this.getNumber_(target.getAttribute('data-page-index'));
		let pageIndexMax = this.getNumber_(target.getAttribute('data-max-index'));

		if (pageIndex === 0) {
<<<<<<< HEAD
			let pageIndexCurrent = this.getNumber_(
				target.getAttribute('data-current-index')
			);

			if (pageIndexCurrent === 0) {
				pageIndex = initialPages;
			} else {
=======
			let pageIndexCurrent = this.getNumber_(target.getAttribute('data-current-index'));

			if (pageIndexCurrent === 0) {
				pageIndex = initialPages;
			}
			else {
>>>>>>> compatible
				pageIndex = pageIndexCurrent + initialPages;
			}
		}

		if (pageIndexMax === 0) {
			pageIndexMax = pages;
		}

<<<<<<< HEAD
		if (
			cur <= pages &&
			pageIndex < pageIndexMax &&
			target.getAttribute('scrollTop') >=
				target.getAttribute('scrollHeight') - 300
		) {
=======
		if ((cur <= pages) && (pageIndex < pageIndexMax) && (target.getAttribute('scrollTop') >= (target.getAttribute('scrollHeight') - 300))) {
>>>>>>> compatible
			this.addListItem_(target, pageIndex);
		}
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
DynamicInlineScroll.STATE = {
	/**
	 * Current page
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {string}
	 */
	cur: {
		setter: 'getNumber_',
<<<<<<< HEAD
		validator: core.isString,
=======
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * URL parameter for current page
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	curParam: {
		validator: core.isString,
=======
	 * @type {string}
	 */
	curParam: {
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * Forces a form post when a page on the dropdown menu is clicked
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {boolean}
	 */
	forcePost: {
		validator: core.isBoolean,
=======
	 * @type {boolean}
	 */
	forcePost: {
		validator: core.isBoolean
>>>>>>> compatible
	},

	/**
	 * Form name
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	formName: {
		validator: core.isString,
=======
	 * @type {string}
	 */
	formName: {
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * Number of pages loaded to the inline-scroll dropdown menu for the first
	 * page load
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {string}
	 */
	initialPages: {
		setter: 'getNumber_',
<<<<<<< HEAD
		validator: core.isString,
=======
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * JavaScript call
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	jsCall: {
		validator: core.isString,
=======
	 * @type {string}
	 */
	jsCall: {
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * Namespace
	 * @instance
	 * @memberof DynamicInlineScroll
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
	 * Total number of pages
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {string}
	 */
	pages: {
		setter: 'getNumber_',
<<<<<<< HEAD
		validator: core.isString,
=======
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * Random namespace
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	randomNamespace: {
		validator: core.isString,
=======
	 * @type {string}
	 */
	randomNamespace: {
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * URL
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	url: {
		validator: core.isString,
=======
	 * @type {string}
	 */
	url: {
		validator: core.isString
>>>>>>> compatible
	},

	/**
	 * URL anchor
	 * @instance
	 * @memberof DynamicInlineScroll
<<<<<<< HEAD
	 * @review
	 * @type {string}
	 */
	urlAnchor: {
		validator: core.isString,
	},
};

export default DynamicInlineScroll;
=======
	 * @type {string}
	 */
	urlAnchor: {
		validator: core.isString
	}
};

export default DynamicInlineScroll;
>>>>>>> compatible
