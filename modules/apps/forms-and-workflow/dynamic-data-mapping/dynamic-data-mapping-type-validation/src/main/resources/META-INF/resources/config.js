;(function() {
	var LiferayAUI = Liferay.AUI;

	AUI().applyConfig(
		{
			groups: {
				'field-validation': {
					base: MODULE_PATH + '/',
<<<<<<< HEAD
					combine: Liferay.AUI.getCombine(),
					filter: LiferayAUI.getFilterConfig(),
=======
					filter: LiferayAUI.getFilterConfig(),
					combine: Liferay.AUI.getCombine(),
>>>>>>> compatible
					modules: {
						'liferay-ddm-form-field-validation': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'validation_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
<<<<<<< HEAD
=======
						},
						'liferay-ddm-form-field-validation-template': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'validation.soy.js',
							requires: [
								'soyutils'
							]
>>>>>>> compatible
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();