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
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class OpenMenuItemJavaTypeAction implements IViewActionDelegate {
	private String className;

	public void init(IViewPart view) {
		//do nothing
	}

	public void run(IAction action) {
		OpenJavaTypeAction.openJavaType(this.className);
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.className = "";
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection s = (IStructuredSelection) selection;
			Object o = s.getFirstElement();
			if (o != null && o instanceof MenuItem) {
				MenuItem mi = (MenuItem) o;
				String str = (String) mi.getData("JAVA_TYPE");
				if (str != null) {
					this.className = str;
				}
			}
		}
	}
}
