AUI.add(
	'liferay-ddm-form-renderer',
	function(A) {
<<<<<<< HEAD
=======
		var AArray = A.Array;
>>>>>>> compatible
		var Renderer = Liferay.DDM.Renderer;

		var TPL_CONTAINER = '<div class="lfr-ddm-form-container"></div>';

		var Form = A.Component.create(
			{
				ATTRS: {
<<<<<<< HEAD
					builder: {
						value: {}
					},

=======
>>>>>>> compatible
					container: {
						setter: A.one,
						valueFn: '_valueContainer'
					},

					dataProviderURL: {
						value: ''
					},

<<<<<<< HEAD
					enableEvaluations: {
						value: true
					},

=======
>>>>>>> compatible
					portletNamespace: {
						value: ''
					},

<<<<<<< HEAD
=======
					readOnlyFields: {
						value: []
					},

>>>>>>> compatible
					strings: {
						value: {
							next: Liferay.Language.get('next'),
							previous: Liferay.Language.get('previous'),
<<<<<<< HEAD
							requestErrorMessage: Liferay.Language.get('there-was-an-error-when-trying-to-validate-your-form'),
							requiredFields: Liferay.Language.get('all-fields-marked-with-x-are-required')
=======
							requestErrorMessage: Liferay.Language.get('there-was-an-error-when-trying-to-validate-your-form')
>>>>>>> compatible
						}
					}
				},

				AUGMENTS: [
<<<<<<< HEAD
					Renderer.FormContextSupport,
					Renderer.FormEvaluationSupport,
=======
					Renderer.FormDefinitionSupport,
>>>>>>> compatible
					Renderer.FormFeedbackSupport,
					Renderer.FormPaginationSupport,
					Renderer.FormTabsSupport,
					Renderer.FormTemplateSupport,
					Renderer.FormValidationSupport,
					Renderer.NestedFieldsSupport
				],

				EXTENDS: A.Base,

				NAME: 'liferay-ddm-form-renderer',

				prototype: {
					_eventHandlers: [],

					initializer: function() {
						var instance = this;

						var formNode = instance.getFormNode();

						var readOnly = instance.get('readOnly');

						if (formNode && !readOnly) {
							instance._eventHandlers.push(
								formNode.on('submit', A.bind('_onSubmitForm', instance)),
								Liferay.on('submitForm', instance._onLiferaySubmitForm, instance)
							);
						}

						instance.after('render', instance._afterFormRender);
					},

					destructor: function() {
						var instance = this;

						instance.get('container').remove();

						(new A.EventHandle(instance._eventHandlers)).detach();
					},

<<<<<<< HEAD
					getEvaluationPayload: function() {
						var instance = this;

						var languageId;

						var builder = instance.get('builder');

						if (_.isEmpty(builder)) {
							languageId = themeDisplay.getDefaultLanguageId();
						}
						else {
							languageId = builder.get('defaultLanguageId');
						}

						return {
							languageId: languageId,
							p_auth: Liferay.authToken,
							portletNamespace: instance.get('portletNamespace'),
							serializedFormContext: JSON.stringify(instance.get('context'))
						};
					},

					getFormId: function() {
						var instance = this;

						var formNode = instance.getFormNode();

						if (!formNode) {
							return 0;
						}

						return formNode.getData('DDMFormInstanceId');
					},

=======
>>>>>>> compatible
					getFormNode: function() {
						var instance = this;

						var container = instance.get('container');

						return container.ancestor('form', true);
					},

					getSubmitButton: function() {
						var instance = this;

						var container = instance.get('container');

						var formNode = instance.getFormNode();

						return (formNode || container).one('[type="submit"]');
					},

<<<<<<< HEAD
					hasFocus: function() {
						var instance = this;

						var container = instance.get('container');

						var hasFocus = false;

						instance.eachField(
							function(field) {
								hasFocus = field.hasFocus();

								return hasFocus;
							}
						);

						return hasFocus || container.contains(document.activeElement);
					},

=======
>>>>>>> compatible
					submit: function() {
						var instance = this;

						instance.validate(
							function(hasErrors) {
								if (!hasErrors) {
									var formNode = instance.getFormNode();

									instance.showLoadingFeedback();

									Liferay.Util.submitForm(formNode);
								}
							}
						);
					},

					toJSON: function() {
						var instance = this;

<<<<<<< HEAD
						return instance.get('context');
=======
						var definition = instance.get('definition');

						var defaultLanguageId = definition.defaultLanguageId;

						return {
							availableLanguageIds: [defaultLanguageId],
							defaultLanguageId: defaultLanguageId,
							fieldValues: AArray.invoke(instance.getImmediateFields(), 'toJSON')
						};
>>>>>>> compatible
					},

					_afterFormRender: function() {
						var instance = this;

						instance.eachField(
							function(field) {
								field.render();
							}
						);

						var submitButton = instance.getSubmitButton();

						if (submitButton) {
							submitButton.attr('disabled', false);
						}
<<<<<<< HEAD

						var container = instance.get('container');

						Liferay.fire(
							Liferay.namespace('DDM').Form + ':render',
							{
								containerId: container.get('id')
							}
						);
=======
>>>>>>> compatible
					},

					_onLiferaySubmitForm: function(event) {
						var instance = this;

						if (event.form === instance.getFormNode()) {
							event.preventDefault();
						}
					},

					_onSubmitForm: function(event) {
						var instance = this;

						event.preventDefault();

						var currentPage = instance.getCurrentPage();
						var pagesTotal = instance.getPagesTotal();

						if (pagesTotal > 1 && currentPage < pagesTotal) {
							instance.nextPage();
						}
						else {
							instance.submit();
						}
					},

					_valueContainer: function() {
						var instance = this;

						return A.Node.create(TPL_CONTAINER);
					}
				}
			}
		);

		Liferay.namespace('DDM.Renderer').Form = Form;
	},
	'',
	{
<<<<<<< HEAD
		requires: ['aui-component', 'liferay-ddm-form-renderer-context', 'liferay-ddm-form-renderer-evaluation', 'liferay-ddm-form-renderer-feedback', 'liferay-ddm-form-renderer-nested-fields', 'liferay-ddm-form-renderer-pagination', 'liferay-ddm-form-renderer-tabs', 'liferay-ddm-form-renderer-template', 'liferay-ddm-form-renderer-validation', 'liferay-ddm-soy-template-util']
=======
		requires: ['aui-component', 'liferay-ddm-form-renderer-definition', 'liferay-ddm-form-renderer-feedback', 'liferay-ddm-form-renderer-nested-fields', 'liferay-ddm-form-renderer-pagination', 'liferay-ddm-form-renderer-tabs', 'liferay-ddm-form-renderer-template', 'liferay-ddm-form-renderer-validation', 'liferay-ddm-form-soy']
>>>>>>> compatible
	}
);