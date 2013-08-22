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
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ChmodAction implements IObjectActionDelegate {

	public void run(IAction action) {
		// TODO Auto-generated method stub
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
	}

	// public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	//
	// }
	//
	// public void run(IAction action) {
	// // get the resource file
	// ISelection s = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	// .getSelectionService().getSelection();
	// if (s instanceof IStructuredSelection) {
	// IStructuredSelection ss = (IStructuredSelection) s;
	// Iterator iter = ss.iterator();
	// while (iter.hasNext()) {
	// Object tmpO = iter.next();
	// if (tmpO instanceof IFile) {
	// IFile f = (IFile) tmpO;
	//
	// IPath location = f.getLocation();
	// try {
	// Runtime.getRuntime().exec(
	// "chmod 664 " + location.toOSString());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// // System.out.println(f.getFullPath().toOSString());
	// // String[] allAda = Platform.getAdapterManager()
	// // .computeAdapterTypes(f.getClass());
	// // if (allAda != null) {
	// // for (String tmp : allAda) {
	// // System.out.println(tmp);
	// // }
	// //
	// // }
	//
	// }
	// }
	//
	// }
	// }
	//
	// public void selectionChanged(IAction action, ISelection selection) {
	//
	// }

}
