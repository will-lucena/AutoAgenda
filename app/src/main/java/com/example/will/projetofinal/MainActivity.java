 package com.example.will.projetofinal;

 import android.content.Intent;
 import android.os.Bundle;
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


 public class MainActivity extends AppCompatActivity
 {
     private Toolbar toolbar;
     public static CustomCalendar calendarView;

     public static List<BaseEvent> events;
     public static Hashtable<Date, BaseEvent> customEvents;
     public static HashSet<Date> dates;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         calendarView = findViewById(R.id.calendarView);
         dates = new HashSet<>();
         customEvents = new Hashtable<>();

         if (events == null)
         {
             events = new ArrayList<>();
         }
         if ((boolean)getIntent().getExtras().get(BundleKeys.is_logged_in.toString()))
         {
             Log.i("debug", "logado");
             loadEvents((String)getIntent().getExtras().get(BundleKeys.facebook_events_json.toString()));
         }
         else
         {
             Log.i("debug", "deslogado");
         }

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
                                         Intent intent1 = new Intent(getApplicationContext(), EventsListActivity.class);
                                         startActivity(intent1);
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
     }

     public static void loadEvents(String response)
     {
         Log.i("debug", "carregando eventos");
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
                 Date d = BaseEvent.toDate(date[0], date[1], date[2]);
                 dates.add(d);
                 events.add(event);
                 customEvents.put(d, event);
             }
             calendarView.updateCalendar(dates);
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }
 }