/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Dropdown.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Dropdown.
 * @public
 */

goog.module('liferay.frontend.Dropdown.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  alignedPosition: (null|number|undefined),
 *  body: (function()|null|undefined),
 *  classMap: (?),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  expanded: (boolean|null|undefined),
 *  header: (function()|null|undefined),
 *  position: (null|number|undefined),
 *  positionClassOnMenu: (?)
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
  /** @type {function()|null|undefined} */
  var body = soy.asserts.assertType(opt_data.body == null || goog.isFunction(opt_data.body), 'body', opt_data.body, 'function()|null|undefined');
  /** @type {?} */
  var classMap = opt_data.classMap;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var expanded = soy.asserts.assertType(opt_data.expanded == null || (goog.isBoolean(opt_data.expanded) || opt_data.expanded === 1 || opt_data.expanded === 0), 'expanded', opt_data.expanded, 'boolean|null|undefined');
  /** @type {function()|null|undefined} */
  var header = soy.asserts.assertType(opt_data.header == null || goog.isFunction(opt_data.header), 'header', opt_data.header, 'function()|null|undefined');
  /** @type {null|number|undefined} */
  var position = soy.asserts.assertType(opt_data.position == null || goog.isNumber(opt_data.position), 'position', opt_data.position, 'null|number|undefined');
  /** @type {?} */
  var positionClassOnMenu = opt_data.positionClassOnMenu;
  var classes__soy111 = classMap ? classMap : ['dropup', 'dropup', 'dropright', 'dropdown', 'dropdown', 'dropdown', 'dropleft', 'dropup'];
  var currentPosition__soy113 = (alignedPosition != null) ? alignedPosition : position;
  var positionClass__soy115 = (currentPosition__soy113 != null) ? classes__soy111[currentPosition__soy113] : 'dropdown';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', (positionClassOnMenu ? 'dropdown' : positionClass__soy115) + (elementClasses ? ' ' + elementClasses : '') + (expanded ? ' open' : ''));
  incrementalDom.elementOpenEnd();
    if (header) {
      header();
    }
    incrementalDom.elementOpenStart('ul');
        incrementalDom.attr('class', 'dropdown-menu' + (positionClassOnMenu ? ' ' + positionClass__soy115 : ''));
    incrementalDom.elementOpenEnd();
      if (body) {
        body();
      }
    incrementalDom.elementClose('ul');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  alignedPosition: (null|number|undefined),
 *  body: (function()|null|undefined),
 *  classMap: (?),
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  expanded: (boolean|null|undefined),
 *  header: (function()|null|undefined),
 *  position: (null|number|undefined),
 *  positionClassOnMenu: (?)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Dropdown.render';
}

exports.render.params = ["alignedPosition","body","classMap","elementClasses","expanded","header","position","positionClassOnMenu"];
exports.render.types = {"alignedPosition":"int","body":"html","classMap":"?","elementClasses":"string","expanded":"bool","header":"html","position":"int","positionClassOnMenu":"?"};
templates = exports;
return exports;

});

class liferayfrontendDropdown extends Component {}
Soy.register(liferayfrontendDropdown, templates);
export { liferayfrontendDropdown, templates };
export default templates;
/* jshint ignore:end */
