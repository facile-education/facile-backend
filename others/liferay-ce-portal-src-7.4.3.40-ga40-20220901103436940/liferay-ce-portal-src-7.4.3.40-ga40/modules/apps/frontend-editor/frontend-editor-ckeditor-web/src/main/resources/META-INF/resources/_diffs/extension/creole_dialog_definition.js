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

CKEDITOR.on(
	'dialogDefinition',
	(event) => {
		if (event.editor === ckEditor) {
			const boundingWindow = event.editor.window;

			const dialogName = event.data.name;

			const dialogDefinition = event.data.definition;

			const dialog = event.data.dialog;

			const onShow = dialogDefinition.onShow;

			const centerDialog = function () {
				const dialogSize = dialog.getSize();

				const x = window.innerWidth / 2 - dialogSize.width / 2;
				const y = window.innerHeight / 2 - dialogSize.height / 2;

				dialog.move(x, y, false);
			};

			dialogDefinition.onShow = function () {
				if (typeof onShow === 'function') {
					onShow.apply(this, arguments);
				}

				centerDialog();
			};

			const debounce = function (fn, delay) {
				return function debounced() {
					const args = arguments;
					clearTimeout(debounced.id);
					debounced.id = setTimeout(() => {
						fn.apply(null, args);
					}, delay);
				};
			};

			boundingWindow.on(
				'resize',
				debounce(() => {
					centerDialog();
				}, 250)
			);

			let infoTab;

			if (dialogName === 'cellProperties') {
				infoTab = dialogDefinition.getContents('info');

				infoTab.remove('bgColor');
				infoTab.remove('bgColorChoose');
				infoTab.remove('borderColor');
				infoTab.remove('borderColorChoose');
				infoTab.remove('colSpan');
				infoTab.remove('hAlign');
				infoTab.remove('height');
				infoTab.remove('htmlHeightType');
				infoTab.remove('rowSpan');
				infoTab.remove('vAlign');
				infoTab.remove('width');
				infoTab.remove('widthType');
				infoTab.remove('wordWrap');

				dialogDefinition.minHeight = 40;
				dialogDefinition.minWidth = 210;
			}
			else if (
				dialogName === 'table' ||
				dialogName === 'tableProperties'
			) {
				infoTab = dialogDefinition.getContents('info');

				infoTab.remove('cmbAlign');
				infoTab.remove('cmbWidthType');
				infoTab.remove('cmbWidthType');
				infoTab.remove('htmlHeightType');
				infoTab.remove('txtBorder');
				infoTab.remove('txtCellPad');
				infoTab.remove('txtCellSpace');
				infoTab.remove('txtHeight');
				infoTab.remove('txtSummary');
				infoTab.remove('txtWidth');

				dialogDefinition.minHeight = 180;
				dialogDefinition.minWidth = 210;
			}
			else if (dialogName === 'image') {
				dialogDefinition.removeContents('Link');
				dialogDefinition.removeContents('advanced');
			}
		}
	},
	null,
	null,
	100
);
