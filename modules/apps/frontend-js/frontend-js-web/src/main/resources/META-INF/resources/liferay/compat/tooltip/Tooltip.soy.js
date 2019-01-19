/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Tooltip.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Tooltip.
 * @public
 */

goog.module('liferay.frontend.Tooltip.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  alignedPosition: (null|number|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  position: (null|number|undefined),
 *  title: (!goog.soy.data.SanitizedContent|function()|null|string|undefined)
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
  var alignedPosition = soy.asserts.assertType(opt_data.alignedPosition == null || goog.isNumber(opt_data.alignedPosition), 'alignedPosition', opt_data.alignedPosition, 'null|number|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {null|number|undefined} */
  var position = soy.asserts.assertType(opt_data.position == null || goog.isNumber(opt_data.position), 'position', opt_data.position, 'null|number|undefined');
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var title = soy.asserts.assertType(opt_data.title == null || goog.isFunction(opt_data.title) || (goog.isString(opt_data.title) || opt_data.title instanceof goog.soy.data.SanitizedContent), 'title', opt_data.title, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  var positionClasses__soy759 = ['top', 'top', 'right', 'bottom', 'bottom', 'bottom', 'left', 'top'];
  var currentPosition__soy761 = (alignedPosition != null) ? alignedPosition : position;
  var positionClass__soy763 = (currentPosition__soy761 != null) ? positionClasses__soy759[currentPosition__soy761] : 'bottom';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'tooltip ' + positionClass__soy763 + (elementClasses ? ' ' + elementClasses : ''));
      incrementalDom.attr('role', 'tooltip');
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'tooltip-arrow');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('section');
        incrementalDom.attr('class', 'tooltip-inner');
    incrementalDom.elementOpenEnd();
      if (title) {
        soyIdom.print(title);
      }
    incrementalDom.elementClose('section');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  alignedPosition: (null|number|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  position: (null|number|undefined),
 *  title: (!goog.soy.data.SanitizedContent|function()|null|string|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Tooltip.render';
}

exports.render.params = ["alignedPosition","elementClasses","position","title"];
exports.render.types = {"alignedPosition":"int","elementClasses":"string","position":"int","title":"html|string"};
templates = exports;
return exports;

});

class liferayfrontendTooltip extends Component {}
Soy.register(liferayfrontendTooltip, templates);
export { liferayfrontendTooltip, templates };
export default templates;
/* jshint ignore:end */
