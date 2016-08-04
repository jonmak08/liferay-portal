AUI.add(
	'liferay-ddm-link-to-page-field',
	function(A) {
		var Lang = A.Lang;

		var TPL_ICON_CARET = '<span class="collapse-icon-closed"><span class="icon-caret-right"></span></span>';

		var TPL_LAYOUTS_NAVBAR = '<nav class="navbar navbar-default">' +
				'<div class="collapse navbar-collapse" style="height: 48px;">' +
					'<ul class="nav navbar-nav">' +
						'<li class="public {publicLayoutClass}"><a href="javascript:;">' + Liferay.Language.get('public-pages') + '</a></li>' +
						'<li class="private {privateLayoutClass}"><a href="javascript:;">' + Liferay.Language.get('private-pages') + '</a></li>' +
					'</ul>' +
				'</div>' +
			'</nav>';

		var TPL_LOADER = '<span class="linear loading-icon"></span>';

		var TPL_PAGE = '<li class="lfr-ddm-link" data-groupId="{groupId}" data-layoutId="{layoutId}" data-nodeType="{nodeType}" data-privateLayout="{privateLayout}">' +
				'<input class="lfr-ddm-page-radio" {checked} name="lfr-ddm-page" type="radio" />' +
				'<a class="collapsed collapse-icon lfr-ddm-page-label" href="javascript:;">{pageTitle}{icon}</a>' +
			'</li>';

		var TPL_PAGES_BREADCRUMB = '<ul class="breadcrumb lfr-ddm-breadcrumb"></ul>';

		var TPL_PAGES_BREADCRUMB_ELEMENT = '<li class="lfr-ddm-breadcrumb-element" data-groupId={groupId} data-layoutId={layoutId} data-privateLayout={privateLayout}>' +
				'<a title="{label}">{label}</a>' +
				'<span class="lfr-ddm-breadcrumb-element-separator">&gt;</span>' +
			'</li>';

		var TPL_PAGES_CONTAINER = '<ul class="lfr-ddm-pages-container nav"></ul>';

		var LinkToPageField = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: A.one
					},

					delta: {
						value: 10
					},

					selectedLayout: {
						value: null
					},

					selectedLayoutPath: {
						value: null
					}
				},

				EXTENDS: A.Base,

				NAME: 'ddm-link-to-page',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance.set('container', config.container);
						instance.set('fieldName', config.fieldName);
						instance.set('fieldNamespace', config.fieldNamespace);
						instance.set('portletNamespace', config.portletNamespace);

						var container = instance.get('container');

						instance._currentParentLayoutId = 0;

						instance._loadingAnimationNode = A.Node.create(TPL_LOADER);

						instance._cache = {};

						instance.after('selectedLayoutChange', instance._afterSelectedLayoutChange);
						instance.after('selectedLayoutPathChange', instance._afterSelectedLayoutPathChange);

						container.delegate('click', instance._handleControlButtonsClick, '.btn', instance);
					},

					getInputName: function() {
						var instance = this;

						var fieldName = instance.get('fieldName');

						var fieldNamespace = instance.get('fieldNamespace');

						var portletNamespace = instance.get('portletNamespace');

						return portletNamespace + fieldName + fieldNamespace;
					},

					getInputNode: function() {
						var instance = this;

						return instance.get('container').one('[name=' + instance.getInputName() + ']');
					},

					getLabelNode: function() {
						var instance = this;

						return instance.get('container').one('.control-label');
					},

					getParsedValue: function(value) {
						var instance = this;

						if (Lang.isString(value)) {
							if (value) {
								value = JSON.parse(value);
							}
							else {
								value = {};
							}
						}

						return value;
					},

					getSelectedLayout: function() {
						var instance = this;

						var selectedLayout = instance.get('selectedLayout');

						if (selectedLayout) {
							return selectedLayout;
						}

						var layoutValue = instance.getParsedValue(instance.getValue());

						if (layoutValue.layoutId) {
							return layoutValue;
						}

						return null;
					},

					getSelectedLayoutPath: function() {
						var instance = this;

						var selectedLayoutPath = instance.get('selectedLayoutPath');

						if (selectedLayoutPath) {
							return selectedLayoutPath;
						}

						var value = instance.getValue();

						var privateLayout = !!(value && value.privateLayout);

						var layoutsRoot = {
							groupId: themeDisplay.getScopeGroupId(),
							label: Liferay.Language.get('all'),
							layoutId: 0,
							privateLayout: privateLayout
						};

						return [layoutsRoot];
					},

					getValue: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						return Lang.String.unescapeHTML(inputNode.val());
					},

					setValue: function(value) {
						var instance = this;

						var container = instance.get('container');

						var inputName = instance.getInputName();

						var layoutNameNode = container.one('#' + inputName + 'LayoutName');

						var parsedValue = instance.getParsedValue(value);

						if (parsedValue && parsedValue.layoutId) {
							if (parsedValue.label) {
								layoutNameNode.val(parsedValue.label);
								layoutNameNode.attr('defaultValue', parsedValue.label);
							}

							value = JSON.stringify(parsedValue);
						}
						else {
							layoutNameNode.val('');

							value = '';
						}

						var clearButtonNode = container.one('#' + inputName + 'ClearButton');

						clearButtonNode.toggle(!!value);

						var inputNode = instance.getInputNode();

						if (Lang.isValue(value)) {
							inputNode.val(value);
							inputNode.attr('defaultValue', value);
						}

					},

					syncReadOnlyUI: function() {
						var instance = this;

						var container = instance.get('container');

						var selectButtonNode = container.one('#' + instance.getInputName() + 'SelectButton');

						selectButtonNode.attr('disabled', instance.get('readOnly'));
					},

					_addBreadcrumbElement: function(label, layoutId, groupId, privateLayout) {
						var instance = this;

						var breadcrumbNode = instance._modal.bodyNode.one('.lfr-ddm-breadcrumb');

						var breadcrumbElementNode = A.Node.create(
							Lang.sub(
								TPL_PAGES_BREADCRUMB_ELEMENT,
								{
									groupId: groupId,
									label: label,
									layoutId: layoutId,
									privateLayout: privateLayout
								}
							)
						);

						breadcrumbNode.append(breadcrumbElementNode);
					},

					_addListElement: function(layout, container, selected, prepend) {
						var instance = this;

						var entryNode = A.Node.create(
							Lang.sub(
								TPL_PAGE,
								{
									checked: selected ? 'checked="checked"' : '',
									groupId: layout.groupId,
									icon: layout.hasChildren ? TPL_ICON_CARET : '',
									layoutId: layout.layoutId,
									nodeType: layout.hasChildren ? 'root' : 'leaf',
									pageTitle: layout.name,
									privateLayout: layout.privateLayout
								}
							)
						);

						if (prepend) {
							container.prepend(entryNode);
						}
						else {
							container.append(entryNode);
						}

						if (selected) {
							entryNode.scrollIntoView();
						}
					},

					_afterSelectedLayoutChange: function(event) {
						var instance = this;

						var modal = instance._modal;

						if (modal) {
							var notSelected = !event.newVal;

							var selectButton = modal.get('toolbars.footer')[0];

							var boundingBox = selectButton.boundingBox;

							boundingBox.attr('disabled', notSelected);
							boundingBox.toggleClass('disabled', notSelected);
						}
					},

					_afterSelectedLayoutPathChange: function(event) {
						var instance = this;

						instance._renderBreadcrumb(event.newVal);
					},

					_canLoadMore: function(key, start, end) {
						var instance = this;

						var cache = instance._getCache(key);

						return !cache || start < cache.start || end > cache.end;
					},

					_cleanSelectedLayout: function() {
						var instance = this;

						var checkedElement = instance._modal.bodyNode.one('.lfr-ddm-page-radio:checked');

						if (checkedElement) {
							checkedElement.attr('checked', false);

							instance.set('selectedLayout', null);
						}
					},

					_getCache: function(key) {
						var instance = this;

						var cache;

						if (instance._cache && instance._cache[key]) {
							cache = instance._cache[key];
						}

						return cache;
					},

					_getModalConfig: function() {
						var instance = this;

						return {
							dialog:	{
								cssClass: 'lfr-ddm-link-to-page-modal',
								height: 600,
								modal: true,
								on: {
									destroy: function() {
										instance.set('selectedLayout', null);
									}
								},
								resizable: false,
								toolbars: {
									footer: [
										{
											cssClass: 'btn-lg btn-primary',
											disabled: !instance.getSelectedLayout(),
											label: Liferay.Language.get('select'),
											on: {
												click: A.bind(instance._handleChooseButtonClick, instance)
											}
										},
										{
											cssClass: 'btn-lg btn-link',
											label: Liferay.Language.get('cancel'),
											on: {
												click: A.bind(instance._handleCancelButtonClick, instance)
											}
										}
									],
									header: [
										{
											cssClass: 'close',
											discardDefaultButtonCssClasses: true,
											on: {
												click: A.bind(instance._handleCancelButtonClick, instance)
											}
										}
									]
								},
								width: 400
							},
							title: Liferay.Language.get('select-layout')
						};
					},

					_handleBreadcrumbElementClick: function(event) {
						var instance = this;

						var currentTargetLayoutId = Number(event.currentTarget.getData('layoutId'));

						var selectedLayoutPath = instance.getSelectedLayoutPath();

						var lastLayoutIndex = selectedLayoutPath.length - 1;

						var lastLayout = selectedLayoutPath[lastLayoutIndex];

						var clickedLastElement = Number(lastLayout.layoutId) === currentTargetLayoutId;

						if (!clickedLastElement) {
							instance._cleanSelectedLayout();

							while (!clickedLastElement) {
								if (Number(lastLayout.layoutId) !== currentTargetLayoutId) {
									selectedLayoutPath.pop();

									lastLayoutIndex = selectedLayoutPath.length - 1;

									lastLayout = selectedLayoutPath[lastLayoutIndex];
								}
								else {
									clickedLastElement = true;

									var groupId = lastLayout.groupId;

									var privateLayout = lastLayout.privateLayout;

									instance._currentParentLayoutId = Number(currentTargetLayoutId);

									var bodyNode = instance._modal.bodyNode;

									var listNode = bodyNode.one('.lfr-ddm-pages-container');

									listNode.empty();

									instance._showLoader(listNode);

									listNode.addClass('top-ended');

									instance._requestInitialLayouts(currentTargetLayoutId, groupId, privateLayout, instance._renderLayouts);
								}
							}

							instance.set('selectedLayoutPath', selectedLayoutPath);
						}
					},

					_handleCancelButtonClick: function(event) {
						var instance = this;

						instance._modal.hide();
					},

					_handleChooseButtonClick: function() {
						var instance = this;

						var selectedLayout = instance.getSelectedLayout();

						instance.setValue(selectedLayout);

						instance._modal.hide();
					},

					_handleClearButtonClick: function() {
						var instance = this;

						instance.setValue('');
						instance.set('selectedLayout', instance.getSelectedLayoutPath()[0]);
					},

					_handleControlButtonsClick: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						if (currentTarget.test('.select-button')) {
							instance._handleSelectButtonClick(event);
						}
						else {
							instance._handleClearButtonClick(event);
						}
					},

					_handleListEntryClick: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var label = event.currentTarget.text();

						var layoutId = event.currentTarget.getData('layoutId');

						var groupId = Number(event.currentTarget.getData('groupId'));

						var privateLayout = A.DataType.Boolean.parse(event.currentTarget.getData('privateLayout'));

						if (event.target.hasClass('lfr-ddm-page-label')) {
							if (currentTarget.getData('nodeType') === 'root') {
								instance._cleanSelectedLayout();

								instance._currentParentLayoutId = layoutId;

								instance._showLoader(currentTarget);

								var selectedLayoutPath = instance.getSelectedLayoutPath();

								selectedLayoutPath.push(
									{
										groupId: groupId,
										label: label,
										layoutId: layoutId,
										privateLayout: privateLayout
									}
								);

								instance.set('selectedLayoutPath', selectedLayoutPath);

								var listNode = instance._modal.bodyNode.one('.lfr-ddm-pages-container');

								listNode.addClass('top-ended');

								instance._requestInitialLayouts(layoutId, groupId, privateLayout, instance._renderLayouts);
							}
						}
						else if (event.target.hasClass('lfr-ddm-page-radio')) {
							var path = instance.getSelectedLayoutPath();

							instance.set(
								'selectedLayout',
								{
									groupId: groupId,
									label: label,
									layoutId: layoutId,
									path: path,
									privateLayout: privateLayout
								}
							);
						}
					},

					_handleModalScroll: function(event) {
						var instance = this;

						var listNode = event.currentTarget;

						var innerHeight = listNode.innerHeight();

						var scrollHeight = listNode.get('scrollHeight');
						var scrollTop = listNode.get('scrollTop');

						var delta = instance.get('delta');

						var groupId = themeDisplay.getScopeGroupId();

						var parentLayoutId = instance._currentParentLayoutId;

						var privateLayout = !!instance._navbar.one('.private').hasClass('active');

						var key = [parentLayoutId, groupId, privateLayout].join('-');

						if (!instance._isListNodeEmpty(key)) {
							var cache = instance._getCache(key);

							var end = cache.end;
							var start = cache.start;

							if (scrollTop === 0) {
								start -= delta;

								if (start < 0) {
									start = 0;
									end = cache.start;
								}

								if (end > start) {
									listNode.prepend(instance._loadingAnimationNode);

									instance._requestLayouts(parentLayoutId, groupId, privateLayout, start, end, A.rbind('_renderLayoutsFragment', instance, key, 'up'));
								}
							}
							else if (scrollTop + innerHeight === scrollHeight) {
								start = end;
								end = start + delta;

								if (start <= cache.total) {
									listNode.append(instance._loadingAnimationNode);

									instance._requestLayouts(parentLayoutId, groupId, privateLayout, start, end, A.rbind('_renderLayoutsFragment', instance, key));
								}

							}
						}
					},

					_handleNavbarClick: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						event.container.one('.active').removeClass('active');

						currentTarget.addClass('active');

						instance._cleanSelectedLayout();
						instance._renderLayoutsList(currentTarget.test('.private'));

					},

					_handleSelectButtonClick: function() {
						var instance = this;

						instance._openLinkToPageModal();
					},

					_hideLoader: function() {
						var instance = this;

						instance._loadingAnimationNode.remove();
					},

					_initBreadcrumb: function() {
						var instance = this;

						var breadcrumbNode = A.Node.create(TPL_PAGES_BREADCRUMB);

						instance._modal.bodyNode.append(breadcrumbNode);

						breadcrumbNode.delegate('click', instance._handleBreadcrumbElementClick, '.lfr-ddm-breadcrumb-element', instance);
					},

					_initLayoutsList: function() {
						var instance = this;

						var bodyNode = instance._modal.bodyNode;

						if (!bodyNode.one('.lfr-ddm-pages-container')) {
							var navNode = A.Node.create(TPL_PAGES_CONTAINER);

							bodyNode.append(navNode);

							navNode.delegate('click', instance._handleListEntryClick, '.lfr-ddm-link', instance);
						}
					},

					_isListNodeEmpty: function(key) {
						var instance = this;

						var cache = instance._getCache(key);

						return !(cache && cache.layouts);
					},

					_openLinkToPageModal: function() {
						var instance = this;

						var value = instance.getParsedValue(instance.getValue());

						var privateLayout = !!value.privateLayout;

						var modal = instance._modal;

						if (!modal) {
							var config = instance._getModalConfig();

							modal = Liferay.Util.Window.getWindow(config);

							modal.render();

							A.after('windowresize', A.bind(instance._syncModalHeight, instance));

							instance._modal = modal;

							instance._initBreadcrumb();
							instance._initLayoutsList();

							instance._renderNavbar(privateLayout);
							instance._renderBreadcrumb(instance.getSelectedLayoutPath());
							instance._renderLayoutsList(privateLayout);

							var listNode = modal.bodyNode.one('.lfr-ddm-pages-container');

							listNode.on('scroll', instance._handleModalScroll, instance);
						}
						else {
							var path = instance.getSelectedLayoutPath();

							if (path) {
								instance.set(
									'selectedLayout',
									{
										groupId: value.groupId,
										label: value.label,
										layoutId: value.layoutId,
										path: path.slice(),
										privateLayout: privateLayout
									}
								);
							}

							instance._renderLayoutsList(privateLayout);
						}

						modal.show();

						instance._syncModalHeight();
					},

					_renderBreadcrumb: function(layoutsPath) {
						var instance = this;

						var bodyNode = instance._modal.bodyNode;

						var breadcrumbContainer = bodyNode.one('.lfr-ddm-breadcrumb');

						breadcrumbContainer.empty();

						var layoutsPathLenght = layoutsPath ? layoutsPath.length : -1;

						for (var index = 0; index < layoutsPathLenght; index++) {
							var layoutPath = layoutsPath[index];

							instance._addBreadcrumbElement(layoutPath.label, layoutPath.layoutId, layoutPath.groupId, layoutPath.privateLayout);
						}
					},

					_renderLayouts: function(layouts) {
						var instance = this;

						var bodyNode = instance._modal.bodyNode;

						var listNode = bodyNode.one('.lfr-ddm-pages-container');

						var selectedLayout = instance.getSelectedLayout();

						listNode.empty();

						layouts.forEach(
							function(layout) {
								var selected = selectedLayout &&
									layout.layoutId.toString() === selectedLayout.layoutId &&
									layout.privateLayout === selectedLayout.privateLayout;

								instance._addListElement(layout, listNode, selected);
							}
						);

						instance._syncModalHeight();
					},

					_renderLayoutsFragment: function(layouts, key, direction) {
						var instance = this;

						var bodyNode = instance._modal.bodyNode;

						var index;

						var listNode = bodyNode.one('.lfr-ddm-pages-container');

						instance._hideLoader();

						var total = layouts.length;

						if (direction === 'up') {
							var cache = instance._getCache(key);

							listNode.toggleClass('top-ended', cache.start === 0);

							for (index = total - 1; index >= 0; index--) {
								instance._addListElement(layouts[index], listNode, false, true);
							}

							if (cache.start > 0 && listNode.get('scrollTop') === 0) {
								listNode.set('scrollTop', 60);
							}
						}
						else {
							for (index = 0; index < total; index++) {
								instance._addListElement(layouts[index], listNode, false);
							}
						}

						instance._syncModalHeight();
					},

					_renderLayoutsList: function(privateLayout) {
						var instance = this;

						var bodyNode = instance._modal.bodyNode;

						var listNode = bodyNode.one('.lfr-ddm-pages-container');

						instance._showLoader(listNode);

						instance._syncModalHeight();

						var selectedLayout = instance.getSelectedLayout();

						if (selectedLayout && selectedLayout.layoutId &&
								selectedLayout.privateLayout === privateLayout) {

							var groupId = themeDisplay.getScopeGroupId();

							instance._requestSiblingLayouts(
								groupId,
								privateLayout,
								function(layouts) {
									var key = [instance._currentParentLayoutId, groupId, privateLayout].join('-');

									var cache = instance._getCache(key);

									listNode.toggleClass('top-ended', cache.start === 0);

									instance._renderLayouts(layouts);

									if (cache.start > 0 && listNode.get('scrollTop') === 0) {
										listNode.set('scrollTop', 50);
									}

									instance._hideLoader();
								}
							);
						}
						else {
							listNode.addClass('top-ended');

							instance._requestInitialLayouts(0, themeDisplay.getScopeGroupId(), privateLayout, instance._renderLayouts);
						}
					},

					_renderNavbar: function(privateLayout) {
						var instance = this;

						var navbar = instance._navbar;

						if (!navbar) {
							navbar = A.Node.create(
								Lang.sub(
									TPL_LAYOUTS_NAVBAR,
									{
										privateLayoutClass: privateLayout ? 'active' : '',
										publicLayoutClass: privateLayout ? '' : 'active'
									}
								)
							);

							navbar.delegate('click', instance._handleNavbarClick, 'li', instance);

							instance._navbar = navbar;

							navbar.insertBefore(navbar, instance._modal.bodyNode);
						}
					},

					_requestInitialLayouts: function(parentLayoutId, groupId, privateLayout, callback) {
						var instance = this;

						var end = instance.get('delta');

						var start = 0;

						instance._requestLayouts(parentLayoutId, groupId, privateLayout, start, end, callback);
					},

					_requestLayouts: function(parentLayoutId, groupId, privateLayout, start, end, callback) {
						var instance = this;

						var key = [parentLayoutId, groupId, privateLayout].join('-');

						var cache = instance._getCache(key);

						if (!cache || start <= cache.total) {
							if (instance._canLoadMore(key, start, end)) {
								A.io.request(
									themeDisplay.getPathMain() + '/layouts_admin/get_layouts',
									{
										after: {
											success: function() {
												var	response = JSON.parse(this.get('responseData'));

												var layouts = response && response.layouts;

												if (layouts) {
													instance._updateCache(key, layouts, start, end, response.total);

													callback.call(instance, layouts);
												}
											}
										},
										data: {
											cmd: 'get',
											end: end,
											expandParentLayouts: false,
											groupId: groupId,
											p_auth: Liferay.authToken,
											paginate: true,
											parentLayoutId: parentLayoutId,
											privateLayout: privateLayout,
											start: start
										}
									}
								);
							}
							else if (cache) {
								callback.call(instance, cache.layouts);
							}
						}
					},

					_requestSiblingLayouts: function(groupId, privateLayout, callback) {
						var instance = this;

						var cache;

						var path = instance.getSelectedLayoutPath();

						var lastIndex = path.length - 1;

						if (lastIndex >= 0) {
							var parentLayout = path[lastIndex];

							var key = [parentLayout.layoutId, parentLayout.groupId, parentLayout.privateLayout].join('-');

							cache = instance._getCache(key);
						}

						if (cache) {
							callback.call(instance, cache.layouts);
						}
						else {
							var selectedLayout = instance.getSelectedLayout();

							A.io.request(
								themeDisplay.getPathMain() + '/layouts_admin/get_layouts',
								{
									after: {
										success: function() {
											var	response = JSON.parse(this.get('responseData'));

											var layouts = response && response.layouts;

											if (layouts) {
												var parentLayoutId = response.ancestorLayoutIds[0];

												if (!parentLayoutId) {
													parentLayoutId = 0;
												}

												var key = [parentLayoutId, groupId, privateLayout].join('-');

												var start = response.start;

												var end = start + layouts.length;

												instance._currentParentLayoutId = parentLayoutId;

												instance._setSelectedLayoutPath(groupId, privateLayout, response);

												instance._updateCache(key, layouts, start, end, response.total);

												callback.call(instance, layouts);
											}
										}
									},
									data: {
										cmd: 'getSiblingLayoutsJSON',
										expandParentLayouts: false,
										groupId: groupId,
										layoutId: selectedLayout.layoutId,
										max: instance.get('delta'),
										p_auth: Liferay.authToken,
										paginate: true,
										privateLayout: privateLayout
									}
								}
							);
						}
					},

					_setSelectedLayoutPath: function(groupId, privateLayout, response) {
						var instance = this;

						var ancestorLayoutIds = response.ancestorLayoutIds;

						if (ancestorLayoutIds) {
							var selectedLayoutPath = [instance.getSelectedLayoutPath()[0]];

							var ancestorLayoutNames = response.ancestorLayoutNames;

							for (var index = ancestorLayoutIds.length - 1; index >= 0; index--) {
								selectedLayoutPath.push(
									{
										groupId: groupId,
										label: ancestorLayoutNames[index],
										layoutId: ancestorLayoutIds[index],
										privateLayout: privateLayout
									}
								);
							}

							instance.set('selectedLayoutPath', selectedLayoutPath);
						}
					},

					_showLoader: function(node) {
						var instance = this;

						instance._loadingAnimationNode.appendTo(node);
					},

					_syncModalHeight: function() {
						var instance = this;

						var modal = instance._modal;

						var bodyNode = modal.bodyNode;

						modal.fillHeight(bodyNode);

						bodyNode.set('offsetHeight', Lang.toInt(bodyNode.get('offsetHeight')) - Lang.toInt(instance._navbar.get('offsetHeight')));
					},

					_updateCache: function(key, layouts, start, end, total) {
						var instance = this;

						var cache = instance._cache[key];

						if (!cache) {
							var path = instance.getSelectedLayoutPath();

							if (!path) {
								return;
							}

							cache = {
								end: end,
								layouts: layouts,
								path: path.slice(),
								start: start,
								total: total
							};

							instance._cache[key] = cache;
						}
						else {
							var cachedLayouts = cache.layouts || [];

							if (cache.start > start) {
								cachedLayouts = layouts.concat(cachedLayouts);

								cache.start = start;
							}

							if (cache.end < end) {
								cachedLayouts = cachedLayouts.concat(layouts);

								cache.end = end;
							}

							cache.layouts = cachedLayouts;
						}
					}

				}
			}
		);

		A.LinkToPageField = LinkToPageField;
	},
	'',
	{
		requires: ['aui-base', 'aui-datatable', 'aui-datatype', 'aui-image-viewer', 'aui-io-request', 'aui-parse-content', 'aui-set', 'aui-sortable-list', 'json', 'liferay-form', 'liferay-item-selector-dialog', 'liferay-layouts-tree', 'liferay-layouts-tree-radio', 'liferay-layouts-tree-selectable', 'liferay-map-base', 'liferay-notice', 'liferay-portlet-url', 'liferay-translation-manager']
	}
);