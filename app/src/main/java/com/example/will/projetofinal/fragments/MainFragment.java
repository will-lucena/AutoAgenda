package com.example.will.projetofinal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.projetofinal.R;
import com.example.will.projetofinal.utils.CustomCalendar;
import com.example.will.projetofinal.utils.ICallendarHandler;
import com.example.will.projetofinal.utils.IFragmentReceiver;

import java.util.Date;
import java.util.HashSet;

public class MainFragment extends Fragment implements IFragmentReceiver
{
    private CustomCalendar calendarView;
    private ICallendarHandler listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof ICallendarHandler))
        {
            throw new RuntimeException("Deve ser um ICallendarHandler");
        }

        listener = (ICallendarHandler) context;
        listener.setFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setEventHandler(listener);

        return view;
    }

    @Override
    public void updateCalendar(HashSet<Date> set) {
        calendarView.updateCalendar(set);
    }
}
