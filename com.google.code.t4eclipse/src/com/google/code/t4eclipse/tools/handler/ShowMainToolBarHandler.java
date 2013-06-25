package com.google.code.t4eclipse.tools.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.ui.IViewPart;

import com.google.code.t4eclipse.core.eclipse.helper.EclipseWorkPartHelper;
import com.google.code.t4eclipse.core.utility.ToolBarUtility;
import com.google.code.t4eclipse.tools.view.ToolBarAnalyzerView;

public class ShowMainToolBarHandler extends AbstractHandler
{
  public Object execute(ExecutionEvent event)
    throws ExecutionException
  {
    IViewPart vp = EclipseWorkPartHelper.getDefault().getViewInCurrentpage(
      "com.google.code.t4eclipse.tools.view.ToolBarAnalyzerView");
    if ((vp instanceof ToolBarAnalyzerView)) {
      ToolBarAnalyzerView view = (ToolBarAnalyzerView)vp;
      CoolBar cb = ToolBarUtility.getEclipseCoolBar();
      if (cb != null) {
        view.update(cb);
      } else {
        ICoolBarManager cbManager = ToolBarUtility.getEclipseCoolBarForE4();
        if (cbManager == null) {
          return null;
        }

        view.update(cbManager);
      }
    }

    return null;
  }
}