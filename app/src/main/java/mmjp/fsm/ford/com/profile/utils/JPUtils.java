package mmjp.fsm.ford.com.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by userpmp on 2/26/18.
 */

public class JPUtils {

    public static final SimpleDateFormat SDF_ENGLISH = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    public static final SimpleDateFormat SDF_US = new SimpleDateFormat( "hh:mm a", Locale.US );
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

    public static float convertMetersToMiles(Integer Meters) {
        float miles = (float) (0.000621371 * Meters);
        return miles;
    }

    public static float convertMetersToKms(Integer Meters) {
        float kms = (float) (0.001 * Meters);
        return kms;
    }

    public static String convertEpochTime(Long Date) {
        long itemLong = (long) ((Date)/1000);
        java.util.Date d = new java.util.Date(itemLong*1000L);
        return JPUtils.SDF_ENGLISH.format(d);
    }

    public static String buildDate(String dateString){
        System.out.println("dateString " + dateString);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(Integer.parseInt(dateString));

        return formatted;
    }

    public static void alertDialogShow(Context context, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void setFocusForView(View view) {
        view.requestFocusFromTouch();
    }

    public static void showKeyBoard(Activity activity, View view) {
        InputMethodManager lManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        lManager.showSoftInput(view, 0);

    }

}