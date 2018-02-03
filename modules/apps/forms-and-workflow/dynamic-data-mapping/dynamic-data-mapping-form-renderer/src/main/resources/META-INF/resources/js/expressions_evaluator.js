AUI.add(
	'liferay-ddm-form-renderer-expressions-evaluator',
	function(A) {
<<<<<<< HEAD
		var CACHE = {};

=======
>>>>>>> compatible
		var ExpressionsEvaluator = A.Component.create(
			{
				ATTRS: {
					enabled: {
<<<<<<< HEAD
						getter: '_getEnabled',
=======
>>>>>>> compatible
						value: true
					},

					evaluatorURL: {
						valueFn: '_valueEvaluatorURL'
					},

					form: {
					}
				},

				NAME: 'liferay-ddm-form-renderer-expressions-evaluator',

				prototype: {
					initializer: function() {
						var instance = this;

<<<<<<< HEAD
						instance._queue = new A.Queue();

						instance.publish(
							{
								'evaluate': {
									defaultFn: instance._evaluate
								},
								start: {
									defaultFn: instance._start
								}
							}
						);

						instance.after('evaluationEnded', instance._afterEvaluationEnded);
					},

					evaluate: function(trigger, callback) {
=======
						instance.after('evaluationEnded', instance._afterEvaluationEnded);
					},

					evaluate: function(callback) {
>>>>>>> compatible
						var instance = this;

						var enabled = instance.get('enabled');

						var form = instance.get('form');

<<<<<<< HEAD
						if (enabled && form) {
							if (instance.isEvaluating()) {
								instance.stop();
							}

							instance._evaluating = true;

							instance.fire(
								'start',
								{
									trigger: trigger
								}
							);

							instance._queue.add(trigger);

							instance.fire(
								'evaluate',
								{
									callback: function(result) {
										instance._evaluating = false;

										var triggers = {};

										while (instance._queue.size() > 0) {
											var next = instance._queue.next();

											if (!triggers[next.get('name')]) {
												instance.fire(
													'evaluationEnded',
													{
														result: result,
														trigger: next
													}
												);
											}

											triggers[next.get('name')] = true;
										}

										if (callback) {
											callback.apply(instance, arguments);
										}
									},
									trigger: trigger
=======
						if (instance.isEvaluating()) {
							instance.stop();
						}

						if (enabled && form) {
							instance.fire('evaluationStarted');

							form.disableSubmitButton();

							instance._evaluate(
								function(result) {
									form.enableSubmitButton();

									instance.fire(
										'evaluationEnded',
										{
											result: result
										}
									);

									if (callback) {
										callback.apply(instance, arguments);
									}
>>>>>>> compatible
								}
							);
						}
					},

					isEvaluating: function() {
						var instance = this;

<<<<<<< HEAD
						return instance._evaluating;
=======
						return instance._request !== undefined;
>>>>>>> compatible
					},

					stop: function() {
						var instance = this;

<<<<<<< HEAD
						if (instance._request) {
							instance._request.destroy();

							delete instance._request;
						}
=======
						instance._request.destroy();

						delete instance._request;
>>>>>>> compatible
					},

					_afterEvaluationEnded: function() {
						var instance = this;

						instance.stop();
					},

<<<<<<< HEAD
					_evaluate: function(event) {
						var instance = this;

						var callback = event.callback;

						var form = instance.get('form');

						var payload = form.getEvaluationPayload();

						var type = payload.type;

						if (payload.newField && CACHE[type]) {
							callback.call(instance, JSON.parse(CACHE[type]));
						}
						else {
							instance._request = A.io.request(
								instance.get('evaluatorURL'),
								{
									data: A.merge(
										payload,
										{
											trigger: event.trigger ? event.trigger.get('fieldName') || '' : ''
										}
									),
									method: 'POST',
									on: {
										failure: function(event) {
											if (event.details[1].statusText !== 'abort') {
												callback.call(instance, null);
											}
											else {
												callback.call(instance, {});
											}
										},
										success: function(event, id, xhr) {
											var result = xhr.responseText;

											if (payload.newField) {
												CACHE[type] = result;
											}

											callback.call(instance, JSON.parse(result));
										}
									}
								}
							);
						}
					},

					_getEnabled: function(enabled) {
						var instance = this;

						return enabled && !!instance.get('evaluatorURL');
					},

					_start: function(event) {
						var instance = this;

						if (instance.isEvaluating()) {
							instance.fire(
								'evaluationStarted',
								{
									trigger: event.trigger
								}
							);
						}
=======
					_evaluate: function(callback) {
						var instance = this;

						var form = instance.get('form');

						var definition = form.get('definition');

						instance._request = A.io.request(
							instance.get('evaluatorURL'),
							{
								data: {
									languageId: definition.defaultLanguageId,
									serializedDDMForm: JSON.stringify(definition),
									serializedDDMFormValues: JSON.stringify(form.toJSON())
								},
								dataType: 'JSON',
								method: 'POST',
								on: {
									failure: function(event) {
										if (event.details[1].statusText !== 'abort') {
											callback.call(instance, null);
										}

										callback.call(instance, {});
									},
									success: function() {
										var result = this.get('responseData');

										callback.call(instance, result);
									}
								}
							}
						);
>>>>>>> compatible
					},

					_valueEvaluatorURL: function() {
						var instance = this;

						var evaluatorURL;

						var form = instance.get('form');

						if (form) {
							evaluatorURL = form.get('evaluatorURL');
						}

						return evaluatorURL;
					}
				}
			}
		);

		Liferay.namespace('DDM.Renderer').ExpressionsEvaluator = ExpressionsEvaluator;
	},
	'',
	{
		requires: ['aui-component', 'aui-io-request']
	}
);