AUI.add(
	'liferay-ddm-form-field-text',
	function(A) {
<<<<<<< HEAD
		var Renderer = Liferay.DDM.Renderer;

		var Util = Renderer.Util;

=======
>>>>>>> compatible
		new A.TooltipDelegate(
			{
				position: 'left',
				trigger: '.liferay-ddm-form-field-text .help-icon',
				triggerHideEvent: ['blur', 'mouseleave'],
				triggerShowEvent: ['focus', 'mouseover'],
				visible: false
			}
		);

		var TextField = A.Component.create(
			{
				ATTRS: {
<<<<<<< HEAD
					autocompleteEnabled: {
						state: true,
						value: false
					},

					displayStyle: {
						state: true,
						value: 'singleline'
					},

					initialHeight: {
						value: 0
					},

					options: {
						value: []
					},

					placeholder: {
						state: true,
=======
					displayStyle: {
						value: 'singleline'
					},

					placeholder: {
						value: ''
					},

					tooltip: {
>>>>>>> compatible
						value: ''
					},

					type: {
						value: 'text'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-text',

				prototype: {
<<<<<<< HEAD
					initializer: function() {
						var instance = this;

						instance._eventHandlers.push(
							instance.after('optionsChange', instance._afterOptionsChange),
							instance.after('valueChange', instance._onTextFieldValueChange)
						);

						instance.evaluate = A.debounce(
							function() {
								TextField.superclass.evaluate.apply(instance, arguments);
							},
							300
						);
					},

					getAutoComplete: function() {
						var instance = this;

						var autoComplete = instance._autoComplete;

						var inputNode = instance.getInputNode();

						if (autoComplete) {
							autoComplete.set('inputNode', inputNode);
						}
						else {
							instance._createAutocomplete();
							autoComplete = instance._autoComplete;
						}

						return autoComplete;
					},

					getChangeEventName: function() {
						return 'input';
					},

=======
					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							TextField.superclass.getTemplateContext.apply(instance, arguments),
							{
								displayStyle: instance.get('displayStyle'),
								placeholder: instance.getLocalizedValue(instance.get('placeholder')),
								tooltip: instance.getLocalizedValue(instance.get('tooltip'))
							}
						);
					},

>>>>>>> compatible
					render: function() {
						var instance = this;

						TextField.superclass.render.apply(instance, arguments);

<<<<<<< HEAD
						var autocompleteEnabled = instance.get('autocompleteEnabled');

						if (autocompleteEnabled && instance.get('visible')) {
							instance._createAutocomplete();
						}

						if (instance.get('displayStyle') === 'multiline') {
							instance._setInitialHeight();
							instance.syncInputHeight();
=======
						if (instance.get('displayStyle') === 'multiline') {
							var textAreaNode = instance.getInputNode();

							if (!textAreaNode.autosize) {
								textAreaNode.plug(A.Plugin.Autosize);
								textAreaNode.height(textAreaNode.get('scrollHeight'));
							}

							textAreaNode.autosize._uiAutoSize();
>>>>>>> compatible
						}

						return instance;
					},

<<<<<<< HEAD
					showErrorMessage: function() {
						var instance = this;

						TextField.superclass.showErrorMessage.apply(instance, arguments);

						var container = instance.get('container');

						var formGroup = container.one('.form-group');

						formGroup.insert(container.one('.form-feedback-item'), 'after');
					},

					syncInputHeight: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						var initialHeight = instance.get('initialHeight');

						inputNode.setStyle('height', initialHeight);

						var height = inputNode.get('scrollHeight');

						if (height > initialHeight) {
							inputNode.setStyle('height', height);
						}
					},

					_afterOptionsChange: function(event) {
						var instance = this;

						if (instance.get('autocompleteEnabled')) {
							var autoComplete = instance.getAutoComplete();

							if (!Util.compare(event.newVal, event.prevVal)) {
								autoComplete.set('source', event.newVal);

								autoComplete.fire(
									'query',
									{
										inputValue: instance.getValue(),
										query: instance.getValue(),
										src: A.AutoCompleteBase.UI_SRC
									}
								);
							}
						}
					},

					_createAutocomplete: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						if (instance._autoComplete) {
							instance._autoComplete.destroy();
						}

						instance._autoComplete = new A.AutoComplete(
							{
								after: {
									select: A.bind(instance.evaluate, instance)
								},
								inputNode: inputNode,
								maxResults: 10,
								render: true,
								resultFilters: ['charMatch', 'subWordMatch'],
								resultHighlighter: 'subWordMatch',
								resultTextLocator: 'label',
								source: instance.get('options')
							}
						);
					},

					_onTextFieldValueChange: function() {
						var instance = this;

						if (instance.get('displayStyle') === 'multiline') {
							instance.syncInputHeight();
						}
					},

					_setInitialHeight: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						var initialHeightInPx = inputNode.getStyle('height');

						var initialHeight = parseInt(initialHeightInPx, 10);

						instance.set('initialHeight', initialHeight);
=======
					_renderErrorMessage: function() {
						var instance = this;

						TextField.superclass._renderErrorMessage.apply(instance, arguments);

						var container = instance.get('container');

						var inputGroup = container.one('.input-group-container');

						inputGroup.insert(container.one('.help-block'), 'after');
					},

					_showFeedback: function() {
						var instance = this;

						TextField.superclass._showFeedback.apply(instance, arguments);

						var container = instance.get('container');

						var feedBack = container.one('.form-control-feedback');

						var inputGroupAddOn = container.one('.input-group-addon');

						if (inputGroupAddOn) {
							feedBack.appendTo(inputGroupAddOn);
						}
						else {
							var inputGroupContainer = container.one('.input-group-container');

							inputGroupContainer.placeAfter(feedBack);
						}
>>>>>>> compatible
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').Text = TextField;
	},
	'',
	{
<<<<<<< HEAD
		requires: ['aui-autosize-deprecated', 'aui-tooltip', 'autocomplete', 'autocomplete-filters', 'autocomplete-highlighters', 'autocomplete-highlighters-accentfold', 'liferay-ddm-form-renderer-field']
=======
		requires: ['aui-autosize-deprecated', 'aui-tooltip', 'liferay-ddm-form-renderer-field']
>>>>>>> compatible
	}
);