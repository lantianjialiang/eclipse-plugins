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
package com.google.code.t4eclipse.tools.utility;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Control;

import com.google.code.t4eclipse.tools.model.WidgetModel;

public class WidgetModelUtility   {

	public static WidgetModel[] getWidetLevelModels(Control control) {

		List<WidgetModel> list = new ArrayList<WidgetModel>();

		WidgetModel root = new WidgetModel("Root",
				control.getClass().getName(), control);
		root.parse(list);
		return list.toArray(new WidgetModel[0]);
	}
}
