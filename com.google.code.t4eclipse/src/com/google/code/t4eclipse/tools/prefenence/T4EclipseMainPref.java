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

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.google.code.t4eclipse.Activator;

public class T4EclipseMainPref extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public T4EclipseMainPref() {

		super(GRID);
		IPreferenceStore x = Activator.getDefault().getPreferenceStore();

		setPreferenceStore(x);
	}

	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		addField(new LabelFieldEditor("Welcom to T4Eclipse",
				getFieldEditorParent()));
	}
}
