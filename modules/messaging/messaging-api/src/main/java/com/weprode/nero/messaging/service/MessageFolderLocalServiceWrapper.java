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

package com.weprode.nero.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageFolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderLocalService
 * @generated
 */
public class MessageFolderLocalServiceWrapper
	implements MessageFolderLocalService,
			   ServiceWrapper<MessageFolderLocalService> {

	public MessageFolderLocalServiceWrapper(
		MessageFolderLocalService messageFolderLocalService) {

		_messageFolderLocalService = messageFolderLocalService;
	}

	@Override
	public com.weprode.nero.messaging.model.MessageFolder addFolderMessage(
		long userId, String folderName, int type, long parentFolderId) {

		return _messageFolderLocalService.addFolderMessage(
			userId, folderName, type, parentFolderId);
	}

	/**
	 * Adds the message folder to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageFolder the message folder
	 * @return the message folder that was added
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageFolder addMessageFolder(
		com.weprode.nero.messaging.model.MessageFolder messageFolder) {

		return _messageFolderLocalService.addMessageFolder(messageFolder);
	}

	/**
	 * Creates a new message folder with the primary key. Does not add the message folder to the database.
	 *
	 * @param folderId the primary key for the new message folder
	 * @return the new message folder
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageFolder createMessageFolder(
		long folderId) {

		return _messageFolderLocalService.createMessageFolder(folderId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageFolderLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the message folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder that was removed
	 * @throws PortalException if a message folder with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageFolder deleteMessageFolder(
			long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageFolderLocalService.deleteMessageFolder(folderId);
	}

	/**
	 * Deletes the message folder from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageFolder the message folder
	 * @return the message folder that was removed
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageFolder deleteMessageFolder(
		com.weprode.nero.messaging.model.MessageFolder messageFolder) {

		return _messageFolderLocalService.deleteMessageFolder(messageFolder);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageFolderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deletePersonalFolder(
		com.weprode.nero.messaging.model.MessageFolder personalFolder,
		long userId) {

		_messageFolderLocalService.deletePersonalFolder(personalFolder, userId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _messageFolderLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _messageFolderLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messageFolderLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _messageFolderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _messageFolderLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _messageFolderLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _messageFolderLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _messageFolderLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageFolder fetchMessageFolder(
		long folderId) {

		return _messageFolderLocalService.fetchMessageFolder(folderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messageFolderLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessageFolder>
		getAllUserFolders(long userId) {

		return _messageFolderLocalService.getAllUserFolders(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.User getFolderUser(long folderId) {
		return _messageFolderLocalService.getFolderUser(folderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messageFolderLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the message folder with the primary key.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder
	 * @throws PortalException if a message folder with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageFolder getMessageFolder(
			long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageFolderLocalService.getMessageFolder(folderId);
	}

	/**
	 * Returns a range of all the message folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @return the range of message folders
	 */
	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessageFolder>
		getMessageFolders(int start, int end) {

		return _messageFolderLocalService.getMessageFolders(start, end);
	}

	/**
	 * Returns the number of message folders.
	 *
	 * @return the number of message folders
	 */
	@Override
	public int getMessageFoldersCount() {
		return _messageFolderLocalService.getMessageFoldersCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageFolderLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageFolderLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageFolder getUserDraftFolder(
		long userId) {

		return _messageFolderLocalService.getUserDraftFolder(userId);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageFolder getUserInboxFolder(
		long userId) {

		return _messageFolderLocalService.getUserInboxFolder(userId);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageFolder
		getUserSendingBoxFolder(long userId) {

		return _messageFolderLocalService.getUserSendingBoxFolder(userId);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessageFolder>
		getUserSubFolders(long userId, long parentFolderId) {

		return _messageFolderLocalService.getUserSubFolders(
			userId, parentFolderId);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageFolder getUserTrashFolder(
		long userId) {

		return _messageFolderLocalService.getUserTrashFolder(userId);
	}

	/**
	 * Updates the message folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageFolder the message folder
	 * @return the message folder that was updated
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageFolder updateMessageFolder(
		com.weprode.nero.messaging.model.MessageFolder messageFolder) {

		return _messageFolderLocalService.updateMessageFolder(messageFolder);
	}

	@Override
	public MessageFolderLocalService getWrappedService() {
		return _messageFolderLocalService;
	}

	@Override
	public void setWrappedService(
		MessageFolderLocalService messageFolderLocalService) {

		_messageFolderLocalService = messageFolderLocalService;
	}

	private MessageFolderLocalService _messageFolderLocalService;

}