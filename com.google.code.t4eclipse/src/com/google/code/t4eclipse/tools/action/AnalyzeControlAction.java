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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.google.code.t4eclipse.selection.ControlSelection;
import com.google.code.t4eclipse.tools.dialog.AboutT4EclipseDialog;
import com.google.code.t4eclipse.tools.dialog.AnalyzeControlDialog;

public class AnalyzeControlAction implements IObjectActionDelegate {
	private Control c;

	public AnalyzeControlAction() {
		// TODO Auto-generated constructor stub
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	public void run(IAction action) {
		Shell shell =  Display.getDefault().getActiveShell();

		AnalyzeControlDialog d = new AnalyzeControlDialog(shell, this.c);
		d.open();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.c = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object o = ss.getFirstElement();
			if (o instanceof ControlSelection) {
				ControlSelection cs = (ControlSelection) o;
				this.c = cs.getControl();
			}
		}

	}

}
