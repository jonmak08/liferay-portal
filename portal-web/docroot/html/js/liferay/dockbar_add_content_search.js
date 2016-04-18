AUI.add(
	'liferay-dockbar-add-content-search',
	function(A) {
		var Dockbar = Liferay.Dockbar;

		var AddSearch = Dockbar.AddSearch;

		var AddContentSearch = function() {
		};

		AddContentSearch.prototype = {
			initializer: function(config) {
				var instance = this;

				var contentSearch = new AddSearch(
					{
						inputNode: instance.get('inputNode')
					}
				);

				instance._search = contentSearch;

				instance._eventHandlesDockbarAddContenSearch = [];

				instance._bindUISearch();
			},

			destructor: function() {
				var instance = this;

				(new A.EventHandle(instance._eventHandlesDockbarAddContenSearch)).detach();

				instance._eventHandlesDockbarAddContenSearch = null;
			},

			_bindUISearch: function() {
				var instance = this;

				instance._eventHandlesDockbarAddContenSearch.push(
					instance._search.after('query', instance._refreshContentList, instance),
					instance.get('inputNode').on('keydown', instance._onSearchInputKeyDown, instance)
				);
			},

			_onSearchInputKeyDown: function(event) {
				if (event.isKey('ENTER')) {
					event.halt();
				}
			}
		}

		Dockbar.AddContentSearch = AddContentSearch;
	},
	'',
	{
		requires: ['aui-base', 'liferay-dockbar', 'liferay-dockbar-add-search']
	}
);