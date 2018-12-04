package com.example.will.projetofinal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailsFragment extends Fragment
{
    private IFragmentComunication listener;
    private ImageView eventImage;
    private TextView eventName;
    private TextView eventStartDate;
    private TextView eventEndDate;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_card, container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventImage = getActivity().findViewById(R.id.eventImage);
        eventName = getActivity().findViewById(R.id.eventName);
        eventStartDate = getActivity().findViewById(R.id.eventStartDate);
        eventEndDate = getActivity().findViewById(R.id.eventEndDate);
        event = listener.getEvent();

        eventName.setText(event.name);
        //eventStartDate.setText(event.simpleStartDate);
        //eventEndDate.setText(event.simpleEndDate);
    }
}
