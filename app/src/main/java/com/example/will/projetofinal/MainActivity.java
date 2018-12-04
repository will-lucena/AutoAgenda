 package com.example.will.projetofinal;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentManager;
 import android.support.v4.app.FragmentTransaction;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.PopupMenu;
 import android.util.Log;
 import android.support.v7.widget.Toolbar;
 import android.view.MenuItem;
 import android.view.View;

 import com.facebook.AccessToken;
 import com.facebook.GraphRequest;
 import com.facebook.GraphResponse;
 import com.facebook.HttpMethod;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Hashtable;
 import java.util.List;
 import java.util.Set;


 public class MainActivity extends AppCompatActivity implements IFragmentComunication
 {
     private Toolbar toolbar;
     private Fragment fragment;
     public static CustomCalendar calendarView;

     private static Hashtable<Date, List<BaseEvent>> events;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);

         if (events == null)
         {
             events = new Hashtable<>();
         }

         setContentView(R.layout.activity_main);

         calendarView = findViewById(R.id.calendarView);

         toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         toolbar.setNavigationIcon(R.drawable.baseline_menu_24);

         toolbar.setNavigationOnClickListener(
                 new View.OnClickListener(){
                     @Override
                     public void onClick(View v) {
                         PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                         popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                             @Override
                             public boolean onMenuItemClick(MenuItem item) {
                                 switch (item.getItemId())
                                 {
                                     case R.id.menu_addAccount:
                                         Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                         intent.putExtras(getIntent().getExtras());
                                         startActivity(intent);
                                         return true;
                                     case R.id.menu_listEvents:
                                         //*
                                         FragmentManager fragmentManager = getSupportFragmentManager();
                                         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                         fragment = new EventListFragment();
                                         fragmentTransaction.add(R.id.eventsListFragment, fragment);
                                         fragmentTransaction.commit();
                                         /**/
                                         return true;
                                     case R.id.menu_addEvent:
                                         Intent intent2 = new Intent(getApplicationContext(), EventCreationActivity.class);
                                         startActivity(intent2);
                                         return true;
                                     default:
                                         return false;
                                 }
                             }
                         });
                         popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                         popupMenu.show();
                     }
                 }
         );

         if ((boolean)getIntent().getExtras().get(BundleKeys.is_logged_in.toString()))
         {
             loadEvents((String)getIntent().getExtras().get(BundleKeys.facebook_events_json.toString()));
         }
     }

     public static void loadEvents(String response)
     {
         Log.i("debug", "carregando eventos");

         if(response != null)
         {
             try {
                 JSONObject json = new JSONObject(response);
                 JSONArray results = json.getJSONArray("data");

                 for(int i = 0; i < results.length(); i++)
                 {
                     String eventName = results.getJSONObject(i).getString("name");
                     String startDate = results.getJSONObject(i).getString("start_time");
                     String endDate = results.getJSONObject(i).getString("end_time");
                     String[] date = startDate.split("T")[0].split("-");
                     Event event = new Event(eventName, startDate, endDate);
                     Date eventDate = BaseEvent.toDate(date[0], date[1], date[2]);

                     List<BaseEvent> list = new ArrayList<>();
                     if (events.containsKey(eventDate))
                     {
                         list = events.get(eventDate);
                     }
                     list.add(event);
                     events.put(eventDate, list);
                 }

                 HashSet<Date> keys = new HashSet<>();
                 for (Date key: events.keySet()) {
                     keys.add(key);
                 }

                 calendarView.updateCalendar(keys);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     }

     @Override
     public List<BaseEvent> getEvents() {
         List<BaseEvent> allEvents = new ArrayList<>();

         for (List<BaseEvent> list : events.values()) {
             allEvents.addAll(list);
         }
         return allEvents;
     }

     @Override
     public void setEvents(List<BaseEvent> list) {
        //TODO
     }

     @Override
     public void changeFragment() {

     }

     public static void addEvent(Date key, BaseEvent event)
     {
         List<BaseEvent> list = new ArrayList<>();
         if (events.containsKey(key))
         {
             list = events.get(key);
         }
         list.add(event);
     }

     public static boolean eventsIsEmpty()
     {
         return events.isEmpty();
     }

     public static Set<Date> getKeys()
     {
         return events.keySet();
     }

     public static boolean containsKey(Date key)
     {
         return events.containsKey(key);
     }

     public static List<BaseEvent> getEvents(Date key)
     {
         return events.get(key);
     }
 }