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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *
 * @author xufengbing
 *
 */
public class Displays {

	/**
	 * @param display
	 *            a {@link Display}
	 * @return <code>true</code> if the caller is running the {@link Display}'s
	 *         {@link Thread}, <code>false</code> otherwise.
	 */
	public static boolean isOnDisplayThread(Display display) {
		return display.getThread() == Thread.currentThread();
	}

	/**
	 * Gets the default {@link Display}.
	 */
	private static Display getDisplay() {
		// return Robot.getDefault().getDisplay();
		return Display.getDefault();
		// changed by ben.xu
		// Return Display.getDisplay();
	}

	/**
	 * Gets all undisposed root {@link Shell}s for the default {@link Display}.
	 */
    @SuppressWarnings("rawtypes")
	public static List getShells() {
		return getShells(getDisplay());
	}

	/**
	 * Gets all undisposed root {@link Shell}s for the specified
	 * {@link Display}.
	 */
    @SuppressWarnings("rawtypes")
	public static List getShells(Display display) {
		Shell[] shells = getShellsArray(display);
		if (shells.length > 0)
			return Arrays.asList(shells);
		return Collections.EMPTY_LIST;
	}

	/**
	 * Gets an array of all undisposed root {@link Shell}s for the specified
	 * {@link Display}.
	 *
	 * @see #getShells(Display)
	 */
    @SuppressWarnings("rawtypes")
	private static Shell[] getShellsArray(final Display display) {
		if (!display.isDisposed()) {

			// If we're the UI thread then we can call display.getShells()
			// directly.
			if (display.getThread() == Thread.currentThread())
				return display.getShells();

			// We're not the UI thread, so have the UI thread do the call for
			// us.
			return (Shell[]) syncExec(display, new Result() {
				public Object result() {
					return display.getShells();
				}
			});
		}
		return new Shell[0];
	}

	/*
	 * The following methods are not specific to a particular Display.
	 */
	public static void syncExec(Display display, Runnable runnable) {
		try {
			display.syncExec(runnable);
		} catch (SWTException exception) {
			throw exception;
		}
	}

	public static void syncExec(Runnable runnable) {
		syncExec(getDisplay(), runnable);
	}

	public static interface Result<T> {
		T result();
	}

	public static interface BooleanResult {
		boolean result();
	}

	public static interface IntResult {
		int result();
	}

	public static interface CharResult {
		char result();
	}

	public static interface StringResult {
		String result();
	}

    @SuppressWarnings("rawtypes")
	public static Object syncExec(Display display, final Result result) {
		final Object[] wrapper = new Object[1];
		display.syncExec(new Runnable() {
			public void run() {
				wrapper[0] = result.result();
			}
		});
		return wrapper[0];
	}

	public static boolean syncExec(Display display, final BooleanResult result) {
		final boolean[] wrapper = new boolean[1];
		display.syncExec(new Runnable() {
			public void run() {
				wrapper[0] = result.result();
			}
		});
		return wrapper[0];
	}

	public static int syncExec(Display display, final IntResult result) {
		final int[] wrapper = new int[1];
		display.syncExec(new Runnable() {
			public void run() {
				wrapper[0] = result.result();
			}
		});
		return wrapper[0];
	}

	public static char syncExec(Display display, final CharResult result) {
		final char[] wrapper = new char[1];
		display.syncExec(new Runnable() {
			public void run() {
				wrapper[0] = result.result();
			}
		});
		return wrapper[0];
	}

	public static String syncExec(Display display, final StringResult result) {
		final String[] wrapper = new String[1];
		display.syncExec(new Runnable() {
			public void run() {
				wrapper[0] = result.result();
			}
		});
		return wrapper[0];
	}

}
