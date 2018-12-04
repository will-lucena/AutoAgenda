package com.example.will.projetofinal.fragments;

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

import com.example.will.projetofinal.models.Event;
import com.example.will.projetofinal.models.Place;
import com.example.will.projetofinal.utils.EventType;
import com.example.will.projetofinal.utils.IFragmentComunication;
import com.example.will.projetofinal.R;
import com.example.will.projetofinal.models.BaseEvent;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventDetailsFragment extends Fragment implements OnMapReadyCallback
{
    private IFragmentComunication listener;
    private ImageView eventImage;
    private TextView eventName;
    private TextView eventStartDate;
    private TextView eventEndDate;
    private TextView eventLocation;
    private BaseEvent baseEvent;

    private GoogleMap map;

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

        eventName.setText(baseEvent.getName());
        eventStartDate.setText(baseEvent.getStartDateFormated());
        eventEndDate.setText(baseEvent.getEndDateFormated());

        if (baseEvent.getEventType() == EventType.Event)
        {
            view.findViewById(R.id.locationBlock).setVisibility(View.VISIBLE);
            eventLocation.setText(((Event) baseEvent).getLocation().toString());

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseEvent = listener.getEvent();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        Place location = ((Event) baseEvent).getLocation();
        LatLng coordinates = new LatLng(location.getLatitude(), location.getLongitude());
        map.addMarker(new MarkerOptions().position(coordinates).title(location.getName()));
        map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(coordinates).zoom(15).build()));
    }
}
