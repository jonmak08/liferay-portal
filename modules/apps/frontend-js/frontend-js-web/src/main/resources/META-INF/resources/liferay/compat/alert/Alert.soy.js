/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Alert.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Alert.
 * @public
 */

goog.module('liferay.frontend.Alert.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
goog.require('goog.string');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  body: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  closeButtonHtml: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  dismissible: (boolean|null|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  opt_data = opt_data || {};
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var body = soy.asserts.assertType(opt_data.body == null || goog.isFunction(opt_data.body) || (goog.isString(opt_data.body) || opt_data.body instanceof goog.soy.data.SanitizedContent), 'body', opt_data.body, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var closeButtonHtml = soy.asserts.assertType(opt_data.closeButtonHtml == null || goog.isFunction(opt_data.closeButtonHtml) || (goog.isString(opt_data.closeButtonHtml) || opt_data.closeButtonHtml instanceof goog.soy.data.SanitizedContent), 'closeButtonHtml', opt_data.closeButtonHtml, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var dismissible = soy.asserts.assertType(opt_data.dismissible == null || (goog.isBoolean(opt_data.dismissible) || opt_data.dismissible === 1 || opt_data.dismissible === 0), 'dismissible', opt_data.dismissible, 'boolean|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'alert' + (dismissible ? ' alert-dismissible' : '') + (elementClasses ? ' ' + elementClasses : ''));
      incrementalDom.attr('role', 'alert');
  incrementalDom.elementOpenEnd();
    if (body) {
      soyIdom.print(body);
    }
    if (dismissible) {
      var msg_1735__soy1736 = '';
      /** @desc The word 'Close' used as a verb */
      var MSG_EXTERNAL_202442244688100878 = goog.getMsg('close');
      msg_1735__soy1736 += goog.string.unescapeEntities(MSG_EXTERNAL_202442244688100878);
      incrementalDom.elementOpenStart('button');
          incrementalDom.attr('type', 'button');
          incrementalDom.attr('class', 'close');
          incrementalDom.attr('aria-label', msg_1735__soy1736);
          incrementalDom.attr('data-onclick', 'toggle');
      incrementalDom.elementOpenEnd();
        if (closeButtonHtml) {
          soyIdom.print(closeButtonHtml);
        } else {
          incrementalDom.elementOpenStart('span');
              incrementalDom.attr('aria-hidden', 'true');
          incrementalDom.elementOpenEnd();
            incrementalDom.text('\u00D7');
          incrementalDom.elementClose('span');
        }
      incrementalDom.elementClose('button');
    }
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  body: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  closeButtonHtml: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  dismissible: (boolean|null|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Alert.render';
}

exports.render.params = ["body","closeButtonHtml","dismissible","elementClasses"];
exports.render.types = {"body":"html|string","closeButtonHtml":"html|string","dismissible":"bool","elementClasses":"string"};
templates = exports;
return exports;

});

class liferayfrontendAlert extends Component {}
Soy.register(liferayfrontendAlert, templates);
export { liferayfrontendAlert, templates };
export default templates;
/* jshint ignore:end */
