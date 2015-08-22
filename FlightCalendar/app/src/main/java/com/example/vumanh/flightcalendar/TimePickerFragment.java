package com.example.vumanh.flightcalendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by VuManh on 8/22/2015.
 */
public class TimePickerFragment extends DialogFragment {
    private Activity mActivity;
    private TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mListener = (TimePickerDialog.OnTimeSetListener) activity;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(mActivity, mListener, hour, minute, DateFormat.is24HourFormat(mActivity));
    }
}
