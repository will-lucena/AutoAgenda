 package com.example.will.projetofinal;

 import android.graphics.Color;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;
 import android.support.v7.widget.Toolbar;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.view.View;

 import com.github.sundeepk.compactcalendarview.CompactCalendarView;
 import com.github.sundeepk.compactcalendarview.domain.Event;

 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;


 public class MainActivity extends AppCompatActivity {

     private Toolbar toolbar;
     private Menu myMenu;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         toolbar.setNavigationIcon(R.drawable.baseline_menu_24);

         toolbar.setNavigationOnClickListener(
                 new View.OnClickListener(){
                     @Override
                     public void onClick(View v) {
                         getMenuInflater().inflate(R.menu.menu_main, myMenu);
                     }
                 }
         );

         //*
         final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
         // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
         // Use constants provided by Java Calendar class
         compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

         // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
         Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
         compactCalendarView.addEvent(ev1);

         // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
         Event ev2 = new Event(Color.GREEN, 1433704251000L);
         compactCalendarView.addEvent(ev2);

         // Query for events on Sun, 07 Jun 2015 GMT.
         // Time is not relevant when querying for events, since events are returned by day.
         // So you can pass in any arbitary DateTime and you will receive all events for that day.
         List<Event> events = compactCalendarView.getEvents(1433701251000L); // can also take a Date object

         // events has size 2 with the 2 events inserted previously
         Log.d("debug", "Events: " + events);

         // define a listener to receive callbacks when certain events happen.
         compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
             @Override
             public void onDayClick(Date dateClicked) {
                 List<Event> events = compactCalendarView.getEvents(dateClicked);
                 Log.d("debug", "Day was clicked: " + dateClicked + " with events " + events);
             }

             @Override
             public void onMonthScroll(Date firstDayOfNewMonth) {
                 Log.d("debug", "Month was scrolled to: " + firstDayOfNewMonth);
             }
         });
         /**/
     }

     @Override
     public boolean onPrepareOptionsMenu(Menu menu) {
         myMenu = menu;
         return super.onPrepareOptionsMenu(menu);
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         //getMenuInflater().inflate(R.menu.menu_main, menu);
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
             case R.id.menu_addAccount:
                 // User chose the "Settings" item, show the app settings UI...
                 return true;

             default:
                 // If we got here, the user's action was not recognized.
                 // Invoke the superclass to handle it.
                 return super.onOptionsItemSelected(item);
         }
     }
 }