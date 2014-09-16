AUI.add(
	'liferay-input-range-fallback',
	function(A) {

		var NAME = 'input-range-fallback';

		InputRangeFallback = A.Component.create({

			ATTRS: {
				axis: {
					value: 'x'
				},
				fallBack: {
					valueFn: function () {
						var testInput = A.Node.create('<input />');
						testInput.set('type', 'range');

						return testInput.get('type') !== "range";
					}
				},
				max: {
					value: 100
				},
				min: {
					value: 0
				},
				outNode: {
				},
				srcNode: {
				},
				value: {
					value: 50
				}
			},

			NAME: NAME,

			prototype: {

				bindUI: function () {
					var instance = this,
						outNode = instance.get('outNode'),
						inputNode = instance.fallBackSlider;

					var outputNode = A.one(outNode);

					if (outputNode && inputNode) {
						inputNode.after('valueChange', function(event) {
							outputNode.val(inputNode.getValue());
						});
					}
				},

				renderUI: function () {
					var instance = this,
						axis = instance.get('axis'),
						fallBack = instance.get('fallBack'),
						max = instance.get('max'),
						min = instance.get('min'),
						srcNode = instance.get('srcNode'),
						value = instance.get('value');

					var inputNode = A.one(srcNode);

					if (fallBack && inputNode) {

						inputNode.setStyle('display', 'none');

						var length = inputNode.ancestor().width();

						var fallBackSlider = new A.Slider({
							axis: axis,
							length: length,
							max: max,
							min: min,
							render: inputNode.ancestor(),
							value: value
						});

						instance.fallBackSlider = fallBackSlider;
					}
				}
			}

		});

		Liferay.InputRangeFallback = InputRangeFallback;
	},
	'',
	{
		requires: ['aui-base', 'aui-node', 'slider']
	}
);