AUI.add(
	'liferay-input-move-boxes',
	function(A) {
		var Util = Liferay.Util;

		var CSS_LEFT_REORDER = 'left-reorder';

		var CSS_RIGHT_REORDER = 'right-reorder';

		var NAME = 'inputmoveboxes';

		var InputMoveBoxes = A.Component.create(
			{
				ATTRS: {
					leftReorder: {
					},

					rightReorder: {
					},

					strings: {
						LEFT_MOVE_DOWN: '',
						LEFT_MOVE_UP: '',
						MOVE_LEFT: '',
						MOVE_RIGHT: '',
						RIGHT_MOVE_DOWN: '',
						RIGHT_MOVE_UP: ''
					}
				},

				HTML_PARSER: {
					leftReorder: function(contentBox) {
						return contentBox.hasClass(CSS_LEFT_REORDER);
					},

					rightReorder: function(contentBox) {
						return contentBox.hasClass(CSS_RIGHT_REORDER);
					}
				},

				NAME: NAME,

				prototype: {
					renderUI: function() {
						var instance = this;

						instance._renderBoxes();
						instance._renderButtons();
					},

					bindUI: function() {
						var instance = this;

						var leftBox = instance._leftBox;
						var leftReorderToolbar = instance._leftReorderToolbar;

						if (leftReorderToolbar) {
							leftReorderToolbar.after('click', A.rbind('_afterOrderClick', instance, leftBox));
						}

						var rightBox = instance._rightBox;
						var rightReorderToolbar = instance._rightReorderToolbar;

						if (rightReorderToolbar) {
							rightReorderToolbar.after('click', A.rbind('_afterOrderClick', instance, rightBox));
						}

						instance._moveToolbar.on('click', instance._afterMoveClick, instance);

						leftBox.on(
							'change',
							function(event) {
								instance._toggleBtnSort(
									{
										box: event.currentTarget
									}
								);
							}
						);

						leftBox.on('focus', A.rbind('_onSelectFocus', instance, rightBox));

						rightBox.on(
							'change',
							function(event) {
								instance._toggleBtnSort(
									{
										box: event.currentTarget
									}
								);
							}
						);

						rightBox.on('focus', A.rbind('_onSelectFocus', instance, leftBox));

						Liferay.on(NAME + ':orderItem', A.bind('_toggleBtnSort', instance));
					},

					sortBox: function(box) {
						var newBox = [];

						var options = box.all('option');

						for (var i = 0; i < options.size(); i++) {
							newBox[i] = [options.item(i).val(), options.item(i).text()];
						}

						newBox.sort(Util.sortByAscending);

						var boxObj = A.one(box);

						boxObj.all('option').remove(true);

						newBox.forEach(
							function(item, index) {
								boxObj.append('<option value="' + item[0] + '">' + item[1] + '</option>');
							}
						);
					},

					_afterMoveClick: function(event) {
						var instance = this;

						var target = event.domEvent.target;
						var targetBtn = target.ancestor('.btn', true);

						if (targetBtn) {
							var cssClass = targetBtn.get('className');

							var from = instance._leftBox;
							var to = instance._rightBox;

							var sort = !instance.get('rightReorder');

							if (cssClass.indexOf('move-left') !== -1) {
								from = instance._rightBox;
								to = instance._leftBox;

								sort = !instance.get('leftReorder');
							}

							instance._moveItem(from, to, sort);
						}
					},

					_afterOrderClick: function(event, box) {
						var instance = this;

						var target = event.domEvent.target;
						var targetBtn = target.ancestor('.btn', true);

						if (targetBtn) {
							var cssClass = targetBtn.get('className');

							var direction = 1;

							if (cssClass.indexOf('reorder-up') !== -1) {
								direction = 0;
							}

							instance._orderItem(box, direction);
						}
					},

					_getToolbarButtons: function(toolbar, cssClass) {
						var instance = this;

						var retVal;

						if (toolbar) {
							var contentBox = toolbar.get('contentBox');

							if (contentBox) {
								var childNodes = contentBox.all(cssClass);

								if (childNodes) {
									retVal = childNodes;
								}
							}
						}

						return retVal;
					},

					_moveItem: function(from, to, sort) {
						var instance = this;

						from = A.one(from);
						to = A.one(to);

						var selectedIndex = from.get('selectedIndex');

						var selectedOption;

						if (selectedIndex >= 0) {
							var options = from.all('option');

							selectedOption = options.item(selectedIndex);

							options.each(
								function(item, index) {
									if (item.get('selected')) {
										to.append(item);
									}
								}
							);
						}

						if (selectedOption && selectedOption.text() !== '' && sort === true) {
							instance.sortBox(to);
						}

						Liferay.fire(
							NAME + ':moveItem',
							{
								fromBox: from,
								toBox: to
							}
						);
					},

					_onSelectFocus: function(event, toBox) {
						var instance = this;

						instance._toggleBtnMove(event);

						toBox.attr('selectedIndex', '-1');
					},

					_orderItem: function(box, direction) {
						Util.reorder(box, direction);

						Liferay.fire(
							NAME + ':orderItem',
							{
								box: box,
								direction: direction
							}
						);
					},

					_renderBoxes: function() {
						var instance = this;

						var contentBox = instance.get('contentBox');

						instance._leftBox = contentBox.one('.left-selector');
						instance._rightBox = contentBox.one('.right-selector');
					},

					_renderButtons: function() {
						var instance = this;

						var contentBox = instance.get('contentBox');
						var strings = instance.get('strings');

						var moveButtonsColumn = contentBox.one('.move-arrow-buttons');

						if (moveButtonsColumn) {
							instance._moveToolbar = new A.Toolbar(
								{
									children: [
										[
											'normal',
											'vertical',
											{
												cssClass: 'move-right',
												icon: 'icon-circle-arrow-right',
												on: {
													click: function(event) {
														event.domEvent.preventDefault();
													}
												},
												title: strings.MOVE_RIGHT
											},
											{
												cssClass: 'move-left',
												icon: 'icon-circle-arrow-left',
												on: {
													click: function(event) {
														event.domEvent.preventDefault();
													}
												},
												title: strings.MOVE_LEFT
											}
										]
									]
								}
							).render(moveButtonsColumn);
						}

						var config_reorder = {
							children: [
								{
									cssClass: 'reorder-up',
									icon: 'icon-circle-arrow-up',
									on: {
										click: function(event) {
											event.domEvent.preventDefault();
										}
									}
								},
								{
									cssClass: 'reorder-down',
									icon: 'icon-circle-arrow-down',
									on: {
										click: function(event) {
											event.domEvent.preventDefault();
										}
									}
								}
							]
						};

						if (instance.get('leftReorder')) {
							var leftColumn = contentBox.one('.left-selector-column');

							config_reorder.children[0].title = strings.LEFT_MOVE_UP;
							config_reorder.children[1].title = strings.LEFT_MOVE_DOWN;

							instance._leftReorderToolbar = new A.Toolbar(config_reorder).render(leftColumn);
						}

						if (instance.get('rightReorder')) {
							var rightColumn = contentBox.one('.right-selector-column');

							config_reorder.children[0].title = strings.RIGHT_MOVE_UP;
							config_reorder.children[1].title = strings.RIGHT_MOVE_DOWN;

							instance._rightReorderToolbar = new A.Toolbar(config_reorder).render(rightColumn);
						}
					},

					_toggleBtnMove: function(event) {
						var instance = this;

						var contentBox = instance.get('contentBox');

						var moveBtnLeft = contentBox.one('.move-left');
						var moveBtnRight = contentBox.one('.move-right');

						var target = event.target;

						if (moveBtnLeft && moveBtnRight && target) {
							var btnDisabledLeft = true;
							var btnDisabledRight = true;

							if (target.get('length')) {
								var leftBox = instance._leftBox;
								var rightBox = instance._rightBox;

								if (target == rightBox) {
									btnDisabledLeft = false;

									instance._toggleBtnSort(
										{
											box: rightBox
										}
									);

								}
								else if (target == leftBox) {
									btnDisabledRight = false;

									instance._toggleBtnSort(
										{
											box: leftBox
										}
									);
								}
							}

							instance._toggleBtnState(moveBtnLeft, btnDisabledLeft);
							instance._toggleBtnState(moveBtnRight, btnDisabledRight);
						}
					},

					_toggleBtnSort: function(config) {
						var instance = this;

						var box = config.box;

						var length = box.get('length');
						var selectedIndex = box.get('selectedIndex');

						var optionLength = length - 1;

						var leftReorder = instance.get('leftReorder');
						var rightReorder = instance.get('rightReorder');

						var leftReorderToolbar = instance._leftReorderToolbar;
						var rightReorderToolbar = instance._rightReorderToolbar;

						var leftReorderToolbarButtons = instance._getToolbarButtons(leftReorderToolbar, '.btn-toolbar-button');
						var rightReorderToolbarButtons = instance._getToolbarButtons(rightReorderToolbar, '.btn-toolbar-button');

						var reorderDown;
						var reorderUp;

						if (box == instance._leftBox) {
							if (leftReorder) {
								instance._toggleBtnState(leftReorderToolbarButtons, false);

								instance._toggleBtnState(leftReorderToolbarButtons, !length);

								if (selectedIndex === -1) {
									instance._toggleBtnState(leftReorderToolbarButtons, true);

								}
								else if (selectedIndex === 0) {
									reorderUp = instance._getToolbarButtons(leftReorderToolbar, '.reorder-up');

									instance._toggleBtnState(reorderUp, true);
								}
								else if (selectedIndex === optionLength) {
									reorderDown = instance._getToolbarButtons(leftReorderToolbar, '.reorder-down');

									instance._toggleBtnState(reorderDown, true);
								}
							}

							if (rightReorder) {
								instance._toggleBtnState(rightReorderToolbarButtons, true);
							}
						}
						else if (box == instance._rightBox) {
							if (leftReorder) {
								instance._toggleBtnState(leftReorderToolbarButtons, true);
							}

							if (rightReorder) {
								instance._toggleBtnState(rightReorderToolbarButtons, false);

								instance._toggleBtnState(rightReorderToolbarButtons, !length);

								if (selectedIndex === -1) {
									instance._toggleBtnState(rightReorderToolbarButtons, true);
								}
								else if (selectedIndex === 0) {
									reorderUp = instance._getToolbarButtons(rightReorderToolbar, '.reorder-up');

									instance._toggleBtnState(reorderUp, true);
								}
								else if (selectedIndex === optionLength) {
									reorderDown = instance._getToolbarButtons(rightReorderToolbar, '.reorder-down');

									instance._toggleBtnState(reorderDown, true);
								}
							}
						}
					},

					_toggleBtnState: function(btn, state) {
						Util.toggleDisabled(btn, state);
					}
				}
			}
		);

		Liferay.InputMoveBoxes = InputMoveBoxes;
	},
	'',
	{
		requires: ['aui-base', 'aui-toolbar']
	}
);