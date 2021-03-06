package com.example.will.projetofinal.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.will.projetofinal.R;
import com.example.will.projetofinal.models.BaseEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CustomCalendar extends LinearLayout {
    
    private int currentExibitionMonth;
    private int currentExibitionYear;
    
    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT = 42;

    // default date format
    private static final String DATE_FORMAT = "MMM yyyy";

    // date format
    private String dateFormat;

    // current displayed month
    private Calendar currentDate = Calendar.getInstance();

    //event handling
    private ICallendarHandler handler;

    // internal components
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;

    // seasons' rainbow
    int[] rainbow = new int[] {
            R.color.summer,
            R.color.fall,
            R.color.winter,
            R.color.spring
    };

    // month-season association (northern hemisphere, sorry australia :)
    int[] monthSeason = new int[] {2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};

    public CustomCalendar(Context context)
    {
        super(context);
    }

    public CustomCalendar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs)
    {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomCalendar);

        try
        {
            // try to load provided date format, and fallback to default otherwise
            dateFormat = ta.getString(R.styleable.CustomCalendar_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        }
        finally
        {
            ta.recycle();
        }
    }
    private void assignUiElements()
    {
        // layout is inflated, assign local variables to components
        header = findViewById(R.id.calendar_header);
        btnPrev = findViewById(R.id.calendar_prev_button);
        btnNext = findViewById(R.id.calendar_next_button);
        txtDate = findViewById(R.id.calendar_date_display);
        grid = findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers()
    {
        // add one month and refresh UI
        btnNext.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentDate.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        // subtract one month and refresh UI
        btnPrev.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentDate.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar()
    {
        if (handler == null || handler.isEmptyList())
        {
            updateCalendar(new HashSet<Date>());
        }
        else
        {
            updateCalendar(handler.getKeys());
        }
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar(HashSet<Date> events)
    {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // fill cells
        while (cells.size() < DAYS_COUNT)
        {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

        // update title
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));

        // set header color according to current season
        int month = currentDate.get(Calendar.MONTH);
        currentExibitionMonth = month;
        currentExibitionYear = currentDate.get(Calendar.YEAR);
        int season = monthSeason[month];
        int color = rainbow[season];

        header.setBackgroundColor(getResources().getColor(color));
    }


    private class CalendarAdapter extends ArrayAdapter<Date>
    {
        // days with events
        private HashSet<Date> eventDays;

        // for view inflation
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
        {
            super(context, R.layout.custom_calendar_day, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }
        
        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            // day in question
            Date date = getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear();
            // today
            final Date today = new Date();

            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.custom_calendar_day, parent, false);

            // if this day has an event, specify event image
            //view.setBackgroundResource(0);
            if (eventDays != null)
            {
                for (final Date eventDate : eventDays)
                {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year)
                    {

                        if (handler.containsKey(eventDate))
                        {
                            for (BaseEvent event: handler.getEvents(eventDate)) {
                                switch (event.getEventType())
                                {
                                    case Event:
                                        view.findViewById(R.id.eventIcon).setVisibility(VISIBLE);
                                        break;
                                    case Exam:
                                        view.findViewById(R.id.examIcon).setVisibility(VISIBLE);
                                        break;
                                }
                            }
                        }

                        // mark this day for event
                        //view.setBackgroundResource(R.drawable.reminder);
                        view.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //TODO implementar abrir uma lista com os eventos daquele dia
                                handler.onDayClick(eventDate);
                            }
                        });
                        break;
                    }
                }
            }

            // clear styling
            TextView textView = view.findViewById(R.id.day);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setTextColor(getResources().getColor(R.color.greyed_out));
            
            if (month != currentExibitionMonth || year != currentExibitionYear - 1900)
            {
                // if this day is outside current month, grey it out
                textView.setTextColor(Color.YELLOW);
            }
            if (day == today.getDate() && month == today.getMonth() && year == today.getYear())
            {
                // if it is today, set it to blue/bold
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextColor(getResources().getColor(R.color.today));
                view.findViewById(R.id.dayOverlay).setVisibility(VISIBLE);
            }

            // set text
            textView.setText(String.valueOf(date.getDate()));

            return view;
        }
    }

    /**
     * Assign event handler to be passed needed events
     */
    public void setHandler(ICallendarHandler callendarHandler)
    {
        this.handler = callendarHandler;
    }
}
