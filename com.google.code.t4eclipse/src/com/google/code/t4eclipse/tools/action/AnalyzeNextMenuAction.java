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
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.code.t4eclipse.core.utility.MenuUtility;
import com.google.code.t4eclipse.tools.view.MenuAnalyzerView;

public class AnalyzeNextMenuAction implements IViewActionDelegate {
	public static boolean checkMenu;

	private static IPartListener partListener;
	static {

		partListener = new IPartListener() {

			public void partOpened(IWorkbenchPart part) {
				// TODO Auto-generated method stub
			}

			public void partDeactivated(IWorkbenchPart part) {
				if (!(part instanceof MenuAnalyzerView)) {
					checkMenu = false;
				}
			}

			public void partClosed(IWorkbenchPart part) {
				if (part instanceof MenuAnalyzerView) {
					AnalyzeNextMenuAction.checkMenu = false;
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().removePartListener(this);
				}
			}

			public void partBroughtToTop(IWorkbenchPart part) {
				//do nothing
			}

			public void partActivated(IWorkbenchPart part) {
				if (!(part instanceof MenuAnalyzerView)) {
					MenuUtility.removeMenuHideListener(part);
					MenuUtility.addMenuHideListener(part);
					checkMenu = true;
				}
			}
		};

	}

	@SuppressWarnings("unused")
	private MenuAnalyzerView view;

	public void init(IViewPart aView) {
		this.view = (MenuAnalyzerView) aView;
	}

	public void run(IAction action) {
		boolean isChecked = action.isChecked();

		addRemovePageListener(isChecked);

	}

	public static void addRemovePageListener(boolean isChecked) {
		if (isChecked) {
			checkMenu = true;
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().addPartListener(partListener);

		} else {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().removePartListener(partListener);
			checkMenu = false;
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		//do nothing
	}

}
