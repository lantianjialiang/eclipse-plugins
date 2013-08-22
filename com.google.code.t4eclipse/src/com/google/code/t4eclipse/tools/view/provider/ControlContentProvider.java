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
package com.google.code.t4eclipse.tools.view.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.google.code.t4eclipse.selection.ControlSelection;

public class ControlContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof ControlSelection) {
			ControlSelection cs = (ControlSelection) parentElement;
			return cs.getChildren();
		}
		return new Object[0];

	}

	public Object getParent(Object element) {
		if (element instanceof ControlSelection) {
			ControlSelection com = (ControlSelection) element;
			return com.getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof ControlSelection) {
			ControlSelection com = (ControlSelection) element;
			return com.getChildren().length > 0;

		}
		return false;
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ControlSelection) {
	 
			ControlSelection com = (ControlSelection) inputElement;
			return com.getChildren();
		}
		
		return null;
	}

	public void dispose() {
		//do nothing
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//do nothing
	}
}
