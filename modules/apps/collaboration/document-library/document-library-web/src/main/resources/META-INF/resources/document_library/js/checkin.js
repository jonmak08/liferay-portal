AUI.add(
	'document-library-checkin',
	function(A) {
		var DocumentLibraryCheckin = {
<<<<<<< HEAD
			showDialog: function(contentId, title, saveButton, cancelLabel) {
=======
			showDialog: function(contentId, title, onSave) {
				var instance = this;

>>>>>>> compatible
				var versionDetailsDialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							bodyContent: A.one('#' + contentId).html(),
<<<<<<< HEAD
							destroyOnHide: true,
							height: 400,
							'toolbars.footer': [
								{
									cssClass: 'btn-primary',
									label: saveButton.label,
									on: {
										click: saveButton.callback
									}
								},
								{
									cssClass: 'btn-link',
									label: cancelLabel,
									on: {
										click: function() {
											Liferay.Util.getWindow(contentId + 'Dialog').destroy();
										}
									}
								}
							],
							width: 700
						},
						dialogIframe: {
							bodyCssClass: 'dialog-with-footer'
						},
						id: contentId + 'Dialog',
=======
							destroyOnHide: true
						},
>>>>>>> compatible
						title: title
					}
				);

<<<<<<< HEAD
=======
				var versionDetailsDialogBoundingBox = versionDetailsDialog.get('boundingBox');

				var saveButton = versionDetailsDialogBoundingBox.one('.btn-primary');

				saveButton.on('click', onSave);

				var cancelButton = versionDetailsDialogBoundingBox.one('.btn-cancel');

				cancelButton.on(
					'click',
					function(event) {
						versionDetailsDialog.destroy();
					}
				);

>>>>>>> compatible
				versionDetailsDialog.render();
			}
		};

		Liferay.Portlet.DocumentLibrary.Checkin = DocumentLibraryCheckin;
	},
	'',
	{
		requires: ['liferay-document-library', 'liferay-util-window']
	}
);