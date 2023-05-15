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

import ClayLabel from '@clayui/label';
import {
	API,
	AutoComplete,
	FormCustomSelect,
	FormError,
	Input,
	invalidateRequired,
	useForm,
} from '@liferay/object-js-components-web';
import React, {useEffect, useMemo, useState} from 'react';

export enum ObjectRelationshipType {
	MANY_TO_MANY = 'manyToMany',
	ONE_TO_MANY = 'oneToMany',
	ONE_TO_ONE = 'oneToOne',
}

const MANY_TO_MANY = {
	description: Liferay.Language.get(
		"multiple-object's-entries-can-interact-with-many-others-object's-entries"
	),
	label: Liferay.Language.get('many-to-many'),
	value: ObjectRelationshipType.MANY_TO_MANY,
};
const ONE_TO_MANY = {
	description: Liferay.Language.get(
		"one-object's-entry-interacts-with-many-others-object's-entries"
	),
	label: Liferay.Language.get('one-to-many'),
	value: ObjectRelationshipType.ONE_TO_MANY,
};
const ONE_TO_ONE = {
	description: Liferay.Language.get(
		"one-object's-entry-interacts-only-with-one-other-object's-entry"
	),
	label: Liferay.Language.get('one-to-one'),
	value: ObjectRelationshipType.ONE_TO_ONE,
};

const REQUIRED_MSG = Liferay.Language.get('required');

const defaultLanguageId = Liferay.ThemeDisplay.getDefaultLanguageId();

export function useObjectRelationshipForm({
	initialValues,
	onSubmit,
	parameterRequired,
}: IUseObjectRelationshipForm) {
	const validate = (relationship: Partial<ObjectRelationship>) => {
		const errors: FormError<ObjectRelationship> = {};

		const label =
			relationship.label?.[Liferay.ThemeDisplay.getDefaultLanguageId()];

		if (invalidateRequired(label)) {
			errors.label = REQUIRED_MSG;
		}

		if (invalidateRequired(relationship.name ?? label)) {
			errors.name = REQUIRED_MSG;
		}

		if (invalidateRequired(relationship.type)) {
			errors.type = REQUIRED_MSG;
		}

		if (!relationship.objectDefinitionId2) {
			errors.objectDefinitionId2 = REQUIRED_MSG;
		}

		if (
			parameterRequired &&
			relationship.type === ObjectRelationshipType.ONE_TO_MANY &&
			!relationship.parameterObjectFieldId
		) {
			errors.parameterObjectFieldId = REQUIRED_MSG;
		}

		return errors;
	};

	const {errors, handleChange, handleSubmit, setValues, values} = useForm({
		initialValues,
		onSubmit,
		validate,
	});

	return {errors, handleChange, handleSubmit, setValues, values};
}

export function ObjectRelationshipFormBase({
	errors,
	ffOneToOneRelationshipConfigurationEnabled,
	handleChange,
	readonly,
	setValues,
	values,
}: IPros) {
	const [objectDefinitions, setObjectDefinitions] = useState<
		ObjectDefinition[]
	>([]);
	const [query, setQuery] = useState<string>('');

	const [types, selectedType] = useMemo(() => {
		const types = [ONE_TO_MANY, MANY_TO_MANY];
		if (ffOneToOneRelationshipConfigurationEnabled) {
			types.push(ONE_TO_ONE);
		}

		return [types, types.find(({value}) => value === values.type)?.label];
	}, [ffOneToOneRelationshipConfigurationEnabled, values.type]);

	useEffect(() => {
		const fetchObjectDefinitions = async () => {
			const items = await API.getObjectDefinitions();

			const currentObjectDefinition = items.find(
				({id}) => values.objectDefinitionId1 === id
			)!;

			const objectDefinitions = items.filter(
				({parameterRequired, storageType, system}) =>
					(!currentObjectDefinition.system || !system) &&
					(!Liferay.FeatureFlags['LPS-135430'] ||
						storageType === 'default') &&
					!parameterRequired
			);

			setObjectDefinitions(objectDefinitions);
		};

		if (readonly) {
			setObjectDefinitions([
				{
					id: values.objectDefinitionId2 as number,
					label: values.label as LocalizedValue<string>,
					name: values.objectDefinitionName2 as string,
					system: false,
				},
			]);
		}
		else {
			fetchObjectDefinitions();
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [readonly, values.objectDefinitionId1]);

	const filteredObjectDefinitions = useMemo(
		() =>
			values.type === ObjectRelationshipType.MANY_TO_MANY
				? objectDefinitions.filter(
						({id}) => id !== values.objectDefinitionId1
				  )
				: objectDefinitions,
		[objectDefinitions, values.objectDefinitionId1, values.type]
	);

	const filteredRelationships = useMemo(() => {
		if (Liferay.FeatureFlags['LPS-158478']) {
			return objectDefinitions.filter(({label}) => {
				return label[defaultLanguageId]
					?.toLocaleLowerCase()
					.includes(query.toLowerCase());
			});
		}
		else {
			return filteredObjectDefinitions.filter(({label}) => {
				return label[defaultLanguageId]
					?.toLocaleLowerCase()
					.includes(query.toLowerCase());
			});
		}
	}, [filteredObjectDefinitions, objectDefinitions, query]);

	return (
		<>
			<Input
				disabled={readonly}
				error={errors.name}
				label={Liferay.Language.get('name')}
				name="name"
				onChange={handleChange}
				required
				value={values.name}
			/>

			<FormCustomSelect
				disabled={readonly}
				error={errors.type}
				label={Liferay.Language.get('type')}
				onChange={({value}) => setValues({type: value})}
				options={types}
				required
				value={selectedType}
			/>

			<AutoComplete
				disabled={readonly}
				emptyStateMessage={Liferay.Language.get(
					'no-objects-were-found'
				)}
				error={errors.objectDefinitionId2}
				items={filteredRelationships}
				label={Liferay.Language.get('object')}
				onChangeQuery={setQuery}
				onSelectItem={(item: ObjectDefinition) => {
					setValues({
						objectDefinitionId2: item.id,
						objectDefinitionName2: item.name,
					});
				}}
				query={query}
				required
				value={values.objectDefinitionName2}
			>
				{({label, name, system}) => (
					<div className="d-flex justify-content-between">
						<div>{label[defaultLanguageId] ?? name}</div>

						<ClayLabel displayType={system ? 'info' : 'warning'}>
							{system
								? Liferay.Language.get('system')
								: Liferay.Language.get('custom')}
						</ClayLabel>
					</div>
				)}
			</AutoComplete>
		</>
	);
}

interface IUseObjectRelationshipForm {
	initialValues: Partial<ObjectRelationship>;
	onSubmit: (relationship: ObjectRelationship) => void;
	parameterRequired: boolean;
}

interface IPros {
	errors: FormError<ObjectRelationship>;
	ffOneToOneRelationshipConfigurationEnabled?: boolean;
	handleChange: React.ChangeEventHandler<HTMLInputElement>;
	readonly?: boolean;
	setValues: (values: Partial<ObjectRelationship>) => void;
	values: Partial<ObjectRelationship>;
}

type ObjectDefinition = {
	id: number;
	label: LocalizedValue<string>;
	name: string;
	system: boolean;
};