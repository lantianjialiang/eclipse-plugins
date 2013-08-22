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
package com.google.code.t4eclipse.core.eclipse.helper;

import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

/**
 *
 *
 * @author xufengbing
 *
 */
public class EclipseWizardHelper {

    public static EclipseWizardHelper getDefault() {
        return new EclipseWizardHelper();
    }

    /**
     * creates and opens a wizard
     *
     * @param wizardID
     *            the ID of the wizard to create and open
     * @param selection
     *            the selection which is used to initialize the wizard
     * @param block
     *            true if it opens a model dialog
     */
    @SuppressWarnings("null")
	public IWorkbenchWizard openWizard(String wizardID, IStructuredSelection selection,
            final boolean block) {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWizardDescriptor wizDesc = workbench.getNewWizardRegistry().findWizard(wizardID);
        //Assert.assertNotNull("wizard " + wizardID + " not found", wizDesc);

        IWorkbenchWizard wizard = null;
        try {
            wizard = wizDesc.createWizard();
        } catch (CoreException e) {
            e.printStackTrace();
        }
       // Assert.assertNotNull(wizard);
        //bugfix 02-11-2007
        if (selection == null)
            selection = StructuredSelection.EMPTY;

        wizard.init(workbench, selection);
        final WizardDialog dialog = new WizardDialog(workbench.getActiveWorkbenchWindow()
                .getShell(), wizard);
        dialog.create();

        final Semaphore sem = new Semaphore(0);

        dialog.getShell().getDisplay().syncExec(new Runnable() {
            public void run() {
                dialog.setBlockOnOpen(block);
                dialog.open();
                sem.release();
            }
        });

        try {
            sem.acquire();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }

        return wizard;
    }

}
