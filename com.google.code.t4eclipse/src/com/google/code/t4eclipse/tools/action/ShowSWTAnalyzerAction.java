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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.google.code.t4eclipse.tools.utility.EclipseCommonUtility;

public class ShowSWTAnalyzerAction implements IWorkbenchWindowActionDelegate {

	public void run(IAction action) {
		EclipseCommonUtility
				.showView("com.google.code.t4eclipse.tools.view.EventAnalyzerView");
	}

	public void selectionChanged(IAction action, ISelection selection) {
		//do nothing
	}

	public void dispose() {
		//do nothing
	}

	public void init(IWorkbenchWindow window) {
		//do nothing
	}

}
