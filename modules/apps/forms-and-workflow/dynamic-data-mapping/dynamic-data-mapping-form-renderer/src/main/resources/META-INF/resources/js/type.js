AUI.add(
	'liferay-ddm-form-renderer-type',
	function(A) {
		var FormRendererFieldType = A.Component.create(
			{
<<<<<<< HEAD
				ATTRS: {
					description: {
						value: ''
					},
					group: {
						value: ''
					}
				},

=======
>>>>>>> compatible
				EXTENDS: A.FormBuilderFieldType,

				NAME: 'liferay-ddm-form-renderer-type',

				prototype: {
					TPL_FIELD_TYPE_CONTENT: '<div class="field-type-icon">' +
						Liferay.Util.getLexiconIconTpl('{icon}') +
					'</div>' +
					'<div class="field-type-label">{label}</div></div>'
				}
			}
		);

		Liferay.namespace('DDM').FormRendererFieldType = FormRendererFieldType;
	},
	'',
	{
		requires: ['aui-form-builder-field-type']
	}
);