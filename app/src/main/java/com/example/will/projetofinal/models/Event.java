package com.example.will.projetofinal.models;

import com.example.will.projetofinal.utils.EventType;

import java.util.Date;

public class Event extends BaseEvent
{
    private Place location;

    public Event(String name, Date date)
    {
        super(name, date);
        setEventType(EventType.Event);
    }

    public Event(String name, String startTimestamp, String endTimestamp, Place location)
    {
        super(name, startTimestamp.split("T")[0].split("-"), endTimestamp.split("T")[0].split("-"));
        this.location = location;
        setEventType(EventType.Event);
    }

    public Place getLocation()
    {
        return location;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
