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
package com.google.code.t4eclipse.tools.eventtrack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class DisplayListener implements Listener {

	public void handleEvent(Event event) {
		onEvent(event);

	}

	private void onEvent(Event event) {
		synchronized(Utility.lock){
		if (Utility.filter(event)) {
			Utility.addStrToText(event);
		}
		}
	}

	static DisplayListener dL=new DisplayListener();
	public static DisplayListener getDefault(){
		return dL ;
		
	}
}
