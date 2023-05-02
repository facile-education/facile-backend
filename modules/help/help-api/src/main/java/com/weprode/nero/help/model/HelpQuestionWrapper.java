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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HelpQuestion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpQuestion
 * @generated
 */
public class HelpQuestionWrapper
	extends BaseModelWrapper<HelpQuestion>
	implements HelpQuestion, ModelWrapper<HelpQuestion> {

	public HelpQuestionWrapper(HelpQuestion helpQuestion) {
		super(helpQuestion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("questionId", getQuestionId());
		attributes.put("itemId", getItemId());
		attributes.put("question", getQuestion());
		attributes.put("answer", getAnswer());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
		}

		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String question = (String)attributes.get("question");

		if (question != null) {
			setQuestion(question);
		}

		String answer = (String)attributes.get("answer");

		if (answer != null) {
			setAnswer(answer);
		}
	}

	@Override
	public HelpQuestion cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the answer of this help question.
	 *
	 * @return the answer of this help question
	 */
	@Override
	public String getAnswer() {
		return model.getAnswer();
	}

	/**
	 * Returns the item ID of this help question.
	 *
	 * @return the item ID of this help question
	 */
	@Override
	public long getItemId() {
		return model.getItemId();
	}

	/**
	 * Returns the primary key of this help question.
	 *
	 * @return the primary key of this help question
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the question of this help question.
	 *
	 * @return the question of this help question
	 */
	@Override
	public String getQuestion() {
		return model.getQuestion();
	}

	/**
	 * Returns the question ID of this help question.
	 *
	 * @return the question ID of this help question
	 */
	@Override
	public long getQuestionId() {
		return model.getQuestionId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the answer of this help question.
	 *
	 * @param answer the answer of this help question
	 */
	@Override
	public void setAnswer(String answer) {
		model.setAnswer(answer);
	}

	/**
	 * Sets the item ID of this help question.
	 *
	 * @param itemId the item ID of this help question
	 */
	@Override
	public void setItemId(long itemId) {
		model.setItemId(itemId);
	}

	/**
	 * Sets the primary key of this help question.
	 *
	 * @param primaryKey the primary key of this help question
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the question of this help question.
	 *
	 * @param question the question of this help question
	 */
	@Override
	public void setQuestion(String question) {
		model.setQuestion(question);
	}

	/**
	 * Sets the question ID of this help question.
	 *
	 * @param questionId the question ID of this help question
	 */
	@Override
	public void setQuestionId(long questionId) {
		model.setQuestionId(questionId);
	}

	@Override
	protected HelpQuestionWrapper wrap(HelpQuestion helpQuestion) {
		return new HelpQuestionWrapper(helpQuestion);
	}

}