AUI.add(
	'liferay-ddm-form-renderer-field-feedback',
	function(A) {
		var Lang = A.Lang;

<<<<<<< HEAD
		var TPL_ERROR_MESSAGE = '<div class="form-feedback-item help-block">{errorMessage}</div>';
=======
		var TPL_ERROR_MESSAGE = '<div class="help-block">{errorMessage}</div>';
>>>>>>> compatible

		var TPL_FEEDBACK = '<span aria-hidden="true" class="form-control-feedback"><span class="icon-{icon}"></span></span>';

		var FieldFeedbackSupport = function() {
		};

		FieldFeedbackSupport.ATTRS = {
			errorMessage: {
				value: ''
			}
		};

		FieldFeedbackSupport.prototype = {
			initializer: function() {
				var instance = this;

<<<<<<< HEAD
				instance._errorMessageNode = instance._createErrorMessageNode();

				instance._eventHandlers.push(
					instance.after('errorMessageChange', instance._afterErrorMessageChange),
					instance.after('visibleChange', instance._afterVisibleChange)
=======
				instance._eventHandlers.push(
					instance.after('errorMessageChange', instance._afterErrorMessageChange),
					instance.after(instance._renderErrorMessage, instance, 'render')
>>>>>>> compatible
				);
			},

			clearValidationStatus: function() {
				var instance = this;

				var container = instance.get('container');

				container.removeClass('has-error');
<<<<<<< HEAD
=======
				container.removeClass('has-success');
>>>>>>> compatible

				instance.hideFeedback();
			},

			hideErrorMessage: function() {
				var instance = this;

<<<<<<< HEAD
				instance._errorMessageNode.hide();

				instance.clearValidationStatus();
=======
				instance.set('errorMessage', '');
>>>>>>> compatible
			},

			hideFeedback: function() {
				var instance = this;

				var container = instance.get('container');

				container.removeClass('has-feedback');

				container.all('.form-control-feedback').remove();
			},

			showErrorFeedback: function() {
				var instance = this;

				instance._showFeedback('remove');
			},

<<<<<<< HEAD
			showErrorMessage: function() {
				var instance = this;

				var errorMessage = instance.get('errorMessage');

				var inputNode = instance.getInputNode();

				if (errorMessage && inputNode) {
					inputNode.insert(instance._errorMessageNode, 'after');

					instance._errorMessageNode.show();

					instance.showValidationStatus();

					var root = instance.getRoot();

					if (root) {
						Liferay.fire("ddmFieldValidationError", {
							formId: root.getFormId(),
							fieldName:  instance.get("fieldName")
						});
					}
				}
=======
			showErrorMessage: function(errorMessage) {
				var instance = this;

				instance.set('errorMessage', errorMessage);
>>>>>>> compatible
			},

			showLoadingFeedback: function() {
				var instance = this;

				instance._showFeedback('spinner icon-spin');
			},

			showSuccessFeedback: function() {
				var instance = this;

				instance._showFeedback('ok');
			},

			showValidationStatus: function() {
				var instance = this;

<<<<<<< HEAD
				var container = instance.get('container');

				container.toggleClass('has-error', instance.hasErrors());
			},

			_afterErrorMessageChange: function(event) {
				var instance = this;

				if (event.newVal) {
					instance._errorMessageNode.html(event.newVal);
				}
			},

			_afterVisibleChange: function(event) {
=======
				if (instance.hasValidation()) {
					var container = instance.get('container');
					var hasErrors = instance.hasErrors();

					container.toggleClass('has-error', hasErrors);
				}
			},

			_afterErrorMessageChange: function() {
				var instance = this;

				instance._renderErrorMessage();
			},

			_renderErrorMessage: function() {
>>>>>>> compatible
				var instance = this;

				var container = instance.get('container');

<<<<<<< HEAD
				container.toggleClass('hide', !event.newVal);
			},

			_createErrorMessageNode: function() {
				var instance = this;

				var errorMessage = instance.get('errorMessage');

				return A.Node.create(
					Lang.sub(
						TPL_ERROR_MESSAGE,
						{
							errorMessage: errorMessage
						}
					)
				);
=======
				container.all('.help-block').remove();

				var errorMessage = instance.get('errorMessage');

				var inputNode = instance.getInputNode();

				if (errorMessage && inputNode) {
					inputNode.insert(
						Lang.sub(
							TPL_ERROR_MESSAGE,
							{
								errorMessage: errorMessage
							}
						),
						'after'
					);
				}
>>>>>>> compatible
			},

			_showFeedback: function(icon) {
				var instance = this;

				instance.hideFeedback();

				var container = instance.get('container');

				container.addClass('has-feedback');

				instance.getInputNode().insert(
					Lang.sub(
						TPL_FEEDBACK,
						{
							icon: icon
						}
					),
					'after'
				);
			}
		};

		Liferay.namespace('DDM.Renderer').FieldFeedbackSupport = FieldFeedbackSupport;
	},
	'',
	{
		requires: ['aui-node']
	}
);