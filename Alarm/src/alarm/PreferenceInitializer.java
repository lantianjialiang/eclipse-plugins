package alarm;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * The preference initializer for alarm plugins
 *
 * @author jialiang
 *
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public static final String INTERVAL_KEY = "interval_key";
	public static final String STOP_ALARM = "stop_alarm";
    /**
     * The constructor.
     */
    public PreferenceInitializer() {
        super();
    }

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore preStore = Activator.getDefault().getPreferenceStore();

        preStore.setDefault(INTERVAL_KEY, "45");
        preStore.setDefault(STOP_ALARM, false);
    }
}
