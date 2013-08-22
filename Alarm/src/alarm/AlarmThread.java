package alarm;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * The early startup class, it will create a thread according to the preference.
 * 
 * @author jialiang
 */
public class AlarmThread extends Thread {
	private static final String ALARM_CONSOLE_ID = "Alarm Console";
	static AlarmThread alarmThread = null;

	private boolean isStop = false;
	private static final long MUTIL = 1000 * 60;
	private int intervalFromPref = 45;

	private MessageConsoleStream out = null;
	private MessageConsole myConsole = null;

	private final IPreferenceStore preStore;
	@SuppressWarnings("unused")
	private final boolean isConsoleVisible = false;

	final static IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			final String propertyName = event.getProperty();

			System.out.println("changed property: " + event);
			if (propertyName.equals(PreferenceInitializer.INTERVAL_KEY)) {
				if (alarmThread != null) {
					alarmThread.updateInterval();
				}
			}

			if (propertyName.equals(PreferenceInitializer.STOP_ALARM)) {
				if (event.getOldValue().equals(Boolean.FALSE)
						|| event.getNewValue().equals(Boolean.TRUE)) {
					if (alarmThread.isAlive()) {
						alarmThread.interrupt();
					}
					alarmThread = null;
				}

				if (event.getOldValue().equals(Boolean.TRUE)
						|| event.getNewValue().equals(Boolean.FALSE)) {
					if (alarmThread == null) {
						startAlarmThread();
					}
				}

				if (alarmThread != null) {
					alarmThread.updateIsStop();
				}
			}

		}
	};

	static void startAlarmThread() {
		alarmThread = new AlarmThread();
		alarmThread.updateInterval();
		alarmThread.start();
	}

	public AlarmThread() {
		super("Alarm Thread");
		preStore = Activator.getDefault().getPreferenceStore();
		myConsole = findConsole(ALARM_CONSOLE_ID);
		out = myConsole.newMessageStream();
		makeConsoleVisible();
	}

	public void stopAlarmThread() {
		isStop = true;
	}

	public void updateInterval() {
		intervalFromPref = preStore.getInt(PreferenceInitializer.INTERVAL_KEY);
		System.out.println(this);
	}

	public void updateIsStop() {
		isStop = preStore.getBoolean(PreferenceInitializer.STOP_ALARM);
		System.out.println(this);
	}

	@Override
	public void run() {
		if (preStore.getBoolean(PreferenceInitializer.STOP_ALARM)) {
			return;
		}
		long start = System.currentTimeMillis();
		while (!isStop) {
			try {
				System.out.println(this);
				Thread.sleep(5000);
				Thread.sleep(intervalFromPref * MUTIL);
				System.out.println("Alarm start, you should have a rest.");
				
				
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						makeConsoleVisible();
					}
				});
				long startTime = (System.currentTimeMillis() - start)/MUTIL;
				out.getConsole().clearConsole();
				out.println("--------------------------------------------");
				out.println("Hey gays, You have worked almost "
						+ intervalFromPref + " minutes,");
				out.println("You should have a rest for a while!!!");
				out.println("The alarm plugins has already started " + startTime + " minites");
				out.println("--------------------------------------------");

			} catch (Exception e) {
				//e.printStackTrace();
				return;
			}
		}

	}

	private void makeConsoleVisible() {
		IWorkbenchWindow ww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(ww == null) {
			return;
		}
		
		IWorkbenchPage page = ww.getActivePage();
		if(page == null) {
			return;
		}
		
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view;
		try {
			view = (IConsoleView) page.showView(id);
			view.display(myConsole);
			view.setFocus();
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("hiding")
	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Thread Name is: " + this.getName());
		sb.append(", Is stop is: " + isStop);
		sb.append(", Interval is: " + intervalFromPref);
		return sb.toString();
	}
}
