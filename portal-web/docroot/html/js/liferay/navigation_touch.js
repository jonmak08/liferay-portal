AUI.add(
	'liferay-navigation-touch',
	function(A) {
		var Util = Liferay.Util;

		var SELECTOR_DIRECT_ANCHOR = ' > a';

		var SELECTOR_DRAG_HANDLE = '.drag-handle';

		var SELECTOR_LFR_NAV_SORTABLE = '.lfr-nav-sortable';

		var TPL_DRAG_HANDLE = '<span class="drag-handle"><i class="icon-reorder"></i></span>';

		var afterMakeSortable = Liferay.Navigation.prototype._afterMakeSortable;

		A.mix(
			Liferay.Navigation.prototype,
			{
				_afterMakeSortable: function(sortable) {
					var instance = this;

					var navBlock = instance.get('navBlock');

					var navItems = navBlock.all(instance._navItemSelector + SELECTOR_DIRECT_ANCHOR);

					instance._createDragHandles(navItems);

					navBlock.delegate(
						'click',
						function(event) {
							if (event.target.ancestor('.drag-handle', true, SELECTOR_LFR_NAV_SORTABLE)) {
								event.preventDefault();
							}
						},
						SELECTOR_LFR_NAV_SORTABLE + SELECTOR_DIRECT_ANCHOR
					);

					var sortableDD = sortable.delegate.dd;

					instance._sortableDD = sortableDD;

					afterMakeSortable.call(instance, sortable);

					sortableDD.plug(A.Plugin.DDConstrained);

					instance._toggleDragConfig(sortableDD);

					A.on('windowresize', A.bind('_onWindowResize', instance));
				},

				_createDragHandles: function(items) {
					var instance = this;

					items.append(TPL_DRAG_HANDLE);
				},

				_onWindowResize: function() {
					var instance = this;

					instance._toggleDragConfig(instance._sortableDD);
				},

				_toggleDragConfig: function(dd) {
					var instance = this;

					var tablet = Util.isTablet();

					dd.con.set('stickY', tablet);

					var addHandleString = SELECTOR_DRAG_HANDLE;
					var removeHandleString = SELECTOR_LFR_NAV_SORTABLE;

					if (!tablet) {
						addHandleString = SELECTOR_LFR_NAV_SORTABLE;
						removeHandleString = SELECTOR_DRAG_HANDLE;
					}

					dd.addHandle(addHandleString);
					dd.removeHandle(removeHandleString);
				}
			},
			true
		);
	},
	'',
	{
		requires: ['dd-constrain', 'event-resize', 'event-touch', 'liferay-navigation']
	}
);