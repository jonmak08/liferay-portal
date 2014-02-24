AUI.add(
	'liferay-input-move-boxes-touch',
	function(A) {
		var EDIT_SELECTION_TPL = '<button class=\'btn edit-selection\' type=\'button\'><i class=\'icon-edit\'></i> ' + Liferay.Language.get('edit') + ' </button>';

		var SORTABLE_CONTAINER_TPL = '<div class=\'sortable-container\'></div>';

		var STR_CLICK = 'click';

		var STR_DATA_SELECTED = 'data-selected';

		var STR_DATA_VALUE = 'data-value';

		var STR_ICON_CHECK = 'icon-check';

		var STR_ICON_CHECK_EMPTY = 'icon-check-empty';

		var STR_MOVE_OPTION_SELECTOR = '.move-option';

		var STR_NODE = 'node';

		var STR_SELECTED = 'selected';

		var MOVE_OPTION_TPL = '{{#each options}}' +
			'<div class=\'move-option ' +
				'{{#if selected}}' +
					STR_SELECTED +
				'{{/if}}' +
			'\' data-value=\'{{value}}\'>' +
				'<i class=\'handle icon-reorder\'></i>' +
				'<i data-value=\'{{value}}\' data-selected=\'{{selected}}\' class=\'checkbox ' +
					'{{#if selected}}' +
						STR_ICON_CHECK +
					'{{else}}' +
						STR_ICON_CHECK_EMPTY +
					'{{/if}}' +
				'\'></i>' +
				'<div class=\'title\'>{{name}}</div>' +
			'</div>' +
		'{{/each}}';

		var STR_TRUE = 'true';

		A.mix(
			Liferay.InputMoveBoxes.prototype,
			{
				renderUI: function() {
					var instance = this;

					instance._contentBox = instance.get('contentBox');

					instance._sortableContainer = A.Node.create(SORTABLE_CONTAINER_TPL);
					instance._contentBox.append(instance._sortableContainer);

					instance._renderBoxes();
					instance._renderButtons();
					instance._renderSortList();

					instance._afterDropHitTask = A.debounce(instance._afterDropHitFn, 50, instance);
				},

				bindUI: function() {
					var instance = this;

					instance._editSelection.on(STR_CLICK, A.bind(instance._onEditSelectionClick, instance));

					instance._sortable.delegate.dd.after('drag:drophit', A.bind(instance._afterDropHit, instance));
					instance._sortable.delegate.dd.after('drag:start', A.bind(instance._afterDragStart, instance));

					instance._sortableContainer.delegate(STR_CLICK, A.bind(instance._onCheckBoxClick, instance), '.checkbox');
				},

				_afterDragStart: function(event) {
					var instance = this;

					var dragNode = event.target.get('dragNode');

					dragNode.addClass('move-option-dragging');
				},

				_afterDropHit: function(event) {
					var instance = this;

					var dragNode = event.drag.get(STR_NODE);
					var dropNode = event.drop.get(STR_NODE);

					var value = dragNode.attr(STR_DATA_VALUE);

					instance._afterDropHitTask(
						{
							dropNode: dropNode,
							value: value
						}
					);
				},

				_afterDropHitFn: function(event) {
					var instance = this;

					instance._syncSelectedSortList();

					var dropNode = event.dropNode;
					var value = event.value;

					var moveOption = instance._sortableContainer.one(STR_MOVE_OPTION_SELECTOR + '[data-value="' + value + '"]');

					var dragNodeIndex = instance._selectedSortList.indexOf(moveOption);
					var dropNodeIndex = instance._selectedSortList.indexOf(dropNode);

					var leftBoxOptions = instance._leftBox.all('option');
					var referenceNodeIndex = ((dropNodeIndex > dragNodeIndex) ? dragNodeIndex : (dragNodeIndex + 1));

					var item = instance._getOption(instance._leftBox, value);
					var referenceNode = leftBoxOptions.item(referenceNodeIndex);

					instance._leftBox.insertBefore(item, referenceNode);
				},

				_getOption: function(box, value) {
					return box.one('option[value="' + value + '"]');
				},

				_onCheckBoxClick: function(event) {
					var instance = this;

					var currentTarget = event.currentTarget;

					var selected = (currentTarget.attr(STR_DATA_SELECTED) === STR_TRUE);
					var value = currentTarget.attr(STR_DATA_VALUE);

					var from;
					var to;

					if (selected) {
						from = instance._leftBox;
						to = instance._rightBox;
					}
					else {
						from = instance._rightBox;
						to = instance._leftBox;
					}

					var option = instance._getOption(from, value);

					option.attr(STR_SELECTED, true);

					instance._moveItem(from, to);

					instance._getOption(to, value).attr(STR_SELECTED, false);

					currentTarget.attr(STR_DATA_SELECTED, !selected);

					instance._toggleMoveOption(currentTarget);
				},

				_onEditSelectionClick: function(event) {
					var instance = this;

					var currentTarget = event.currentTarget;

					currentTarget.toggleClass('active');

					instance._sortableContainer.toggleClass('edit-list-active');
				},

				_renderButtons: function() {
					var instance = this;

					instance._editSelection = A.Node.create(EDIT_SELECTION_TPL);

					instance._sortableContainer.placeBefore(instance._editSelection);
				},

				_renderSortList: function() {
					var instance = this;

					var options = instance._contentBox.all('.choice-selector option');

					var template = A.Handlebars.compile(MOVE_OPTION_TPL);

					var data = {
						options: []
					};

					options.each(
						function(item, index, collection) {
							var selected = (item.attr(STR_DATA_SELECTED) === STR_TRUE);

							data.options.push(
								{
									name: item.html(),
									selected: selected,
									value: item.val()
								}
							);
						}
					);

					var html = template(data);

					instance._sortableContainer.append(html);

					instance._sortable = new A.Sortable(
						{
							container: instance._sortableContainer,
							handles: [instance._sortableContainer.all('.handle')],
							nodes: STR_MOVE_OPTION_SELECTOR,
							opacity: '0.2'
						}
					);

					instance._syncSelectedSortList();
				},

				_syncSelectedSortList: function() {
					var instance = this;

					instance._selectedSortList = instance._sortableContainer.all(STR_MOVE_OPTION_SELECTOR + '.' + STR_SELECTED);
				},

				_toggleMoveOption: function(checkbox) {
					var instance = this;

					var moveOption = checkbox.ancestor(STR_MOVE_OPTION_SELECTOR);

					moveOption.toggleClass(STR_SELECTED);

					checkbox.toggleClass(STR_ICON_CHECK);
					checkbox.toggleClass(STR_ICON_CHECK_EMPTY);

					var lastItem = instance._selectedSortList.last();

					if (lastItem != null) {
						lastItem.placeAfter(moveOption);
					}
					else {
						instance._sortableContainer.prepend(moveOption);
					}

					instance._syncSelectedSortList();
				}
			},
			true
		);
	},
	'',
	{
		requires: ['aui-base', 'handlebars', 'liferay-input-move-boxes', 'sortable']
	}
);