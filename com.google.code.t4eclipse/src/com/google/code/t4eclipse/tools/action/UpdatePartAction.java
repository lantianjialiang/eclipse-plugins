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
package com.google.code.t4eclipse.tools.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.part.WorkbenchPart;

import com.google.code.t4eclipse.core.utility.ControlUtility;
import com.google.code.t4eclipse.tools.utility.MainViewTabUtility;
import com.google.code.t4eclipse.tools.view.ActivePartControlView;
import com.google.code.t4eclipse.tools.view.MainView;

public class UpdatePartAction extends ActionDelegate implements
		IWorkbenchWindowActionDelegate {
	private static final String IWorkbenchPart = null;
	IWorkbenchWindow window;

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {

	
		IWorkbenchPart part = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActivePart();

		try {
			PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(
							"com.google.code.t4eclipse.tools.view.ActivePartControlView",
							null, IWorkbenchPage.VIEW_ACTIVATE);
		} catch (PartInitException e) {
			// do nothing
		}

		ActivePartControlView.view.showAcitivePart( part);

	}
}
