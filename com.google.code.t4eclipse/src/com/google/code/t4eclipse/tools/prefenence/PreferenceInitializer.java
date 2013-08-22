package com.google.code.t4eclipse.tools.prefenence;


import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import com.google.code.t4eclipse.Activator;
 
 
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
	public void initializeDefaultPreferences() {
    	EclipseTestLibPref.initDefaults(Activator.getDefault().getPreferenceStore());
    }
}
