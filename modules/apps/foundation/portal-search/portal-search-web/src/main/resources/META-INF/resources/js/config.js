;(function() {
	AUI().applyConfig(
		{
			groups: {
				search: {
					base: MODULE_PATH + '/js/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'liferay-search-facet-util': {
							path: 'facet_util.js',
							requires: []
						},
						'liferay-search-modified-facet': {
							path: 'modified_facet.js',
							requires: ['liferay-search-facet-util']
						}
					},
					root: MODULE_PATH + '/js/'
				}
			}
		}
	);
})();