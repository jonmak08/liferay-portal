/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Slider.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Slider.
 * @public
 */

goog.module('liferay.frontend.Slider.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  inputName: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  max: (null|number|undefined),
 *  min: (null|number|undefined),
 *  value: (null|number|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  var $$temp;
  opt_data = opt_data || {};
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var inputName = soy.asserts.assertType(opt_data.inputName == null || (goog.isString(opt_data.inputName) || opt_data.inputName instanceof goog.soy.data.SanitizedContent), 'inputName', opt_data.inputName, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {null|number|undefined} */
  var max = soy.asserts.assertType(opt_data.max == null || goog.isNumber(opt_data.max), 'max', opt_data.max, 'null|number|undefined');
  /** @type {null|number|undefined} */
  var min = soy.asserts.assertType(opt_data.min == null || goog.isNumber(opt_data.min), 'min', opt_data.min, 'null|number|undefined');
  /** @type {null|number|undefined} */
  var value = soy.asserts.assertType(opt_data.value == null || goog.isNumber(opt_data.value), 'value', opt_data.value, 'null|number|undefined');
  var maxNumber__soy633 = ($$temp = max) == null ? 100 : $$temp;
  var minNumber__soy635 = ($$temp = min) == null ? 0 : $$temp;
  var valueNumber__soy637 = ($$temp = value) == null ? 0 : $$temp;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'slider ' + (($$temp = elementClasses) == null ? '' : $$temp));
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('input');
        incrementalDom.attr('name', ($$temp = inputName) == null ? '' : $$temp);
        incrementalDom.attr('type', 'hidden');
        incrementalDom.attr('value', valueNumber__soy637);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('input');
    incrementalDom.elementOpen('span');
      soyIdom.print(valueNumber__soy637);
    incrementalDom.elementClose('span');
    var percentage__soy647 = 100 * (valueNumber__soy637 - minNumber__soy635) / (maxNumber__soy633 - minNumber__soy635) + '%';
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'rail');
        incrementalDom.attr('data-onclick', 'onRailClick_');
        incrementalDom.attr('ref', 'rail');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'rail-active');
          incrementalDom.attr('style', 'width: ' + percentage__soy647);
      incrementalDom.elementOpenEnd();
      incrementalDom.elementClose('div');
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'rail-handle');
          incrementalDom.attr('style', 'left: ' + percentage__soy647);
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'handle');
            incrementalDom.attr('ref', 'handle');
            incrementalDom.attr('tabindex', '0');
            incrementalDom.attr('role', 'slider');
            incrementalDom.attr('aria-valuemin', minNumber__soy635);
            incrementalDom.attr('aria-valuemax', maxNumber__soy633);
            incrementalDom.attr('aria-valuenow', valueNumber__soy637);
        incrementalDom.elementOpenEnd();
        incrementalDom.elementClose('div');
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  inputName: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  max: (null|number|undefined),
 *  min: (null|number|undefined),
 *  value: (null|number|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Slider.render';
}

exports.render.params = ["elementClasses","inputName","max","min","value"];
exports.render.types = {"elementClasses":"string","inputName":"string","max":"int","min":"int","value":"int"};
templates = exports;
return exports;

});

class liferayfrontendSlider extends Component {}
Soy.register(liferayfrontendSlider, templates);
export { liferayfrontendSlider, templates };
export default templates;
/* jshint ignore:end */
