AUI.add(
	'liferay-ddm-form-renderer-field',
	function(A) {
		var AArray = A.Array;
		var AObject = A.Object;
		var Lang = A.Lang;
		var Renderer = Liferay.DDM.Renderer;

		var FieldTypes = Renderer.FieldTypes;
		var Util = Renderer.Util;

		var TPL_DIV = '<div></div>';

		var TPL_FORM_FIELD_CONTAINER = '<div class="clearfix {hide} lfr-ddm-form-field-container"></div>';

		var Field = A.Component.create(
			{
				ATTRS: {
<<<<<<< HEAD
					autoFocus: {
						value: false
					},

=======
>>>>>>> compatible
					container: {
						setter: A.one,
						valueFn: '_valueContainer'
					},

					dataType: {
<<<<<<< HEAD
						value: 'string'
					},

					fieldName: {
						state: true,
						value: ''
					},

=======
						getter: '_getDataType',
						value: 'string'
					},

					fieldNamespace: {
						value: ''
					},

					indexType: {
						value: 'keyword'
					},

>>>>>>> compatible
					instanceId: {
						valueFn: '_valueInstanceId'
					},

					label: {
<<<<<<< HEAD
						state: true,
=======
						getter: '_getLabel',
>>>>>>> compatible
						value: ''
					},

					locale: {
<<<<<<< HEAD
						value: themeDisplay.getLanguageId()
					},

					name: {
						state: true,
=======
						value: themeDisplay.getDefaultLanguageId()
					},

					localizable: {
						setter: A.DataType.Boolean.parse,
						value: true
					},

					name: {
>>>>>>> compatible
						value: ''
					},

					parent: {
						setter: '_setParent'
					},

					portletNamespace: {
						value: ''
					},

					predefinedValue: {
<<<<<<< HEAD
						value: ''
					},

					readOnly: {
						state: true,
						value: false
					},

					rendered: {
=======
						valueFn: '_getDefaultValue'
					},

					readOnly: {
						getter: '_getReadOnly',
						value: false
					},

					required: {
						setter: A.DataType.Boolean.parse,
>>>>>>> compatible
						value: false
					},

					showLabel: {
<<<<<<< HEAD
						state: true,
						value: true
					},

					startedFilling: {
						value: false
=======
						setter: A.DataType.Boolean.parse,
						value: true
					},

					tip: {
						value: ''
>>>>>>> compatible
					},

					type: {
						value: ''
					},

<<<<<<< HEAD
					validation: {
						value: {
							errorMessage: '',
							expression: '',
							type: ''
						}
					},

					value: {
						state: false,
						value: ''
					},

					visible: {
						state: true,
						value: true
=======
					value: {
						valueFn: '_getDefaultValue'
>>>>>>> compatible
					}
				},

				AUGMENTS: [
<<<<<<< HEAD
					Renderer.FieldContextSupport,
					Renderer.FieldEvaluationSupport,
=======
>>>>>>> compatible
					Renderer.FieldEventsSupport,
					Renderer.FieldFeedbackSupport,
					Renderer.FieldRepetitionSupport,
					Renderer.FieldValidationSupport,
<<<<<<< HEAD
=======
					Renderer.FieldVisibilitySupport,
>>>>>>> compatible
					Renderer.NestedFieldsSupport
				],

				EXTENDS: A.Base,

				NAME: 'liferay-ddm-form-renderer-field',

				prototype: {
<<<<<<< HEAD
=======
					initializer: function() {
						var instance = this;

						instance._eventHandlers = [
							instance.after('localizableChange', instance._afterLocalizableChange),
							instance.after('valueChange', instance._afterValueChange)
						];
					},

>>>>>>> compatible
					destructor: function() {
						var instance = this;

						var container = instance.get('container');

						if (container && container.inDoc()) {
							container.remove(true);
						}

						var parent = instance.get('parent');

						if (parent) {
							parent.removeChild(instance);
						}

						(new A.EventHandle(instance._eventHandlers)).detach();
<<<<<<< HEAD

						instance.set('rendered', false);
=======
>>>>>>> compatible
					},

					fetchContainer: function() {
						var instance = this;

						var instanceId = instance.get('instanceId');

						var container = instance._getContainerByInstanceId(instanceId);

						if (!container) {
<<<<<<< HEAD
							var name = instance.get('fieldName');
=======
							var name = instance.get('name');
>>>>>>> compatible

							var repeatedIndex = instance.get('repeatedIndex');

							container = instance._getContainerByNameAndIndex(name, repeatedIndex);
						}

						return container;
					},

					focus: function() {
						var instance = this;

<<<<<<< HEAD
						instance.scrollIntoView();
=======
						instance.get('container').scrollIntoView();
>>>>>>> compatible

						instance.getInputNode().focus();
					},

					getChildElementsHTML: function() {
						var instance = this;

						return instance.get('fields').map(
							function(field) {
								var fragment = A.Node.create(TPL_DIV);

								var container = field._createContainer();

								container.html(field.getTemplate());

								container.appendTo(fragment);

								return fragment.html();
							}
						).join('');
					},

<<<<<<< HEAD
=======
					getContextValue: function() {
						var instance = this;

						var predefinedValue = instance.get('predefinedValue');

						var value = instance.getLocalizedValue(instance.get('value'));

						if (!value && predefinedValue && !instance.get('readOnly')) {
							value = instance.getLocalizedValue(predefinedValue);
						}

						return value;
					},

>>>>>>> compatible
					getInputNode: function() {
						var instance = this;

						return instance.get('container').one(instance.getInputSelector());
					},

					getInputSelector: function() {
						var instance = this;

						var qualifiedName = instance.getQualifiedName().replace(/\$/ig, '\\$');

						return '[name="' + qualifiedName + '"]';
					},

<<<<<<< HEAD
=======
					getLabel: function() {
						var instance = this;

						var label = instance.get('label');
						var locale = instance.get('locale');

						if (Lang.isObject(label) && locale in label) {
							label = label[locale];
						}

						return label || instance.get('name');
					},

					getLabelNode: function() {
						var instance = this;

						return instance.get('container').one('label');
					},

					getLocalizedValue: function(localizedValue) {
						var instance = this;

						if (Lang.isObject(localizedValue) && !Array.isArray(localizedValue)) {
							localizedValue = localizedValue[instance.get('locale')];
						}

						return localizedValue;
					},

>>>>>>> compatible
					getQualifiedName: function() {
						var instance = this;

						return [
							instance.get('portletNamespace'),
							'ddm$$',
<<<<<<< HEAD
							instance.get('fieldName'),
=======
							instance.get('name'),
>>>>>>> compatible
							'$',
							instance.get('instanceId'),
							'$',
							instance.get('repeatedIndex'),
							'$$',
							instance.get('locale')
						].join('');
					},

<<<<<<< HEAD
					getTemplate: function() {
						var instance = this;

						var renderer = instance.getTemplateRenderer();

						var container = document.createDocumentFragment();

						new renderer(instance.getTemplateContext(), container);

						return container.firstChild.outerHTML;
=======
					getSerializedValue: function() {
						var instance = this;

						var serializedValue;

						if (instance.get('localizable')) {
							serializedValue = {};

							serializedValue[instance.get('locale')] = instance.getValue();
						}
						else {
							serializedValue = instance.getValue();
						}

						return serializedValue;
					},

					getTemplate: function() {
						var instance = this;

						var renderer = instance.getTemplateRenderer();

						return renderer(instance.getTemplateContext());
>>>>>>> compatible
					},

					getTemplateContext: function() {
						var instance = this;

<<<<<<< HEAD
						return A.merge(
							instance.get('context'),
							{
								name: instance.getQualifiedName(),
								value: instance.get('value')
=======
						var context = {};

						var fieldType = FieldTypes.get(instance.get('type'));

						A.each(
							fieldType.get('settings').fields,
							function(item, index) {
								context[item.name] = instance.get(item.name);
							}
						);

						return A.merge(
							context,
							{
								childElementsHTML: instance.getChildElementsHTML(),
								label: instance.getLabel(),
								name: instance.getQualifiedName(),
								readOnly: instance.get('readOnly'),
								tip: instance.getLocalizedValue(instance.get('tip')),
								value: instance.getContextValue() || ''
>>>>>>> compatible
							}
						);
					},

					getTemplateRenderer: function() {
						var instance = this;

						var type = instance.get('type');

						var fieldType = FieldTypes.get(type);

						if (!fieldType) {
							throw new Error('Unknown field type "' + type + '".');
						}

						var templateNamespace = fieldType.get('templateNamespace');

						return AObject.getValue(window, templateNamespace.split('.'));
					},

					getValue: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						return Lang.String.unescapeHTML(inputNode.val());
					},

<<<<<<< HEAD
					hasFocus: function() {
						var instance = this;

						var container = instance.get('container');

						return container.contains(document.activeElement);
					},

=======
>>>>>>> compatible
					render: function(target) {
						var instance = this;

						var container = instance.get('container');

						var parent = instance.get('parent');

						if (target && !parent) {
							container.appendTo(target);
						}

<<<<<<< HEAD
						container.setContent(instance.getTemplate());
=======
						container.html(instance.getTemplate());
>>>>>>> compatible

						instance.eachField(
							function(field) {
								field.updateContainer();
							}
						);

						instance.fire('render');

<<<<<<< HEAD
						instance.set('rendered', true);

						return instance;
					},

					scrollIntoView: function() {
						var instance = this;

						instance.get('container').scrollIntoView(false);
					},

=======
						return instance;
					},

>>>>>>> compatible
					setValue: function(value) {
						var instance = this;

						instance.getInputNode().val(value);
					},

					toJSON: function() {
						var instance = this;

<<<<<<< HEAD
						var context = instance.get('context');

						context.value = instance.getValue();
						context.localizedValue = instance.get('context.localizedValue');
						context.nestedFields = AArray.invoke(instance.getImmediateFields(), 'toJSON');

						return context;
=======
						var fieldJSON = {
							instanceId: instance.get('instanceId'),
							name: instance.get('name'),
							value: instance.getSerializedValue()
						};

						var fields = instance.getImmediateFields();

						if (fields.length > 0) {
							fieldJSON.nestedFieldValues = AArray.invoke(fields, 'toJSON');
						}

						return fieldJSON;
>>>>>>> compatible
					},

					updateContainer: function() {
						var instance = this;

						instance.set('container', instance._valueContainer());
					},

<<<<<<< HEAD
=======
					_afterLocalizableChange: function() {
						var instance = this;

						instance.set('value', instance._getDefaultValue());
					},

					_afterValueChange: function() {
						var instance = this;

						instance.setValue(instance.getContextValue());
					},

>>>>>>> compatible
					_createContainer: function() {
						var instance = this;

						var visible = instance.get('visible');

<<<<<<< HEAD
						return A.Node.create(
=======
						var container = A.Node.create(
>>>>>>> compatible
							Lang.sub(
								TPL_FORM_FIELD_CONTAINER,
								{
									hide: visible ? '' : 'hide'
								}
							)
						);
<<<<<<< HEAD
=======

						container.html(instance.getTemplate());

						return container;
>>>>>>> compatible
					},

					_getContainerByInstanceId: function(instanceId) {
						var instance = this;

						var container;

						var root = instance.getRoot();

						if (root) {
							container = root.filterNodes(
								function(qualifiedName) {
									var nodeInstanceId = Util.getInstanceIdFromQualifiedName(qualifiedName);

									return instanceId === nodeInstanceId;
								}
							).item(0);
						}

						return container;
					},

					_getContainerByNameAndIndex: function(name, repeatedIndex) {
						var instance = this;

						var container;

						var root = instance.getRoot();

						if (root) {
							container = instance.getRoot().filterNodes(
								function(qualifiedName) {
									var nodeFieldName = Util.getFieldNameFromQualifiedName(qualifiedName);

									return name === nodeFieldName;
								}
							).item(repeatedIndex);
						}

						return container;
					},

<<<<<<< HEAD
					_setParent: function(val) {
						var instance = this;

=======
					_getDataType: function(dataType) {
						var instance = this;

						var validation = instance.get('validation');

						if (validation) {
							dataType = Util.getDataTypeFromValidation(dataType, validation);
						}

						return dataType;
					},

					_getDefaultValue: function() {
						var instance = this;

						var value = '';

						if (instance.get('localizable')) {
							value = instance.get('predefinedValue');

							if (!Lang.isObject(value)) {
								value = {};

								value[instance.get('locale')] = '';
							}
						}

						return value;
					},

					_getReadOnly: function(readOnly) {
						var instance = this;

						var form = instance.getRoot();

						if (form && !readOnly) {
							var readOnlyFields = form.get('readOnlyFields');

							var name = instance.get('name');

							readOnly = readOnlyFields.indexOf(name) > -1;
						}

						return readOnly;
					},

					_setParent: function(val) {
						var instance = this;

						var fields = val.get('fields');

						var name = instance.get('name');

						if (fields && !val.getField(name)) {
							fields.push(instance);
						}

>>>>>>> compatible
						instance.addTarget(val);
					},

					_valueContainer: function() {
						var instance = this;

						var container = instance.fetchContainer();

						if (!container) {
							container = instance._createContainer();
						}

						return container;
					},

					_valueInstanceId: function() {
						var instance = this;

<<<<<<< HEAD
						var instanceId;

						var name = instance.get('name');

						if (name) {
							instanceId = Util.getInstanceIdFromQualifiedName(name);
						}
						else {
							instanceId = Util.generateInstanceId(8);
						}

						return instanceId;
=======
						return Util.generateInstanceId(8);
>>>>>>> compatible
					}
				}
			}
		);

		Liferay.namespace('DDM.Renderer').Field = Field;
	},
	'',
	{
<<<<<<< HEAD
		requires: ['aui-datatype', 'aui-node', 'liferay-ddm-form-renderer', 'liferay-ddm-form-renderer-field-context-support', 'liferay-ddm-form-renderer-field-evaluation', 'liferay-ddm-form-renderer-field-events', 'liferay-ddm-form-renderer-field-feedback', 'liferay-ddm-form-renderer-field-repetition', 'liferay-ddm-form-renderer-field-validation', 'liferay-ddm-form-renderer-nested-fields', 'liferay-ddm-form-renderer-types', 'liferay-ddm-form-renderer-util']
=======
		requires: ['aui-datatype', 'aui-node', 'liferay-ddm-form-renderer', 'liferay-ddm-form-renderer-field-events', 'liferay-ddm-form-renderer-field-feedback', 'liferay-ddm-form-renderer-field-repetition', 'liferay-ddm-form-renderer-field-validation', 'liferay-ddm-form-renderer-field-visibility', 'liferay-ddm-form-renderer-nested-fields', 'liferay-ddm-form-renderer-types', 'liferay-ddm-form-renderer-util']
>>>>>>> compatible
	}
);