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
package com.google.code.t4eclipse.tools.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.ui.IViewPart;

import com.google.code.t4eclipse.core.eclipse.helper.EclipseWorkPartHelper;
import com.google.code.t4eclipse.core.utility.ToolBarUtility;
import com.google.code.t4eclipse.tools.view.ToolBarAnalyzerView;

public class ShowMainToolBarHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IViewPart vp = EclipseWorkPartHelper.getDefault().getViewInCurrentpage(
				"com.google.code.t4eclipse.tools.view.ToolBarAnalyzerView");
		if ((vp instanceof ToolBarAnalyzerView)) {
			ToolBarAnalyzerView view = (ToolBarAnalyzerView) vp;
			CoolBar cb = ToolBarUtility.getEclipseCoolBar();
			if (cb != null) {
				view.update(cb);
			} else {
				ICoolBarManager cbManager = ToolBarUtility
						.getEclipseCoolBarForE4();
				if (cbManager == null) {
					return null;
				}

				view.update(cbManager);
			}
		}

		return null;
	}
}
