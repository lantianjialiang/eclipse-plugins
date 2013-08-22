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

import org.eclipse.swt.widgets.Control;

import com.google.code.t4eclipse.tools.ktable.RowModel;
import com.google.code.t4eclipse.tools.ktable.SimpleKTable;
import com.google.code.t4eclipse.tools.ktable.SimpleKTableModel;

import de.kupzog.ktable.t4eclipse.KTableCellRenderer;

@SuppressWarnings("hiding")
public class ControlAnalysisModel extends RowModel {
	Object object;

	String info;

	@Override
	public int[] getColumLengths() {
		return new int[] { 100, 150 };
	}

	@Override
	public String[] getColumNames() {
		return new String[] { "Type", "Info" };
	}

	@Override
	public Object getObject(){
		return this.object;
	}
	public ControlAnalysisModel(Object o, String info) {
		this.object = o;
		this.info = info;
	}

	public ControlAnalysisModel() {
		super();
	}

	public String getColType() {
		if (this.object == null) {
			return "null";
		}

		if (this.object instanceof Control) {
			Control c = (Control) this.object;
			if (c.isDisposed()) {
				return "disposed:" + this.object.getClass().getSimpleName();
			}
		}
		return this.object.getClass().getSimpleName();
	}

	public String getColInfo() {
		return this.info == null ? "null" : this.info;
	}

	@Override
	public KTableCellRenderer getCellRender(String columName, int col, int row) {
		return m_textRenderer;

	}

	protected void runMenuAnalysis(SimpleKTable table, int col, int row) {
		//transfer this object to the MainView?
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void runMenuDelete(SimpleKTable table, int col, int row) {
		SimpleKTableModel  model=(SimpleKTableModel) table.getModel();
		model.deleteTableRow(this);
		table.redraw();
	}
	


	@SuppressWarnings("rawtypes")
	protected void runMenuClear(SimpleKTable table, int col, int row) {
		SimpleKTableModel  model=(SimpleKTableModel) table.getModel();
		model.clearTableRow();
		table.redraw();
	}
	
	@Override
	public String[] getMenuItemNames() {
		return new String[] { "Delete", "Clear" };
	}

}
