package alarm;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Alarm preference page
 *
 * @author jialiang
 *
 */
public class AlarmPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {


    /**
     * Constructor.
     */
    public AlarmPreferencePage() {
        super(GRID);
    }

    @Override
    protected void createFieldEditors() {
        BooleanFieldEditor stopAlarm = new BooleanFieldEditor(PreferenceInitializer.STOP_ALARM,
                "Stop alarm",
                getFieldEditorParent());
        addField(stopAlarm);

        IntegerFieldEditor interval = new IntegerFieldEditor(PreferenceInitializer.INTERVAL_KEY,
                                                                      "Interval(unit:minute)",
                                                                      getFieldEditorParent());

        interval.setValidRange(10, 500);
        addField(interval);
    }

    @Override
    public void init(IWorkbench workbench) {
        // do nothing here
    }

    @Override
    public IPreferenceStore getPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }
}
