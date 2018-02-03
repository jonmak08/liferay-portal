import Component from 'metal-component';
import Soy from 'metal-soy';
import { core } from 'metal';

import 'metal-dropdown';

import CompatibilityEventProxy from 'frontend-js-web/liferay/CompatibilityEventProxy.es'

import templates from './TranslationManager.soy';

/**
 * TranslationManager
 *
 * This class adds functionallity to manage existing language options, and
 * create new ones.
<<<<<<< HEAD
 * @review
=======
>>>>>>> compatible
 */
class TranslationManager extends Component {
	/**
	 * @inheritDoc
	 */
	constructor(opt_config, opt_element) {
		super(opt_config, opt_element);

		this.resetEditingLocale_();

		this.startCompatibility_();
	}

	/**
	 * Add a language to the available locales list and set it as the
	 * current editing language.
<<<<<<< HEAD
	 * @param  {MouseEvent} event
	 * @review
=======
	 *
	 * @param  {MouseEvent} event
>>>>>>> compatible
	 */
	addLocale(event) {
		let localeId = event.delegateTarget.getAttribute('data-locale-id');

		if (this.availableLocales.indexOf(localeId) === -1) {
			this.availableLocales.push(localeId);
		}

		this.availableLocales = this.availableLocales;

		this.editingLocale = localeId;
	}

	/**
	 * Registers another EventTarget as a bubble target.
<<<<<<< HEAD
	 * @param  {!Object} target YUI component where events will be emited to
	 * @review
=======
	 *
	 * @param  {!Object} target YUI component where events will be emited to
>>>>>>> compatible
	 */
	addTarget(target) {
		this.compatibilityEventProxy_.addTarget(target);
	}

	/**
	 * Change the default language.
<<<<<<< HEAD
	 * @param  {MouseEvent} event
	 * @review
=======
	 *
	 * @param  {MouseEvent} event
>>>>>>> compatible
	 */
	changeDefaultLocale(event) {
		let localeId = event.delegateTarget.getAttribute('data-locale-id');

		this.defaultLocale = localeId;

		this.editingLocale = localeId;
	}

	/**
	 * Change current editing language.
<<<<<<< HEAD
	 * @param  {MouseEvent} event
	 * @review
=======
	 *
	 * @param  {MouseEvent} event
>>>>>>> compatible
	 */
	changeLocale(event) {
		let localeId = event.delegateTarget.getAttribute('data-locale-id');

		this.editingLocale = localeId;
	}

	/**
	 * Returns a property.
<<<<<<< HEAD
	 * @param  {String} attr Name of the attribute wanted to get
	 * @review
=======
	 *
	 * @param  {String} attr Name of the attribute wanted to get
>>>>>>> compatible
	 */
	get(attr) {
		return this[attr];
	}

	/**
	 * Remove a language from the available locales list and reset the current
	 * editing language to default if removed one was selected.
<<<<<<< HEAD
	 * @param  {MouseEvent} event
	 * @review
=======
	 *
	 * @param  {MouseEvent} event
>>>>>>> compatible
	 */
	removeAvailableLocale(event) {
		let localeId = event.delegateTarget.getAttribute('data-locale-id');

		let localePosition = this.availableLocales.indexOf(localeId);

		this.availableLocales.splice(localePosition, 1);

		this.availableLocales = this.availableLocales;

		if (localeId === this.editingLocale) {
			this.resetEditingLocale_();
		}

		this.emit(
			'deleteAvailableLocale',
			{
				locale: localeId
			}
		);
	}

	/**
	 * Set the current editing locale to the default locale.
<<<<<<< HEAD
	 * @private
	 * @review
=======
	 *
	 * @private
>>>>>>> compatible
	 */
	resetEditingLocale_() {
		this.editingLocale = this.defaultLocale;
	}

	/**
	 * Configuration to emit yui-based events to maintain
	 * backwards compatibility.
<<<<<<< HEAD
	 * @private
	 * @review
	 */
	startCompatibility_() {
		this.destroy = this.dispose;

=======
	 *
	 * @private
	 */
	startCompatibility_() {
>>>>>>> compatible
		this.compatibilityEventProxy_ = new CompatibilityEventProxy(
			{
				host: this,
				namespace: 'translationmanager'
			}
		);
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
TranslationManager.STATE = {
	/**
	 * Current editing language key.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {String}
	 */
	editingLocale: {
		validator: core.isString
	},

	/**
	 * List of available languages keys.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Array.<String>}
	 */
	availableLocales: {
		validator: core.isArray
	},

	/**
	 * Indicates if the default language is editable or not.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Boolean}
	 */
	changeableDefaultLanguage: {
		validator: core.isBoolean
	},

	/**
	 * Default language key.
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {String}
	 */
	defaultLocale: {
		validator: core.isString
	},

	/**
	 * Map of all languages
<<<<<<< HEAD
	 * @review
=======
>>>>>>> compatible
	 * @type {Object}
	 */
	locales: {
		validator: core.isObject
	}
};

<<<<<<< HEAD
=======
// Register component
>>>>>>> compatible
Soy.register(TranslationManager, templates);

export default TranslationManager;