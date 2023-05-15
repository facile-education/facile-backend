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

import classNames from 'classnames';
import React from 'react';

import ItemConfiguration from './ItemConfiguration';
import StructureTree from './StructureTree';

export default function PageStructureSidebar() {
	return (
		<div
			className={classNames('page-editor__page-structure', {
				'overflow-auto': Liferay.FeatureFlags['LPS-153452'],
			})}
		>
			<StructureTree />

			{!Liferay.FeatureFlags['LPS-153452'] && <ItemConfiguration />}
		</div>
	);
}
