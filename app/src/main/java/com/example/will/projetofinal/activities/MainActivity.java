 package com.example.will.projetofinal.activities;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentManager;
 import android.support.v4.app.FragmentTransaction;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.PopupMenu;
 import android.support.v7.widget.Toolbar;
 import android.view.MenuItem;
 import android.view.View;

 import com.example.will.projetofinal.utils.BundleKeys;
 import com.example.will.projetofinal.fragments.EventDetailsFragment;
 import com.example.will.projetofinal.fragments.EventListFragment;
 import com.example.will.projetofinal.utils.EventType;
 import com.example.will.projetofinal.utils.Helper;
 import com.example.will.projetofinal.utils.ICallendarHandler;
 import com.example.will.projetofinal.R;
 import com.example.will.projetofinal.models.BaseEvent;
 import com.example.will.projetofinal.models.Event;
 import com.example.will.projetofinal.utils.IFragmentManager;
 import com.example.will.projetofinal.utils.IFragmentCallback;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Hashtable;
 import java.util.List;


 public class MainActivity extends AppCompatActivity implements IFragmentManager, ICallendarHandler
 {
     private Toolbar toolbar;
     private Fragment fragment;
     private BaseEvent selectedEvent;
     private Hashtable<Date, List<BaseEvent>> events;
     private List<BaseEvent> listToFragment;
     private IFragmentCallback fragmentReceiver;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);

         if (events == null)
         {
             events = new Hashtable<>();
         }
         listToFragment = new ArrayList<>();

         setContentView(R.layout.activity_main);
         toolbar = findViewById(R.id.toolbar);
         setupToolbar();

         if ((boolean)getIntent().getExtras().get(BundleKeys.is_logged_in.toString()))
         {
             loadEvents((String)getIntent().getExtras().get(BundleKeys.facebook_events_json.toString()));
         }

         getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
             @Override
             public void onBackStackChanged() {

                 if (getSupportFragmentManager().getBackStackEntryCount() == 0)
                 {
                     findViewById(R.id.mainFragment).setVisibility(View.VISIBLE);
                 }
             }
         });
     }

     private void setupToolbar()
     {
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
                                         fragmentTransaction.replace(R.id.auxFragment, fragment, "EventList");
                                         findViewById(R.id.mainFragment).setVisibility(View.INVISIBLE);
                                         fragmentTransaction.addToBackStack(null);
                                         listToFragment = getAllEvents();
                                         fragmentTransaction.commit();
                                         /**/
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

     public void loadEvents(String response)
     {
         if(response != null)
         {
             try {
                 JSONObject json = new JSONObject(response);
                 JSONArray results = json.getJSONArray("data");

                 for(int i = 0; i < results.length(); i++)
                 {
                     Event event = (Event) Helper.buildEvent(results.getJSONObject(i), EventType.Event);
                     List<BaseEvent> list = new ArrayList<>();
                     if (events.containsKey(event.getStartDate()))
                     {
                         list = events.get(event.getStartDate());
                     }
                     list.add(event);
                     events.put(event.getStartDate(), list);
                 }
                 HashSet<Date> keys = new HashSet<>();
                 for (Date key: events.keySet())
                 {
                     keys.add(key);
                 }
                 fragmentReceiver.updateCalendar(keys);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     }

     @Override
     public List<BaseEvent> getEvents() {
         return listToFragment;
     }

     private List<BaseEvent> getAllEvents()
     {
         List<BaseEvent> allEvents = new ArrayList<>();

         for (List<BaseEvent> list : events.values()) {
             allEvents.addAll(list);
         }
         return allEvents;
     }

     @Override
     public void changeFragment(BaseEvent selectedEvent)
     {
         this.selectedEvent = selectedEvent;
         EventDetailsFragment fragment = new EventDetailsFragment();
         FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
         findViewById(R.id.mainFragment).setVisibility(View.INVISIBLE);
         fragmentTransaction.replace(R.id.auxFragment, fragment, "EventDetails");
         fragmentTransaction.addToBackStack(null);
         fragmentTransaction.commit();
     }

     @Override
     public BaseEvent getEvent() {
         return selectedEvent;
     }

     @Override
     public boolean isEmptyList() {
         return events.isEmpty();
     }

     @Override
     public HashSet<Date> getKeys()
     {
         HashSet<Date> set = new HashSet<>();

         for (Date key: events.keySet())
         {
             set.add(key);
         }

         return set;
     }

     @Override
     public boolean containsKey(Date key)
     {
         return events.containsKey(key);
     }

     @Override
     public List<BaseEvent> getEvents(Date key)
     {
         return events.get(key);
     }

     @Override
     public void onDayClick(Date date)
     {
         if (events.containsKey(date))
         {
             FragmentManager fragmentManager = getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragment = new EventListFragment();
             listToFragment = events.get(date);
             findViewById(R.id.mainFragment).setVisibility(View.INVISIBLE);
             fragmentTransaction.add(R.id.auxFragment, fragment, "EventList");
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }
     }

     @Override
     public void setFragment(IFragmentCallback receiver) {
         this.fragmentReceiver = receiver;
     }
 }