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

import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageUtility {

	/**
	 * Loads an image from the root of this package.
	 * 
	 * @param name
	 *            The string name inclusive path to the image.
	 * @return returns the image or null.
	 */
	public static Image loadImageResource(String name) {
		try {
			Image ret = null;
			InputStream is = ImageUtility.class.getResourceAsStream(name);
			if (is != null) {
				ret = new Image(Display.getCurrent(), is);
				is.close();
			}
			return ret;
		} catch (Exception e1) {
			return null;
		}
	}

}
