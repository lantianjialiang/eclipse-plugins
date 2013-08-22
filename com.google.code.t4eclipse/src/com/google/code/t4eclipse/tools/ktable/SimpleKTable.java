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
package com.google.code.t4eclipse.tools.ktable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

import de.kupzog.ktable.t4eclipse.KTable;
import de.kupzog.ktable.t4eclipse.KTableCellSelectionListener;
import de.kupzog.ktable.t4eclipse.KTableModel;
import de.kupzog.ktable.t4eclipse.SWTX;

public class SimpleKTable extends KTable {

	Menu menu;

	KTableCellSelectionListener listener;

	public SimpleKTable(Composite parent) {
		super(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWTX.FILL_WITH_LASTCOL
				| SWTX.EDIT_ON_KEY);

		menu = new Menu(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell());
		setMenu(menu);
	}

	public KTableCellSelectionListener addMenuListener() {
		listener = new KTableCellSelectionListener() {
			public void cellSelected(int col, int row, int statemask) {
				if (col < SimpleKTable.this.getModel().getColumnCount()
						&& row < SimpleKTable.this.getModel().getRowCount()) {

					disposeAllMenuItem();
					if (row == 0 || col == 0) {
						//do nothing
					} else {
						addMenuItem(col, row);
					}
				}
			}

			public void fixedCellSelected(int col, int row, int statemask) {
				if (col < SimpleKTable.this.getModel().getColumnCount()
						&& row < SimpleKTable.this.getModel().getRowCount()) {

					disposeAllMenuItem();
				}
			}

		};
		this.addCellSelectionListener(listener);
		return listener;
	}

	// this mehtod add a menu item to the KTable.
	@SuppressWarnings("rawtypes")
	public void addMenuItem(int col, int row) {

		KTableModel model = this.getModel();
		if (model != null && model instanceof SimpleKTableModel) {
			SimpleKTableModel smodel = (SimpleKTableModel) model;
			smodel.addSimpelKTableMenuItem(this, col, row);
		}
	}

	public void disposeAllMenuItem() {
		if (menu != null && !menu.isDisposed()) {
			MenuItem[] items = menu.getItems();
			for (int i = 0; i < items.length; i++)
				// not disposed yet
				if (!items[i].isDisposed()) {
					items[i].dispose();
				}
		}
	}

	@Override
	public void dispose() {
		if (this.listener != null) {
			this.removeCellSelectionListener(listener);
		}
		listener = null;
		super.dispose();
	}

}
