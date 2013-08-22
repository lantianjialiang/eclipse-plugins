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
package com.google.code.t4eclipse.core.eclipse.helper;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.IIntroPart;
/**
 *
 * @author xufengbing
 */
public class EclipseCommonHelper {
    public static EclipseCommonHelper getDefault() {
        return new EclipseCommonHelper();
    }

	public void actionCloseWelcomePage() {
		IIntroManager introManager = PlatformUI.getWorkbench()
				.getIntroManager();
		IIntroPart introPart = introManager.getIntro();
		if (introPart != null)
			introManager.closeIntro(introPart);
	}

	public void actionMaximizeWorkbench() {
		Rectangle clientArea = PlatformUI.getWorkbench().getDisplay()
				.getClientArea();
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				.setLocation(0, 0);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				.setSize(new Point(clientArea.width, clientArea.height));
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				.redraw();
	}

	public static IWorkbenchPage actionShowPerspective(String id)
			throws WorkbenchException {
		return PlatformUI.getWorkbench().showPerspective(id,
				PlatformUI.getWorkbench().getActiveWorkbenchWindow());
	}

	public String[] getAllPerspective() {
		ArrayList<String> array = new ArrayList<String>();
		IPerspectiveRegistry perspectives = PlatformUI.getWorkbench()
				.getPerspectiveRegistry();
		IPerspectiveDescriptor[] s = perspectives.getPerspectives();
		for (IPerspectiveDescriptor dis : s) {
			array.add(dis.getId());
		}
		String[] strs = new String[0];
		return array.toArray(strs);
	}

	public IViewPart[] getAllViewInCurrentPage(IWorkbenchPage page) {
		IViewReference[] refs = page.getViewReferences();
		IViewPart[] vs = new IViewPart[refs.length];
		for (int i = 0; i < refs.length; i++) {
			vs[i] = refs[i].getView(true);

		}
		return vs;
	}

	public static IEditorPart[] getAllEditorInCurrentPage(IWorkbenchPage page) {
		IEditorReference[] refs = page.getEditorReferences();
		IEditorPart[] vs = new IEditorPart[refs.length];
		for (int i = 0; i < refs.length; i++) {
			vs[i] = refs[i].getEditor(true);
		}
		return vs;
	}

	public static void processDisplayEvents() {

		Display display = PlatformUI.getWorkbench().getDisplay();
		for (;;) {
			if (!display.readAndDispatch())
				break;
		}
	}
}
