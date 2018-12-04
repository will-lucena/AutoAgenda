package com.example.will.projetofinal.utils;

import com.example.will.projetofinal.models.BaseEvent;

import java.util.List;

public interface IFragmentComunication
{
    List<BaseEvent> getEvents();
    void setEvents(List<BaseEvent> list);
    void changeFragment(BaseEvent selectedEvent);
    BaseEvent getEvent();
}
