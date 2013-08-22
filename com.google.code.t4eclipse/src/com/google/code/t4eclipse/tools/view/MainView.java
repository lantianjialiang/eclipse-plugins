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
package com.google.code.t4eclipse.tools.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MainView extends ViewPart {
	public static final String viewID = "com.google.code.t4eclipse.tools.view.MainView";

	static MainSWT instance = null;

	private Composite composite;

	public static MainSWT getMainSWT() {
		return instance;
	}

	/**
	 * Create the example
	 * 
	 * @see ViewPart#createPartControl
	 */
	@Override
	public void createPartControl(Composite frame) {
		this.composite = frame;
		instance = new MainSWT(composite, this);
	}

	/**
	 * Called when we must grab focus.
	 * 
	 * @see org.eclipse.ui.part.ViewPart#setFocus
	 */
	@Override
	public void setFocus() {
		instance.setFocus();
	}

	/**
	 * Called when the View is to be disposed
	 */
	@Override
	public void dispose() {
		instance.dispose();
		instance = null;
		this.composite.dispose();
		super.dispose();
	}

	public synchronized void updateView() {
		instance.updatePerspectiveTable();
		instance.updatePrefTable();
		instance.updateViewTable();
		instance.updateWizardTable();
		instance.updateToolbarTable();
		instance.updateMainMenuTable();
		instance.updateEditorTable();
	}

}
