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

import com.google.code.t4eclipse.tools.utility.EclipseCommonUtility;
import com.google.code.t4eclipse.tools.view.ActivePartControlView;

public class AutoSyncActivePartAction implements IViewActionDelegate {

	@SuppressWarnings("unused")
	private ActivePartControlView view;
	private static IPartListener partListener;
	static {

		partListener = new IPartListener() {

			public void partOpened(IWorkbenchPart part) {
				//do nothing
			}

			public void partDeactivated(IWorkbenchPart part) {
				//do nothing
			}

			public void partClosed(IWorkbenchPart part) {
				//do nothing
			}

			public void partBroughtToTop(IWorkbenchPart part) {
				//do nothing
			}

			public void partActivated(IWorkbenchPart part) {
				// if it is not the view itself
				if (!(part instanceof ActivePartControlView)) {
					IViewPart v = EclipseCommonUtility
							.getViewPart("com.google.code.t4eclipse.tools.view.ActivePartControlView");
					if (v != null && v instanceof ActivePartControlView) {
						ActivePartControlView ac = (ActivePartControlView) v;
						ac.showAcitivePart(part);
					}
				}
			}
		};

	}

	@SuppressWarnings("hiding")
	public void init(IViewPart view) {
		if (view != null && view instanceof ActivePartControlView) {
			ActivePartControlView partView = (ActivePartControlView) view;
			this.view = partView;
		}
	}

	public void run(IAction action) {
		boolean isChecked = action.isChecked();
		if (isChecked) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().addPartListener(partListener);
		} else {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().removePartListener(partListener);

		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		//do nothing
	}

	public static IPartListener getAutoSyncPartListener() {
		return partListener;
	}

}
