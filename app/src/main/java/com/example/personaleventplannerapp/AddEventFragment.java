package com.example.personaleventplannerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class AddEventFragment extends Fragment {
    private TextInputEditText etTitle, etLoc, etDate, etTime;
    private Spinner spCat;
    private Button btnSave, btnDel;
    private AppDatabase db;
    private int eventId = -1;
    private Event currentEvent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);

        etTitle = v.findViewById(R.id.et_title);
        spCat = v.findViewById(R.id.spinner_category);
        etLoc = v.findViewById(R.id.et_location);
        etDate = v.findViewById(R.id.et_date);
        etTime = v.findViewById(R.id.et_time);
        btnSave = v.findViewById(R.id.btn_save);
        btnDel = v.findViewById(R.id.btn_delete);

        db = AppDatabase.getInstance(requireContext());

        if (getArguments() != null) {
            eventId = getArguments().getInt("eventId", -1);
            if (eventId != -1) {
                currentEvent = db.eventDao().getById(eventId);
                if (currentEvent != null) {
                    etTitle.setText(currentEvent.title);
                    etLoc.setText(currentEvent.location);
                    etDate.setText(currentEvent.date);
                    etTime.setText(currentEvent.time);
                    btnDel.setVisibility(View.VISIBLE);
                    btnSave.setText("Update Event");
                }
            }
        }

        etDate.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog dp = new DatePickerDialog(requireContext(), (d, y, m, day) -> 
                etDate.setText(y + "-" + (m + 1) + "-" + day), 
                c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            // Requirement: Prevent picking a date in the past
            dp.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dp.show();
        });

        etTime.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(requireContext(), (t, h, min) -> 
                etTime.setText(h + ":" + min), 
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String date = etDate.getText().toString().trim();

            // Requirement: Ensure Title and Date are not empty
            if (title.isEmpty()) {
                Toast.makeText(getContext(), "Title is required", Toast.LENGTH_SHORT).show();
                return;
            }
            if (date.isEmpty()) {
                Toast.makeText(getContext(), "Date is required", Toast.LENGTH_SHORT).show();
                return;
            }

            Event e = new Event(title, spCat.getSelectedItem().toString(), etLoc.getText().toString(), date, etTime.getText().toString());
            
            if (eventId == -1) {
                db.eventDao().insert(e);
                Toast.makeText(getContext(), "Event Created", Toast.LENGTH_SHORT).show();
            } else {
                e.id = eventId;
                db.eventDao().update(e);
                Toast.makeText(getContext(), "Event Updated", Toast.LENGTH_SHORT).show();
            }
            Navigation.findNavController(v).popBackStack();
        });

        btnDel.setOnClickListener(view -> {
            if (currentEvent != null) {
                db.eventDao().delete(currentEvent);
                Toast.makeText(getContext(), "Event Deleted", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).popBackStack();
            }
        });

        return v;
    }
}
