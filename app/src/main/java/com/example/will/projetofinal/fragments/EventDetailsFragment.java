package com.example.will.projetofinal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.projetofinal.models.Event;
import com.example.will.projetofinal.utils.EventType;
import com.example.will.projetofinal.utils.IFragmentComunication;
import com.example.will.projetofinal.R;
import com.example.will.projetofinal.models.BaseEvent;

public class EventDetailsFragment extends Fragment
{
    private IFragmentComunication listener;
    private ImageView eventImage;
    private TextView eventName;
    private TextView eventStartDate;
    private TextView eventEndDate;
    private TextView eventLocation;
    private BaseEvent event;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.event_card, container,false);
        eventImage = view.findViewById(R.id.eventImage);
        eventName = view.findViewById(R.id.eventName);
        eventStartDate = view.findViewById(R.id.eventStartDate);
        eventEndDate = view.findViewById(R.id.eventEndDate);
        eventLocation = view.findViewById(R.id.eventLocation);

        eventName.setText(event.getName());
        eventStartDate.setText(event.getStartDateFormated());
        eventEndDate.setText(event.getEndDateFormated());

        if (event.getEventType() == EventType.Event)
        {
            view.findViewById(R.id.locationBlock).setVisibility(View.VISIBLE);
            eventLocation.setText(((Event) event).getLocation().toString());
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = listener.getEvent();
    }
}
