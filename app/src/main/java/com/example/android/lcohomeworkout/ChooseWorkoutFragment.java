package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;

public class ChooseWorkoutFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public ChooseWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        binding = Binding
        return inflater.inflate(R.layout.fragment_choose_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner pushUpsSpinner = view.findViewById(R.id.pushUpsSpinner);
        Spinner crunchesSpinner = view.findViewById(R.id.cruchesSpinner);
        Spinner benchPressSpinner = view.findViewById(R.id.benchPressSpinner);
        Spinner barbellCurlSpinner = view.findViewById(R.id.barbellCurlSpinner);
        Spinner shoulderPressSpinner = view.findViewById(R.id.shoulderPressSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sets_spinner_items, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pushUpsSpinner.setAdapter(adapter);
        crunchesSpinner.setAdapter(adapter);
        benchPressSpinner.setAdapter(adapter);
        barbellCurlSpinner.setAdapter(adapter);
        shoulderPressSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
