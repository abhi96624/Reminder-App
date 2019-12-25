package com.example.setreminder;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
   long eventTime = 1577291964000L;
   String title ="travel reminder";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setReminders(View view){
         int x= 24;
          Log.d("dkfj","kdjfkjf");
       addReminder(2019,11,25,23,25,2019,11,25,23,20);

//        ContentResolver cr = this.getContentResolver();
//        ContentValues calEvent = new ContentValues();
//        calEvent.put(CalendarContract.Events.CALENDAR_ID, 1); // XXX pick)
//        calEvent.put(CalendarContract.Events.TITLE, "Reminder to " + title );
//        calEvent.put(CalendarContract.Events.DTSTART, eventTime);
//        calEvent.put(CalendarContract.Events.DTEND, eventTime + (1 * 60 * 1000));
//        calEvent.put(CalendarContract.Events.HAS_ALARM, 1);
//        calEvent.put(CalendarContract.Events.EVENT_TIMEZONE, CalendarContract.Calendars.CALENDAR_TIME_ZONE);
//
//        //save an event
//        final Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, calEvent);
//
//        int dbId = Integer.parseInt(uri.getLastPathSegment());
//
//        //Now create a reminder and attach to the reminder
//        ContentValues reminders = new ContentValues();
//        reminders.put(CalendarContract.Reminders.EVENT_ID, dbId);
//        reminders.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_DEFAULT);
//        reminders.put(CalendarContract.Reminders.MINUTES, 0);
//
//        final Uri reminder = cr.insert(CalendarContract.Reminders.CONTENT_URI, reminders);
//
//        int added = Integer.parseInt(reminder.getLastPathSegment());
//
////this means reminder is added
//        if(added > 0) {
//            Intent view = new Intent(Intent.ACTION_VIEW);
//            view.setData(uri); // enter the uri of the event not the reminder
//
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH){
//                view.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                        |Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP
//                        |Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//            }
//            else {
//                view.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                        Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                        Intent.FLAG_ACTIVITY_NO_HISTORY |
//                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//            }
//            //view the event in calendar
//            startActivity(view);
//        }
    }

    public void  addReminder(int statrYear, int startMonth, int startDay, int startHour, int startMinut, int endYear, int endMonth, int endDay, int endHour, int endMinuts){
        Calendar beginTime = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
        beginTime.set(statrYear, startMonth, startDay, startHour, startMinut);
        long startMillis = beginTime.getTimeInMillis();
        Log.d("time",startMillis+"");

        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
        endTime.set(endYear, endMonth, endDay, endHour, endMinuts);
        long endMillis = endTime.getTimeInMillis();

        String eventUriString = "content://com.android.calendar/events";
        ContentValues eventValues = new ContentValues();

        eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
        eventValues.put(CalendarContract.Events.TITLE, "Flight to Agra");
        eventValues.put(CalendarContract.Events.DESCRIPTION, "Clinic App");
        eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Calcutta");
        eventValues.put(CalendarContract.Events.DTSTART, startMillis);
        eventValues.put(CalendarContract.Events.DTEND, endMillis);

        //eventValues.put(Events.RRULE, "FREQ=DAILY;COUNT=2;UNTIL="+endMillis);
        eventValues.put("eventStatus", 1);
//        eventValues.put("visibility", 3);
//        eventValues.put("transparency", 0);
        eventValues.put(CalendarContract.Events.HAS_ALARM, 1);

        Uri eventUri = getContentResolver().insert(Uri.parse(eventUriString), eventValues);
        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        /***************** Event: Reminder(with alert) Adding reminder to event *******************/

        String reminderUriString = "content://com.android.calendar/reminders";

        ContentValues reminderValues = new ContentValues();

        reminderValues.put("event_id", eventID);
        reminderValues.put("minutes", 10);
        reminderValues.put("method", 1);

        Uri reminderUri = getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);

    }

}
