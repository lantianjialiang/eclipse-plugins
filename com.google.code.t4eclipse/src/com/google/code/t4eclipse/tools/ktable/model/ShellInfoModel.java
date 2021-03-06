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
package com.google.code.t4eclipse.tools.ktable.model;


import com.google.code.t4eclipse.tools.action.OpenJavaTypeAction;
import com.google.code.t4eclipse.tools.ktable.RowModel;
import com.google.code.t4eclipse.tools.ktable.SimpleKTable;
import com.google.code.t4eclipse.tools.utility.Constants;
import com.google.code.t4eclipse.tools.utility.ShellListnerChecker;

public class ShellInfoModel extends RowModel {

	private final String shellDataName;

	private final String shellTitle;

	private final String info;

	public ShellInfoModel(String shellDataName, String shellTitle) {
		super();
		this.shellDataName = shellDataName;
		this.shellTitle = shellTitle;
		this.info = "";
	}

	public ShellInfoModel(String shellDataName, String shellTitle, String info) {
		super();
		this.shellDataName = shellDataName;
		this.shellTitle = shellTitle;
		this.info = info;
	}

	public ShellInfoModel() {
		super();
		this.shellDataName = "";
		this.shellTitle = "";
		this.info = "";
	}

	@Override
	public int[] getColumLengths() {
		return new int[] { 250, 300, 200 };
	}

	@Override
	public String[] getColumNames() {
		// return new String[] { "GroupID", "WizardID","Plugin","Class" };
		return new String[] { "Class", "Title", "Info" };
	}

	// the content actually can get using java reflection.
	@Override
	public String[] getMenuItemNames() {
		return new String[] { "Open_Class" };
	}

	// folloiwng method are used to get colum content.
	protected String getColClass() {
		return this.shellDataName;
	}

	protected String getColTitle() {
		return this.shellTitle;
	}

	protected String getColInfo() {
		return this.info;
	}

	// folloiwng method are used to add popup menuitem
	protected void runMenuOpen_Class(SimpleKTable table, int col, int row) {
		boolean oldvalue=ShellListnerChecker.getCheck();
		try {
			ShellListnerChecker.setCheck(false);
			String className = this.getColClass();
			if (!this.info.equals("")) {

					int start = this.info.indexOf(Constants.classStartInInfo);
					int end = this.info.indexOf(Constants.classEndInInfo);
					if(start>=0&&end>=0&&start+7<end){
						className=this.info.substring(start+7,end);
					}

			}

			new OpenJavaTypeAction(className).run();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		finally{
			ShellListnerChecker.setCheck(oldvalue);
		}
	}
}
