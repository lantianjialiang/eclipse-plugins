package alarm;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IStartup;

/**
 * Early start to start alarm thread
 *
 * @author jialiang
 *
 */
public class AlarmStartup implements IStartup {

	@Override
	public void earlyStartup() {
		//add property change listener to preference store.
        Activator.getDefault().getPreferenceStore().addPropertyChangeListener(AlarmThread.propertyChangeListener);
        
		IPreferenceStore preStore = Activator.getDefault().getPreferenceStore();		
		if(preStore.getBoolean(PreferenceInitializer.STOP_ALARM)) {
			// not start alarm thread
			if(AlarmThread.alarmThread != null) {
				AlarmThread.alarmThread.stopAlarmThread();
			}
			return;
		}
		
		AlarmThread.startAlarmThread();
	}

}
