AUI.add(
	'liferay-ddm-form-field-checkbox',
	function(A) {
		var DataTypeBoolean = A.DataType.Boolean;

		var CheckboxField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'boolean'
					},

					showAsSwitcher: {
<<<<<<< HEAD
						state: true,
=======
>>>>>>> compatible
						value: false
					},

					type: {
						value: 'checkbox'
<<<<<<< HEAD
=======
					},

					value: {
						setter: '_setValue'
>>>>>>> compatible
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-checkbox',

				prototype: {
					getTemplateContext: function() {
						var instance = this;

<<<<<<< HEAD
						return A.merge(
							CheckboxField.superclass.getTemplateContext.apply(instance, arguments),
							{
								showAsSwitcher: instance.get('showAsSwitcher')
=======
						var value = instance.getContextValue();

						return A.merge(
							CheckboxField.superclass.getTemplateContext.apply(instance, arguments),
							{
								showAsSwitcher: instance.get('showAsSwitcher'),
								status: DataTypeBoolean.parse(value) ? 'checked' : ''
>>>>>>> compatible
							}
						);
					},

					getValue: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						return inputNode.attr('checked');
					},

					setValue: function(value) {
						var instance = this;

						var inputNode = instance.getInputNode();

						inputNode.attr('checked', DataTypeBoolean.parse(value));
					},

<<<<<<< HEAD
					showErrorMessage: function() {
=======
					_renderErrorMessage: function() {
						var instance = this;

						var container = instance.get('container');

						CheckboxField.superclass._renderErrorMessage.apply(instance, arguments);

						container.all('.help-block').appendTo(container);
					},

					_setValue: function(value) {
						var instance = this;

						if (instance.get('localizable')) {
							for (var locale in value) {
								value[locale] = DataTypeBoolean.parse(value[locale]);
							}
						}
						else {
							value = DataTypeBoolean.parse(value);
						}

						return value;
					},

					_showFeedback: function() {
>>>>>>> compatible
						var instance = this;

						var container = instance.get('container');

<<<<<<< HEAD
						CheckboxField.superclass.showErrorMessage.apply(instance, arguments);

						container.all('.form-feedback-indicator').appendTo(container);
=======
						CheckboxField.superclass._showFeedback.apply(instance, arguments);

						container.all('.form-control-feedback').appendTo(container);
>>>>>>> compatible
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').Checkbox = CheckboxField;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-field']
	}
);