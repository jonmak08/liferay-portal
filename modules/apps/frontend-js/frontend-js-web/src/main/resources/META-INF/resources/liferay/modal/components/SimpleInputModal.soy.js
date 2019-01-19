/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from SimpleInputModal.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace liferay.frontend.SimpleInputModal.
 * @public
 */

goog.module('liferay.frontend.SimpleInputModal.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
goog.require('goog.string');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('liferay.frontend.Modal.incrementaldom', 'render');


/**
 * @param {{
 *  dialogTitle: (!goog.soy.data.SanitizedContent|string),
 *  fixFormData: boolean,
 *  formSubmitURL: (!goog.soy.data.SanitizedContent|string),
 *  mainFieldLabel: function(),
 *  mainFieldName: (!goog.soy.data.SanitizedContent|string),
 *  namespace: (!goog.soy.data.SanitizedContent|string),
 *  spritemap: (!goog.soy.data.SanitizedContent|string),
 *  _errorMessage: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  _handleCancelButtonClick: (*|null|undefined),
 *  _handleFormSubmit: (*|null|undefined),
 *  _handleModalVisibleChanged: (*|null|undefined),
 *  _loadingResponse: (boolean|null|undefined),
 *  checkboxFieldLabel: (function()|null|undefined),
 *  checkboxFieldName: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  checkboxFieldValue: (boolean|null|undefined),
 *  id: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  idFieldName: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  idFieldValue: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  mainFieldPlaceholder: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  mainFieldValue: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  var $$temp;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var dialogTitle = soy.asserts.assertType(goog.isString(opt_data.dialogTitle) || opt_data.dialogTitle instanceof goog.soy.data.SanitizedContent, 'dialogTitle', opt_data.dialogTitle, '!goog.soy.data.SanitizedContent|string');
  /** @type {boolean} */
  var fixFormData = soy.asserts.assertType(goog.isBoolean(opt_data.fixFormData) || opt_data.fixFormData === 1 || opt_data.fixFormData === 0, 'fixFormData', opt_data.fixFormData, 'boolean');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var formSubmitURL = soy.asserts.assertType(goog.isString(opt_data.formSubmitURL) || opt_data.formSubmitURL instanceof goog.soy.data.SanitizedContent, 'formSubmitURL', opt_data.formSubmitURL, '!goog.soy.data.SanitizedContent|string');
  /** @type {function()} */
  var mainFieldLabel = soy.asserts.assertType(goog.isFunction(opt_data.mainFieldLabel), 'mainFieldLabel', opt_data.mainFieldLabel, 'function()');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var mainFieldName = soy.asserts.assertType(goog.isString(opt_data.mainFieldName) || opt_data.mainFieldName instanceof goog.soy.data.SanitizedContent, 'mainFieldName', opt_data.mainFieldName, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var namespace = soy.asserts.assertType(goog.isString(opt_data.namespace) || opt_data.namespace instanceof goog.soy.data.SanitizedContent, 'namespace', opt_data.namespace, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var spritemap = soy.asserts.assertType(goog.isString(opt_data.spritemap) || opt_data.spritemap instanceof goog.soy.data.SanitizedContent, 'spritemap', opt_data.spritemap, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var _errorMessage = soy.asserts.assertType(opt_data._errorMessage == null || (goog.isString(opt_data._errorMessage) || opt_data._errorMessage instanceof goog.soy.data.SanitizedContent), '_errorMessage', opt_data._errorMessage, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {*|null|undefined} */
  var _handleCancelButtonClick = opt_data._handleCancelButtonClick;
  /** @type {*|null|undefined} */
  var _handleFormSubmit = opt_data._handleFormSubmit;
  /** @type {*|null|undefined} */
  var _handleModalVisibleChanged = opt_data._handleModalVisibleChanged;
  /** @type {boolean|null|undefined} */
  var _loadingResponse = soy.asserts.assertType(opt_data._loadingResponse == null || (goog.isBoolean(opt_data._loadingResponse) || opt_data._loadingResponse === 1 || opt_data._loadingResponse === 0), '_loadingResponse', opt_data._loadingResponse, 'boolean|null|undefined');
  /** @type {function()|null|undefined} */
  var checkboxFieldLabel = soy.asserts.assertType(opt_data.checkboxFieldLabel == null || goog.isFunction(opt_data.checkboxFieldLabel), 'checkboxFieldLabel', opt_data.checkboxFieldLabel, 'function()|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var checkboxFieldName = soy.asserts.assertType(opt_data.checkboxFieldName == null || (goog.isString(opt_data.checkboxFieldName) || opt_data.checkboxFieldName instanceof goog.soy.data.SanitizedContent), 'checkboxFieldName', opt_data.checkboxFieldName, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var checkboxFieldValue = soy.asserts.assertType(opt_data.checkboxFieldValue == null || (goog.isBoolean(opt_data.checkboxFieldValue) || opt_data.checkboxFieldValue === 1 || opt_data.checkboxFieldValue === 0), 'checkboxFieldValue', opt_data.checkboxFieldValue, 'boolean|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var id = soy.asserts.assertType(opt_data.id == null || (goog.isString(opt_data.id) || opt_data.id instanceof goog.soy.data.SanitizedContent), 'id', opt_data.id, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var idFieldName = soy.asserts.assertType(opt_data.idFieldName == null || (goog.isString(opt_data.idFieldName) || opt_data.idFieldName instanceof goog.soy.data.SanitizedContent), 'idFieldName', opt_data.idFieldName, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var idFieldValue = soy.asserts.assertType(opt_data.idFieldValue == null || (goog.isString(opt_data.idFieldValue) || opt_data.idFieldValue instanceof goog.soy.data.SanitizedContent), 'idFieldValue', opt_data.idFieldValue, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var mainFieldPlaceholder = soy.asserts.assertType(opt_data.mainFieldPlaceholder == null || (goog.isString(opt_data.mainFieldPlaceholder) || opt_data.mainFieldPlaceholder instanceof goog.soy.data.SanitizedContent), 'mainFieldPlaceholder', opt_data.mainFieldPlaceholder, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var mainFieldValue = soy.asserts.assertType(opt_data.mainFieldValue == null || (goog.isString(opt_data.mainFieldValue) || opt_data.mainFieldValue instanceof goog.soy.data.SanitizedContent), 'mainFieldValue', opt_data.mainFieldValue, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('id', ($$temp = id) == null ? '' : $$temp);
  incrementalDom.elementOpenEnd();
    var param992 = function() {
      incrementalDom.elementOpenStart('form');
          incrementalDom.attr('action', formSubmitURL);
          incrementalDom.attr('data-onsubmit', _handleFormSubmit);
          incrementalDom.attr('method', 'post');
          incrementalDom.attr('ref', 'form');
      incrementalDom.elementOpenEnd();
        if (idFieldName) {
          incrementalDom.elementOpenStart('input');
              incrementalDom.attr('name', namespace + idFieldName);
              incrementalDom.attr('type', 'hidden');
              incrementalDom.attr('value', ($$temp = idFieldValue) == null ? '' : $$temp);
          incrementalDom.elementOpenEnd();
          incrementalDom.elementClose('input');
        }
        var mainFieldClasses__soy1007 = '';
        mainFieldClasses__soy1007 += 'form-group';
        mainFieldClasses__soy1007 += _errorMessage ? ' has-error' : '';
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', mainFieldClasses__soy1007);
        incrementalDom.elementOpenEnd();
          incrementalDom.elementOpen('label');
            mainFieldLabel();
            incrementalDom.elementOpenStart('span');
                incrementalDom.attr('class', 'reference-mark');
            incrementalDom.elementOpenEnd();
              incrementalDom.elementOpenStart('svg');
                  incrementalDom.attr('class', 'lexicon-icon');
              incrementalDom.elementOpenEnd();
                incrementalDom.elementOpenStart('use');
                    incrementalDom.attr('xlink:href', spritemap + '#asterisk');
                incrementalDom.elementOpenEnd();
                incrementalDom.elementClose('use');
              incrementalDom.elementClose('svg');
            incrementalDom.elementClose('span');
          incrementalDom.elementClose('label');
          var mainFieldAttributes__soy1020 = function() {
            if (_loadingResponse) {
              incrementalDom.attr('disabled', '');
            }
            incrementalDom.attr('class', 'form-control');
            incrementalDom.attr('name', namespace + mainFieldName);
            incrementalDom.attr('placeholder', ($$temp = mainFieldPlaceholder) == null ? '' : $$temp);
            incrementalDom.attr('ref', 'mainField');
            incrementalDom.attr('type', 'text');
            incrementalDom.attr('value', ($$temp = mainFieldValue) == null ? '' : $$temp);
          };
          incrementalDom.elementOpenStart('input');
              mainFieldAttributes__soy1020();
          incrementalDom.elementOpenEnd();
          incrementalDom.elementClose('input');
          if (_errorMessage) {
            incrementalDom.elementOpenStart('form');
                incrementalDom.attr('class', 'form-feedback-item');
            incrementalDom.elementOpenEnd();
              incrementalDom.elementOpenStart('span');
                  incrementalDom.attr('class', 'form-feedback-indicator');
              incrementalDom.elementOpenEnd();
                incrementalDom.elementOpenStart('svg');
                    incrementalDom.attr('class', 'lexicon-icon');
                incrementalDom.elementOpenEnd();
                  incrementalDom.elementOpenStart('use');
                      incrementalDom.attr('xlink:href', spritemap + '#exclamation-full');
                  incrementalDom.elementOpenEnd();
                  incrementalDom.elementClose('use');
                incrementalDom.elementClose('svg');
              incrementalDom.elementClose('span');
              soyIdom.print(_errorMessage);
            incrementalDom.elementClose('form');
          }
        incrementalDom.elementClose('div');
        if (checkboxFieldName && checkboxFieldLabel) {
          incrementalDom.elementOpenStart('div');
              incrementalDom.attr('class', 'form-check');
          incrementalDom.elementOpenEnd();
            incrementalDom.elementOpenStart('label');
                incrementalDom.attr('class', 'form-check-label');
            incrementalDom.elementOpenEnd();
              var checkboxFieldAttributes__soy1047 = function() {
                if (checkboxFieldValue) {
                  incrementalDom.attr('checked', '');
                }
                if (_loadingResponse) {
                  incrementalDom.attr('disabled', '');
                }
                incrementalDom.attr('class', 'form-check-input');
                incrementalDom.attr('name', namespace + checkboxFieldName);
                incrementalDom.attr('type', 'checkbox');
              };
              incrementalDom.elementOpenStart('input');
                  checkboxFieldAttributes__soy1047();
              incrementalDom.elementOpenEnd();
              incrementalDom.elementClose('input');
              incrementalDom.elementOpenStart('span');
                  incrementalDom.attr('class', 'form-check-label-text');
              incrementalDom.elementOpenEnd();
                checkboxFieldLabel();
              incrementalDom.elementClose('span');
            incrementalDom.elementClose('label');
          incrementalDom.elementClose('div');
        }
        if (fixFormData) {
          incrementalDom.elementOpenStart('input');
              incrementalDom.attr('type', 'hidden');
              incrementalDom.attr('name', '__ie11_formdata_fix__');
          incrementalDom.elementOpenEnd();
          incrementalDom.elementClose('input');
        }
      incrementalDom.elementClose('form');
    };
    var param1072 = function() {
      var cancelButtonAttributes__soy1074 = function() {
        if (_loadingResponse) {
          incrementalDom.attr('disabled', '');
        }
        incrementalDom.attr('class', 'btn btn-secondary');
        incrementalDom.attr('data-onclick', _handleCancelButtonClick);
        incrementalDom.attr('type', 'button');
      };
      incrementalDom.elementOpenStart('button');
          cancelButtonAttributes__soy1074();
      incrementalDom.elementOpenEnd();
        /** @desc  */
        var MSG_EXTERNAL_503445309622982213 = goog.getMsg('cancel');
        incrementalDom.text(goog.string.unescapeEntities(MSG_EXTERNAL_503445309622982213));
      incrementalDom.elementClose('button');
      var submitButtonAttributes__soy1089 = function() {
        if (_loadingResponse) {
          incrementalDom.attr('disabled', '');
        }
        incrementalDom.attr('class', 'btn btn-primary');
        incrementalDom.attr('data-onclick', _handleFormSubmit);
      };
      incrementalDom.elementOpenStart('button');
          submitButtonAttributes__soy1089();
      incrementalDom.elementOpenEnd();
        if (_loadingResponse) {
          incrementalDom.elementOpenStart('span');
              incrementalDom.attr('class', 'inline-item inline-item-before');
          incrementalDom.elementOpenEnd();
            incrementalDom.elementOpenStart('span');
                incrementalDom.attr('aria-hidden', 'true');
                incrementalDom.attr('class', 'loading-animation');
            incrementalDom.elementOpenEnd();
            incrementalDom.elementClose('span');
          incrementalDom.elementClose('span');
        }
        /** @desc  */
        var MSG_EXTERNAL_8640257554156088424 = goog.getMsg('save');
        incrementalDom.text(goog.string.unescapeEntities(MSG_EXTERNAL_8640257554156088424));
      incrementalDom.elementClose('button');
    };
    var param1108 = function() {
      soyIdom.print(dialogTitle);
    };
    $templateAlias1({body: param992, dialogClasses: 'modal-dialog-sm', events: {visibleChanged: _handleModalVisibleChanged}, footer: param1072, header: param1108, ref: 'modal', spritemap: spritemap}, null, opt_ijData);
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  dialogTitle: (!goog.soy.data.SanitizedContent|string),
 *  fixFormData: boolean,
 *  formSubmitURL: (!goog.soy.data.SanitizedContent|string),
 *  mainFieldLabel: function(),
 *  mainFieldName: (!goog.soy.data.SanitizedContent|string),
 *  namespace: (!goog.soy.data.SanitizedContent|string),
 *  spritemap: (!goog.soy.data.SanitizedContent|string),
 *  _errorMessage: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  _handleCancelButtonClick: (*|null|undefined),
 *  _handleFormSubmit: (*|null|undefined),
 *  _handleModalVisibleChanged: (*|null|undefined),
 *  _loadingResponse: (boolean|null|undefined),
 *  checkboxFieldLabel: (function()|null|undefined),
 *  checkboxFieldName: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  checkboxFieldValue: (boolean|null|undefined),
 *  id: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  idFieldName: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  idFieldValue: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  mainFieldPlaceholder: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  mainFieldValue: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'liferay.frontend.SimpleInputModal.render';
}

exports.render.params = ["dialogTitle","fixFormData","formSubmitURL","mainFieldLabel","mainFieldName","namespace","spritemap","_errorMessage","_handleCancelButtonClick","_handleFormSubmit","_handleModalVisibleChanged","_loadingResponse","checkboxFieldLabel","checkboxFieldName","checkboxFieldValue","id","idFieldName","idFieldValue","mainFieldPlaceholder","mainFieldValue"];
exports.render.types = {"dialogTitle":"string","fixFormData":"bool","formSubmitURL":"string","mainFieldLabel":"html","mainFieldName":"string","namespace":"string","spritemap":"string","_errorMessage":"string","_handleCancelButtonClick":"any","_handleFormSubmit":"any","_handleModalVisibleChanged":"any","_loadingResponse":"bool","checkboxFieldLabel":"html","checkboxFieldName":"string","checkboxFieldValue":"bool","id":"string","idFieldName":"string","idFieldValue":"string","mainFieldPlaceholder":"string","mainFieldValue":"string"};
templates = exports;
return exports;

});

class liferayfrontendSimpleInputModal extends Component {}
Soy.register(liferayfrontendSimpleInputModal, templates);
export { liferayfrontendSimpleInputModal, templates };
export default templates;
/* jshint ignore:end */
