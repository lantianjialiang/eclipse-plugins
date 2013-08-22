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
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import com.google.code.t4eclipse.tools.view.ActivePartControlView;


public class ExpandAllAction implements IViewActionDelegate {

	private ActivePartControlView view;

	public void init(IViewPart view) {
		if (view != null && view instanceof ActivePartControlView) {
			ActivePartControlView partView = (ActivePartControlView) view;
			this.view = partView;

		}

	}

	public void run(IAction action) {
		if (this.view != null)
			this.view.getTreeViewer().expandAll();
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

}
