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
package com.google.code.t4eclipse.tools.view.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.internal.provisional.action.ICoolBarManager2;
import org.eclipse.jface.internal.provisional.action.ToolBarContributionItem2;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ToolBarContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		// if (parentElement instanceof ToolItem) {
		// ToolItem item = (ToolItem) parentElement;
		//
		// Object data = item.getData();
		//
		// if (data != null && data instanceof MenuManager) {
		// MenuManager mm = (MenuManager) data;
		//
		// if (mm != null) {
		//
		// return mm.getMenu().getItems();
		// }
		// }
		//
		// }

		return null;

	}

	public Object getParent(Object element) {

		if (element != null && element instanceof ToolItem) {
			ToolItem item = (ToolItem) element;

			ToolBar toolbar = item.getParent();
			if (toolbar != null) {
				return toolbar;
			}
		}

		return null;
	}

	public boolean hasChildren(Object element) {
		// if (element instanceof MenuItem) {
		// MenuItem item = (MenuItem) element;
		//
		// Object data = item.getData();
		//
		// if (data != null && data instanceof MenuManager) {
		// MenuManager mm = (MenuManager) data;
		//
		// if (mm != null)
		// return mm.getMenu().getItemCount() > 0;
		//
		// }
		//
		// }

		return false;
	}

	@SuppressWarnings("restriction")
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof ToolBar) {
			ToolBar toolBar = (ToolBar) inputElement;
			return toolBar.getItems();
		}
		
	    ToolBarContributionItem2 item2;
	    if ((inputElement instanceof ICoolBarManager)) {
	      ICoolBarManager toolbar = (ICoolBarManager)inputElement;
	      IContributionItem[] items = toolbar.getItems();
	      List list = new ArrayList();
	      for (int i = 0; i < items.length; i++) {
	        if ((items[i] == null) || (items[i].isGroupMarker()) || 
	          (items[i].isSeparator()))
	        {
	          continue;
	        }
	        if ((items[i] instanceof ToolBarContributionItem2)) {
	          ToolBarContributionItem2 item = (ToolBarContributionItem2)items[i];
	          item.getToolBarManager().getItems();
	          fillList(list, item.getToolBarManager().getItems());
	        } else if ((items[i] instanceof ICoolBarManager2)) {
	          ICoolBarManager2 coolBarMng = (ICoolBarManager2)items[i];
	          fillList(list, coolBarMng.getItems());
	        } else if ((items[i] instanceof ToolBarContributionItem2)) {
	          item2 = (ToolBarContributionItem2)items[i];
	          fillList(list, item2.getToolBarManager().getItems());
	        }
	      }
	      return list.toArray();
	    }

		if (inputElement instanceof CoolBar) {
			List<ToolItem> list = new ArrayList<ToolItem>();

			CoolBar cBar = (CoolBar) inputElement;
			Control[] childs = cBar.getChildren();
			for (Control c : childs) {
				if (c instanceof ToolBar) {
					ToolBar tb = (ToolBar) c;
					for (ToolItem tmpItem : tb.getItems()) {
						list.add(tmpItem);
					}

				}
			}
			return list.toArray(new ToolItem[0]);
		}

		return null;
	}
	
	  private void fillList(List<IContributionItem> list, IContributionItem[] items)
	  {
	    for (int i = 0; i < items.length; i++) {
	      if ((items[i] == null) || (items[i].isGroupMarker()) || 
	        (items[i].isSeparator()))
	      {
	        continue;
	      }
	      if (items[i].getId() == null)
	      {
	        continue;
	      }
	      list.add(items[i]);
	    }
	  }

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
