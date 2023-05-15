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

package com.liferay.bulk.rest.client.serdes.v1_0;

import com.liferay.bulk.rest.client.dto.v1_0.DocumentBulkSelection;
import com.liferay.bulk.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Alejandro Tardín
 * @generated
 */
@Generated("")
public class DocumentBulkSelectionSerDes {

	public static DocumentBulkSelection toDTO(String json) {
		DocumentBulkSelectionJSONParser documentBulkSelectionJSONParser =
			new DocumentBulkSelectionJSONParser();

		return documentBulkSelectionJSONParser.parseToDTO(json);
	}

	public static DocumentBulkSelection[] toDTOs(String json) {
		DocumentBulkSelectionJSONParser documentBulkSelectionJSONParser =
			new DocumentBulkSelectionJSONParser();

		return documentBulkSelectionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(DocumentBulkSelection documentBulkSelection) {
		if (documentBulkSelection == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (documentBulkSelection.getDocumentIds() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"documentIds\": ");

			sb.append("[");

			for (int i = 0; i < documentBulkSelection.getDocumentIds().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(documentBulkSelection.getDocumentIds()[i]));

				sb.append("\"");

				if ((i + 1) < documentBulkSelection.getDocumentIds().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (documentBulkSelection.getSelectionScope() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"selectionScope\": ");

			sb.append(
				String.valueOf(documentBulkSelection.getSelectionScope()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		DocumentBulkSelectionJSONParser documentBulkSelectionJSONParser =
			new DocumentBulkSelectionJSONParser();

		return documentBulkSelectionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		DocumentBulkSelection documentBulkSelection) {

		if (documentBulkSelection == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (documentBulkSelection.getDocumentIds() == null) {
			map.put("documentIds", null);
		}
		else {
			map.put(
				"documentIds",
				String.valueOf(documentBulkSelection.getDocumentIds()));
		}

		if (documentBulkSelection.getSelectionScope() == null) {
			map.put("selectionScope", null);
		}
		else {
			map.put(
				"selectionScope",
				String.valueOf(documentBulkSelection.getSelectionScope()));
		}

		return map;
	}

	public static class DocumentBulkSelectionJSONParser
		extends BaseJSONParser<DocumentBulkSelection> {

		@Override
		protected DocumentBulkSelection createDTO() {
			return new DocumentBulkSelection();
		}

		@Override
		protected DocumentBulkSelection[] createDTOArray(int size) {
			return new DocumentBulkSelection[size];
		}

		@Override
		protected void setField(
			DocumentBulkSelection documentBulkSelection,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "documentIds")) {
				if (jsonParserFieldValue != null) {
					documentBulkSelection.setDocumentIds(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "selectionScope")) {
				if (jsonParserFieldValue != null) {
					documentBulkSelection.setSelectionScope(
						SelectionScopeSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}