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

package com.liferay.style.book.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.style.book.constants.StyleBookPortletKeys;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.service.StyleBookEntryLocalService;
import com.liferay.style.book.service.StyleBookEntryService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StyleBookPortletKeys.STYLE_BOOK,
		"mvc.command.name=/style_book/delete_style_book_entry_preview"
	},
	service = MVCActionCommand.class
)
public class DeleteStyleBookEntryPreviewMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long styleBookEntryId = ParamUtil.getLong(
			actionRequest, "styleBookEntryId");

		StyleBookEntry styleBookEntry =
			_styleBookEntryLocalService.fetchStyleBookEntry(styleBookEntryId);

		if (styleBookEntry != null) {
			PortletFileRepositoryUtil.deletePortletFileEntry(
				styleBookEntry.getPreviewFileEntryId());

			_styleBookEntryService.updatePreviewFileEntryId(
				styleBookEntryId, 0);
		}
	}

	@Reference
	private StyleBookEntryLocalService _styleBookEntryLocalService;

	@Reference
	private StyleBookEntryService _styleBookEntryService;

}