AUI.add(
	'liferay-icon',
	function(A) {
		var Icon = {
			register: function(config) {
				var instance = this;

				var doc = A.getDoc();
				var id = '#' + config.id;

				var forcePost = config.forcePost;
				var src = config.src;
				var srcHover = config.srcHover;
				var useDialog = config.useDialog;

				if (srcHover) {
					instance._onMouseOver = A.rbind('_onMouseHover', instance, srcHover);
					instance._onMouseOut = A.rbind('_onMouseHover', instance, src);

					doc.delegate('hover', instance._onMouseOver, instance._onMouseOut, id);
				}

				if (useDialog) {
					doc.delegate('click', instance._useDialog, id);
				}
				else if (forcePost) {
					doc.delegate('click', instance._forcePost, id);
				}
			},

			_forcePost: function(event) {
				var instance = this;

				if (!Liferay.Surface || !Liferay.Surface.app) {
					Liferay.Util.forcePost(event.currentTarget);

					event.preventDefault();
				}
			},

			_onMouseHover: function(event, src) {
				var instance = this;

				var img = event.currentTarget.one('img');

				if (img) {
					img.attr('src', src);
				}
			},

			_useDialog: function(event) {
				Liferay.Util.openInDialog(
					event,
					{
						dialogIframe: {
							bodyCssClass: 'dialog-with-footer'
						}
					}
				);
			}
		};

		Liferay.Icon = Icon;
	},
	'',
	{
		requires: ['aui-base', 'liferay-util-window']
	}
);