package com.example.will.projetofinal.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.will.projetofinal.utils.EventsAdapter;
import com.example.will.projetofinal.utils.IFragmentComunication;
import com.example.will.projetofinal.R;
import com.example.will.projetofinal.models.BaseEvent;


public class EventListFragment extends ListFragment
{
    private IFragmentComunication listener;
    private ArrayAdapter<BaseEvent> eventsAdapter;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(!(context instanceof IFragmentComunication))
        {
            throw new RuntimeException("Deve ser um IFragmentComunication");
        }

        listener = (IFragmentComunication) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventsAdapter = new EventsAdapter(getActivity(), R.layout.event_list_card, listener.getEvents());
        setListAdapter(eventsAdapter);
        eventsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        BaseEvent selectedEvent = eventsAdapter.getItem(position);
        listener.changeFragment(selectedEvent);
    }
}
