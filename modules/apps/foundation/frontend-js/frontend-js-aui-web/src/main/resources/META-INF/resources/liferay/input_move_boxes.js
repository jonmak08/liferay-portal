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

						var leftReorderToolbar = instance._leftReorderToolbar;

						if (leftReorderToolbar) {
							leftReorderToolbar.after('click', A.rbind('_afterOrderClick', instance, instance._leftBox));
						}

						var rightReorderToolbar = instance._rightReorderToolbar;

						if (rightReorderToolbar) {
							rightReorderToolbar.after('click', A.rbind('_afterOrderClick', instance, instance._rightBox));
						}

						instance._moveToolbar.on('click', instance._afterMoveClick, instance);

						instance._leftBox.on('focus', A.rbind('_disableMoveButtons', instance, instance._leftBox));
						instance._leftBox.after('valuechange', A.rbind('_disableMoveButtons', instance, instance._leftBox));

						instance._rightBox.on('focus', A.rbind('_disableMoveButtons', instance, instance._rightBox));
						instance._rightBox.after('valuechange', A.rbind('_disableMoveButtons', instance, instance._rightBox));

						instance._disableMoveButtons(null, null);
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

						if (targetBtn && !targetBtn.attr('disabled')) {

							var from = instance._leftBox;
							var to = instance._rightBox;

							var sort = !instance.get('rightReorder');

							if (targetBtn.hasClass('move-right')) {
								from = instance._rightBox;
								to = instance._leftBox;

								sort = !instance.get('leftReorder');
							}

							instance._moveItem(from, to, sort);
							instance._afterOrderClick(event, instance._leftBox);

							instance._disableMoveButtons(event, from);
						}
					},

					_afterOrderClick: function(event, box) {
						var target = event.domEvent.target;
						var targetBtn = target.ancestor('.btn', true);

						if (targetBtn && !targetBtn.attr('disabled')) {

							var direction = 1;

							if (targetBtn.hasClass('reorder-up')) {
								direction = 0;
							}

							Util.reorder(box, direction);

							if (targetBtn.hasClass('move-left') || targetBtn.hasClass('move-right')) {
								box.attr('selectedIndex', -1);
							}
						}

						this._disableMoveButtons(event, box);
					},

					_disableMoveButtons: function(event, box) {

						var down = this.get('contentBox').one('.reorder-down');
						var up = this.get('contentBox').one('.reorder-up');
						var left = this.get('contentBox').one('.move-right');
						var right = this.get('contentBox').one('.move-left');

						down.attr('disabled', 'disabled');
						up.attr('disabled', 'disabled');
						left.attr('disabled', 'disabled');
						right.attr('disabled', 'disabled');

						if (box && box.hasClass('left-selector')) {

							this._rightBox.attr('selectedIndex', -1);

							right.removeAttribute('disabled');
							right.removeClass('disabled');

							if (box.get('length') > 1) {
								if (box.get('selectedIndex') === box.get('length') - 1) {
									up.removeAttribute('disabled');
									up.removeClass('disabled')
								}
								else if (box.get('selectedIndex') === 0) {
									down.removeAttribute('disabled');
									down.removeClass('disabled');
								}
								else if (box.get('selectedIndex') !== -1) {
									down.removeAttribute('disabled');
									down.removeClass('disabled');
									up.removeAttribute('disabled');
									up.removeClass('disabled');
								}
							}
							else if (box.get('length') !== 1) {
								right.attr('disabled', 'disabled');
							}
						}
						else if (box && box.hasClass('right-selector')) {

							this._leftBox.attr('selectedIndex', -1);

							if (box.get('selectedIndex') !== -1 && box.get('length') !== 0) {
								left.removeAttribute('disabled');
								left.removeClass('disabled');
							}
						}
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
												cssClass: 'move-left',
												icon: 'icon-circle-arrow-right',
												on: {
													click: function(event) {
														event.domEvent.preventDefault();
													}
												},
												title: strings.MOVE_LEFT
											},
											{
												cssClass: 'move-right',
												icon: 'icon-circle-arrow-left',
												on: {
													click: function(event) {
														event.domEvent.preventDefault();
													}
												},
												title: strings.MOVE_RIGHT
											}
										]
									]
								}
							).render(moveButtonsColumn);
						}

						var config_reorder = {
							children: [
								[
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
							]
						};

						if (instance.get('leftReorder')) {
							var leftColumn = contentBox.one('.left-selector-column');

							config_reorder.children[0][0].title = strings.LEFT_MOVE_UP;
							config_reorder.children[0][1].title = strings.LEFT_MOVE_DOWN;

							instance._leftReorderToolbar = new A.Toolbar(config_reorder).render(leftColumn);
						}

						if (instance.get('rightReorder')) {
							var rightColumn = contentBox.one('.right-selector-column');

							config_reorder.children[0][0].title = strings.RIGHT_MOVE_UP;
							config_reorder.children[0][1].title = strings.RIGHT_MOVE_DOWN;

							instance._rightReorderToolbar = new A.Toolbar(config_reorder).render(rightColumn);
						}
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