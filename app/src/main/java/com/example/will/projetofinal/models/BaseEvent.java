package com.example.will.projetofinal.models;

import com.example.will.projetofinal.utils.EventType;

import java.util.Calendar;
import java.util.Date;

public abstract class BaseEvent
{
    protected String name;
    protected Date startDate;
    protected Date endDate;

    protected String simpleStartDate;
    protected String simpleEndDate;
    
    private EventType type;

    protected BaseEvent()
    {
        name = "default event";
        startDate = Calendar.getInstance().getTime();
        endDate = Calendar.getInstance().getTime();
        type = EventType.Default;
    }

    protected BaseEvent(String name, Date date)
    {
        this.name = name;
        this.startDate = date;
        this.endDate = date;
        type = EventType.Default;
    }

    protected BaseEvent(String name, Date startDate, Date endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        type = EventType.Default;
    }

    protected BaseEvent(String name, String[] date)
    {
        this.name = name;
        Date d = toDate(date[0], date[1], date[2]);
        this.startDate = d;
        this.endDate = d;

        simpleStartDate = String.format("%s/%s/%s", date[2], date[1], date[0]);
        simpleEndDate = simpleStartDate;
        type = EventType.Default;
    }

    protected BaseEvent(String name, String[] startDate, String[] endDate)
    {
        this.name = name;
        Date start = toDate(startDate[0], startDate[1], startDate[2]);
        this.startDate = start;
        Date end = toDate(endDate[0], endDate[1], endDate[2]);
        this.endDate = end;

        simpleStartDate = String.format("%s/%s/%s", startDate[2], startDate[1], startDate[0]);
        simpleEndDate = String.format("%s/%s/%s", endDate[2], endDate[1], endDate[0]);
        type = EventType.Default;
    }

    public static Date toDate(String year, String month, String day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
        return calendar.getTime();
    }

    @Override
    public String toString()
    {
        return String.format("The %s starts at %s and ends at %s", name, simpleStartDate, simpleEndDate);
    }
    
    public void setEventType(EventType type)
    {
        this.type = type;
    }
    
    public EventType getEventType()
    {
        return type;
    }

    public Long timeUntilStart()
    {
       Date now = Calendar.getInstance().getTime();
       return now.getTime() - startDate.getTime();
    }

    public String timeUntil()
    {
        long difference = -timeUntilStart();

        System.out.println("now : " + Calendar.getInstance().getTime().getTime());
        System.out.println("startDate : " + startDate);
        System.out.println("different : " + difference);

        long secondsInMilli = 1000;
        long daysInMilli = secondsInMilli * 24 * 60 * 60;

        long elapsedDays = difference / daysInMilli;
        difference = difference % daysInMilli;

        if (difference < 0)
        {
            return "O evento já ocorreu";
        }
        else if (difference > 0)
        {
            return "Faltam " + elapsedDays + " para o evento começar";
        }
        else
        {
            return "O evento é hoje";
        }
    }

    public String getName()
    {
        return name;
    }

    public String getStartDateFormated()
    {
        return simpleStartDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public String getEndDateFormated()
    {
        return simpleEndDate;
    }
}