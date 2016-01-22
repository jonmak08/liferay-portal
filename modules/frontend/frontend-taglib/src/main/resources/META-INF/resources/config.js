;(function() {
	var PATH_FRONTEND_TAGLIB = Liferay.ThemeDisplay.getPathContext() + '/o/frontend-taglib';

	AUI().applyConfig(
		{
			groups: {
				'frontend-taglib': {
					base: PATH_FRONTEND_TAGLIB + '/',
					modules: {
						'liferay-diff-version-comparator': {
							path: 'diff_version_comparator/js/diff_version_comparator.js',
							requires: [
								'aui-io-request',
								'autocomplete-base',
								'autocomplete-filters',
								'liferay-portlet-base'
							]
						},
						'liferay-management-bar': {
							path: 'management_bar/js/management_bar.js',
							requires: [
								'aui-component',
								'liferay-portlet-base'
							]
						},
						'liferay-input-move-boxes': {
							path: 'input_move_boxes/js/input_move_boxes.js',
							plugins: {
								'liferay-input-move-boxes-touch': {
									condition: {
										name: 'liferay-input-move-boxes-touch',
										trigger: 'liferay-input-move-boxes',
										ua: 'touchMobile'
									}
								}
							},
							requires: [
								'aui-base',
								'aui-toolbar'
							]
						},
						'liferay-input-move-boxes-touch': {
							path: 'input_move_boxes/js/input_move_boxes_touch.js',
							requires: [
								'aui-base',
								'aui-template-deprecated',
								'liferay-input-move-boxes',
								'sortable'
							]
						},
						'liferay-sidebar-panel': {
							path: 'sidebar_panel/js/sidebar_panel.js',
							requires: [
								'aui-base',
								'aui-io-request',
								'aui-parse-content',
								'liferay-portlet-base'
							]
						}
					},
					root: PATH_FRONTEND_TAGLIB + '/'
				}
			}
		}
	);
})();