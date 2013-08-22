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
package com.google.code.t4eclipse.errorlog.data;

import java.util.concurrent.atomic.AtomicBoolean;

public class LogChecker {
	private static AtomicBoolean checkLogError = new AtomicBoolean(false);

	public  static void setCheck(boolean check) {
		checkLogError.set(check);
	}

	public  static boolean getCheck() {
		return checkLogError.get();
	}
}
