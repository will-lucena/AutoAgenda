package com.example.will.projetofinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.will.projetofinal.R;
import com.example.will.projetofinal.models.Event;

import java.util.Calendar;
import java.util.Date;

public class EventCreationActivity extends AppCompatActivity {

    private EditText eventName;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);

        eventName = findViewById(R.id.eventName);
        datePicker = findViewById(R.id.datePicker);

        final Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
            }
        });

    }

    public void createEvent(View view) {
        Log.i("events", Calendar.getInstance().getTime().toString());
        Date date = Calendar.getInstance().getTime();
        Event event = new Event(eventName.getText().toString(), date);
        MainActivity.addEvent(date, event);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
