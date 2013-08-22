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
package com.google.code.t4eclipse.core.utility;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

import com.google.code.t4eclipse.core.swt.helper.Displays.BooleanResult;
import com.google.code.t4eclipse.core.swt.helper.HelperRoot;


/**
 *
 * for all applications ,only one such thread exists actively
 *
 * @author xufengbing
 *
 */
class CheckBlockShell extends HelperRoot implements Runnable {

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while (true) {
            sleepPrim(10 * 1000);
            boolean isTimeOut = syncExec(new BooleanResult() {

                @SuppressWarnings("boxing")
				public boolean result() {
                    Shell s = Display.getDefault().getActiveShell();
                    if (s != null) {
                        Object data = s.getData(GuilibUtility.TIME_OUT_START_AT);
                        if (data != null) {
                            long timeStart = (Long) data;
                            long used = System.currentTimeMillis() - timeStart;
                            if (used > GuilibUtility.TIME_OUT_FOR_SHELL) {
                                s.dispose();
                                return true;
                            }

                        }
                    }
                    return false;
                }
            });
            if (isTimeOut) {
            	//TODO
            }
        }
    }

    private void sleepPrim(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException exception) {
            // Empty block intended.
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.code.t4eclipse.core.swt.helper.HelperRoot#checkInstance(org.eclipse.swt.widgets.Widget)
     */
    @Override
	protected void checkInstance(Widget widget) {
        // TODO Auto-generated method stub
    }
}
