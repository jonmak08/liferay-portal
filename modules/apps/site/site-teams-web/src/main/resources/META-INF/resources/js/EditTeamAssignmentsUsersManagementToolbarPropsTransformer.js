/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {openSelectionModal} from 'frontend-js-web';

export default function propsTransformer({portletNamespace, ...otherProps}) {
	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteUsers') {
				if (
					confirm(
						Liferay.Language.get(
							'are-you-sure-you-want-to-delete-this'
						)
					)
				) {
					const form = document.getElementById(
						`${portletNamespace}fm`
					);

					if (form) {
						submitForm(form);
					}
				}
			}
		},
		onCreateButtonClick(event, {item}) {
			const data = item?.data;

			const action = data?.action;

			if (action === 'selectUser') {
				openSelectionModal({
					multiple: true,
					onSelect(selectedItem) {
						if (selectedItem) {
							const form = document.getElementById(
								`${portletNamespace}addTeamUsersFm`
							);

							if (!form) {
								return;
							}

							selectedItem.forEach((item) => {
								form.appendChild(item);
							});

							submitForm(form);
						}
					},
					selectEventName: `${portletNamespace}selectUser`,
					title: data?.title,
					url: data?.selectUserURL,
				});
			}
		},
	};
}
