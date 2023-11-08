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

package com.weprode.facile.organization.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.organization.model.OrgCiteScolaire;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OrgCiteScolaire in entity cache.
 *
 * @author Marc Salvat
 * @generated
 */
public class OrgCiteScolaireCacheModel
	implements CacheModel<OrgCiteScolaire>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OrgCiteScolaireCacheModel)) {
			return false;
		}

		OrgCiteScolaireCacheModel orgCiteScolaireCacheModel =
			(OrgCiteScolaireCacheModel)object;

		if (childENTStructureUAI.equals(
				orgCiteScolaireCacheModel.childENTStructureUAI)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, childENTStructureUAI);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{parentENTStructureUAI=");
		sb.append(parentENTStructureUAI);
		sb.append(", childENTStructureUAI=");
		sb.append(childENTStructureUAI);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OrgCiteScolaire toEntityModel() {
		OrgCiteScolaireImpl orgCiteScolaireImpl = new OrgCiteScolaireImpl();

		if (parentENTStructureUAI == null) {
			orgCiteScolaireImpl.setParentENTStructureUAI("");
		}
		else {
			orgCiteScolaireImpl.setParentENTStructureUAI(parentENTStructureUAI);
		}

		if (childENTStructureUAI == null) {
			orgCiteScolaireImpl.setChildENTStructureUAI("");
		}
		else {
			orgCiteScolaireImpl.setChildENTStructureUAI(childENTStructureUAI);
		}

		orgCiteScolaireImpl.resetOriginalValues();

		return orgCiteScolaireImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		parentENTStructureUAI = objectInput.readUTF();
		childENTStructureUAI = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (parentENTStructureUAI == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(parentENTStructureUAI);
		}

		if (childENTStructureUAI == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(childENTStructureUAI);
		}
	}

	public String parentENTStructureUAI;
	public String childENTStructureUAI;

}