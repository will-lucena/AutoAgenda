package com.example.will.projetofinal;

import java.util.List;

public interface IFragmentComunication
{
    List<BaseEvent> getEvents();
    void setEvents(List<BaseEvent> list);
    void changeFragment(BaseEvent selectedEvent);
    BaseEvent getEvent();
}
