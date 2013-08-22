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
package com.google.code.t4eclipse.core.finder;

import org.eclipse.swt.widgets.Widget;

/**
 * @author xufengbing
 * 
 */
@SuppressWarnings("rawtypes")
public class ConditionClassFind implements IConditionFind {

	private final Class classV;

	public ConditionClassFind(Class c) {
		this.classV = c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.code.t4eclipse.core.finder.IConditionFind#check(org.eclipse
	 * .swt.widgets.Widget)
	 */
	@SuppressWarnings("unchecked")
	public boolean check(Widget widget) {
		if (classV == null || widget == null)
			return false;

		Class a = widget.getClass();
		return this.classV.isAssignableFrom(a);

	}
}
