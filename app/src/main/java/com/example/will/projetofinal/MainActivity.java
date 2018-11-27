 package com.example.will.projetofinal;

 import android.content.Intent;
 import android.graphics.Color;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.PopupMenu;
 import android.util.Log;
 import android.support.v7.widget.Toolbar;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.CalendarView;

 import com.facebook.AccessToken;
 import com.facebook.GraphRequest;
 import com.facebook.GraphResponse;
 import com.facebook.HttpMethod;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;


 public class MainActivity extends AppCompatActivity {

     private Toolbar toolbar;
     private CalendarView calendarView;

     public static List<BaseEvent> events;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         AccessToken accessToken = AccessToken.getCurrentAccessToken();
         boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

         if (events == null)
         {
             events = new ArrayList<>();
         }

         if (isLoggedIn)
         {
             Log.i("debug", "logado");
             new GraphRequest(
                     AccessToken.getCurrentAccessToken(),
                     "/" + accessToken.getUserId() + "/events",
                     null,
                     HttpMethod.GET,
                     new GraphRequest.Callback() {
                         public void onCompleted(GraphResponse response) {
                             Log.i("debug", "loading");
                            loadEvents(response.getRawResponse());
                         }
                     }
             ).executeAsync();
         }
         else
         {
             Log.i("debug", "deslogado");
         }

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

     private void loadEvents(String response)
     {
         try {
             JSONObject json = new JSONObject(response);
             JSONArray results = json.getJSONArray("data");

             for(int i = 0; i < results.length(); i++)
             {
                 String eventName = results.getJSONObject(i).getString("name");
                 String startDate = results.getJSONObject(i).getString("start_time");
                 String endDate = results.getJSONObject(i).getString("end_time");
                 events.add(new Event(eventName, startDate, endDate));
             }
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }
 }