<<<<<<< HEAD
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

AUI.add(
	'liferay-ddm-form-field-checkbox-multiple',
	function(A) {
=======
AUI.add(
	'liferay-ddm-form-field-checkbox-multiple',
	function(A) {
		var Lang = A.Lang;

		var TPL_OPTION = '<option>{label}</option>';

>>>>>>> compatible
		var CheckboxMultipleField = A.Component.create(
			{
				ATTRS: {
					inline: {
						value: true
					},

					options: {
<<<<<<< HEAD
						getter: '_getOptions',
						state: true,
=======
>>>>>>> compatible
						validator: Array.isArray,
						value: []
					},

					showAsSwitcher: {
						value: false
					},

					type: {
						value: 'checkbox_multiple'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-checkbox-multiple',

				prototype: {
<<<<<<< HEAD
=======
					getOptions: function() {
						var instance = this;

						var options = instance.get('options');

						return A.map(
							instance.get('options'),
							function(item) {
								return {
									label: item.label[instance.get('locale')],
									status: instance._getOptionStatus(item),
									value: item.value
								};
							}
						);
					},

>>>>>>> compatible
					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							CheckboxMultipleField.superclass.getTemplateContext.apply(instance, arguments),
							{
								inline: instance.get('inline'),
<<<<<<< HEAD
								options: instance.get('options'),
=======
								options: instance.getOptions(),
>>>>>>> compatible
								showAsSwitcher: instance.get('showAsSwitcher')
							}
						);
					},

					getValue: function() {
						var instance = this;

						var container = instance.get('container');

						var values = [];

<<<<<<< HEAD
						container.all(instance.getInputSelector()).each(
							function(optionNode) {
								var checked = !!optionNode.attr('checked');

								if (checked) {
									values.push(optionNode.val());
								}
							}
						);

						return values;
					},

					setValue: function(value) {
=======
						if (container) {
							container.all(instance.getInputSelector()).each(
								function(optionNode) {
									var isChecked = !!optionNode.attr('checked');

									if (isChecked) {
										values.push(optionNode.val());
									}
								}
							);
						}

						return values.join();
					},

					setValue: function(val) {
>>>>>>> compatible
						var instance = this;

						var container = instance.get('container');

<<<<<<< HEAD
						var checkboxNodeList = container.all('input[type="checkbox"]');

						for (var i = 0; i < checkboxNodeList.length; i++) {
							if (value.includes(checkboxNodeList[i].val())) {
								var node = checkboxNodeList[i];

								node.attr('checked', true);
							}
						}
					},

					showErrorMessage: function() {
						var instance = this;

						CheckboxMultipleField.superclass.showErrorMessage.apply(instance, arguments);

						var container = instance.get('container');

						var formGroup = container.one('.form-group');

						formGroup.insert(container.one('.form-feedback-indicator'), 'after');
					},

					_getOptions: function(options) {
						var instance = this;

						return options || [];
=======
						var checkboxesNodeList = container.all('input[type="checkbox"]');

						checkboxesNodeList.removeAttribute('checked');

						var checkboxCheck = checkboxesNodeList.filter(
							function(node) {
								return node.val() === value;
							}
						).item(0);

						if (checkboxCheck) {
							checkboxCheck.attr('checked', true);

							instance.fire(
								'valueChanged',
								{
									field: instance,
									value: value
								}
							);
						}
					},

					_getOptionStatus: function(option) {
						var instance = this;

						var status = '';

						var value = instance.getValue();

						if (value.indexOf(option.value) > -1) {
							status = 'checked';
						}

						return status;
					},

					_renderErrorMessage: function() {
						var instance = this;

						var container = instance.get('container');

						CheckboxMultipleField.superclass._renderErrorMessage.apply(instance, arguments);

						container.all('.help-block').appendTo(container);
>>>>>>> compatible
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').CheckboxMultiple = CheckboxMultipleField;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-field']
	}
);