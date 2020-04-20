/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

AUI.add(
	'liferay-admin',
	A => {
		var Lang = A.Lang;

		var IN_PROGRESS_SELECTOR = '.background-task-status-in-progress';

		var INTERVAL_RENDER_IDLE = 60000;

		var INTERVAL_RENDER_IN_PROGRESS = 2000;

		var MAP_DATA_PARAMS = {
			classname: 'className',
		};

		var STR_CLICK = 'click';

		var STR_FORM = 'form';

		var STR_INDEX_ACTIONS_PANEL = 'indexActionsPanel';

		var STR_URL = 'url';

		var Admin = A.Component.create({
			ATTRS: {
				form: {
					setter: A.one,
					value: null,
				},

				indexActionWrapperSelector: {
					validator: Lang.isString,
					value: null,
				},

				indexActionsPanel: {
					validator: Lang.isString,
					value: null,
				},

				redirectUrl: {
					validator: Lang.isString,
					value: null,
				},

				submitButton: {
					validator: Lang.isString,
					value: null,
				},

				url: {
					value: null,
				},
			},

			AUGMENTS: [Liferay.PortletBase],

			EXTENDS: A.Base,

			NAME: 'admin',

			prototype: {
				_addInputsFromData(data) {
					var instance = this;

					var form = instance.get(STR_FORM);

					var inputsArray = A.Object.map(data, (value, key) => {
						key = MAP_DATA_PARAMS[key] || key;

						var nsKey = instance.ns(key);

						return (
							'<input id="' +
							nsKey +
							'" name="' +
							nsKey +
							'" type="hidden" value="' +
							value +
							'" />'
						);
					});

					form.append(inputsArray.join(''));
				},

				_isBackgroundTaskInProgress() {
					var instance = this;

					var indexActionsNode = A.one(
						instance.get(STR_INDEX_ACTIONS_PANEL)
					);

					return !!(
						indexActionsNode &&
						indexActionsNode.one(IN_PROGRESS_SELECTOR)
					);
				},

				_onSubmit(event) {
					var instance = this;

					var data = event.currentTarget.getData();
					var form = instance.get(STR_FORM);

					var redirect = instance.one('#redirect', form);

					if (redirect) {
						redirect.val(instance.get('redirectUrl'));
					}

					instance._addInputsFromData(data);

					submitForm(form, instance.get(STR_URL));
				},

				_updateIndexActions() {
					var instance = this;

					var currentAdminIndexPanel = A.one(
						instance.get(STR_INDEX_ACTIONS_PANEL)
					);

					if (currentAdminIndexPanel) {
						Liferay.Util.fetch(instance.get(STR_URL), {
							method: 'POST',
						})
							.then(response => {
								return response.text();
							})
							.then(response => {
								var responseDataNode = A.Node.create(response);

								// Replace each progress bar

								var responseAdminIndexPanel = responseDataNode.one(
									instance.get(STR_INDEX_ACTIONS_PANEL)
								);

								var responseAdminIndexNodeList = responseAdminIndexPanel.all(
									instance.get('indexActionWrapperSelector')
								);

								var currentAdminIndexNodeList = currentAdminIndexPanel.all(
									instance.get('indexActionWrapperSelector')
								);

								currentAdminIndexNodeList.each(
									(currentNode, index) => {
										var responseAdminIndexNode = responseAdminIndexNodeList.item(
											index
										);

										var inProgress =
											currentNode.one(
												IN_PROGRESS_SELECTOR
											) ||
											responseAdminIndexNode.one(
												IN_PROGRESS_SELECTOR
											);

										if (inProgress) {
											currentNode.replace(
												responseAdminIndexNode
											);
										}
									}
								);

								// Replace control menu bar

								var controlMenuId =
									'#' + instance.ns('controlMenu');

								var currentControlMenu = A.one(controlMenuId);

								var responseControlMenu = responseDataNode.one(
									controlMenuId
								);

								if (currentControlMenu && responseControlMenu) {
									currentControlMenu.replace(
										responseControlMenu
									);
								}

								// Start timeout for refreshing the data

								var renderInterval = INTERVAL_RENDER_IDLE;

								if (instance._isBackgroundTaskInProgress()) {
									renderInterval = INTERVAL_RENDER_IN_PROGRESS;
								}

								instance._laterTimeout = A.later(
									renderInterval,
									instance,
									'_updateIndexActions'
								);
							});
					}
				},

				bindUI() {
					var instance = this;

					instance._eventHandles.push(
						instance
							.get(STR_FORM)
							.delegate(
								STR_CLICK,
								A.bind('_onSubmit', instance),
								instance.get('submitButton')
							)
					);
				},

				destructor() {
					var instance = this;

					A.Array.invoke(instance._eventHandles, 'detach');

					instance._eventHandles = null;

					A.clearTimeout(instance._laterTimeout);
				},

				initializer() {
					var instance = this;

					instance._eventHandles = [];

					instance.bindUI();

					instance._laterTimeout = A.later(
						INTERVAL_RENDER_IN_PROGRESS,
						instance,
						'_updateIndexActions'
					);
				},
			},
		});

		Liferay.Portlet.Admin = Admin;
	},
	'',
	{
		requires: [
			'aui-io-plugin-deprecated',
			'liferay-portlet-base',
			'querystring-parse',
		],
	}
);
