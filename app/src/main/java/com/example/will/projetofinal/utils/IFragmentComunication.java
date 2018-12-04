package com.example.will.projetofinal.utils;

import com.example.will.projetofinal.models.BaseEvent;
import com.example.will.projetofinal.models.Place;

import java.util.List;

public interface IFragmentComunication
{
    List<BaseEvent> getEvents();
    void setEvents(List<BaseEvent> list);
    void setEvents(BaseEvent event);
    void changeFragment(BaseEvent selectedEvent);
    BaseEvent getEvent();
}

