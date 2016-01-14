;(function() {
	var PATH_ASSET_TAGLIB = Liferay.ThemeDisplay.getPathContext() + '/o/asset-taglib';

	AUI().applyConfig(
		{
			groups: {
				'asset-taglib': {
					base: PATH_ASSET_TAGLIB + '/',
					modules: {
						'liferay-asset-addon-entry-selector': {
							path: 'asset_addon_entry_selector/js/asset_addon_entry_selector.js',
							requires: [
								'aui-component',
								'liferay-portlet-base',
								'liferay-util-window'
							]
						}
					},
					root: PATH_ASSET_TAGLIB + '/'
				}
			}
		}
	);
})();