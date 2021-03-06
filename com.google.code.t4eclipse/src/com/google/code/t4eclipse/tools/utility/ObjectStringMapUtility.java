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
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
public class ObjectStringMapUtility {

	public static String getStringDescription(Object result, String info) {
		String start = "******" + info + "******";
		String end = getStrings(start.length(), "*");
		String center = getStrings(start.length(), "*");
		String resturnStr = start + "\n" + center + "\n"
				+ getObjectString(result) + "\n" + center + "\n" + end;
		return resturnStr;
	}

	@SuppressWarnings("rawtypes")
	public static String getObjectString(Object result) {
		if (result == null) {
			return "null";
		} 

		String start = "Field Value Type:" + result.getClass().getSimpleName()
				+ "\n";

		if (result instanceof Number) {
			return start + ((Number) result).toString();
		}
		if (result instanceof String) {
			return start + result.toString();
		}
		if (result instanceof Character) {
			return start + result.toString();
		}

		// Array need to be solved Separatly
		if (result.getClass().isArray()) {

			if (result.getClass().getComponentType().isPrimitive()) {
				// primitive array
				int length = Array.getLength(result);
				start += "Array lengthe is:" + length;
				for (int i = 0; i < length; i++) {
					start += "[" + i + "]:" + Array.get(result, i) + "\n";
				}
				return start;
			}

			// non-primitive array
			int length = Array.getLength(result);
			start += "Array lengthe is:" + length + "\n";
			for (int i = 0; i < length; i++) {
				start += "[" + i + "]:" + Array.get(result, i) + "\n";
			}
			return start;
		}

		// Collection need to be solved Separatly
		if (result instanceof Collection) {
			Collection tmp = (Collection) result;
			start += "Collection Contents:\n";
			int index = 0;
			for (Iterator it = tmp.iterator(); it.hasNext();) {
				Object element = it.next();
				start += "(" + (++index) + "):" + element.toString() + "\n";
			}
		}
		return start + result.toString();
	}

	private static String getStrings(int i, String str) {
		String a = "";
		if (i <= 0)
			return "";
		while ((--i) >= 0) {
			a += str;
		}
		return a;
	}
}
