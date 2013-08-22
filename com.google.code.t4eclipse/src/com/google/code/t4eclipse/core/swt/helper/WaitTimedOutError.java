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
package com.google.code.t4eclipse.core.swt.helper;

/**
 *
 * @author xufengbing
 */
public class WaitTimedOutError extends RuntimeException {
    private static final long serialVersionUID = 6286817949395706974L;
	public WaitTimedOutError() {
		super();
	}

	public WaitTimedOutError(String message) {
		super(message);
	}
}
