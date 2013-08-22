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
package com.google.code.t4eclipse.tools.model;

import org.eclipse.swt.widgets.ToolItem;

public class LocalViewToolBarModel {

	// the toolitem itself
	public ToolItem item;

	// for a toolbar item in view, one of its id and ToolTip is exists in most
	// situations.
	// the contribution ID, also can be null
	public String ID;
	// can be null
	public String ToolTip;

	// PUSH, CHECK, RADIO, SEPARATOR, DROP_DOWN
	public int Style;

	public boolean Enabled;

	public boolean Selected;

	public LocalViewToolBarModel() {
		this.ID = "";
		this.ToolTip = "";
		this.Enabled = false;
		this.Selected = false;
	}
}
