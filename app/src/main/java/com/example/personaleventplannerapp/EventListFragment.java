package com.example.personaleventplannerapp;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class EventListFragment extends Fragment {
    private EventAdapter adapter;
    private AppDatabase db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_list, container, false);
        RecyclerView rv = v.findViewById(R.id.rv_events);
        db = AppDatabase.getInstance(getContext());

        adapter = new EventAdapter(e -> {
            Bundle b = new Bundle();
            b.putInt("eventId", e.id);
            Navigation.findNavController(v).navigate(R.id.action_eventListFragment_to_addEventFragment, b);
        });

        rv.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setList(db.eventDao().getAll());
    }
}
