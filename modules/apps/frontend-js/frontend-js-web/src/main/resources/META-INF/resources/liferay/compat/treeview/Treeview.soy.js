/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Treeview.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.Treeview.
 * @public
 */

goog.module('liferay.frontend.Treeview.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {{
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  lastFocusedRef_: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  nodes: (?),
 *  parentPath: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var elementClasses = soy.asserts.assertType(opt_data.elementClasses == null || (goog.isString(opt_data.elementClasses) || opt_data.elementClasses instanceof goog.soy.data.SanitizedContent), 'elementClasses', opt_data.elementClasses, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var lastFocusedRef_ = soy.asserts.assertType(opt_data.lastFocusedRef_ == null || (goog.isString(opt_data.lastFocusedRef_) || opt_data.lastFocusedRef_ instanceof goog.soy.data.SanitizedContent), 'lastFocusedRef_', opt_data.lastFocusedRef_, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {?} */
  var nodes = opt_data.nodes;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'treeview' + (elementClasses ? ' ' + elementClasses : ''));
  incrementalDom.elementOpenEnd();
    $nodes(opt_data, null, opt_ijData);
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  elementClasses: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  lastFocusedRef_: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  nodes: (?),
 *  parentPath: (?)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.Treeview.render';
}


/**
 * @param {{
 *  lastFocusedRef_: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  nodes: (?),
 *  parentPath: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $nodes(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var lastFocusedRef_ = soy.asserts.assertType(opt_data.lastFocusedRef_ == null || (goog.isString(opt_data.lastFocusedRef_) || opt_data.lastFocusedRef_ instanceof goog.soy.data.SanitizedContent), 'lastFocusedRef_', opt_data.lastFocusedRef_, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {?} */
  var nodes = opt_data.nodes;
  /** @type {?} */
  var parentPath = opt_data.parentPath;
  if (nodes) {
    incrementalDom.elementOpenStart('ul');
        incrementalDom.attr('class', 'treeview-nodes');
        incrementalDom.attr('role', 'tree');
    incrementalDom.elementOpenEnd();
      var node831List = nodes;
      var node831ListLen = node831List.length;
      for (var node831Index = 0; node831Index < node831ListLen; node831Index++) {
          var node831Data = node831List[node831Index];
          var index__soy824 = node831Index;
          $node({lastFocusedRef_: lastFocusedRef_, node: node831Data, path: (parentPath != null) ? parentPath + '-' + index__soy824 : index__soy824}, null, opt_ijData);
        }
    incrementalDom.elementClose('ul');
  }
}
exports.nodes = $nodes;
/**
 * @typedef {{
 *  lastFocusedRef_: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  nodes: (?),
 *  parentPath: (?)
 * }}
 */
$nodes.Params;
if (goog.DEBUG) {
  $nodes.soyTemplateName = 'liferay.frontend.Treeview.nodes';
}


/**
 * @param {{
 *  lastFocusedRef_: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  node: (?),
 *  path: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $node(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var lastFocusedRef_ = soy.asserts.assertType(opt_data.lastFocusedRef_ == null || (goog.isString(opt_data.lastFocusedRef_) || opt_data.lastFocusedRef_ instanceof goog.soy.data.SanitizedContent), 'lastFocusedRef_', opt_data.lastFocusedRef_, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {?} */
  var node = opt_data.node;
  /** @type {?} */
  var path = opt_data.path;
  var focusRef__soy840 = lastFocusedRef_ ? lastFocusedRef_ : 'node-0';
  var ref__soy842 = 'node-' + path;
  incrementalDom.elementOpenStart('li');
      incrementalDom.attr('class', 'treeview-node');
      incrementalDom.attr('data-treeview-path', path);
      incrementalDom.attr('data-onkeyup', 'handleNodeKeyUp_');
      $ariaExpanded(opt_data, null, opt_ijData);
      incrementalDom.attr('role', 'treeitem');
      incrementalDom.attr('tabindex', focusRef__soy840 == ref__soy842 ? '0' : '-1');
      incrementalDom.attr('ref', ref__soy842);
  incrementalDom.elementOpenEnd();
    if (node) {
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'treeview-node-wrapper' + (node.expanded ? ' expanded' : ''));
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'treeview-node-main clearfix' + (node.children ? ' hasChildren' : ''));
            incrementalDom.attr('data-onclick', 'handleNodeClicked_');
        incrementalDom.elementOpenEnd();
          if (node.children) {
            incrementalDom.elementOpenStart('div');
                incrementalDom.attr('class', 'treeview-node-toggler');
            incrementalDom.elementOpenEnd();
            incrementalDom.elementClose('div');
          }
          incrementalDom.elementOpenStart('span');
              incrementalDom.attr('class', 'treeview-node-name');
          incrementalDom.elementOpenEnd();
            soyIdom.print(node.name);
          incrementalDom.elementClose('span');
        incrementalDom.elementClose('div');
        $nodes({lastFocusedRef_: lastFocusedRef_, nodes: node.children, parentPath: path}, null, opt_ijData);
      incrementalDom.elementClose('div');
    }
  incrementalDom.elementClose('li');
}
exports.node = $node;
/**
 * @typedef {{
 *  lastFocusedRef_: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  node: (?),
 *  path: (?)
 * }}
 */
$node.Params;
if (goog.DEBUG) {
  $node.soyTemplateName = 'liferay.frontend.Treeview.node';
}


/**
 * @param {{
 *  node: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $ariaExpanded(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var node = opt_data.node;
  if (node.children) {
    incrementalDom.attr('aria-expanded', node.expanded ? 'true' : 'false');
  }
}
exports.ariaExpanded = $ariaExpanded;
/**
 * @typedef {{
 *  node: (?)
 * }}
 */
$ariaExpanded.Params;
if (goog.DEBUG) {
  $ariaExpanded.soyTemplateName = 'liferay.frontend.Treeview.ariaExpanded';
}

exports.render.params = ["elementClasses","lastFocusedRef_","nodes"];
exports.render.types = {"elementClasses":"string","lastFocusedRef_":"string","nodes":"?"};
exports.nodes.params = ["lastFocusedRef_","nodes","parentPath"];
exports.nodes.types = {"lastFocusedRef_":"string","nodes":"?","parentPath":"?"};
exports.node.params = ["lastFocusedRef_","node","path"];
exports.node.types = {"lastFocusedRef_":"string","node":"?","path":"?"};
exports.ariaExpanded.params = ["node"];
exports.ariaExpanded.types = {"node":"?"};
templates = exports;
return exports;

});

class liferayfrontendTreeview extends Component {}
Soy.register(liferayfrontendTreeview, templates);
export { liferayfrontendTreeview, templates };
export default templates;
/* jshint ignore:end */
