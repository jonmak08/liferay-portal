;(function() {
	var PATH_TRASH_TAGLIB = Liferay.ThemeDisplay.getPathContext() + '/o/trash-taglib';

	AUI().applyConfig(
		{
			groups: {
				'trash-taglib': {
					base: PATH_TRASH_TAGLIB + '/',
					modules: {
						'liferay-restore-entry': {
							path: '/restore_entry/js/restore_entry.js',
							requires: [
								'aui-io-plugin-deprecated',
								'aui-io-request',
								'aui-modal',
								'liferay-portlet-base'
							]
						},
					},
					root: PATH_TRASH_TAGLIB + '/'
				}
			}
		}
	);
})();