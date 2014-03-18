AUI.add(
	'liferay-navigation-touch',
	function(A) {
		var Util = Liferay.Util;

		var SELECTOR_DRAG_HANDLE = '.drag-handle';

		var STR_HANDLES = 'handles';

		var TPL_DRAG_HANDLE = '<span class="drag-handle"><i class="icon-reorder"></i></span>';

		var NavigationProto = Liferay.Navigation.prototype;

		A.mix(
			NavigationProto,
			{
				TPL_DELETE_BUTTON: NavigationProto.TPL_DELETE_BUTTON.replace('hide', ''),

				_afterMakeSortable: function(sortable) {
					var instance = this;

					var navItems = instance.get('navBlock').all(instance._navItemSelector);

					instance._createDragHandles(navItems);

					var sortableDD = instance._sortableDD = sortable.delegate.dd;

					sortableDD.removeInvalid('a');

					var tablet = Util.isTablet();

					if (tablet) {
						sortableDD.set(STR_HANDLES, [instance._navList.all(SELECTOR_DRAG_HANDLE)]);
					}

					sortableDD.plug(
						A.Plugin.DDConstrained,
						{
							stickY: tablet
						}
					);

					A.on('windowresize', A.bind('_onWindowResize', instance));
				},

				_createDragHandles: function(items) {
					var instance = this;

					items.each(
						function(item, index, collection) {
							item.append(TPL_DRAG_HANDLE);
						}
					);
				},

				_onWindowResize: function() {
					var instance = this;

					var tablet = Util.isTablet();

					var sortableDD = instance._sortableDD;

					sortableDD.con.set('stickY', tablet);
					sortableDD.set(STR_HANDLES, [instance._navList.all(tablet ? SELECTOR_DRAG_HANDLE : '.lfr-nav-sortable')]);
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