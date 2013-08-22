package com.google.code.t4eclipse.tools.prefenence;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.google.code.t4eclipse.Activator;

public class T4EclipseMainPref extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public T4EclipseMainPref() {

		super(GRID);
		IPreferenceStore x = Activator.getDefault().getPreferenceStore();

		setPreferenceStore(x);
	}

	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		addField(new LabelFieldEditor("Welcom to T4Eclipse",
				getFieldEditorParent()));
	}
}
