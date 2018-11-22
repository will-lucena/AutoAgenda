package com.example.will.projetofinal;

import java.util.Calendar;
import java.util.Date;

public class Event {

    public String nome;
    public Date date;

    public Event()
    {
        nome = "default event";
        date = Calendar.getInstance().getTime();
    }

    public Event(String nome, Date date)
    {
        this.nome = nome;
        this.date = date;
    }

    public Event(String nome, Long date)
    {
        this.nome = nome;
        this.date = new Date(date);
    }

    @Override
    public String toString() {
        return nome + "\n" + date;
    }
}
