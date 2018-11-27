package com.example.will.projetofinal;

import java.util.Date;

public class Event extends BaseEvent
{
    public Event()
    {
        super();
    }

    public Event(String name, Date date)
    {
        super(name, date);
    }

    public Event(String name, Long date)
    {
        super(name, new Date(date));
    }

    public Event(String name, String timestamp)
    {
        super(name, timestamp.split("T")[0].split("-"));
    }

    public Event(String name, String startTimestamp, String endTimestamp)
    {
        super(name, startTimestamp.split("T")[0].split("-"), endTimestamp.split("T")[0].split("-"));
    }

    public String getName()
    {
        return name;
    }

    public String getStartDate()
    {
        return simpleStartDate;
    }

    public String getEndDate()
    {
        return simpleEndDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
