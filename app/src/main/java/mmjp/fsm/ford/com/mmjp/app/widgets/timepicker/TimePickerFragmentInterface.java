package mmjp.fsm.ford.com.mmjp.app.widgets.timepicker;

import android.widget.TimePicker;

/**
 * Created by troger56 on 4/3/18.
 */

public interface TimePickerFragmentInterface {
    public void OnTimePickerListener(TimePicker view, int hourOfDay, int minute);
}

