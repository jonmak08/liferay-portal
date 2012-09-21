AUI.add(
	'liferay-admin-charts',
	function(A) {
		var CONFIG = {
			dataProvider: [],
			legend: {
				position: 'bottom',
				styles: {
					background: {
						fill: {
							color: '#EEF0F2'
						},
						border: {
							color: '#CDCDCD',
							weight: 1
						}
					},
					gap: 20,
					hAlign: 'left',
					item: {
						label: {
							color: '#333',
							fontSize: '14px'
						},
						vSpacing: 5
					},
					marker: {
						height: 10
					}
				}
			},
			seriesCollection: [
				{
					categoryKey: 'category',
					styles: {
						border: {
							alpha: 0.2,
							colors: ['#888'],
							weight: 1
						}
					},
					valueKey: 'values'
				}
			],
			tooltip: {
				show: false
			},
			type: 'pie'
		},

		CSS_LABEL = 'pieSeriesLabel',

		CSS_LABEL_POS_NE = 'label-NE',
		CSS_LABEL_POS_NW = 'label-NW',
		CSS_LABEL_POS_SE = 'label-SE',
		CSS_LABEL_POS_SW = 'label-SW',

		CSS_LABEL_WRAPPER = 'label-wrapper',

		SLICE_PERCENT_MIN = 15,

		START_ANGLE = 180,

		STR_BLANK = '',
		STR_COMMA = ',',

		TPL_LABEL = new A.Template(
			'<div class="{className} id="{seriesId}_label_{index}" style="left: {left}px; top: {top}px;">',
				'<div class="', CSS_LABEL_WRAPPER, '">',
					'<div class="label-pec">{valuePercent}%</div>',
					'<div class="label-MB">{valueMB} ', Liferay.Language.get('mb'),'</div>',
				'</div>',
			'</div>'
		);

		var AdminPieChart = A.Component.create(
			{
				ATTRS: {
					dataProvider: {
						value: []
					},
					renderContainer: {
						value: ''
					},
					stylesColors: {
						value: []
					}
				},

				NAME: 'admin-charts',

				prototype: {
					chart: null,

					initializer: function() {
						var instance = this;

						instance.chart = instance._createPieChart();

						instance._drawLabels();
					},

					destructor: function() {
						var instance = this;

					},

					_createPieChart: function() {
						var instance = this;

						var dataProvider = instance.get('dataProvider');

						var renderContainer = instance.get('renderContainer');

						var stylesColors = instance.get('stylesColors');

						var configDataProvider = {
							dataProvider: dataProvider
						};

						var configStylesColors = {
							seriesCollection: [
								{
									styles: {
										fill: {
											colors: stylesColors
										}
									}
								}
							]
						};

						var config = A.clone(CONFIG);

						A.mix(config, configDataProvider, true, null, 0, true);
						A.mix(config, configStylesColors, true, null, 0, true);

						var chart = new A.Chart(config).render(renderContainer);

						return chart;
					},

					/**
					 * Adapted from Tripp Bridges
					 * http://yuilibrary.com/forum/viewtopic.php?p=34051
					 *
					 * @method _setColumnArrays
					 */
					_drawLabels: function() {
						var instance = this;

						var pieChart = instance.chart;

						var graphic = pieChart.get('graph').get('graphic');

						var series = pieChart.getSeries(0);

						var total = series.getTotalValues();

						if (total === 0) {
							return;
						}

						var index;
						var item;
						var len = series.get('markers').length;

						var cos;
						var radius = graphic.get('width') / 2;
						var sin;
						var startAngle = START_ANGLE;
						var totalAngle;

						var className = A.ClassNameManager.getClassName(CSS_LABEL);
						var label;
						var posX;
						var posY;
						var seriesId = pieChart.get('id') + '_series';
						var value;
						var valuePercent;

						var graphicNode = A.one(graphic.get('node'));

						for (index = len-1; index >= 0; index--) {
							item = pieChart.getSeriesItems(series, index);

							value = item.value.value;

							if (value <= 0) {
								//No slice area to draw label in.
								continue;
							}

							valuePercent = (value / total) * 100;
							valuePercent = Math.round(valuePercent);

							totalAngle = 360 / (total / value);

							//only calculate half the angle before to position in middle of slice
							startAngle = startAngle + (totalAngle / 2);

							if (valuePercent > SLICE_PERCENT_MIN) {
								sin = Math.sin(startAngle / 180 * Math.PI);
								cos = Math.cos(startAngle / 180 * Math.PI);

								posX = Math.round(radius + sin * radius * 0.55);
								posY = Math.round(radius + cos * radius * 0.55);

								value = A.DataType.Number.format(
									value,
									{
										decimalPlaces: 0,
										thousandsSeparator: STR_COMMA
									}
								);

								label = TPL_LABEL.render(
									{
										className: className,
										index: index,
										left: posX,
										seriesId: seriesId,
										top: posY,
										valueMB: value,
										valuePercent: valuePercent
									}
								);

								graphicNode.append(label);
							}

							//add the other half of the angle to the total
							startAngle = startAngle + (totalAngle / 2);
						}
					}
				}
			}
		);

		Liferay.Portlet.AdminPieChart = AdminPieChart;
	},
	'',
	{
		requires: ['aui-base','aui-template','charts-legend']
	}
);

