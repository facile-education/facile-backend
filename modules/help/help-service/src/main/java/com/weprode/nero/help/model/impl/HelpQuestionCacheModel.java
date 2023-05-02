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

package com.weprode.nero.help.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.help.model.HelpQuestion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HelpQuestion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpQuestionCacheModel
	implements CacheModel<HelpQuestion>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpQuestionCacheModel)) {
			return false;
		}

		HelpQuestionCacheModel helpQuestionCacheModel =
			(HelpQuestionCacheModel)object;

		if (questionId == helpQuestionCacheModel.questionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, questionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{questionId=");
		sb.append(questionId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", question=");
		sb.append(question);
		sb.append(", answer=");
		sb.append(answer);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpQuestion toEntityModel() {
		HelpQuestionImpl helpQuestionImpl = new HelpQuestionImpl();

		helpQuestionImpl.setQuestionId(questionId);
		helpQuestionImpl.setItemId(itemId);

		if (question == null) {
			helpQuestionImpl.setQuestion("");
		}
		else {
			helpQuestionImpl.setQuestion(question);
		}

		if (answer == null) {
			helpQuestionImpl.setAnswer("");
		}
		else {
			helpQuestionImpl.setAnswer(answer);
		}

		helpQuestionImpl.resetOriginalValues();

		return helpQuestionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		questionId = objectInput.readLong();

		itemId = objectInput.readLong();
		question = objectInput.readUTF();
		answer = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(questionId);

		objectOutput.writeLong(itemId);

		if (question == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(question);
		}

		if (answer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(answer);
		}
	}

	public long questionId;
	public long itemId;
	public String question;
	public String answer;

}