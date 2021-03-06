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
package com.google.code.t4eclipse.tools.prefenence;


import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import com.google.code.t4eclipse.Activator;
 
 
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
	public void initializeDefaultPreferences() {
    	EclipseTestLibPref.initDefaults(Activator.getDefault().getPreferenceStore());
    }
}
