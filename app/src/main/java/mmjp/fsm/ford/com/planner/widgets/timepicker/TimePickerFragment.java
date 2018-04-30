package mmjp.fsm.ford.com.planner.widgets.timepicker;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private int mMinutes;
    private int mHours;
    private Activity mActivity;
    private TextView mField;
    private TimePicker mTimePicker;
    private TimePickerDialog timePickerDialog;

    public static TimePickerFragment newInstance(Activity activity,TextView displayText){
       TimePickerFragment fragment = new TimePickerFragment();
       fragment.setDisplayTextField(displayText);

       return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        timePickerDialog = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        setTimePicker();
        setCurrentTimeOnView();

        return timePickerDialog;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        view.setMinute(minute);
        view.setHour(hourOfDay);

        mTimePicker = view;
        mField.setText(hourOfDay + ":" + minute);
    }

    public void setTimePicker(){
        mTimePicker = (TimePicker) new TimePicker(getActivity().getBaseContext());
    }
    public TimePicker getTimePicker(){
        return mTimePicker;
    }

    // display current time
    public void setCurrentTimeOnView() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // set current time into textview
        // set current time into timepicker
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mField.setText(formatTimePickerSelection(hourOfDay, minute));
            }
        });

        mField.setText(formatTimePickerSelection(hour, minute));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private StringBuilder formatTimePickerSelection(int hour, int minute){
       return new StringBuilder().append(pad(hour)).append(":").append(pad(minute));
    }

    public void setDisplayTextField(TextView field){
        mActivity = getActivity();
        mField = field;
    }
}