package com.example.will.projetofinal.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.will.projetofinal.R;
import com.example.will.projetofinal.models.BaseEvent;
import com.example.will.projetofinal.models.Event;
import com.example.will.projetofinal.utils.EventType;
import com.example.will.projetofinal.utils.IFragmentComunication;

import java.util.Calendar;
import java.util.Date;

public class EventCreationFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText eventName;
    private DatePicker datePicker;
    private IFragmentComunication listener;
    private Spinner eventTypeSpinner;
    private EventType selectedType;
    private ArrayAdapter<CharSequence> adapter;
    private Button eventCreate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof IFragmentComunication))
        {
            throw new RuntimeException("Deve ser um IFragmentComunication");
        }

        listener = (IFragmentComunication) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_creation, container,false);

        eventName = view.findViewById(R.id.eventName);
        datePicker = view.findViewById(R.id.datePicker);
        eventTypeSpinner = view.findViewById(R.id.eventTypeSpinner);
        eventCreate = view.findViewById(R.id.createEventButton);

        adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.eventTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventTypeSpinner.setAdapter(adapter);
        eventTypeSpinner.setOnItemSelectedListener(this);

        final Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
            }
        });

        eventCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("events", Calendar.getInstance().getTime().toString());
                Date date = Calendar.getInstance().getTime();
                BaseEvent event = new Event(eventName.getText().toString(), date);
                event.setEventType(selectedType);

                listener.setEvents(event);
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i)
        {
            case 0:
                selectedType = EventType.Default;
                break;
            case 1:
                selectedType = EventType.Exam;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        selectedType = EventType.Default;
    }
}
