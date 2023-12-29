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

package com.weprode.facile.document.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LoolToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolToken
 * @generated
 */
public class LoolTokenWrapper
	extends BaseModelWrapper<LoolToken>
	implements LoolToken, ModelWrapper<LoolToken> {

	public LoolTokenWrapper(LoolToken loolToken) {
		super(loolToken);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loolTokenId", getLoolTokenId());
		attributes.put("userId", getUserId());
		attributes.put("token", getToken());
		attributes.put("editionDate", getEditionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loolTokenId = (Long)attributes.get("loolTokenId");

		if (loolTokenId != null) {
			setLoolTokenId(loolTokenId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		Date editionDate = (Date)attributes.get("editionDate");

		if (editionDate != null) {
			setEditionDate(editionDate);
		}
	}

	@Override
	public LoolToken cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the edition date of this lool token.
	 *
	 * @return the edition date of this lool token
	 */
	@Override
	public Date getEditionDate() {
		return model.getEditionDate();
	}

	/**
	 * Returns the lool token ID of this lool token.
	 *
	 * @return the lool token ID of this lool token
	 */
	@Override
	public long getLoolTokenId() {
		return model.getLoolTokenId();
	}

	/**
	 * Returns the primary key of this lool token.
	 *
	 * @return the primary key of this lool token
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the token of this lool token.
	 *
	 * @return the token of this lool token
	 */
	@Override
	public String getToken() {
		return model.getToken();
	}

	/**
	 * Returns the user ID of this lool token.
	 *
	 * @return the user ID of this lool token
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this lool token.
	 *
	 * @return the user uuid of this lool token
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the edition date of this lool token.
	 *
	 * @param editionDate the edition date of this lool token
	 */
	@Override
	public void setEditionDate(Date editionDate) {
		model.setEditionDate(editionDate);
	}

	/**
	 * Sets the lool token ID of this lool token.
	 *
	 * @param loolTokenId the lool token ID of this lool token
	 */
	@Override
	public void setLoolTokenId(long loolTokenId) {
		model.setLoolTokenId(loolTokenId);
	}

	/**
	 * Sets the primary key of this lool token.
	 *
	 * @param primaryKey the primary key of this lool token
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the token of this lool token.
	 *
	 * @param token the token of this lool token
	 */
	@Override
	public void setToken(String token) {
		model.setToken(token);
	}

	/**
	 * Sets the user ID of this lool token.
	 *
	 * @param userId the user ID of this lool token
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this lool token.
	 *
	 * @param userUuid the user uuid of this lool token
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected LoolTokenWrapper wrap(LoolToken loolToken) {
		return new LoolTokenWrapper(loolToken);
	}

}