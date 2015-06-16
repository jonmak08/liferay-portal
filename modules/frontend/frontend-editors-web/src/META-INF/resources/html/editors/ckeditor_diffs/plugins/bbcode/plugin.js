(function() {
	var commandObject = {
		exec: function(editor) {
			editor.focus();
			editor.fire('saveSnapshot');

			var elementPath = new CKEDITOR.dom.elementPath(editor.getSelection().getStartElement());

			var elementAction = 'apply';

			var preElement = new CKEDITOR.style(
				{
					element: 'pre'
				}
			);

			preElement._.enterMode = editor.config.enterMode;

			if (preElement.checkActive(elementPath)) {
				elementAction = 'remove';
			}

			preElement[elementAction](editor.document);

			setTimeout(
				function() {
					editor.fire('saveSnapshot');
				},
				0
			);
		},

		refresh: function( editor, path ) {
			var firstBlock = path.block || path.blockLimit;
			this.setState( editor.elementPath( firstBlock ).contains( 'pre', 1 ) ? CKEDITOR.TRISTATE_ON : CKEDITOR.TRISTATE_OFF );
		},

		context: 'code',

		allowedContent: 'code',
		requiredContent: 'code'
	};

	CKEDITOR.plugins.add(
	'bbcode',
		{
			init: function(editor) {
				var instance = this;

				var path = instance.path;

				var dependencies = [
					CKEDITOR.getUrl(path + 'bbcode_data_processor.js'),
					CKEDITOR.getUrl(path + 'bbcode_parser.js')
				];

				CKEDITOR.scriptLoader.loadScripts(
					dependencies,
					function() {
						var bbcodeDataProcessor = CKEDITOR.plugins.get('bbcode_data_processor');

						bbcodeDataProcessor.init(editor);
					}
				);

				editor.addCommand('code', commandObject);

				editor.ui.addButton(
					'Code',
					{
						command: 'code',
						icon: editor.config.imagesPath + 'code.png',
						label: Liferay.Language.get('code')
					}
				);
			}
		}
	);
})();