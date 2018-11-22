package com.example.will.projetofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;

public class EventsListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Event> eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        listView = findViewById(R.id.listView);

        eventsAdapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(eventsAdapter);

        eventsAdapter.add(new Event());
        eventsAdapter.add(new Event("Test", Calendar.getInstance().getTime()));
        eventsAdapter.notifyDataSetChanged();
    }
}
