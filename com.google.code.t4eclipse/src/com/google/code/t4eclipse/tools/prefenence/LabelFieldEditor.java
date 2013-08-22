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
package com.google.code.t4eclipse.tools.prefenence;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabelFieldEditor extends FieldEditor {
	/***
	 * Label for this field editor.
	 */
	private Label label;

	/***
	 * All labels can use the same preference name since they don't store any
	 * preference.
	 * 
	 * @param labelText text for the label
	 * @param parent Composite
	 */
	public LabelFieldEditor(String labelText, Composite parent) {
		super("label", labelText, parent); //$NON-NLS-1$
	}

	/***
	 * Adjusts the field editor to be displayed correctly for the given number
	 * of columns.
	 * 
	 * @param numColumns number of columns
	 */
	@Override
	protected void adjustForNumColumns(int numColumns) {
		((GridData) this.label.getLayoutData()).horizontalSpan = numColumns;
	}

	/***
	 * Fills the field editor's controls into the given parent.
	 * 
	 * @param parent Composite
	 * @param numColumns cumber of columns
	 */
	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		this.label = getLabelControl(parent);
		GridData gridData = new GridData();
		gridData.horizontalSpan = numColumns;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = false;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessVerticalSpace = false;
		this.label.setLayoutData(gridData);
	}

	/***
	 * Returns the number of controls in the field editor.
	 * 
	 * @return 1
	 */
	@Override
	public int getNumberOfControls() {
		return 1;
	}

	/***
	 * Labels do not persist any preferences, so this method is empty.
	 */
	@Override
	protected void doLoad() {
		//do nothing
	}

	/***
	 * Labels do not persist any preferences, so this method is empty.
	 */
	@Override
	protected void doLoadDefault() {
		//do nothing
	}

	/***
	 * Labels do not persist any preferences, so this method is empty.
	 */
	@Override
	protected void doStore() {
		//do nothing
	}
}

