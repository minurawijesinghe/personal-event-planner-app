package com.example.personaleventplannerapp;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> list = new ArrayList<>();
    private OnItemClick listener;

    public interface OnItemClick {
        void onClick(Event event);
    }

    public EventAdapter(OnItemClick listener) {
        this.listener = listener;
    }

    public void setList(List<Event> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event e = list.get(position);
        holder.t1.setText(e.title);
        holder.t2.setText(e.category);
        holder.t3.setText(e.date + " " + e.time);
        holder.itemView.setOnClickListener(v -> listener.onClick(e));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3;
        public EventViewHolder(@NonNull View v) {
            super(v);
            t1 = v.findViewById(R.id.tv_title);
            t2 = v.findViewById(R.id.tv_category);
            t3 = v.findViewById(R.id.tv_date_time);
        }
    }
}
