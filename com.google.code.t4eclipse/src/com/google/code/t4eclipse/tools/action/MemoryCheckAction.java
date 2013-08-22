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

import java.text.DecimalFormat;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class MemoryCheckAction implements IWorkbenchWindowActionDelegate {

	DecimalFormat decFmt = new DecimalFormat("0.0");
	private long free;
	private long total;
	private long max;

	public void dispose() {
		//do nothing
	}

	public void init(IWorkbenchWindow window) {
		//do nothing
	}

	public void run(IAction action) {
		long freeM = Runtime.getRuntime().freeMemory();
		long totalM = Runtime.getRuntime().totalMemory();
		long maxM = Runtime.getRuntime().maxMemory();

		if (action.isChecked()) {
			this.free = freeM;
			this.total = totalM;
			this.max = maxM;
		} else {
			long used = (totalM - freeM) - (this.total - this.free);

			String begin = "******start******\n"
					+ getAllStatus(this.total, this.free, this.max) + "\n\n";
			String end = "****** end ******\n"
					+ getAllStatus(totalM, freeM, maxM) + "\n\n";
			String changed = "***use changed***\n" + getMemoryStr(used);
			MessageDialog.openInformation(
					Display.getDefault().getActiveShell(),
					"Memory Status Report", begin + end + changed);
		}
	}

	private String getAllStatus(long totalM, long freeM, long maxM) {
		String statusIndex = "total   /   free   /   max   /   use" + "\n";
		String statusData = getMemoryStr(totalM) + " / " + getMemoryStr(freeM)
				+ " / " + getMemoryStr(maxM) + " / "
				+ getMemoryStr(totalM - freeM) + "\n";
		return statusIndex + statusData;
	}

	private String getMemoryStr(long mem) {
		long copy = Math.abs(mem);
		String tmp = mem < 0 ? "-" : "";
		return tmp + decFmt.format(copy / 1024.0 / 1024.0) + "M";
	}

	public void selectionChanged(IAction action, ISelection selection) {
		//do nothing
	}

}
