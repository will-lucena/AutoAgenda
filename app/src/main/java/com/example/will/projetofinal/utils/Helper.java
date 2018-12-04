package com.example.will.projetofinal.utils;

import android.util.Log;

import com.example.will.projetofinal.models.BaseEvent;
import com.example.will.projetofinal.models.Event;
import com.example.will.projetofinal.models.Place;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Helper {

    public static BaseEvent buildEvent(JSONObject json, EventType type) throws JSONException {
        switch (type)
        {
            case Event:
                return facebookEvent(json);
        }
        return null;
    }

    private static Event facebookEvent(JSONObject json) throws JSONException {
        String eventName = json.getString("name");
        String startDate = json.getString("start_time");
        String endDate = json.getString("end_time");

        JSONObject place = json.getJSONObject("place");

        String placeName = place.getString("name");

        place = place.getJSONObject("location");

        String city = place.getString("city");
        String state = place.getString("state");
        String country = place.getString("country");
        String street = place.getString("street");

        double longitude = place.getDouble("longitude");
        double latitude = place.getDouble("latitude");

        Place location = new Place(placeName, city, state, country, street, longitude, latitude);

        String[] date = startDate.split("T")[0].split("-");
        Event event = new Event(eventName, startDate, endDate, location);
        Date eventDate = BaseEvent.toDate(date[0], date[1], date[2]);

        return event;
    }
}
