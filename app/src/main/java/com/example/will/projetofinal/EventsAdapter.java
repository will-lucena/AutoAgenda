package com.example.will.projetofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventsAdapter extends ArrayAdapter<BaseEvent> {


    private Activity context;
    private List<BaseEvent> list;
    private int iLayout;

    public EventsAdapter(Activity context, int layout, List<BaseEvent> userList) {
        super(context, layout, userList);
        this.context = context;
        this.list = userList;
        this.iLayout = layout;
    }

    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView nameLabel;
        public TextView timeLabel;
        public TextView eventTypeLabel;
        public ImageView eventImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(iLayout, null, true);
            viewHolder = new ViewHolder();
            viewHolder.nameLabel = rowView.findViewById(R.id.eventName);
            viewHolder.timeLabel = rowView.findViewById(R.id.timeUntil);
            viewHolder.eventTypeLabel = rowView.findViewById(R.id.eventType);
            viewHolder.eventImage = rowView.findViewById(R.id.eventImage);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        BaseEvent item = list.get(position);
        viewHolder.nameLabel.setText(item.name);
        viewHolder.timeLabel.setText(item.timeUntil());
        viewHolder.eventTypeLabel.setText(item.getEventType().toString());

        return rowView;
    }

}
