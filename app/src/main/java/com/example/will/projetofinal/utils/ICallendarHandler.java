package com.example.will.projetofinal.utils;

import com.example.will.projetofinal.models.BaseEvent;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface ICallendarHandler
{
    boolean isEmptyList();
    HashSet<Date> getKeys();
    boolean containsKey(Date key);
    List<BaseEvent> getEvents(Date key);
    void onDayClick(Date date);
    void setFragment(IFragmentReceiver receiver);
}
