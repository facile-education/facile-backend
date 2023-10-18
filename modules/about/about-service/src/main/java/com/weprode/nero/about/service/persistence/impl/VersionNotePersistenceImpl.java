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

package com.weprode.nero.about.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.weprode.nero.about.exception.NoSuchVersionNoteException;
import com.weprode.nero.about.model.VersionNote;
import com.weprode.nero.about.model.VersionNoteTable;
import com.weprode.nero.about.model.impl.VersionNoteImpl;
import com.weprode.nero.about.model.impl.VersionNoteModelImpl;
import com.weprode.nero.about.service.persistence.VersionNotePersistence;
import com.weprode.nero.about.service.persistence.VersionNoteUtil;
import com.weprode.nero.about.service.persistence.impl.constants.AboutPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the version note service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {VersionNotePersistence.class, BasePersistence.class})
public class VersionNotePersistenceImpl
	extends BasePersistenceImpl<VersionNote> implements VersionNotePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>VersionNoteUtil</code> to access the version note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		VersionNoteImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public VersionNotePersistenceImpl() {
		setModelClass(VersionNote.class);

		setModelImplClass(VersionNoteImpl.class);
		setModelPKClass(long.class);

		setTable(VersionNoteTable.INSTANCE);
	}

	/**
	 * Caches the version note in the entity cache if it is enabled.
	 *
	 * @param versionNote the version note
	 */
	@Override
	public void cacheResult(VersionNote versionNote) {
		entityCache.putResult(
			VersionNoteImpl.class, versionNote.getPrimaryKey(), versionNote);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the version notes in the entity cache if it is enabled.
	 *
	 * @param versionNotes the version notes
	 */
	@Override
	public void cacheResult(List<VersionNote> versionNotes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (versionNotes.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (VersionNote versionNote : versionNotes) {
			if (entityCache.getResult(
					VersionNoteImpl.class, versionNote.getPrimaryKey()) ==
						null) {

				cacheResult(versionNote);
			}
		}
	}

	/**
	 * Clears the cache for all version notes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VersionNoteImpl.class);

		finderCache.clearCache(VersionNoteImpl.class);
	}

	/**
	 * Clears the cache for the version note.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VersionNote versionNote) {
		entityCache.removeResult(VersionNoteImpl.class, versionNote);
	}

	@Override
	public void clearCache(List<VersionNote> versionNotes) {
		for (VersionNote versionNote : versionNotes) {
			entityCache.removeResult(VersionNoteImpl.class, versionNote);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(VersionNoteImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(VersionNoteImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new version note with the primary key. Does not add the version note to the database.
	 *
	 * @param versionNoteId the primary key for the new version note
	 * @return the new version note
	 */
	@Override
	public VersionNote create(long versionNoteId) {
		VersionNote versionNote = new VersionNoteImpl();

		versionNote.setNew(true);
		versionNote.setPrimaryKey(versionNoteId);

		return versionNote;
	}

	/**
	 * Removes the version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note that was removed
	 * @throws NoSuchVersionNoteException if a version note with the primary key could not be found
	 */
	@Override
	public VersionNote remove(long versionNoteId)
		throws NoSuchVersionNoteException {

		return remove((Serializable)versionNoteId);
	}

	/**
	 * Removes the version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the version note
	 * @return the version note that was removed
	 * @throws NoSuchVersionNoteException if a version note with the primary key could not be found
	 */
	@Override
	public VersionNote remove(Serializable primaryKey)
		throws NoSuchVersionNoteException {

		Session session = null;

		try {
			session = openSession();

			VersionNote versionNote = (VersionNote)session.get(
				VersionNoteImpl.class, primaryKey);

			if (versionNote == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVersionNoteException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(versionNote);
		}
		catch (NoSuchVersionNoteException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected VersionNote removeImpl(VersionNote versionNote) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(versionNote)) {
				versionNote = (VersionNote)session.get(
					VersionNoteImpl.class, versionNote.getPrimaryKeyObj());
			}

			if (versionNote != null) {
				session.delete(versionNote);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (versionNote != null) {
			clearCache(versionNote);
		}

		return versionNote;
	}

	@Override
	public VersionNote updateImpl(VersionNote versionNote) {
		boolean isNew = versionNote.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(versionNote);
			}
			else {
				versionNote = (VersionNote)session.merge(versionNote);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(VersionNoteImpl.class, versionNote, false, true);

		if (isNew) {
			versionNote.setNew(false);
		}

		versionNote.resetOriginalValues();

		return versionNote;
	}

	/**
	 * Returns the version note with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the version note
	 * @return the version note
	 * @throws NoSuchVersionNoteException if a version note with the primary key could not be found
	 */
	@Override
	public VersionNote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVersionNoteException {

		VersionNote versionNote = fetchByPrimaryKey(primaryKey);

		if (versionNote == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVersionNoteException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return versionNote;
	}

	/**
	 * Returns the version note with the primary key or throws a <code>NoSuchVersionNoteException</code> if it could not be found.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note
	 * @throws NoSuchVersionNoteException if a version note with the primary key could not be found
	 */
	@Override
	public VersionNote findByPrimaryKey(long versionNoteId)
		throws NoSuchVersionNoteException {

		return findByPrimaryKey((Serializable)versionNoteId);
	}

	/**
	 * Returns the version note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note, or <code>null</code> if a version note with the primary key could not be found
	 */
	@Override
	public VersionNote fetchByPrimaryKey(long versionNoteId) {
		return fetchByPrimaryKey((Serializable)versionNoteId);
	}

	/**
	 * Returns all the version notes.
	 *
	 * @return the version notes
	 */
	@Override
	public List<VersionNote> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @return the range of version notes
	 */
	@Override
	public List<VersionNote> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of version notes
	 */
	@Override
	public List<VersionNote> findAll(
		int start, int end, OrderByComparator<VersionNote> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of version notes
	 */
	@Override
	public List<VersionNote> findAll(
		int start, int end, OrderByComparator<VersionNote> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<VersionNote> list = null;

		if (useFinderCache) {
			list = (List<VersionNote>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_VERSIONNOTE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_VERSIONNOTE;

				sql = sql.concat(VersionNoteModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<VersionNote>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the version notes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VersionNote versionNote : findAll()) {
			remove(versionNote);
		}
	}

	/**
	 * Returns the number of version notes.
	 *
	 * @return the number of version notes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_VERSIONNOTE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "versionNoteId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_VERSIONNOTE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VersionNoteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the version note persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setVersionNoteUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setVersionNoteUtilPersistence(null);

		entityCache.removeCache(VersionNoteImpl.class.getName());
	}

	private void _setVersionNoteUtilPersistence(
		VersionNotePersistence versionNotePersistence) {

		try {
			Field field = VersionNoteUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, versionNotePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_VERSIONNOTE =
		"SELECT versionNote FROM VersionNote versionNote";

	private static final String _SQL_COUNT_VERSIONNOTE =
		"SELECT COUNT(versionNote) FROM VersionNote versionNote";

	private static final String _ORDER_BY_ENTITY_ALIAS = "versionNote.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No VersionNote exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		VersionNotePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}