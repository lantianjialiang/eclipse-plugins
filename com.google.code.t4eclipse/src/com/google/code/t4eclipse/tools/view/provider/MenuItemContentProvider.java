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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.internal.ActionSetContributionItem;

public class MenuItemContentProvider implements ITreeContentProvider {

	@SuppressWarnings("null")
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MenuItem) {
			MenuItem item = (MenuItem) parentElement;
			
			if(item.isDisposed()) {
				return null;
			}

			Object data = item.getData();
			if(data == null) {
				return null;
			}
			
			if (data instanceof MenuManager) {
				MenuManager mm = (MenuManager) data;

				if (mm != null) {
					return mm.getMenu().getItems();
				}
			}
			
			if (data instanceof ActionSetContributionItem) {
				ActionSetContributionItem ascItem = (ActionSetContributionItem) data;
				IContributionItem cItem = ascItem.getInnerItem();
				if(cItem instanceof MenuManager) {
					MenuManager mm = (MenuManager)cItem;
					mm.update(true);
					return mm.getMenu().getItems();
				}
			}
		}

		return null;

	}

	public Object getParent(Object element) {

		if (element != null && element instanceof MenuItem) {
			MenuItem item = (MenuItem) element;

			Menu menu = item.getMenu();
			if (menu != null) {
				return menu.getParentItem();
			}
		}

		return null;
	}

	@SuppressWarnings("null")
	public boolean hasChildren(Object element) {
		if (element instanceof MenuItem) {
			MenuItem item = (MenuItem) element;

			Object data = item.getData();
			if(data == null) {
				return false;
			}
			
			if (data instanceof MenuManager) {
				MenuManager mm = (MenuManager) data;
				mm.update(true);

				if (mm != null)
					return mm.getMenu().getItemCount() > 0;
			}
			
			if (data instanceof ActionSetContributionItem) {
				ActionSetContributionItem ascItem = (ActionSetContributionItem) data;
				IContributionItem cItem = ascItem.getInnerItem();
				if(cItem instanceof MenuManager) {
					MenuManager mm = (MenuManager)cItem;
					mm.update(true);
					return mm.getMenu().getItemCount() > 0;
				}
				
//				if(cItem instanceof PluginActionContributionItem) {
//					PluginActionContributionItem pacItem = (PluginActionContributionItem)cItem;
//					IAction action = pacItem.getAction();
//					if(action instanceof WWinPluginPulldown) {
//						WWinPluginPulldown wppAction = (WWinPluginPulldown)action;
//						ObjectResult result = ReflectionUtil.getField("delegate", wppAction);
//						if(result.result == null) {
//							return false;
//						}
//						
//						ExternalToolMenuDelegate delegate = (ExternalToolMenuDelegate)result.result;
//						//TODO fMenu						
//					}
//				}
			}
		}

		return false;
	}

	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof Menu) {
			Menu menu = (Menu) inputElement;
			return menu.getItems();
		}

		return null;
	}

	public void dispose() {
		//do nothing
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//do nothing
	}

}
