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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.code.t4eclipse.selection.ControlSelection;
import com.google.code.t4eclipse.tools.view.MenuAnalyzerView;

public class AnalysisMenuAction implements IObjectActionDelegate {
	private Control c;

	public AnalysisMenuAction() {
		// TODO Auto-generated constructor stub
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		//do nothing
	}

	public void run(IAction action) {
		if (c == null || c.isDisposed()) {
			return;
		}
		if (!c.isVisible() || !c.isEnabled() || c.getMenu() == null) {
			MessageDialog
					.openConfirm(Display.getCurrent().getActiveShell(),
							"Confirm",
							"This control is not visible or not enabled or has no menu!\n");
			return;
		}
		int itemCount = c.getMenu().getItemCount();
		if (itemCount == 0) {
			MessageDialog
					.openConfirm(
							Display.getCurrent().getActiveShell(),
							"Confirm",
							"This control's menu has no menu items now!\n"
									+ "Control's menu item may be constructed during menu show,\n"
									+ "Please check control's menu first on eclispe ui ! ");

			return;

		}
 
			try {
			IViewPart view = PlatformUI
						.getWorkbench()
						.getActiveWorkbenchWindow()
						.getActivePage()
						.showView(
								"com.google.code.t4eclipse.tools.view.MenuAnalyzerView");
			if(view !=null&& view instanceof MenuAnalyzerView){
				MenuAnalyzerView ma=(MenuAnalyzerView) view;
				ma.update(this.c);
			}
			} catch (PartInitException e) {
				//do nothing
			}
	 

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
