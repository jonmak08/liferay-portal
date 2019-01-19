/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from ProgressBar.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.ProgressBar.
 * @public
 */

goog.module('liferay.frontend.ProgressBar.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  barClass: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  max: (null|number|undefined),
 *  min: (null|number|undefined),
 *  value: (null|number|undefined),
 *  label: (!goog.soy.data.SanitizedContent|function()|null|string|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  opt_data = opt_data || {};
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var barClass = soy.asserts.assertType(opt_data.barClass == null || (goog.isString(opt_data.barClass) || opt_data.barClass instanceof goog.soy.data.SanitizedContent), 'barClass', opt_data.barClass, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {null|number|undefined} */
  var max = soy.asserts.assertType(opt_data.max == null || goog.isNumber(opt_data.max), 'max', opt_data.max, 'null|number|undefined');
  /** @type {null|number|undefined} */
  var min = soy.asserts.assertType(opt_data.min == null || goog.isNumber(opt_data.min), 'min', opt_data.min, 'null|number|undefined');
  /** @type {null|number|undefined} */
  var value = soy.asserts.assertType(opt_data.value == null || goog.isNumber(opt_data.value), 'value', opt_data.value, 'null|number|undefined');
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var label = soy.asserts.assertType(opt_data.label == null || goog.isFunction(opt_data.label) || (goog.isString(opt_data.label) || opt_data.label instanceof goog.soy.data.SanitizedContent), 'label', opt_data.label, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  var curMax__soy565 = max ? max : 100;
  var curMin__soy567 = min ? min : 0;
  var curValue__soy569 = value ? value : 0;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'progress ' + (elementClasses ? ' ' + elementClasses : ''));
      incrementalDom.attr('role', 'progressbar');
      incrementalDom.attr('aria-valuemax', curMax__soy565);
      incrementalDom.attr('aria-valuemin', curMin__soy567);
      incrementalDom.attr('aria-valuenow', curValue__soy569);
      incrementalDom.attr('tabindex', '0');
  incrementalDom.elementOpenEnd();
    var percentage__soy579 = (Math.floor((curValue__soy569 - curMin__soy567) * 100 / (curMax__soy565 - curMin__soy567)));
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'progress-bar' + (barClass ? ' ' + barClass : ''));
        incrementalDom.attr('style', 'width: ' + percentage__soy579 + '%');
    incrementalDom.elementOpenEnd();
      soyIdom.print(label ? label : '');
    incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  barClass: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  max: (null|number|undefined),
 *  min: (null|number|undefined),
 *  value: (null|number|undefined),
 *  label: (!goog.soy.data.SanitizedContent|function()|null|string|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.ProgressBar.render';
}

exports.render.params = ["barClass","elementClasses","max","min","value","label"];
exports.render.types = {"barClass":"string","elementClasses":"string","max":"int","min":"int","value":"int","label":"html|string"};
templates = exports;
return exports;

});

class liferayfrontendProgressBar extends Component {}
Soy.register(liferayfrontendProgressBar, templates);
export { liferayfrontendProgressBar, templates };
export default templates;
/* jshint ignore:end */
