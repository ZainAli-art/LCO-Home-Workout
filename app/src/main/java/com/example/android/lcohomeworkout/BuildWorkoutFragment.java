package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class BuildWorkoutFragment extends Fragment implements View.OnClickListener {
    private View rootView;

    private Spinner pushUpsSpinner;
    private Spinner crunchesSpinner;
    private Spinner benchPressSpinner;
    private Spinner barbellCurlSpinner;
    private Spinner shoulderPressSpinner;
    private Button startButton;
    private RadioGroup radioGroup;

    private WorkoutViewModel viewModel;

    public BuildWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        binding = Binding
        return inflater.inflate(R.layout.fragment_build_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;

        pushUpsSpinner = view.findViewById(R.id.pushUpsSpinner);
        crunchesSpinner = view.findViewById(R.id.cruchesSpinner);
        benchPressSpinner = view.findViewById(R.id.benchPressSpinner);
        barbellCurlSpinner = view.findViewById(R.id.barbellCurlSpinner);
        shoulderPressSpinner = view.findViewById(R.id.shoulderPressSpinner);
        radioGroup = view.findViewById(R.id.radioGroup);
        startButton = view.findViewById(R.id.startButton);

        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sets_spinner_items, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pushUpsSpinner.setAdapter(adapter);
        crunchesSpinner.setAdapter(adapter);
        benchPressSpinner.setAdapter(adapter);
        barbellCurlSpinner.setAdapter(adapter);
        shoulderPressSpinner.setAdapter(adapter);

        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startButton) {
            RadioButton radioButton = rootView.findViewById(radioGroup.getCheckedRadioButtonId());

            viewModel.setWorkoutSession(new Workout.SessionBuilder(Integer.parseInt(radioButton.getText().toString()))
                    .addPushUpsOfSets(Integer.parseInt(pushUpsSpinner.getSelectedItem().toString()))
                    .addCrunchesOfSets(Integer.parseInt(crunchesSpinner.getSelectedItem().toString()))
                    .addBenchPressOfSets(Integer.parseInt(benchPressSpinner.getSelectedItem().toString()))
                    .addBarbellCurlOfSets(Integer.parseInt(barbellCurlSpinner.getSelectedItem().toString()))
                    .addShoulderPressOfSets(Integer.parseInt(shoulderPressSpinner.getSelectedItem().toString()))
                    .build());

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_buildWorkoutFragment_to_workoutListFragment);
        }
    }
}
