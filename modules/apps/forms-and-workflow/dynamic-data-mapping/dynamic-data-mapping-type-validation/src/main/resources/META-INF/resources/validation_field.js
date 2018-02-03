AUI.add(
	'liferay-ddm-form-field-validation',
	function(A) {
		var Lang = A.Lang;

		var Renderer = Liferay.DDM.Renderer;

		var Util = Renderer.Util;

		var ValidationField = A.Component.create(
			{
				ATTRS: {
					errorMessageValue: {
						value: ''
					},

					parameterValue: {
						value: ''
					},

<<<<<<< HEAD
=======
					selectedType: {
						value: 'text'
					},

>>>>>>> compatible
					selectedValidation: {
						getter: '_getSelectedValidation',
						value: 'notEmpty'
					},

					strings: {
						value: {
<<<<<<< HEAD
							email: Liferay.Language.get('email'),
							errorMessageGoesHere: Liferay.Language.get('error-message-goes-here'),
							url: Liferay.Language.get('url'),
							validation: Liferay.Language.get('validation')
=======
							disableValidation: Liferay.Language.get('disable-validation'),
							email: Liferay.Language.get('email'),
							enableValidation: Liferay.Language.get('enable-validation'),
							errorMessageGoesHere: Liferay.Language.get('error-message-goes-here'),
							number: Liferay.Language.get('number'),
							text: Liferay.Language.get('text'),
							url: Liferay.Language.get('url')
>>>>>>> compatible
						}
					},

					type: {
						value: 'validation'
					},

					validations: {
<<<<<<< HEAD
						getter: '_getValidations'
=======
						value: Util.getValidations()
>>>>>>> compatible
					},

					value: {
						setter: '_setValue',
<<<<<<< HEAD
						state: true,
=======
>>>>>>> compatible
						valueFn: '_valueValidation'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-validation',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._eventHandlers.push(
<<<<<<< HEAD
							instance.after('valueChange', A.bind('_afterValueChange', instance)),
							instance.bindContainerEvent('change', A.bind('_setErrorMessage', instance), '.message-input'),
							instance.bindContainerEvent('change', A.bind('_setParameterValue', instance), '.parameter-input'),
							instance.bindContainerEvent('change', A.bind('_syncValidationUI', instance), '.enable-validation'),
							instance.bindContainerEvent('change', A.bind('_syncValidationUI', instance), 'select')
=======
							instance.after('containerChange', instance._afterValidationContainerChange),
							instance.after('render', instance._afterValidationRender)
>>>>>>> compatible
						);
					},

					extractParameterValue: function(regex, expression) {
						var instance = this;

						regex.lastIndex = 0;

						var matches = regex.exec(expression);

						return matches && matches[2] || '';
					},

					getTemplateContext: function() {
						var instance = this;

						var strings = instance.get('strings');

						var parameterMessage = '';

						var selectedValidation = instance.get('selectedValidation');

						if (selectedValidation) {
							parameterMessage = selectedValidation.parameterMessage;
						}

						var value = instance.get('value');

						return A.merge(
							ValidationField.superclass.getTemplateContext.apply(instance, arguments),
							{
<<<<<<< HEAD
=======
								disableValidationMessage: strings.disableValidation,
								enableValidationMessage: strings.enableValidation,
>>>>>>> compatible
								enableValidationValue: !!(value && value.expression),
								errorMessagePlaceholder: strings.errorMessageGoesHere,
								errorMessageValue: instance.get('errorMessageValue'),
								parameterMessagePlaceholder: parameterMessage,
								parameterValue: instance.get('parameterValue'),
<<<<<<< HEAD
								validationMessage: strings.validation,
								validationsOptions: instance._getValidationsOptions()
=======
								typesOptions: instance._getTypesOptions(),
								validationsOptions: instance._getValidatiionsOptions()
>>>>>>> compatible
							}
						);
					},

					getValue: function() {
						var instance = this;

						var expression = '';

						var selectedValidation = instance.get('selectedValidation');

						var validationEnabled = instance._getEnableValidationValue();

						if (selectedValidation && validationEnabled) {
							var root = instance.getRoot();

							var nameField = root.getField('name');

							expression = Lang.sub(
								selectedValidation.template,
								{
<<<<<<< HEAD
									name: nameField && nameField.get('value') || '',
=======
									name: nameField && nameField.getValue() || '',
>>>>>>> compatible
									parameter: instance._getParameterValue()
								}
							);
						}

						return {
							errorMessage: instance._getMessageValue(),
							expression: expression
						};
					},

<<<<<<< HEAD
					_afterValueChange: function() {
						var instance = this;

						instance.evaluate();
=======
					_afterValidationContainerChange: function(event) {
						var instance = this;

						instance._bindContainerEvents();
					},

					_afterValidationRender: function() {
						var instance = this;

						instance._bindContainerEvents();
					},

					_afterValueChange: function() {
						var instance = this;

						instance.render();
					},

					_bindContainerEvents: function() {
						var instance = this;

						var container = instance.get('container');

						instance._eventHandlers.push(
							container.delegate('change', A.bind('_syncValidationUI', instance), '.enable-validation'),
							container.delegate('change', A.bind('_syncValidationUI', instance), 'select')
						);
>>>>>>> compatible
					},

					_getEnableValidationValue: function() {
						var instance = this;

						var container = instance.get('container');

						var enableValidationNode = container.one('.enable-validation');

						return !!enableValidationNode.attr('checked');
					},

					_getMessageValue: function() {
						var instance = this;

						var container = instance.get('container');

						var messageNode = container.one('.message-input');

						return messageNode.val();
					},

					_getParameterValue: function() {
						var instance = this;

						var container = instance.get('container');

						var parameterNode = container.one('.parameter-input');

						return parameterNode.val();
					},

					_getSelectedValidation: function(val) {
						var instance = this;

<<<<<<< HEAD
						var validations = instance.get('validations');

						var selectedValidation = A.Array.find(
							validations,
=======
						var selectedType = instance.get('selectedType');

						var validations = instance.get('validations');

						var selectedValidation = A.Array.find(
							validations[selectedType],
>>>>>>> compatible
							function(validation) {
								return validation.name === val;
							}
						);

						if (!selectedValidation) {
<<<<<<< HEAD
							selectedValidation = validations[0];
=======
							selectedValidation = validations[selectedType][0];
>>>>>>> compatible
						}

						return selectedValidation;
					},

<<<<<<< HEAD
					_getValidations: function() {
						var instance = this;

						return Util.getValidations(instance.get('dataType')) || [];
					},

					_getValidationsOptions: function() {
=======
					_getTypesOptions: function() {
						var instance = this;

						var selectedType = instance.get('selectedType');

						var strings = instance.get('strings');

						var options = [];

						A.each(
							instance.get('validations'),
							function(validation, validationType) {
								var status = selectedType === validationType ? 'selected' : '';

								options.push(
									{
										label: strings[validationType],
										status: status,
										value: validationType
									}
								);
							}
						);

						return options;
					},

					_getValidatiionsOptions: function() {
>>>>>>> compatible
						var instance = this;

						var selectedValidation = instance.get('selectedValidation');

						var validations = instance.get('validations');

<<<<<<< HEAD
						return validations.map(
=======
						return validations[instance.get('selectedType')].map(
>>>>>>> compatible
							function(validation) {
								var status = '';

								if (selectedValidation && selectedValidation.name === validation.name) {
									status = 'selected';
								}

								return {
									label: validation.label,
									status: status,
									value: validation.name
								};
							}
						);
					},

<<<<<<< HEAD
					_setErrorMessage: function(event) {
						var instance = this;

						var input = event.target;

						instance.set('errorMessageValue', input.val());
						instance.set('value', instance.getValue());
					},

					_setParameterValue: function(event) {
						var instance = this;

						var input = event.target;

						instance.set('parameterValue', input.val());
						instance.set('value', instance.getValue());
					},

=======
>>>>>>> compatible
					_setValue: function(validation) {
						var instance = this;

						if (validation) {
							var errorMessage = validation.errorMessage;

							var expression = validation.expression;

							A.each(
								instance.get('validations'),
<<<<<<< HEAD
								function(item, type) {
									var regex = item.regex;

									if (regex.test(expression)) {
										instance.set('errorMessageValue', errorMessage);
										instance.set('selectedValidation', item.name);

										instance.set(
											'parameterValue',
											instance.extractParameterValue(regex, expression)
										);
									}
								}
							);
						}

						return validation;
=======
								function(validation, type) {
									validation.forEach(
										function(item) {
											var regex = item.regex;

											if (regex.test(expression)) {
												instance.set('errorMessageValue', errorMessage);
												instance.set('selectedType', type);
												instance.set('selectedValidation', item.name);

												instance.set(
													'parameterValue',
													instance.extractParameterValue(regex, expression)
												);
											}
										}
									);
								}
							);
						}
>>>>>>> compatible
					},

					_syncValidationUI: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var newVal = currentTarget.val();

						var selectedValidation = newVal;

						if (currentTarget.hasClass('types-select')) {
<<<<<<< HEAD
							var validations = instance.get('validations');

							selectedValidation = validations[0].name;
=======
							instance.set('selectedType', newVal);

							var validations = instance.get('validations');

							selectedValidation = validations[newVal][0].name;
>>>>>>> compatible
						}

						instance.set('selectedValidation', selectedValidation);

						instance.set('value', instance.getValue());
					},

					_valueValidation: function() {
						var instance = this;

						return {
							errorMessage: Liferay.Language.get('is-empty'),
							expression: 'NOT(equals({name}, ""))'
						};
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').Validation = ValidationField;
	},
	'',
	{
		requires: ['aui-dropdown', 'liferay-ddm-form-renderer-field']
	}
);