/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Modal.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Modal.
 * @public
 */

goog.module('liferay.frontend.Modal.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
goog.require('goog.string');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  spritemap: (!goog.soy.data.SanitizedContent|string),
 *  body: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  bodyId: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  dialogClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  footer: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  header: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  headerId: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  noCloseButton: (boolean|null|undefined),
 *  role: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  visible: (boolean|null|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var spritemap = soy.asserts.assertType(goog.isString(opt_data.spritemap) || opt_data.spritemap instanceof goog.soy.data.SanitizedContent, 'spritemap', opt_data.spritemap, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var body = soy.asserts.assertType(opt_data.body == null || goog.isFunction(opt_data.body) || (goog.isString(opt_data.body) || opt_data.body instanceof goog.soy.data.SanitizedContent), 'body', opt_data.body, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var bodyId = soy.asserts.assertType(opt_data.bodyId == null || (goog.isString(opt_data.bodyId) || opt_data.bodyId instanceof goog.soy.data.SanitizedContent), 'bodyId', opt_data.bodyId, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var dialogClasses = soy.asserts.assertType(opt_data.dialogClasses == null || (goog.isString(opt_data.dialogClasses) || opt_data.dialogClasses instanceof goog.soy.data.SanitizedContent), 'dialogClasses', opt_data.dialogClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var footer = soy.asserts.assertType(opt_data.footer == null || goog.isFunction(opt_data.footer) || (goog.isString(opt_data.footer) || opt_data.footer instanceof goog.soy.data.SanitizedContent), 'footer', opt_data.footer, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|function()|null|string|undefined} */
  var header = soy.asserts.assertType(opt_data.header == null || goog.isFunction(opt_data.header) || (goog.isString(opt_data.header) || opt_data.header instanceof goog.soy.data.SanitizedContent), 'header', opt_data.header, '!goog.soy.data.SanitizedContent|function()|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var headerId = soy.asserts.assertType(opt_data.headerId == null || (goog.isString(opt_data.headerId) || opt_data.headerId instanceof goog.soy.data.SanitizedContent), 'headerId', opt_data.headerId, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var noCloseButton = soy.asserts.assertType(opt_data.noCloseButton == null || (goog.isBoolean(opt_data.noCloseButton) || opt_data.noCloseButton === 1 || opt_data.noCloseButton === 0), 'noCloseButton', opt_data.noCloseButton, 'boolean|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var role = soy.asserts.assertType(opt_data.role == null || (goog.isString(opt_data.role) || opt_data.role instanceof goog.soy.data.SanitizedContent), 'role', opt_data.role, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var visible = soy.asserts.assertType(opt_data.visible == null || (goog.isBoolean(opt_data.visible) || opt_data.visible === 1 || opt_data.visible === 0), 'visible', opt_data.visible, 'boolean|null|undefined');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'fade modal show' + (elementClasses ? ' ' + elementClasses : ''));
      incrementalDom.attr('style', 'display: ' + (visible ? 'block' : 'none'));
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'modal-dialog' + (dialogClasses ? ' ' + dialogClasses : '') + ' position-relative');
        incrementalDom.attr('tabindex', '0');
        incrementalDom.attr('role', role ? role : 'dialog');
        incrementalDom.attr('aria-labelledby', headerId);
        incrementalDom.attr('aria-describedby', bodyId);
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'modal-content');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('header');
            incrementalDom.attr('class', 'modal-header');
        incrementalDom.elementOpenEnd();
          if (header) {
            incrementalDom.elementOpenStart('div');
                incrementalDom.attr('class', 'modal-title');
                incrementalDom.attr('id', headerId);
            incrementalDom.elementOpenEnd();
              soyIdom.print(header);
            incrementalDom.elementClose('div');
            if (!noCloseButton) {
              var msg_1738__soy1739 = '';
              /** @desc The word 'Close' used as a verb */
              var MSG_EXTERNAL_202442244688100878 = goog.getMsg('close');
              msg_1738__soy1739 += goog.string.unescapeEntities(MSG_EXTERNAL_202442244688100878);
              incrementalDom.elementOpenStart('button');
                  incrementalDom.attr('type', 'button');
                  incrementalDom.attr('class', 'close');
                  incrementalDom.attr('data-onclick', 'hide');
                  incrementalDom.attr('aria-label', msg_1738__soy1739);
              incrementalDom.elementOpenEnd();
                incrementalDom.elementOpenStart('svg');
                    incrementalDom.attr('class', 'lexicon-icon');
                incrementalDom.elementOpenEnd();
                  incrementalDom.elementOpenStart('use');
                      incrementalDom.attr('xlink:href', spritemap + '#times');
                  incrementalDom.elementOpenEnd();
                  incrementalDom.elementClose('use');
                incrementalDom.elementClose('svg');
              incrementalDom.elementClose('button');
            }
          }
        incrementalDom.elementClose('header');
        incrementalDom.elementOpenStart('section');
            incrementalDom.attr('class', 'modal-body inline-scroller');
            incrementalDom.attr('id', bodyId);
            incrementalDom.attr('role', 'document');
            incrementalDom.attr('tabindex', '0');
        incrementalDom.elementOpenEnd();
          if (body) {
            soyIdom.print(body);
          }
        incrementalDom.elementClose('section');
        if (footer) {
          incrementalDom.elementOpenStart('footer');
              incrementalDom.attr('class', 'modal-footer');
          incrementalDom.elementOpenEnd();
            soyIdom.print(footer);
          incrementalDom.elementClose('footer');
        }
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  spritemap: (!goog.soy.data.SanitizedContent|string),
 *  body: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  bodyId: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  dialogClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  footer: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  header: (!goog.soy.data.SanitizedContent|function()|null|string|undefined),
 *  headerId: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  noCloseButton: (boolean|null|undefined),
 *  role: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  visible: (boolean|null|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Modal.render';
}

exports.render.params = ["spritemap","body","bodyId","dialogClasses","elementClasses","footer","header","headerId","noCloseButton","role","visible"];
exports.render.types = {"spritemap":"string","body":"html|string","bodyId":"string","dialogClasses":"string","elementClasses":"string","footer":"html|string","header":"html|string","headerId":"string","noCloseButton":"bool","role":"string","visible":"bool"};
templates = exports;
return exports;

});

class liferayfrontendModal extends Component {}
Soy.register(liferayfrontendModal, templates);
export { liferayfrontendModal, templates };
export default templates;
/* jshint ignore:end */
