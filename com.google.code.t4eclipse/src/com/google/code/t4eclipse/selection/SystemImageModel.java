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
package com.google.code.t4eclipse.selection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class SystemImageModel {

	private final String name;

	public SystemImageModel(String n) {
		this.name = n;
	}

	public Image getImage() {

		return PlatformUI.getWorkbench().getSharedImages().getImage(name);
	}

	public String getCode() {
		return "PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages."
				+ name + ");";
	}

	public static SystemImageModel[] getAllModels() {
		List<SystemImageModel> list = new ArrayList<SystemImageModel>();

		Field[] fields = ISharedImages.class.getFields();
		for (Field f : fields) {
			list.add(new SystemImageModel(f.getName()));
		}

		return list.toArray(new SystemImageModel[0]);
	}
	
	public String getName(){
		return this.name;
	}
}
