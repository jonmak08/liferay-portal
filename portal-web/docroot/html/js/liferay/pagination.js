AUI.add(
	'liferay-pagination',
	function(A) {
		var Lang = A.Lang;
		var AArray = A.Array;
		var AObject = A.Object;

		var BOUNDING_BOX = 'boundingBox';

		var ITEMS_PER_PAGE = 'itemsPerPage';

		var ITEMS_PER_PAGE_LIST = 'itemsPerPageList';

		var NAME = 'pagination';

		var PAGE = 'page';

		var RESULTS = 'results';

		var STRINGS = 'strings';

		var STR_SPACE = ' ';

		var Pagination = A.Component.create(
			{
				ATTRS: {
					itemsPerPage: {
						validator: Lang.isNumber,
						value: 20
					},

					itemsPerPageList: {
						validator: Lang.isArray,
						value: [5, 10, 20, 30, 50, 75]
					},

					namespace: {
						validator: Lang.isString
					},

					numberOfPages: {
						validator: Lang.isNumber,
						value: 5
					},

					results: {
						validator: Lang.isNumber,
						value: 0
					},

					selectedItem: {
						validator: Lang.isNumber,
						value: 0
					},

					shift: {
						validator: Lang.isNumber
					},

					showControls: {
						value: false
					},

					strings: {
						setter: function(value) {
							return A.merge(
								value,
								{
									items: Liferay.Language.get('items'),
									of: Liferay.Language.get('of'),
									page: Liferay.Language.get('page'),
									per: Liferay.Language.get('per'),
									results: Liferay.Language.get('results'),
									showing: Liferay.Language.get('showing')
								}
							);
						},
						validator: Lang.isObject,
						value: {
								next: Liferay.Language.get('next'),
								prev: Liferay.Language.get('prev')
						}
					},

					visible: {
						setter: '_uiSetVisible',
						validator: Lang.isBoolean
					}
				},

				EXTENDS: A.Pagination,

				NAME: NAME,

				prototype: {
					TOTAL_CONTROLS: 4,

					TPL_CONTAINER: '<div class="lfr-pagination-controls" id="{id}"></div>',

					TPL_DELTA_SELECTOR: '<div class="lfr-pagination-delta-selector">' +
						'<div class="btn-group lfr-icon-menu">' +
							'<a class="btn direction-down dropdown-toggle max-display-items-15" href="javascript:;" id="{id}" title="{title}">' +
								'<span class="lfr-icon-menu-text">{title}</span> <i class="icon-caret-down"></i>' +
							'</a>' +
						'</div>' +
					'</div>',

					TPL_ITEM_CONTAINER: '<ul class="direction-down dropdown-menu lfr-menu-list" id="{id}" role="menu" />',

					TPL_ITEM: '<li id="{idLi}" role="presentation">' +
						'<a href="javascript:;" class="lfr-pagination-link taglib-icon" id="{idLink}" role="menuitem">' +
							'<span class="taglib-text-icon" data-index="{index}" data-value="{value}">{value}</span>' +
						'</a>' +
					'</li>',

					TPL_LABEL: '{x} {items} {per} {page}',

					TPL_RESULTS: '<small class="search-results" id="{id}">{value}</small>',

					TPL_RESULTS_MESSAGE: '{showing} {from} - {to} {of} {x} {results}.',

					TPL_RESULTS_MESSAGE_SHORT: '{showing} {x} {results}.',

					renderUI: function() {
						var instance = this;

						Pagination.superclass.renderUI.apply(instance, arguments);

						var boundingBox = instance.get(BOUNDING_BOX);

						boundingBox.addClass('lfr-pagination');

						var namespace = instance.get('namespace');

						var deltaSelectorId = namespace + 'dataSelectorId';

						var deltaSelector = A.Node.create(
							Lang.sub(
								instance.TPL_DELTA_SELECTOR,
								{
									id: deltaSelectorId,
									title: instance._getLabelContent()
								}
							)
						);

						var itemContainer = A.Node.create(
							Lang.sub(
								instance.TPL_ITEM_CONTAINER,
								{
									id: namespace + 'itemContainerId'
								}
							)
						);

						var itemsContainer = A.Node.create(
							Lang.sub(
								instance.TPL_CONTAINER,
								{
									id: namespace + 'itemsContainer'
								}
							)
						);

						var searchResults = A.Node.create(
							Lang.sub(
								instance.TPL_RESULTS,
								{
									id: namespace + 'searchResultsId',
									value: instance._getResultsContent()
								}
							)
						);

						var buffer = AArray.map(
							instance.get(ITEMS_PER_PAGE_LIST),
							function(item, index, collection) {
								return Lang.sub(
									instance.TPL_ITEM,
									{
										idLi: namespace + 'itemLiId' + index,
										idLink: namespace + 'itemLinkId' + index,
										index: index,
										value: item
									}
								);
							}
						);

						itemContainer.appendChild(buffer.join(''));

						deltaSelector.one('#' + deltaSelectorId).ancestor().appendChild(itemContainer);

						itemsContainer.appendChild(deltaSelector);
						itemsContainer.appendChild(searchResults);

						boundingBox.appendChild(itemsContainer);

						instance._deltaSelector = deltaSelector;
						instance._itemContainer = itemContainer;
						instance._itemsContainer = itemsContainer;
						instance._searchResults = searchResults;

						instance._paginationContentNode = boundingBox.one('.pagination-content');
						instance._paginationControls = boundingBox.one('.lfr-pagination-controls');

						Liferay.Menu.register(deltaSelectorId);
					},

					bindUI: function() {
						var instance = this;

						Pagination.superclass.bindUI.apply(instance, arguments);

						instance._eventHandles = [
							instance._itemContainer.delegate('click', instance._onItemClick, '.lfr-pagination-link', instance)
						];

						instance.on('itemsPerPageChange', instance._onItemsPerPageChange, instance);
						instance.on('changeRequest', instance._onChangeRequest, instance);
					},

					destructor: function() {
						var instance = this;

						(new A.EventHandle(instance._eventHandles)).detach();
					},

					getItem: function(currentPage) {
						var instance = this;

						if (Lang.isNumber(currentPage)) {
							var items = instance.get('items');

							if (items) {
								currentPage = items.item(currentPage + instance.get('shift'));
							}
						}

						return currentPage;
					},

					_afterResultsChange: function(event) {
						var instance = this;

						instance._syncResults();
					},

					_countPaginationControls: function(items) {
						var instance = this;

						var controlCount = 0;

						A.each(
							items,
							function(node, index) {
								if (node.hasClass('pagination-control')) {
									controlCount++;
								}
							}
						);

						return controlCount;
					},

					_dispatchRequest: function(state) {
						var instance = this;

						if (!AObject.owns(state, ITEMS_PER_PAGE)) {
							state.itemsPerPage = instance.get(ITEMS_PER_PAGE);
						}

						Pagination.superclass._dispatchRequest.call(instance, state);
					},

					_getActiveNodeIndex: function(items) {
						var instance = this;

						var activeNodeIndex;

						A.each(
							items,
							function(node, index) {
								if (node.hasClass('active')) {
									activeNodeIndex = (items.indexOf(node) - instance.get('shift'));
								}
							}
						);

						return activeNodeIndex;
					},

					_getLabelContent: function(itemsPerPage) {
						var instance = this;

						var result;

						var strings = instance.get(STRINGS);

						if (!itemsPerPage) {
							itemsPerPage = instance.get(ITEMS_PER_PAGE);
						}

						result = Lang.sub(
							instance.TPL_LABEL,
							{
								items: strings.items,
								page: strings.page,
								per: strings.per,
								x: itemsPerPage
							}
						);

						return result;
					},

					_getResultsContent: function(page, itemsPerPage) {
						var instance = this;

						var results = instance.get(RESULTS);
						var strings = instance.get(STRINGS);

						if (!Lang.isValue(page)) {
							page = instance.get(PAGE);
						}

						if (!Lang.isValue(itemsPerPage)) {
							itemsPerPage = instance.get(ITEMS_PER_PAGE);
						}

						var tpl = instance.TPL_RESULTS_MESSAGE_SHORT;

						var values = {
							results: strings.results,
							showing: strings.showing,
							x: results
						};

						if (results > itemsPerPage) {
							var tmp = page * itemsPerPage;

							tpl = instance.TPL_RESULTS_MESSAGE;

							values.from = ((page - 1) * itemsPerPage) + 1;
							values.of = strings.of;
							values.to = tmp < results ? tmp : results;
						}

						return Lang.sub(tpl, values);
					},

					_goToPage: function(index, controlItem) {
						var instance = this;

						var items = instance.get('items');

						var currentIndex = instance._getActiveNodeIndex(items);

						var numberOfPages = instance.get('numberOfPages');

						var modCurrentIndex = (currentIndex % numberOfPages);

						var pagesStart = modCurrentIndex || numberOfPages;

						pagesStart -= 1;

						if (controlItem.hasClass('first')) {
							instance._dispatchRequest(
								{
									page: 1
								}
							);
						}
						else if (controlItem.hasClass('prev')) {
							instance.prev();
						}
						else if (controlItem.hasClass('prev-pages')) {
							instance._dispatchRequest(
								{
									page: (currentIndex - numberOfPages - pagesStart)
								}
							);
						}
						else if (controlItem.hasClass('next-pages')) {
							instance._dispatchRequest(
								{
									page: (currentIndex + numberOfPages - pagesStart)
								}
							);
						}
						else if (controlItem.hasClass('next')) {
							instance.next();
						}
						else if (controlItem.hasClass('last')) {
							instance._dispatchRequest(
								{
									page: (index - instance.TOTAL_CONTROLS + 1)
								}
							);
						}
						else {
							instance._dispatchRequest(
								{
									page: (index - instance.get('shift'))
								}
							);
						}
					},

					_onChangeRequest: function(event) {
						var instance = this;

						var state = event.state;
						var page = state.page;

						var itemsPerPage = state.itemsPerPage;

						instance._syncLabel(itemsPerPage);
						instance._syncResults(page, itemsPerPage);
					},

					_onClickItem: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var items = instance.get('items');

						var index = items.indexOf(currentTarget);

						var lastIndex = (items.size() - 1);

						event.preventDefault();

						if (!currentTarget.hasClass('disabled') || !currentTarget.hasClass('active')) {
							if (index === 0) {
								instance._goToPage(index, items.first());
							}
							else if (index === 1) {
								instance._goToPage(index, items.item(1));
							}
							else if (index === 2) {
								instance._goToPage(index, items.item(2));
							}
							else if (index === lastIndex - 2) {
								instance._goToPage(index, items.item(lastIndex - 2));
							}
							else if (index === lastIndex - 1) {
								instance._goToPage(index, items.item(lastIndex - 1));
							}
							else if (index === lastIndex) {
								instance._goToPage(index, items.item(lastIndex));
							}
							else {
								instance._dispatchRequest(
									{
										page: (index - instance.get('shift'))
									}
								);
							}
						}
					},

					_onItemClick: function(event) {
						var instance = this;

						var itemsPerPage = Lang.toInt(event.currentTarget.one('.taglib-text-icon').attr('data-value'));

						instance.set(ITEMS_PER_PAGE, itemsPerPage);
					},

					_onItemsPerPageChange: function(event) {
						var instance = this;

						var page = instance.get(PAGE);

						var itemsPerPage = event.newVal;

						instance._dispatchRequest(
							{
								itemsPerPage: itemsPerPage,
								page: page
							}
						);
					},

					_renderItemsUI: function(total) {
						var instance = this;

						var TPL_ITEM_TEMPLATE = instance.ITEM_TEMPLATE;

						var buffer = '';

						var stringFirst = instance.getString('first');

						if (stringFirst) {
							buffer += Lang.sub(
								TPL_ITEM_TEMPLATE,
								{
									content: stringFirst,
									cssClass: 'first pagination-control'
								}
							);
						}

						var stringPrev = instance.getString('prev');

						if (stringPrev) {
							buffer += Lang.sub(
								TPL_ITEM_TEMPLATE,
								{
									content: stringPrev,
									cssClass: 'pagination-control prev'
								}
							);
						}

						var stringPrevPages = instance.getString('prevPages');

						if (stringPrevPages) {
							buffer += Lang.sub(
								TPL_ITEM_TEMPLATE,
								{
									content: stringPrevPages,
									cssClass: 'pagination-control prev-pages'
								}
							);
						}

						var formatter = instance.get('formatter');
						var offset = instance.get('offset');

						for (var i = offset; i <= (offset + total - 1); i++) {
							buffer += formatter.apply(instance, [i]);
						}

						var stringNextPages = instance.getString('nextPages');

						if (stringNextPages) {
							buffer += Lang.sub(
								TPL_ITEM_TEMPLATE,
								{
									content: stringNextPages,
									cssClass: 'next-pages pagination-control'
								}
							);
						}

						var stringNext = instance.getString('next');

						if (stringNext) {
							buffer += Lang.sub(
								TPL_ITEM_TEMPLATE,
								{
									content: stringNext,
									cssClass: 'next pagination-control'
								}
							);
						}

						var stringLast = instance.getString('last');

						if (stringLast) {
							buffer += Lang.sub(
								TPL_ITEM_TEMPLATE,
								{
									content: stringLast,
									cssClass: 'last pagination-control'
								}
							);
						}

						var items = A.NodeList.create(buffer);

						instance.TOTAL_CONTROLS = instance._countPaginationControls(items);

						if (!instance.get('shift')) {
							instance.set('shift', ((instance.TOTAL_CONTROLS / 2) - 1));
						}

						instance.set('items', items);

						instance.get('contentBox').setContent(items);

						if (!instance.get('showControls')) {
							A.each(
								items,
								function(node) {
									if (node.hasClass('pagination-control')) {
										node.remove();
									}
								}
							);
						}
					},

					_syncLabel: function(itemsPerPage) {
						var instance = this;

						var result = instance._getLabelContent(itemsPerPage);

						instance._deltaSelector.one('.lfr-icon-menu-text').html(result);
					},

					_syncNavigationUI: function() {
						var instance = this;

						var items = instance.get('items');
						var page = instance.get('page');
						var total = instance.get('total');

						var firstPage = (page === 1);
						var lastPage = (page === total);

						if (!instance.get('circular')) {
							items.item(1).toggleClass(
								'disabled',
								firstPage
							);

							var lastItemIndex = items.indexOf(items.last());

							var nextToLastItem = (lastItemIndex - 1);

							items.item(nextToLastItem).toggleClass(
								'disabled',
								lastPage
							);
						}

						items.first().toggleClass(
							'disabled',
							firstPage
						);

						items.last().toggleClass(
							'disabled',
							lastPage
						);
					},

					_syncResults: function(page, itemsPerPage) {
						var instance = this;

						var result = instance._getResultsContent(page, itemsPerPage);

						instance._searchResults.html(result);
					},

					_uiSetPage: function(val) {
						var instance = this;

						instance._syncNavigationUI();

						if ((val !== 0) || (val !== instance.getTotalItems())) {
							var item = instance.getItem(val);

							if (item) {
								item.addClass('active');
							}
						}
					},

					_uiSetVisible: function(val) {
						var instance = this;

						var hideClass = instance.get('hideClass');

						var hiddenClass = instance.getClassName('hidden');

						if (hideClass !== false) {
							hiddenClass += STR_SPACE + (hideClass || 'hide');
						}

						var results = instance.get(RESULTS);
						var itemsPerPageList = instance.get(ITEMS_PER_PAGE_LIST);

						instance._paginationControls.toggleClass(hiddenClass, (results <= itemsPerPageList[0]));

						instance._paginationContentNode.toggleClass(hiddenClass, !val);
					}
				}
			}
		);

		Liferay.Pagination = Pagination;
	},
	'',
	{
		requires: ['aui-pagination']
	}
);