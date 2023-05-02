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

package com.weprode.nero.help.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HelpQuestionSoap implements Serializable {

	public static HelpQuestionSoap toSoapModel(HelpQuestion model) {
		HelpQuestionSoap soapModel = new HelpQuestionSoap();

		soapModel.setQuestionId(model.getQuestionId());
		soapModel.setItemId(model.getItemId());
		soapModel.setQuestion(model.getQuestion());
		soapModel.setAnswer(model.getAnswer());

		return soapModel;
	}

	public static HelpQuestionSoap[] toSoapModels(HelpQuestion[] models) {
		HelpQuestionSoap[] soapModels = new HelpQuestionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpQuestionSoap[][] toSoapModels(HelpQuestion[][] models) {
		HelpQuestionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpQuestionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpQuestionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpQuestionSoap[] toSoapModels(List<HelpQuestion> models) {
		List<HelpQuestionSoap> soapModels = new ArrayList<HelpQuestionSoap>(
			models.size());

		for (HelpQuestion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpQuestionSoap[soapModels.size()]);
	}

	public HelpQuestionSoap() {
	}

	public long getPrimaryKey() {
		return _questionId;
	}

	public void setPrimaryKey(long pk) {
		setQuestionId(pk);
	}

	public long getQuestionId() {
		return _questionId;
	}

	public void setQuestionId(long questionId) {
		_questionId = questionId;
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public String getQuestion() {
		return _question;
	}

	public void setQuestion(String question) {
		_question = question;
	}

	public String getAnswer() {
		return _answer;
	}

	public void setAnswer(String answer) {
		_answer = answer;
	}

	private long _questionId;
	private long _itemId;
	private String _question;
	private String _answer;

}