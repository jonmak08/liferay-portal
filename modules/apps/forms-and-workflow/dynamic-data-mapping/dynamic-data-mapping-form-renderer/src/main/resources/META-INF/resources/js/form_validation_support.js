AUI.add(
	'liferay-ddm-form-renderer-validation',
	function(A) {
		var Lang = A.Lang;

<<<<<<< HEAD
=======
		var Renderer = Liferay.DDM.Renderer;

>>>>>>> compatible
		var FormValidationSupport = function() {
		};

		FormValidationSupport.ATTRS = {
<<<<<<< HEAD
		};

		FormValidationSupport.prototype = {
=======
			evaluation: {
				value: null
			},

			evaluatorURL: {
				value: ''
			},

			evaluator: {
				valueFn: '_valueEvaluator'
			}
		};

		FormValidationSupport.prototype = {
			initializer: function() {
				var instance = this;

				var evaluator = instance.get('evaluator');

				instance._eventHandlers.push(
					evaluator.after('evaluationEnded', A.bind('_afterEvaluationEnded', instance)),
					evaluator.after('evaluationStarted', A.bind('_afterEvaluationStarted', instance))
				);
			},

>>>>>>> compatible
			hasErrors: function() {
				var instance = this;

				var hasErrors = false;

				instance.eachField(
					function(field) {
<<<<<<< HEAD
						if (field.hasErrors()) {
							hasErrors = true;

							field.showErrorMessage();
						}
						else {
							field.hideErrorMessage();
						}
=======
						hasErrors = field.hasErrors();

						return hasErrors;
>>>>>>> compatible
					}
				);

				return hasErrors;
			},

<<<<<<< HEAD
			hasPageErrors: function(pageNode) {
				var instance = this;

				var hasPageErrors = false;

				instance.eachField(
					function(field) {
						var container = field.get('container');

						if (pageNode.contains(container) && field.hasErrors()) {
							hasPageErrors = true;

							field.showErrorMessage();
						}
						else {
							field.hideErrorMessage();
=======
			hasValidation: function() {
				var instance = this;

				var hasValidation = false;

				instance.eachField(
					function(field) {
						hasValidation = field.hasValidation();

						return hasValidation;
					}
				);

				return hasValidation;
			},

			pageHasErrors: function(pageNode) {
				var instance = this;

				var hasErrors = false;

				instance.eachField(
					function(field) {
						if (pageNode.contains(field.get('container'))) {
							hasErrors = field.hasErrors();
						}

						return hasErrors;
					}
				);

				return hasErrors;
			},

			processPageValidation: function(pageNode, result) {
				var instance = this;

				var fieldToFocus;

				instance.eachField(
					function(field) {
						if (pageNode.contains(field.get('container'))) {
							field.processValidation(result);

							if (field.hasErrors() && !fieldToFocus) {
								fieldToFocus = field;
							}
						}
					}
				);

				if (fieldToFocus) {
					fieldToFocus.focus();
				}
			},

			processValidation: function(result) {
				var instance = this;

				var fieldToFocus;

				instance.eachField(
					function(field) {
						field.processValidation(result);

						if (field.hasErrors() && !fieldToFocus) {
							fieldToFocus = field;
>>>>>>> compatible
						}
					}
				);

<<<<<<< HEAD
				return hasPageErrors;
=======
				if (fieldToFocus) {
					fieldToFocus.focus();
				}
>>>>>>> compatible
			},

			validate: function(callback) {
				var instance = this;

<<<<<<< HEAD
				if (instance.get('readOnly')) {
					callback.call(instance, false, null);
				}
				else {
					var evaluator = instance.get('evaluator');

					evaluator.evaluate(
						instance,
=======
				if (instance.hasValidation()) {
					var evaluator = instance.get('evaluator');

					evaluator.evaluate(
>>>>>>> compatible
						function(result) {
							var hasErrors = true;

							if (result && Lang.isObject(result)) {
<<<<<<< HEAD
=======
								instance.processValidation(result);

>>>>>>> compatible
								hasErrors = instance.hasErrors();
							}

							if (callback) {
								callback.call(instance, hasErrors, result);
							}
						}
					);
				}
<<<<<<< HEAD
=======
				else if (callback) {
					callback.call(instance, false);
				}
>>>>>>> compatible
			},

			validatePage: function(pageNode, callback) {
				var instance = this;

<<<<<<< HEAD
				if (instance.get('readOnly')) {
					callback.call(instance, false, null);
				}
				else {
					var evaluator = instance.get('evaluator');

					evaluator.evaluate(
						instance,
						function(result) {
							var hasPageErrors = true;

							if (result && Lang.isObject(result)) {
								hasPageErrors = instance.hasPageErrors(pageNode, result);
							}

							if (callback) {
								callback.call(instance, hasPageErrors, result);
=======
				if (instance.hasValidation()) {
					var evaluator = instance.get('evaluator');

					evaluator.evaluate(
						function(result) {
							var hasErrors = true;

							if (result && Lang.isObject(result)) {
								instance.processPageValidation(pageNode, result);

								hasErrors = instance.pageHasErrors(pageNode);
							}

							if (callback) {
								callback.call(instance, hasErrors, result);
>>>>>>> compatible
							}
						}
					);
				}
<<<<<<< HEAD
=======
				else if (callback) {
					callback.call(instance, false);
				}
			},

			_afterEvaluationEnded: function(event) {
				var instance = this;

				var result = event.result;

				instance.hideFeedback();

				if (!result || !Lang.isObject(result)) {
					var strings = instance.get('strings');

					instance.showAlert(strings.requestErrorMessage);
				}
			},

			_afterEvaluationStarted: function() {
				var instance = this;

				instance.showLoadingFeedback();
			},

			_valueEvaluator: function() {
				var instance = this;

				return new Renderer.ExpressionsEvaluator(
					{
						form: instance
					}
				);
>>>>>>> compatible
			}
		};

		Liferay.namespace('DDM.Renderer').FormValidationSupport = FormValidationSupport;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-expressions-evaluator']
	}
);