package com.example.will.projetofinal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

public class EventsListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<BaseEvent> eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        listView = findViewById(R.id.listView);

        eventsAdapter = new EventsAdapter(this, R.layout.event_list_card, MainActivity.events);

        listView.setAdapter(eventsAdapter);

        eventsAdapter.add(new Event());
        eventsAdapter.add(new Event("Test", Calendar.getInstance().getTime()));
        eventsAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((BaseEvent) adapterView.getItemAtPosition(i)).toString();
                Toast.makeText(view.getContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
