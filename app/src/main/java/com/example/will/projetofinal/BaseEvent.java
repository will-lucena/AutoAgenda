package com.example.will.projetofinal;

import java.util.Calendar;
import java.util.Date;

public abstract class BaseEvent
{
    protected String name;
    protected Date startDate;
    protected Date endDate;

    protected String simpleStartDate;
    protected String simpleEndDate;

    protected BaseEvent()
    {
        name = "default event";
        startDate = Calendar.getInstance().getTime();
        endDate = Calendar.getInstance().getTime();
    }

    protected BaseEvent(String name, Date date)
    {
        this.name = name;
        this.startDate = date;
        this.endDate = date;
    }

    protected BaseEvent(String name, Date startDate, Date endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected BaseEvent(String name, String[] date)
    {
        this.name = name;
        Date d = toDate(date[0], date[1], date[2]);
        this.startDate = d;
        this.endDate = d;

        simpleStartDate = String.format("%s/%s/%s", date[2], date[1], date[0]);
        simpleEndDate = simpleStartDate;
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
}
