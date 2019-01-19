/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from List.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.List.
 * @public
 */

goog.module('liferay.frontend.List.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('liferay.frontend.ListItem.incrementaldom', 'render');


/**
 * @param {{
 *  id: (null|number|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  items: (?),
 *  itemsHtml: (function()|null|undefined)
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
  var id = soy.asserts.assertType(opt_data.id == null || goog.isNumber(opt_data.id), 'id', opt_data.id, 'null|number|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {?} */
  var items = opt_data.items;
  /** @type {function()|null|undefined} */
  var itemsHtml = soy.asserts.assertType(opt_data.itemsHtml == null || goog.isFunction(opt_data.itemsHtml), 'itemsHtml', opt_data.itemsHtml, 'function()|null|undefined');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'list' + (elementClasses ? ' ' + elementClasses : ''));
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('ul');
        incrementalDom.attr('class', 'list-group');
        incrementalDom.attr('data-onclick', 'handleClick');
        if (id) {
          incrementalDom.attr('id', id);
        }
        incrementalDom.attr('role', 'list');
    incrementalDom.elementOpenEnd();
      if ((itemsHtml != null)) {
        itemsHtml();
      } else {
        var item180List = items;
        var item180ListLen = item180List.length;
        for (var item180Index = 0; item180Index < item180ListLen; item180Index++) {
            var item180Data = item180List[item180Index];
            $templateAlias1({id: id + '-item-' + (item180Index + 1), index: item180Index, item: item180Data, key: '-items-' + item180Index}, null, opt_ijData);
          }
      }
    incrementalDom.elementClose('ul');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  id: (null|number|undefined),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  items: (?),
 *  itemsHtml: (function()|null|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.List.render';
}

exports.render.params = ["id","elementClasses","items","itemsHtml"];
exports.render.types = {"id":"int","elementClasses":"string","items":"?","itemsHtml":"html"};
templates = exports;
return exports;

});

class liferayfrontendList extends Component {}
Soy.register(liferayfrontendList, templates);
export { liferayfrontendList, templates };
export default templates;
/* jshint ignore:end */
