/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from ListItem.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.ListItem.
 * @public
 */

goog.module('liferay.frontend.ListItem.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  id: (?),
 *  index: number,
 *  item: (?),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var id = opt_data.id;
  /** @type {number} */
  var index = soy.asserts.assertType(goog.isNumber(opt_data.index), 'index', opt_data.index, 'number');
  /** @type {?} */
  var item = opt_data.item;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('li');
      incrementalDom.attr('class', 'listitem list-group-item ' + (elementClasses ? ' ' + elementClasses : '') + ' clearfix');
      incrementalDom.attr('data-index', index);
      if (id) {
        incrementalDom.attr('id', id);
      }
      incrementalDom.attr('role', 'listitem');
  incrementalDom.elementOpenEnd();
    if (item.avatar) {
      if (item.avatar.link) {
          incrementalDom.text(' ');
        incrementalDom.elementOpenStart('a');
            incrementalDom.attr('href', item.avatar.link);
            incrementalDom.attr('class', 'avatar-link');
        incrementalDom.elementOpenEnd();
          incrementalDom.text(' ');
        }
        incrementalDom.elementOpenStart('span');
            incrementalDom.attr('class', 'list-image pull-left ' + item.avatar.class);
        incrementalDom.elementOpenEnd();
          soyIdom.print(item.avatar.content);
        incrementalDom.elementClose('span');
      if (item.avatar.link) {
        incrementalDom.text(' ');
        incrementalDom.elementClose('a');
        incrementalDom.text(' ');
      }
    }
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'list-main-content pull-left');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'list-text-primary');
      incrementalDom.elementOpenEnd();
        soyIdom.print(item.textPrimary);
      incrementalDom.elementClose('div');
      if (item.textSecondary) {
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'list-text-secondary');
        incrementalDom.elementOpenEnd();
          soyIdom.print(item.textSecondary);
        incrementalDom.elementClose('div');
      }
    incrementalDom.elementClose('div');
    if (item.icons) {
      var icon261List = item.icons;
      var icon261ListLen = icon261List.length;
      for (var icon261Index = 0; icon261Index < icon261ListLen; icon261Index++) {
          var icon261Data = icon261List[icon261Index];
          incrementalDom.elementOpenStart('span');
              incrementalDom.attr('class', 'btn-icon ' + icon261Data + ' pull-right');
          incrementalDom.elementOpenEnd();
          incrementalDom.elementClose('span');
        }
    }
    if (item.iconsHtml) {
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'pull-right');
      incrementalDom.elementOpenEnd();
        var iconHtml271List = item.iconsHtml;
        var iconHtml271ListLen = iconHtml271List.length;
        for (var iconHtml271Index = 0; iconHtml271Index < iconHtml271ListLen; iconHtml271Index++) {
            var iconHtml271Data = iconHtml271List[iconHtml271Index];
            soyIdom.print(iconHtml271Data);
          }
      incrementalDom.elementClose('div');
    }
    if (item.label) {
      incrementalDom.elementOpenStart('span');
          incrementalDom.attr('class', 'label list-label pull-right ' + item.label.class);
      incrementalDom.elementOpenEnd();
        soyIdom.print(item.label.content);
      incrementalDom.elementClose('span');
    }
  incrementalDom.elementClose('li');
}
exports.render = $render;
/**
 * @typedef {{
 *  id: (?),
 *  index: number,
 *  item: (?),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.ListItem.render';
}

exports.render.params = ["id","index","item","elementClasses"];
exports.render.types = {"id":"?","index":"int","item":"?","elementClasses":"string"};
templates = exports;
return exports;

});

class liferayfrontendListItem extends Component {}
Soy.register(liferayfrontendListItem, templates);
export { liferayfrontendListItem, templates };
export default templates;
/* jshint ignore:end */
