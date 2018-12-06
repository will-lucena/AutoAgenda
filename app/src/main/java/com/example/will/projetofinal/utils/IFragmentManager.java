package com.example.will.projetofinal.utils;

import com.example.will.projetofinal.models.BaseEvent;

import java.util.List;

public interface IFragmentManager
{
    List<BaseEvent> getEvents();
    void setEvent(BaseEvent event);
    void changeFragment(BaseEvent selectedEvent);
    BaseEvent getEvent();
}

