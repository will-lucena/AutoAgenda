package com.example.will.projetofinal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_list_card, container,false);

        eventsAdapter = new EventsAdapter(getActivity(), R.layout.event_list_card, listener.getEvents());

        setListAdapter(eventsAdapter);
        eventsAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = ((BaseEvent) eventsAdapter.getItem(position)).toString();
        Toast.makeText(v.getContext(), item, Toast.LENGTH_SHORT).show();
    }
}
