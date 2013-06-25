package com.google.code.t4eclipse.tools.view;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;

import com.google.code.t4eclipse.core.utility.MenuUtility;
import com.google.code.t4eclipse.tools.view.provider.ToolBarContentProvider;
import com.google.code.t4eclipse.tools.view.provider.ToolBarItemLabelProvider;

public class ToolBarAnalyzerView extends ViewPart {

	private TreeViewer viewer;

	public ToolBarAnalyzerView() {

	}

	public void update(ToolBar toolbar) {
		if (toolbar != null && !toolbar.isDisposed()) {
			// remove the following method will cause problem when reset input
			this.viewer.setSelection(StructuredSelection.EMPTY);
			this.viewer.setInput(toolbar);

		}

	}
	public void update(CoolBar toolbar) {
		if (toolbar != null && !toolbar.isDisposed()) {
			// remove the following method will cause problem when reset input
			this.viewer.setSelection(StructuredSelection.EMPTY);
			this.viewer.setInput(toolbar);

		}

	}
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createViewer(composite);
		getSite().setSelectionProvider(viewer);
		final MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		Menu menu = menuMgr.createContextMenu(this.viewer.getTree());

		this.viewer.getTree().setMenu(menu);
		getSite().registerContextMenu(menuMgr, this.viewer);

	}

	private void createViewer(Composite parent) {
		viewer = new TreeViewer(parent, SWT.FULL_SELECTION | SWT.H_SCROLL
				| SWT.V_SCROLL);

		viewer.getTree().setBackground(
				parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		// fFilteredTree.setInitialText(Messages.LogView_show_filter_initialText);

		viewer.getTree().setLinesVisible(true);
		createColumns(viewer.getTree());
		viewer.setContentProvider(new ToolBarContentProvider());
		viewer.setLabelProvider(new ToolBarItemLabelProvider());

	}

	private void createColumns(Tree tree) {
		for (int i = 0; i < MenuUtility.COLUMNS.length; i++) {
			TreeColumn col = new TreeColumn(tree, SWT.LEFT);
			col.setText(MenuUtility.COLUMNS[i]);
			col.setWidth(MenuUtility.COL_WIDTH[i]);
		}

		tree.setHeaderVisible(true);

	}

	@Override
	public void setFocus() {
		// DO nothing
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public TreeViewer getTreeViewer() {

		return this.viewer;
	}

	public void update(ICoolBarManager toolbar) {
		if (toolbar != null) {
			this.viewer.setSelection(StructuredSelection.EMPTY);
			this.viewer.setInput(toolbar);
		}
	}
}
