import OpenSimpleInputModal from 'frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es';
import PortletBase from 'frontend-js-web/liferay/PortletBase.es';
import {Config} from 'metal-state';

class LayoutPageTemplateEntryDropdownDefaultEventHandler extends PortletBase {
	handleItemClicked(event) {
		const itemData = event.data.item.data;

		if (itemData && itemData.action && this[itemData.action]) {
			this[itemData.action](itemData);
		}
	}

	deleteLayoutPageTemplateEntry(itemData) {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this'))) {
			this._send(itemData.deleteLayoutPageTemplateEntryURL);
		}
	}

	permissionsLayoutPageTemplateEntry(itemData) {
		Liferay.Util.openWindow(
			{
				dialog: {
					destroyOnHide: true,
					modal: true
				},
				dialogIframe: {
					bodyCssClass: 'dialog-with-footer'
				},
				title: Liferay.Language.get('permissions'),
				uri: itemData.permissionsLayoutPageTemplateEntryURL
			}
		);
	}

	renameLayoutPageTemplateEntry(itemData) {
		OpenSimpleInputModal(
			{
				dialogTitle: Liferay.Language.get('rename-display-page'),
				formSubmitURL: itemData.updateLayoutPageTemplateEntryURL,
				idFieldName: itemData.idFieldName,
				idFieldValue: itemData.idFieldValue,
				mainFieldLabel: Liferay.Language.get('name'),
				mainFieldName: 'name',
				mainFieldPlaceholder: Liferay.Language.get('name'),
				mainFieldValue: itemData.layoutPageTemplateEntryName,
				namespace: this.namespace,
				spritemap: this.spritemap
			}
		);
	}
	
	updateLayoutPageTemplateEntryPreview(itemData) {
		AUI().use(
			'liferay-item-selector-dialog',
			A => {
				const itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: this.ns('changePreview'),
						on: {
							selectedItemChange: function(event) {
								const selectedItem = event.newVal;

								if (selectedItem) {
									const itemValue = JSON.parse(selectedItem.value);

									this.one('#layoutPageTemplateEntryId').value = itemData.layoutPageTemplateEntryId;
									this.one('#fileEntryId').value = itemValue.fileEntryId;

									submitForm(this.one('#layoutPageTemplateEntryPreviewFm'));
								}
							}.bind(this)
						},
						'strings.add': Liferay.Language.get('ok'),
						title: Liferay.Language.get('page-template-thumbnail'),
						url: itemData.itemSelectorURL
					}
				);

				itemSelectorDialog.open();
			}
		);
	}

	_send(url) {
		submitForm(document.hrefFm, url);
	}
}

LayoutPageTemplateEntryDropdownDefaultEventHandler.STATE = {
	namespace: Config.string(),
	spritemap: Config.string()
};

export default LayoutPageTemplateEntryDropdownDefaultEventHandler;