AUI.add(
	'liferay-admin',
	function(A) {
		var AObject = A.Object;
		var Lang = A.Lang;
		var Poller = Liferay.Poller;

		var WIN = A.config.win;

		var STR_CLICK = 'click';

		var STR_PORTLET_MSG_ERROR = 'portlet-msg-error';

		var STR_PORTLET_MSG_SUCCESS = 'portlet-msg-success';

		var Admin = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				ATTRS: {
					form: {
						setter: A.one,
						value: null
					},

					url: {
						value: null
					}
				},

				EXTENDS: A.Base,

				NAME: 'admin',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._errorCount = 0;

						var eventHandles = [];

						var installXugglerButton = instance.one('#installXugglerButton');

						if (installXugglerButton) {
							eventHandles.push(
								installXugglerButton.on(STR_CLICK, instance._installXuggler, instance)
							);

							instance._installXugglerButton = installXugglerButton;

							instance._xugglerProgressInfo = instance.one('#xugglerProgressInfo');

							instance._eventHandles = eventHandles;
						}
					},

					destructor: function() {
						var instance = this;

						A.Array.invoke(instance._eventHandles, 'detach');

						Poller.removeListener(instance.ID);
					},

					_installXuggler: function() {
						var instance = this;

						var xugglerProgressInfo = instance._xugglerProgressInfo;

						Liferay.Util.toggleDisabled(instance._installXugglerButton, true);

						var form = instance.get('form');

						form.get(instance.ns('cmd')).val('installXuggler');

						var ioRequest = A.io.request(
							instance.get('url'),
							{
								autoLoad: false,
								dataType: 'json',
								form: form.getDOM()
							}
						);

						ioRequest.on(['failure', 'success'], instance._onIOResponse, instance);

						WIN[instance.ns('xugglerProgressInfo')].startProgress();

						ioRequest.start();
					},

					_onIOResponse: function(event) {
						var instance = this;

						var responseData = event.currentTarget.get('responseData');

						var progressBar = instance.one('#xugglerProgressInfoBar');

						progressBar.hide();

						WIN[instance.ns('xugglerProgressInfo')].fire('complete');

						var xugglerProgressInfo = instance._xugglerProgressInfo;

						var cssClass = STR_PORTLET_MSG_ERROR;

						var message = '';

						if (responseData.success) {
							cssClass = STR_PORTLET_MSG_SUCCESS;

							message = Liferay.Language.get('xuggler-has-been-installed-you-need-to-reboot-your-server-to-apply-changes');
						}
						else {
							message = Liferay.Language.get('an-unexpected-error-occurred-while-installing-xuggler') + ': ' + responseData.exception;
						}

						xugglerProgressInfo.html(message);

						xugglerProgressInfo.addClass(cssClass);
					}
				}
			}
		);

		Liferay.Portlet.Admin = Admin;
	},
	'',
	{
		requires: ['liferay-poller', 'liferay-portlet-base']
	}
);