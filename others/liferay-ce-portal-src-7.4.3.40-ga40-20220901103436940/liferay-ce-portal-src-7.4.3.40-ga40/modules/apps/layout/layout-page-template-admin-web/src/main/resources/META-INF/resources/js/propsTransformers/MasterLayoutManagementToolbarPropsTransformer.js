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

import {openSimpleInputModal} from 'frontend-js-web';

import openDeletePageTemplateModal from '../modal/openDeletePageTemplateModal';

export default function propsTransformer({portletNamespace, ...otherProps}) {
	const deleteSelectedMasterLayouts = () => {
		openDeletePageTemplateModal({
			onDelete: () => {
				const form = document.getElementById(`${portletNamespace}fm`);

				if (form) {
					submitForm(form);
				}
			},
			title: Liferay.Language.get('masters'),
		});
	};

	const exportMasterLayouts = (itemData) => {
		const form = document.getElementById(`${portletNamespace}fm`);

		if (form) {
			submitForm(form, itemData?.exportMasterLayoutURL);
		}
	};

	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			const data = item?.data;

			const action = data?.action;

			if (action === 'deleteSelectedMasterLayouts') {
				deleteSelectedMasterLayouts();
			}
			else if (action === 'exportMasterLayouts') {
				exportMasterLayouts(data);
			}
		},
		onCreateButtonClick(event, {item}) {
			openSimpleInputModal({
				dialogTitle: Liferay.Language.get('add-master-page'),
				formSubmitURL: item?.data?.addMasterLayoutURL,
				mainFieldLabel: Liferay.Language.get('name'),
				mainFieldName: 'name',
				mainFieldPlaceholder: Liferay.Language.get('name'),
				namespace: portletNamespace,
			});
		},
	};
}
