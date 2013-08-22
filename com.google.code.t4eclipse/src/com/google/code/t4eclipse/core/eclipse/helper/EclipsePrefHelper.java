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

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class EclipsePrefHelper {
    public static EclipsePrefHelper getDefault() {
        return new EclipsePrefHelper();
    }


    public void openPrefPage(String pageID) {

		Shell windowShell = null;

		Shell[] shells = Display.getDefault().getShells();
		for (int i = 0; i < shells.length; i++) {
			Object data = shells[i].getData();
			if (data != null && data instanceof IWorkbenchWindow) {
				windowShell = shells[i];
				break;
			}
		}
		PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(
				windowShell, pageID, null, null);
		dialog.setBlockOnOpen(false);
		dialog.open();

	}
}
