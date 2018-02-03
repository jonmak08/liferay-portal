Liferay.provide(
	window,
	'${namespace}showVersionDetailsDialog',
	function(saveURL) {
		Liferay.Portlet.DocumentLibrary.Checkin.showDialog(
			'${namespace}versionDetails',
			'${dialogTitle}',
<<<<<<< HEAD
			{
				label: '${dialogSaveButtonLabel}',
				callback: function(event) {
					var $ = AUI.$;

					var portletURL = saveURL;

					var majorVersionNode = $("input:radio[name='${namespace}versionDetailsMajorVersion']:checked");

					portletURL += '&${namespace}majorVersion=' + encodeURIComponent(majorVersionNode.val());

					var changeLogNode = $('#${namespace}versionDetailsChangeLog');

					portletURL += '&${namespace}changeLog=' + encodeURIComponent(changeLogNode.val());

					window.location.href = portletURL;
				}
			},
			'${dialogCancelButtonLabel}'
=======
			function(event) {
				var $ = AUI.$;

				var portletURL = new Liferay.PortletURL(null, null, saveURL);

				var majorVersionNode = $("input:radio[name='${namespace}versionDetailsMajorVersion']:checked");

				portletURL.setParameter('majorVersion', majorVersionNode.val());

				var changeLogNode = $('#${namespace}versionDetailsChangeLog');

				portletURL.setParameter('changeLog', changeLogNode.val());

				window.location.href = portletURL.toString();
			}
>>>>>>> compatible
		);
	},
	['document-library-checkin', 'liferay-portlet-url']
);