package com.google.code.t4eclipse.tools.view.provider;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;

import com.google.code.t4eclipse.core.utility.ToolBarUtility;

public class ToolBarItemLabelProvider extends LabelProvider implements
		ITableLabelProvider, ITableFontProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		if (ToolBarUtility.COLUMNS[columnIndex].equals(ToolBarUtility.TEXT)) {
			if (element instanceof ToolItem) {
				ToolItem item = (ToolItem) element;
				return getImage(item);
			}
			
			if ((element instanceof ActionContributionItem)) {
				ActionContributionItem item = (ActionContributionItem) element;
				Widget w = item.getWidget();
				if ((w instanceof ToolItem)) {
					return getImage((ToolItem)w);
				}
			}
		}
		
		return null;
	}
	
	public String getColumnText(Object element, int columnIndex) {
		String head = ToolBarUtility.COLUMNS[columnIndex];
		if ((element instanceof ToolItem)) {
			return processToolItem(element, head);
		}

		if ((element instanceof ActionContributionItem)) {
			ActionContributionItem item = (ActionContributionItem) element;
			if (head.equals(ToolBarUtility.ACTION_ID))
				return item.getId();
			if (head.equals(ToolBarUtility.ACTION)) {
				return ToolBarUtility.getActionContributioItemClass(item);
			}

		}

		return "";
	}

	private static String processToolItem(Object element, String head) {
		ToolItem item = (ToolItem) element;
		if (head.equals("Action")) {
			item.setData("JAVA_TYPE",
					ToolBarUtility.getToolBarRowDataStr(item, "Action"));
		}

		return ToolBarUtility.getToolBarRowDataStr(item, head);
	}

	private static Image getImage(ToolItem item) {
		Image image = item.getImage();
		if (image != null) {
			Image newImage = new Image(Display.getDefault(),
					image.getImageData());
			return newImage;
		}

		return null;
	}

	public Font getFont(Object element, int columnIndex) {
		return null;
	}

}
