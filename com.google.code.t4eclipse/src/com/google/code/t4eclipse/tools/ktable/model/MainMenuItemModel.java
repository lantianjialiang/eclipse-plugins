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
package com.google.code.t4eclipse.tools.ktable.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.code.t4eclipse.tools.ktable.RowModel;

import de.kupzog.ktable.t4eclipse.KTableCellRenderer;

public class MainMenuItemModel extends RowModel {

	public boolean top;

	public boolean group;

	public boolean separator;

	public String menuStr;

	public String path;

	@Override
	public int[] getColumLengths() {
		return new int[] {  300, 300 };
	}

	@Override
	public String[] getColumNames() {
		return new String[] {  "MenuStr", "Path" };
	}

	
	
	public MainMenuItemModel(Object o) {
		super(o);

	}

	public MainMenuItemModel() {
		super();
	}

	public String getColMenuStr() {
		if(this.separator){
			return "-------------------------------------------";
		}
		return this.menuStr == null ? "" : this.menuStr;
	}

	public String getColPath() {
		return this.path == null ? "" : this.path;
	}

	public String getColTop() {
		return this.top ? "Y" : "";
	}

	public String getColSeprator() {
		return this.separator ? "Y" : "";
	}

	public String getColGroup() {

		return this.group ? "Y" : "";
	}

	@Override
	public KTableCellRenderer getCellRender(String columName, int col, int row) {
		if (this.top) {
			m_textRenderer.setBackground(Display.getCurrent().getSystemColor(
					SWT.COLOR_YELLOW));
			m_textRenderer.setForeground(Display.getCurrent().getSystemColor(
					SWT.COLOR_BLACK));

		} else if (this.separator||this.group) {
			m_textRenderer.setBackground(Display.getCurrent().getSystemColor(
					SWT.COLOR_GRAY));
			m_textRenderer.setForeground(Display.getCurrent().getSystemColor(
					SWT.COLOR_BLACK));
		} else {
			m_textRenderer.setBackground(Display.getCurrent().getSystemColor(
					SWT.COLOR_WHITE));
			m_textRenderer.setForeground(Display.getCurrent().getSystemColor(
					SWT.COLOR_BLACK));
		}
		return m_textRenderer;

	}

	@Override
	public String[] getMenuItemNames() {
		return null;
	}

}
