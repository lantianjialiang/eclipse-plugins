/*******************************************************************************
 * Copyright (c) 2013 jialiang.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Ben Xu, xufengbing@gmail.com - initial API and implementation
 *     jialiang, lantianjialiang@gmail.com - add copy right and fix warning
 ******************************************************************************/
package com.google.code.t4eclipse.tools.view.provider;

import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.google.code.t4eclipse.core.utility.ControlUtility;
import com.google.code.t4eclipse.selection.ControlSelection;


public class ControlLabelProvider extends LabelProvider implements
		ITableLabelProvider, ITableFontProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		if (ControlUtility.COLUMNS[columnIndex].equals("Name")) {

			if (element instanceof ControlSelection) {
				ControlSelection cs = (ControlSelection) element;

				Control controlElement = cs.getControl();

				if (!(controlElement instanceof Composite)) {
					return PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJ_FILE);

				}
			}

		}
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {

		if (element instanceof ControlSelection) {
			ControlSelection cs = (ControlSelection) element;
			Control controlElement = cs.getControl();

			String data = ControlUtility.getControlRowDataStr(controlElement,
					ControlUtility.COLUMNS[columnIndex]);

			return data;
		}

		return "";
	}

	public Font getFont(Object element, int columnIndex) {
		return null;
	}
}
