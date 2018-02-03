AUI.add(
	'liferay-workflow-tasks',
	function(A) {
		var WorkflowTasks = {
<<<<<<< HEAD
=======
			moveFormDataFromDialog: function(form) {
				var children = form.get('children');

				var entryActionColumn;
				var updatedComments;
				var updatedContent;

				if (form && form.hasChildNodes() && children.size() >= 2) {
					updatedContent = children.item(0);
					updatedComments = children.item(1);
				}

				if (updatedContent) {
					var contentId = updatedContent.attr('id');

					var originalColumnId = contentId.substring(0, 4);

					if (contentId.search('[a-zA-Z]{4}updateDueDate') != -1) {
						originalColumnId += 'updateAsignee';
					}

					if (originalColumnId) {
						var originalColumnNode = A.one('#' + originalColumnId);

						if (originalColumnNode) {
							entryActionColumn = originalColumnNode.get('parentNode');

							entryActionColumn.append(updatedContent);

							updatedContent.attr('hidden', true);
						}
					}
				}

				if (updatedComments && entryActionColumn) {
					entryActionColumn.append(updatedComments);

					updatedComments.attr('hidden', true);
				}
			},

>>>>>>> compatible
			onTaskClick: function(event, randomId) {
				var instance = this;

				var icon = event.currentTarget;
				var li = icon.get('parentNode');

				event.preventDefault();

				var content = null;

<<<<<<< HEAD
				var height = 400;
=======
				var height = 310;
>>>>>>> compatible

				if (li.hasClass('task-due-date-link')) {
					content = '#' + randomId + 'updateDueDate';

<<<<<<< HEAD
					height = 480;
=======
					height = 410;
>>>>>>> compatible
				}

				var title = icon.text();

<<<<<<< HEAD
				WorkflowTasks._showPopup(icon.attr('href'), A.one(content), title, randomId, height);
			},

			_showPopup: function(url, content, title, randomId, height) {
=======
				WorkflowTasks.showPopup(icon.attr('href'), A.one(content), title, randomId, height);
			},

			showPopup: function(url, content, title, randomId, height) {
>>>>>>> compatible
				var instance = this;

				var form = A.Node.create('<form />');

				form.setAttribute('action', url);
				form.setAttribute('method', 'POST');

				var comments = A.one('#' + randomId + 'updateComments');

<<<<<<< HEAD
				if (comments && !instance._comments[randomId]) {
					instance._comments[randomId] = comments;
				}
				else if (!comments && instance._comments[randomId]) {
					comments = instance._comments[randomId];
				}

				if (content && !instance._content[randomId]) {
					instance._content[randomId] = content;
				}
				else if (!content && title && title.trim().indexOf('Update Due Date') !== -1) {
					content = instance._content[randomId];
				}

=======
>>>>>>> compatible
				if (content) {
					form.append(content);
					content.show();
				}

				if (comments) {
					form.append(comments);
					comments.show();
				}

				var dialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							bodyContent: form,
							destroyOnHide: true,
							height: height,
<<<<<<< HEAD
							resizable: false,
							toolbars: {
								footer: [
									{
										cssClass: 'btn-primary mr-2',
=======
							on: {
								destroy: function() {
									instance.moveFormDataFromDialog(form);
								}
							},
							toolbars: {
								footer: [
									{
										cssClass: 'btn-lg btn-primary',
>>>>>>> compatible
										label: Liferay.Language.get('done'),
										on: {
											click: function() {
												submitForm(form);
											}
										}
									},
									{
<<<<<<< HEAD
										cssClass: 'btn-cancel',
=======
										cssClass: 'btn-cancel btn-lg btn-link',
>>>>>>> compatible
										label: Liferay.Language.get('cancel'),
										on: {
											click: function() {
												dialog.hide();
											}
										}
									}
								],
								header: [
									{
										cssClass: 'close',
										discardDefaultButtonCssClasses: true,
<<<<<<< HEAD
										labelHTML: '<span aria-hidden="true">&times;</span>',
=======
										labelHTML: '<span> \u00D7 </span>',
>>>>>>> compatible
										on: {
											click: function(event) {
												dialog.hide();
											}
										}
									}
								]
							},
							width: 720
						},
						title: A.Lang.String.escapeHTML(title)
					}
				);
<<<<<<< HEAD
			},

			_comments: {},
			_content: {}
=======
			}
>>>>>>> compatible
		};
		Liferay.WorkflowTasks = WorkflowTasks;
	},
	'',
	{
		requires: ['liferay-util-window']
	}
);