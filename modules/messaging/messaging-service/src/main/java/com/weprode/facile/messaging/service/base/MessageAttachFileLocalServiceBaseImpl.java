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

package com.weprode.facile.messaging.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.facile.messaging.model.MessageAttachFile;
import com.weprode.facile.messaging.service.MessageAttachFileLocalService;
import com.weprode.facile.messaging.service.MessageAttachFileLocalServiceUtil;
import com.weprode.facile.messaging.service.persistence.MessageAttachFilePK;
import com.weprode.facile.messaging.service.persistence.MessageAttachFilePersistence;
import com.weprode.facile.messaging.service.persistence.MessageContentPersistence;
import com.weprode.facile.messaging.service.persistence.MessageFinder;
import com.weprode.facile.messaging.service.persistence.MessageFolderPersistence;
import com.weprode.facile.messaging.service.persistence.MessagePersistence;
import com.weprode.facile.messaging.service.persistence.MessageRecipientsPersistence;
import com.weprode.facile.messaging.service.persistence.MessagingConfigPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the message attach file local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.messaging.service.impl.MessageAttachFileLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.messaging.service.impl.MessageAttachFileLocalServiceImpl
 * @generated
 */
public abstract class MessageAttachFileLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   MessageAttachFileLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MessageAttachFileLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MessageAttachFileLocalServiceUtil</code>.
	 */

	/**
	 * Adds the message attach file to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFile the message attach file
	 * @return the message attach file that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MessageAttachFile addMessageAttachFile(
		MessageAttachFile messageAttachFile) {

		messageAttachFile.setNew(true);

		return messageAttachFilePersistence.update(messageAttachFile);
	}

	/**
	 * Creates a new message attach file with the primary key. Does not add the message attach file to the database.
	 *
	 * @param messageAttachFilePK the primary key for the new message attach file
	 * @return the new message attach file
	 */
	@Override
	@Transactional(enabled = false)
	public MessageAttachFile createMessageAttachFile(
		MessageAttachFilePK messageAttachFilePK) {

		return messageAttachFilePersistence.create(messageAttachFilePK);
	}

	/**
	 * Deletes the message attach file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file that was removed
	 * @throws PortalException if a message attach file with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MessageAttachFile deleteMessageAttachFile(
			MessageAttachFilePK messageAttachFilePK)
		throws PortalException {

		return messageAttachFilePersistence.remove(messageAttachFilePK);
	}

	/**
	 * Deletes the message attach file from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFile the message attach file
	 * @return the message attach file that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MessageAttachFile deleteMessageAttachFile(
		MessageAttachFile messageAttachFile) {

		return messageAttachFilePersistence.remove(messageAttachFile);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return messageAttachFilePersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			MessageAttachFile.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return messageAttachFilePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return messageAttachFilePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return messageAttachFilePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return messageAttachFilePersistence.countWithDynamicQuery(dynamicQuery);
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
		DynamicQuery dynamicQuery, Projection projection) {

		return messageAttachFilePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MessageAttachFile fetchMessageAttachFile(
		MessageAttachFilePK messageAttachFilePK) {

		return messageAttachFilePersistence.fetchByPrimaryKey(
			messageAttachFilePK);
	}

	/**
	 * Returns the message attach file with the primary key.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file
	 * @throws PortalException if a message attach file with the primary key could not be found
	 */
	@Override
	public MessageAttachFile getMessageAttachFile(
			MessageAttachFilePK messageAttachFilePK)
		throws PortalException {

		return messageAttachFilePersistence.findByPrimaryKey(
			messageAttachFilePK);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			messageAttachFileLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MessageAttachFile.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.messageId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			messageAttachFileLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MessageAttachFile.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.messageId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			messageAttachFileLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MessageAttachFile.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.messageId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return messageAttachFilePersistence.create(
			(MessageAttachFilePK)primaryKeyObj);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement MessageAttachFileLocalServiceImpl#deleteMessageAttachFile(MessageAttachFile) to avoid orphaned data");
		}

		return messageAttachFileLocalService.deleteMessageAttachFile(
			(MessageAttachFile)persistedModel);
	}

	@Override
	public BasePersistence<MessageAttachFile> getBasePersistence() {
		return messageAttachFilePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return messageAttachFilePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the message attach files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @return the range of message attach files
	 */
	@Override
	public List<MessageAttachFile> getMessageAttachFiles(int start, int end) {
		return messageAttachFilePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of message attach files.
	 *
	 * @return the number of message attach files
	 */
	@Override
	public int getMessageAttachFilesCount() {
		return messageAttachFilePersistence.countAll();
	}

	/**
	 * Updates the message attach file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFile the message attach file
	 * @return the message attach file that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MessageAttachFile updateMessageAttachFile(
		MessageAttachFile messageAttachFile) {

		return messageAttachFilePersistence.update(messageAttachFile);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MessageAttachFileLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		messageAttachFileLocalService = (MessageAttachFileLocalService)aopProxy;

		_setLocalServiceUtilService(messageAttachFileLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MessageAttachFileLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MessageAttachFile.class;
	}

	protected String getModelClassName() {
		return MessageAttachFile.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				messageAttachFilePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		MessageAttachFileLocalService messageAttachFileLocalService) {

		try {
			Field field =
				MessageAttachFileLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, messageAttachFileLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected MessagePersistence messagePersistence;

	@Reference
	protected MessageFinder messageFinder;

	protected MessageAttachFileLocalService messageAttachFileLocalService;

	@Reference
	protected MessageAttachFilePersistence messageAttachFilePersistence;

	@Reference
	protected MessageContentPersistence messageContentPersistence;

	@Reference
	protected MessageFolderPersistence messageFolderPersistence;

	@Reference
	protected MessageRecipientsPersistence messageRecipientsPersistence;

	@Reference
	protected MessagingConfigPersistence messagingConfigPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		MessageAttachFileLocalServiceBaseImpl.class);

}