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

package com.liferay.client.extension.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.client.extension.model.ClientExtensionEntry;
import com.liferay.client.extension.service.ClientExtensionEntryLocalService;
import com.liferay.client.extension.uad.constants.ClientExtensionUADConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the client extension entry UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link ClientExtensionEntryUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseClientExtensionEntryUADAnonymizer
	extends DynamicQueryUADAnonymizer<ClientExtensionEntry> {

	@Override
	public void autoAnonymize(
			ClientExtensionEntry clientExtensionEntry, long userId,
			User anonymousUser)
		throws PortalException {

		if (clientExtensionEntry.getUserId() == userId) {
			clientExtensionEntry.setUserId(anonymousUser.getUserId());
			clientExtensionEntry.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(clientExtensionEntry, anonymousUser);
		}

		if (clientExtensionEntry.getStatusByUserId() == userId) {
			clientExtensionEntry.setStatusByUserId(anonymousUser.getUserId());
			clientExtensionEntry.setStatusByUserName(
				anonymousUser.getFullName());
		}

		clientExtensionEntryLocalService.updateClientExtensionEntry(
			clientExtensionEntry);
	}

	@Override
	public void delete(ClientExtensionEntry clientExtensionEntry)
		throws PortalException {

		clientExtensionEntryLocalService.deleteClientExtensionEntry(
			clientExtensionEntry);
	}

	@Override
	public Class<ClientExtensionEntry> getTypeClass() {
		return ClientExtensionEntry.class;
	}

	protected void autoAnonymizeAssetEntry(
		ClientExtensionEntry clientExtensionEntry, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(clientExtensionEntry);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return clientExtensionEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return ClientExtensionUADConstants.
			USER_ID_FIELD_NAMES_CLIENT_EXTENSION_ENTRY;
	}

	protected AssetEntry fetchAssetEntry(
		ClientExtensionEntry clientExtensionEntry) {

		return assetEntryLocalService.fetchEntry(
			ClientExtensionEntry.class.getName(),
			clientExtensionEntry.getClientExtensionEntryId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected ClientExtensionEntryLocalService clientExtensionEntryLocalService;

}