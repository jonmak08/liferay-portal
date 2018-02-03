;(function() {
	var LiferayAUI = Liferay.AUI;

	AUI().applyConfig(
		{
			groups: {
				'field-select': {
					base: MODULE_PATH + '/',
<<<<<<< HEAD
					combine: Liferay.AUI.getCombine(),
					filter: LiferayAUI.getFilterConfig(),
=======
					filter: LiferayAUI.getFilterConfig(),
					combine: Liferay.AUI.getCombine(),
>>>>>>> compatible
					modules: {
						'liferay-ddm-form-field-select': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'select_field.js',
							requires: [
<<<<<<< HEAD
								'aui-tooltip',
								'liferay-ddm-form-field-select-search-support',
								'liferay-ddm-form-renderer-field',
								'liferay-ddm-soy-template-util'
							]
						},
						'liferay-ddm-form-field-select-search-support': {
							path: 'select_search_support.js',
							requires: [
								'highlight',
								'liferay-ddm-soy-template-util'
=======
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-select-template': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'select.soy.js',
							requires: [
								'soyutils'
>>>>>>> compatible
							]
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();