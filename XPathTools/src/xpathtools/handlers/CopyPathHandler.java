package xpathtools.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Copy path handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 * 
 * @author jialiang
 */
public class CopyPathHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public CopyPathHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@SuppressWarnings("null")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage().getSelection();
		IPath location = null;
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (Iterator<?> iterator = strucSelection.iterator(); iterator
					.hasNext();) {
				Object element = iterator.next();
				//System.out.println(element.toString());
				if(element instanceof IResource) {
					IResource resource = (IResource) element;
					location = resource.getLocation();
					String locationStr = location.toOSString();
					System.out.println("Copy path: " + locationStr + " to clipboard.");
					copyToClipboard(locationStr);
				}
			}
		}
		return null;
	}
	
	private static void copyToClipboard(String string) {
		Clipboard clipboard = new Clipboard(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell().getDisplay());
		// Put the path to clip board
		clipboard.setContents(new Object[] { string },
				new Transfer[] { TextTransfer.getInstance() });
	}	
}
