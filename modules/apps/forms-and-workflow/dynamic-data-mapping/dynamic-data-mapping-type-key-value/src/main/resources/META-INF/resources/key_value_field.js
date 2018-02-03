AUI.add(
	'liferay-ddm-form-field-key-value',
	function(A) {
<<<<<<< HEAD
		var KeyValueField = A.Component.create(
			{
				ATTRS: {
=======
		var Lang = A.Lang;

		var KeyValueField = A.Component.create(
			{
				ATTRS: {
					editing: {
						value: false
					},

>>>>>>> compatible
					generationLocked: {
						valueFn: '_valueGenerationLocked'
					},

					key: {
						valueFn: '_valueKey'
					},

<<<<<<< HEAD
					maxKeyInputSize: {
						value: 50
					},

					minKeyInputSize: {
						value: 5
					},

=======
>>>>>>> compatible
					strings: {
						value: {
							cancel: Liferay.Language.get('cancel'),
							done: Liferay.Language.get('done'),
							keyLabel: Liferay.Language.get('field-name')
						}
					},

					type: {
<<<<<<< HEAD
						value: 'key_value'
=======
						value: 'key-value'
>>>>>>> compatible
					}
				},

				EXTENDS: Liferay.DDM.Field.Text,

				NAME: 'liferay-ddm-form-field-key-value',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._eventHandlers.push(
<<<<<<< HEAD
							instance.after('keyChange', instance._afterKeyChange),
							instance.after('valueChange', instance._afterValueChangeInput),
							instance.bindContainerEvent('blur', instance._onBlurKeyInput, '.key-value-input'),
							instance.bindContainerEvent('keyup', instance._onKeyUpKeyInput, '.key-value-input'),
							instance.bindContainerEvent('valuechange', instance._onValueChangeKeyInput, '.key-value-input')
=======
							instance.after('editingChange', instance._afterEditingChange),
							instance.after('keyChange', instance._afterKeyChange),
							instance.bindContainerEvent('click', instance._onClickCancel, '.key-value-cancel'),
							instance.bindContainerEvent('click', instance._onClickDone, '.key-value-done'),
							instance.bindContainerEvent('click', instance._onClickEditor, '.key-value-output'),
							instance.bindContainerEvent('keypress', instance._onKeyPressEditorInput, '.key-value-input'),
							instance.bindContainerEvent('valuechange', instance._onValueChangeEditorInput, '.key-value-input'),
							instance.bindInputEvent('valuechange', instance._onValueChangeInput)
>>>>>>> compatible
						);
					},

					getTemplateContext: function() {
						var instance = this;

<<<<<<< HEAD
						var key = instance.get('key');

						return A.merge(
							KeyValueField.superclass.getTemplateContext.apply(instance, arguments),
							{
								key: key,
								keyInputSize: instance._getKeyInputSize(key),
								strings: instance.get('strings')
=======
						return A.merge(
							KeyValueField.superclass.getTemplateContext.apply(instance, arguments),
							{
								editing: instance.get('editing'),
								key: instance.get('key'),
								strings: instance.get('strings'),
								tooltip: instance.getLocalizedValue(instance.get('tooltip'))
>>>>>>> compatible
							}
						);
					},

<<<<<<< HEAD
					isValidCharacter: function(character) {
						var instance = this;

						return A.Text.Unicode.test(character, 'L') || A.Text.Unicode.test(character, 'N');
					},

					normalizeKey: function(key) {
						var instance = this;

						var normalizedKey = '';

						var nextUpperCase = false;

=======
					normalizeKey: function(key) {
						var instance = this;

>>>>>>> compatible
						key = key.trim();

						for (var i = 0; i < key.length; i++) {
							var item = key[i];

<<<<<<< HEAD
							if (item === ' ') {
								nextUpperCase = true;

								continue;
							}
							else if (!instance.isValidCharacter(item)) {
								continue;
							}

							if (nextUpperCase) {
								item = item.toUpperCase();

								nextUpperCase = false;
							}

							normalizedKey += item;
						}

						return normalizedKey;
=======
							if (!A.Text.Unicode.test(item, 'L') && !A.Text.Unicode.test(item, 'N')) {
								key = key.replace(item, ' ');
							}
						}

						key = Lang.String.camelize(key, ' ');

						return key.replace(/\s+/ig, '');
>>>>>>> compatible
					},

					render: function() {
						var instance = this;

						var key = instance.get('key');

						if (!key) {
							instance.set('key', instance._valueKey());
						}

						KeyValueField.superclass.render.apply(instance, arguments);

						return instance;
					},

<<<<<<< HEAD
					showErrorMesasage: function() {
						var instance = this;

						KeyValueField.superclass.showErrorMesasage.apply(instance, arguments);

						var container = instance.get('container');

						var editorNode = container.one('.key-value-editor');

						editorNode.insert(container.one('.form-feedback-indicator'), 'after');
					},

					_afterKeyChange: function(event) {
						var instance = this;

						if (event.newVal && event.newVal !== instance.normalizeKey(instance.getValue())) {
							instance.set('generationLocked', true);
						}
						else {
							instance.set('generationLocked', false);
						}

						instance._uiSetKey(event.newVal);
					},

					_afterValueChangeInput: function(event) {
						var instance = this;

						if (!instance.get('generationLocked')) {
							instance.set('key', instance.normalizeKey(event.newVal));
						}
					},

					_getKeyInputSize: function(str) {
						var instance = this;

						var size = str.length;

						var maxKeyInputSize = instance.get('maxKeyInputSize');

						var minKeyInputSize = instance.get('minKeyInputSize');

						if (size > maxKeyInputSize) {
							size = maxKeyInputSize;
						}
						else if (size <= minKeyInputSize) {
							size = minKeyInputSize;
						}

						return size + 1;
					},

					_onBlurKeyInput: function(event) {
						var instance = this;

						var inputNode = event.target;

						var value = inputNode.val();

						if (!value) {
							value = instance.getValue();
						}

						instance._updateInputValue(inputNode, instance.normalizeKey(value));

						instance.fire('blur', instance._getEventPayload(event));
					},

					_onKeyUpKeyInput: function(event) {
						var instance = this;

						var inputNode = event.target;

						var value = inputNode.val();

						var validValue = value.split('').filter(instance.isValidCharacter);

						var newValue = validValue.join('');

						if (newValue !== value) {
							instance._updateInputValue(inputNode, newValue);
						}
					},

					_onValueChangeKeyInput: function(event) {
						var instance = this;

						var value = event.newVal;

						instance.set('key', instance.normalizeKey(value));
					},

					_uiSetKey: function(key) {
						var instance = this;

						var keyInput = instance.get('container').one('.key-value-input');

						if (document.activeElement !== keyInput.getDOM()) {
							keyInput.val(key);
						}

						keyInput.attr('size', instance._getKeyInputSize(key));
					},

					_updateInputValue: function(inputNode, newValue) {
						var instance = this;

						var currentValue = inputNode.val();

						var selectionEnd = inputNode.get('selectionEnd');
						var selectionStart = inputNode.get('selectionStart');

						inputNode.val(newValue);

						inputNode.set('selectionStart', selectionStart);
						inputNode.set('selectionEnd', selectionEnd - (currentValue.length - newValue.length));
=======
					saveEditor: function() {
						var instance = this;

						var container = instance.get('container');

						var editorInput = container.one('.key-value-input');

						var value = editorInput.val();

						if (value) {
							instance.set('key', instance.normalizeKey(value));
						}

						instance.set('editing', false);
					},

					_afterEditingChange: function(event) {
						var instance = this;

						var container = instance.get('container');

						var editing = event.newVal;

						if (editing && !instance._eventOutsideHandler) {
							instance._eventOutsideHandler = container.on(
								'clickoutside',
								function(event) {
									instance.set('editing', false);

									instance._eventOutsideHandler.detach();

									instance._eventOutsideHandler = null;
								},
								'.key-value-input'
							);
						}

						instance._uiSetEditing(editing);
					},

					_afterKeyChange: function(event) {
						var instance = this;

						instance.set('generationLocked', event.newVal !== instance.normalizeKey(instance.getValue()));

						instance._uiSetKey(event.newVal);
					},

					_getMaxInputSize: function(str) {
						var size = str.length;

						if (size > 50) {
							size = 50;
						}
						else if (size <= 5) {
							size = 5;
						}

						return size;
					},

					_onClickCancel: function() {
						var instance = this;

						instance.set('editing', false);
					},

					_onClickDone: function() {
						var instance = this;

						instance.saveEditor();
					},

					_onClickEditor: function() {
						var instance = this;

						instance.set('editing', !instance.get('editing'));
					},

					_onKeyPressEditorInput: function(event) {
						var instance = this;

						if (event.isKey('ENTER')) {
							event.preventDefault();

							instance.saveEditor();
						}
					},

					_onValueChangeEditorInput: function(event) {
						var instance = this;

						var input = event.target;

						var value = event.newVal;

						if (value.length === 0) {
							value = input.attr('placeholder');
						}

						event.target.attr('size', instance._getMaxInputSize(value) + 1);
					},

					_onValueChangeInput: function(event) {
						var instance = this;

						if (!instance.get('generationLocked')) {
							var value = instance.getValue();

							instance.set('key', instance.normalizeKey(value));
						}
					},

					_renderErrorMessage: function() {
						var instance = this;

						KeyValueField.superclass._renderErrorMessage.apply(instance, arguments);

						var container = instance.get('container');

						var editorNode = container.one('.key-value-editor');

						editorNode.insert(container.one('.help-block'), 'after');
					},

					_uiSetEditing: function(editing) {
						var instance = this;

						var container = instance.get('container');

						var editorNode = container.one('.key-value-editor');

						editorNode.toggleClass('active', editing);

						if (editing) {
							var editorInput = container.one('.key-value-input');

							editorInput.val('');
							editorInput.focus();
						}
					},

					_uiSetKey: function(key) {
						var instance = this;

						var container = instance.get('container');

						var editorInput = container.one('.key-value-input');

						editorInput.attr('placeholder', key);
						editorInput.attr('size', instance._getMaxInputSize(key) + 1);

						container.one('.key-value-output').html(key);
>>>>>>> compatible
					},

					_valueGenerationLocked: function() {
						var instance = this;

<<<<<<< HEAD
						return instance.get('key') !== instance.normalizeKey(instance.get('value'));
=======
						return instance.get('key') !== instance.normalizeKey(instance.getContextValue());
>>>>>>> compatible
					},

					_valueKey: function() {
						var instance = this;

<<<<<<< HEAD
						return instance.normalizeKey(instance.get('value'));
=======
						return instance.normalizeKey(instance.getContextValue());
>>>>>>> compatible
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').KeyValue = KeyValueField;
	},
	'',
	{
<<<<<<< HEAD
		requires: ['aui-text-unicode', 'event-valuechange', 'liferay-ddm-form-field-text', 'liferay-ddm-form-renderer-field']
=======
		requires: ['aui-text-unicode', 'liferay-ddm-form-field-text', 'liferay-ddm-form-renderer-field']
>>>>>>> compatible
	}
);