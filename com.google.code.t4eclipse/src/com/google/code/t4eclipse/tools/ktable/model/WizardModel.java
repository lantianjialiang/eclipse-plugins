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


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.wizards.IWizardDescriptor;

import com.google.code.t4eclipse.core.eclipse.helper.EclipseWizardHelper;
import com.google.code.t4eclipse.tools.action.OpenJavaTypeAction;
import com.google.code.t4eclipse.tools.ktable.RowModel;
import com.google.code.t4eclipse.tools.ktable.SimpleKTable;

public class WizardModel extends RowModel {

	public WizardModel(IWizardDescriptor o) {
		super(o);
	}

	public WizardModel() {
		super();
	}

	@Override
	public int[] getColumLengths() {
		return new int[] { 200, 250,200};
	}
	
	@Override
	public String[] getColumNames() {
		//return new String[] {  "GroupID", "WizardID","Plugin","Class" };
		return new String[] {  "GroupID","Class" , "WizardID"};
	}
	
	// the content actually can get using java reflection.
	@Override
	public String[] getMenuItemNames() {
		return new String[] { "Open_Wizard","Open_Class" };
	}

	// folloiwng method are used to get colum content.
	protected String getColGroupID() {
		IWizardDescriptor discriptor = (IWizardDescriptor) this.object;
		return discriptor.getCategory().getId();
	}

	protected String getColPlugin() {
		IWizardDescriptor discriptor = (IWizardDescriptor) this.object;
		String id= discriptor.getId();
		return EclipseExtentionInfoProvider.getWizardClass(id)[0];
	}
	protected String getColClass() {
		IWizardDescriptor discriptor = (IWizardDescriptor) this.object;
		String id=  discriptor.getId();
		return EclipseExtentionInfoProvider.getWizardClass(id)[1];
	}

	protected String getColWizardID() {
		IWizardDescriptor discriptor = (IWizardDescriptor) this.object;
		return discriptor.getId();
	}

	//folloiwng method are used to add popup menuitem
	protected void runMenuOpen_Wizard(SimpleKTable table,int col,int row) {
		 try {
			String wizardStr =this.getColWizardID();
			String comment = "open the wizard: " + wizardStr;
			String message = "EclipseWizardHelper.getDefault().openWizard(\""
					+ wizardStr + "\",null, false);";
			generateCode(comment, message);
			// the following line is used to generate the command.
			EclipseWizardHelper.getDefault().openWizard(wizardStr,
					null, false);
		} catch (Throwable t) {
			MessageDialog.openError(table.getShell(), "Error",
					"Error when open this wizard with no selection");
		}
	}

	//folloiwng method are used to add popup menuitem
	protected void runMenuOpen_Class(SimpleKTable table,int col,int row) {
		 try {
			String className =this.getColClass();
			new OpenJavaTypeAction(className).run();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Override
	public int getRowHeight() {
		return 30;
	}
}
