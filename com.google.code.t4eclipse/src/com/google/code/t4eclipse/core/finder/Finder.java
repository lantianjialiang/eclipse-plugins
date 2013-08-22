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

import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.swt.widgets.Widget;

/**
 * 
 * This class is used to find a widget in View/Editor/or under a Composite<br >
 * 
 * @author xufengbing
 * 
 */
public abstract class Finder {

	Stack<Widget> s;

	/**
	 * @return default FinderImp
	 */
	public static synchronized Finder getDefault() {
		return new FinderImp();
	}

	public Widget findOneByCondition(Widget w, IConditionFind fc) {
		// check the params
		if (w == null || fc == null)
			return null;

		s.push(w);
		while (!s.isEmpty()) {
			Widget tmpWidget = s.pop();
			if (tmpWidget != null) {
				if (fc.check(tmpWidget)) {
					return tmpWidget;
				}
				addWidget(tmpWidget);
			}
		}
		return null;
	}

	public Widget[] findAllByCondition(Widget w, IConditionFind fc) {
		// check the params
		if (w == null || fc == null)
			return null;

		ArrayList<Widget> ws = new ArrayList<Widget>();

		s.push(w);
		while (!s.isEmpty()) {
			Widget tmpWidget = s.pop();
			if (tmpWidget != null) {
				if (fc.check(tmpWidget)) {
					ws.add(tmpWidget);
				}
				addWidget(tmpWidget);
			}
		}
		return ws.toArray(new Widget[0]);

	}

	public Widget findIndexByCondition(Widget w, IConditionFind fc, int index) {
		// check the params
		int count = 0;
		if (w == null || fc == null)
			return null;

		s.push(w);
		while (!s.isEmpty()) {
			Widget tmpWidget = s.pop();
			if (tmpWidget != null) {
				if (fc.check(tmpWidget)) {
					count++;
					if (count == index) {
						return tmpWidget;
					}
				}
				addWidget(tmpWidget);
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Widget findOneByClass(Widget w, Class cas) {
		// check the params
		if (w == null || cas == null)
			return null;

		return findOneByCondition(w, new ConditionClassFind(cas));
	}

	@SuppressWarnings("rawtypes")
	public Widget[] findAllByClass(Widget w, Class cas) {
		// check the params
		if (w == null || cas == null)
			return null;

		return findAllByCondition(w, new ConditionClassFind(cas));
	}

	@SuppressWarnings("rawtypes")
	public Widget findIndexByClass(Widget w, Class cas, int index) {
		// check the params
		if (w == null || cas == null)
			return null;

		return findIndexByCondition(w, new ConditionClassFind(cas), index);
	}

	protected abstract void addWidget(Widget w);

}
