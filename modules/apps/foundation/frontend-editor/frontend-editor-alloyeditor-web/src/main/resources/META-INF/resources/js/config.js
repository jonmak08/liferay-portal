;(function() {
	AUI().applyConfig(
		{
			groups: {
				alloyeditor: {
					base: MODULE_PATH + '/js/',
					combine: Liferay.AUI.getCombine(),
<<<<<<< HEAD
					filter: Liferay.AUI.getFilterConfig(),
=======
>>>>>>> compatible
					modules: {
						'liferay-alloy-editor': {
							path: 'alloyeditor.js',
							requires: [
								'aui-component',
<<<<<<< HEAD
								'liferay-portlet-base',
								'timers'
=======
								'liferay-portlet-base'
>>>>>>> compatible
							]
						},
						'liferay-alloy-editor-source': {
							path: 'alloyeditor_source.js',
							requires: [
								'aui-debounce',
								'liferay-fullscreen-source-editor',
								'liferay-source-editor',
								'plugin'
							]
<<<<<<< HEAD
						},
						'liferay-editor-image-uploader': {
							path: 'editor_image_uploader.js',
							requires: [
								'aui-alert',
								'aui-base',
								'aui-progressbar',
								'uploader'
							]
=======
>>>>>>> compatible
						}
					},
					root: MODULE_PATH + '/js/'
				}
			}
		}
	);
})();