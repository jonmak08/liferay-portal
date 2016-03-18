AUI.add(
	'liferay-ddm-link-to-page-field',
	function(A) {	

	var AArray = A.Array;

	var Lang = A.Lang;
	
	var INSTANCE_ID_PREFIX = '_INSTANCE_';
	
	var GET_LAYOUTS_URL = themeDisplay.getPathMain() + '/layouts_admin/get_layouts';
	
	var TPL_LAYOUTS_NAVBAR = '<nav class="navbar navbar-default">' +
			'<div class="collapse navbar-collapse" style="height: 48px;">' +
				'<ul class="nav navbar-nav">' +
					'<li class="public {publicLayoutClass}"><a href="javascript:;">' + Liferay.Language.get('public-pages') + '</a></li>' +
					'<li class="private {privateLayoutClass}"><a href="javascript:;">' + Liferay.Language.get('private-pages') + '</a></li>' +
				'</ul>' +
			'</div>' +
		'</nav>';
	
	var TPL_LOADER = '<div class="loading-animation"></div>';
	
	var LinkToPageField = A.Component.create(
			{
				ATTRS: {
				
					container: {
						setter: A.one
					},
					
					delta: {
						value: 20
					},

					selectedLayout: {
						value: null
					}
				},
				
				EXTENDS: A.Base,

				NAME: 'ddm-link-to-page',
				
				prototype: {
					initializer: function(config) {
						var instance = this;

						instance.set('container',config.container);
						instance.set('fieldName',config.fieldName);
						instance.set('fieldNamespace',config.fieldNamespace);
						instance.set('portletNamespace',config.portletNamespace);
												
						var container = instance.get('container');

						container.delegate('click', instance._handleControlButtonsClick, '.btn', instance);
					},

					getInitialLayouts: function(privateLayout, callback) {
						var instance = this;

						A.io.request(
							GET_LAYOUTS_URL,
							{
								after: {
									success: function() {
										var	response = JSON.parse(this.get('responseData'));

										if (response && response.layouts) {
											callback.call(instance, response);
										}
									}
								},
								data: {
									cmd: 'get',
									end: instance.get('delta'),
									expandParentLayouts: true,
									groupId: themeDisplay.getScopeGroupId(),
									p_auth: Liferay.authToken,
									paginate: true,
									privateLayout: privateLayout,
									start: 0
								}
							}
						);
					},

					getInputName: function() {
						var instance = this;
						
						var fieldName = instance.get('fieldName');

						var fieldNamespace =  instance.get('fieldNamespace');

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

							inputNode.set('defaultValue', value);
						}

					},

					syncReadOnlyUI: function() {
						var instance = this;

						var container = instance.get('container');

						var selectButtonNode = container.one('#' + instance.getInputName() + 'SelectButton');

						selectButtonNode.attr('disabled', instance.get('readOnly'));
					},

					_createTreeView: function(node, layouts, privateLayout) {
						var instance = this;

						return new Liferay.LayoutsTree(
							{
								boundingBox: node,
								incomplete: true,
								layouts: layouts,
								maxChildren: instance.get('delta'),
								on: {
									radioNodeCheckedChange: A.bind(instance._onRadioNodeCheckedChange, instance, privateLayout)
								},
								plugins: [
									{
										fn: A.Plugin.LayoutsTreeRadio
									}
								],
								root: {
									defaultParentLayoutId: 0,
									expand: true,
									groupId: themeDisplay.getScopeGroupId(),
									label: 'Root Node',
									privateLayout: privateLayout
								},
								urls: []
							}
						).render();
					},

					_getModalConfig: function() {
						var instance = this;

						return {
							dialog:	{
								destroyOnHide: true,
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

					_handleCancelButtonClick: function(event) {
						var instance = this;

						instance.modal.destroy();
					},

					_handleChooseButtonClick: function(event) {
						var instance = this;

						var selectedLayout = instance.get('selectedLayout');

						instance.setValue(selectedLayout);

						instance.modal.destroy();
					},

					_handleClearButtonClick: function(event) {
						var instance = this;

						instance.setValue('');
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

					_handleNavbarClick: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						event.container.one('.active').removeClass('active');
						currentTarget.addClass('active');

						instance._renderTreeView(currentTarget.test('.private'));
					},

					_handleSelectButtonClick: function() {
						var instance = this;

						var modal = instance._openLinkToPageModal();

						var value = instance.getValue();

						var privateLayout = !!(value && value.privateLayout);

						var navbar = instance._renderNavbar(privateLayout);

						navbar.insertBefore(navbar, modal.bodyNode);

						instance.modal = modal;
						instance.navbar = navbar;

						instance._renderTreeView(privateLayout);
					},

					_onRadioNodeCheckedChange: function(privateLayout, event) {
						var instance = this;

						var node = event.node;

						var treeView = event.target;

						instance.set(
							'selectedLayout',
							{
								groupId: treeView.extractGroupId(node),
								label: node.get('labelEl').text(),
								layoutId: treeView.extractLayoutId(node),
								privateLayout: privateLayout
							}
						);
					},

					_openLinkToPageModal: function() {
						var instance = this;

						var config = instance._getModalConfig();

						var modal = Liferay.Util.Window.getWindow(config);

						return modal.render();
					},

					_renderNavbar: function(privateLayout) {
						var instance = this;

						var navbar = A.Node.create(
							Lang.sub(
								TPL_LAYOUTS_NAVBAR,
								{
									privateLayoutClass: privateLayout ? 'active' : '',
									publicLayoutClass: privateLayout ? '' : 'active'
								}
							)
						);

						navbar.delegate('click', instance._handleNavbarClick, 'li', instance);

						return navbar;
					},

					_renderTreeView: function(privateLayout) {
						var instance = this;

						var modal = instance.modal;

						var bodyNode = modal.bodyNode;

						var loader = A.Node.create(TPL_LOADER);

						if (instance.treeView) {
							instance.treeView.destroy();
						}

						var treeViewNode = A.Node.create('<div></div>');

						bodyNode.empty();
						bodyNode.append(loader);
						bodyNode.append(treeViewNode);

						instance._syncModalHeight();

						instance.getInitialLayouts(
							privateLayout,
							function(layouts) {
								loader.remove();

								instance.treeView = instance._createTreeView(treeViewNode, layouts, privateLayout);

								instance._syncModalHeight();
							}
						);
					},

					_syncModalHeight: function() {
						var instance = this;

						var bodyNode = instance.modal.bodyNode;

						instance.modal.fillHeight(bodyNode);

						bodyNode.set('offsetHeight', Lang.toInt(bodyNode.get('offsetHeight')) - Lang.toInt(instance.navbar.get('offsetHeight')) - 10);
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
	