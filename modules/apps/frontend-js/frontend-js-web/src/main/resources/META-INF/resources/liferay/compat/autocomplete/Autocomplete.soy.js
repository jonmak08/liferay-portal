/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Autocomplete.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Autocomplete.
 * @public
 */

goog.module('liferay.frontend.Autocomplete.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('liferay.frontend.List.incrementaldom', 'render');


/**
 * @param {{
 *  listId: (null|number|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  onListItemSelected_: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  opt_data = opt_data || {};
  /** @type {null|number|undefined} */
  var listId = soy.asserts.assertType(opt_data.listId == null || goog.isNumber(opt_data.listId), 'listId', opt_data.listId, 'null|number|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {?} */
  var onListItemSelected_ = opt_data.onListItemSelected_;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'autocomplete autocomplete-list component ' + (elementClasses ? ' ' + elementClasses : ''));
      incrementalDom.attr('data-onclick', 'handleClick_');
  incrementalDom.elementOpenEnd();
    $templateAlias1({events: {itemSelected: onListItemSelected_}, id: listId, ref: 'list'}, null, opt_ijData);
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  listId: (null|number|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  onListItemSelected_: (?)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Autocomplete.render';
}

exports.render.params = ["listId","elementClasses","onListItemSelected_"];
exports.render.types = {"listId":"int","elementClasses":"string","onListItemSelected_":"?"};
templates = exports;
return exports;

});

class liferayfrontendAutocomplete extends Component {}
Soy.register(liferayfrontendAutocomplete, templates);
export { liferayfrontendAutocomplete, templates };
export default templates;
/* jshint ignore:end */
