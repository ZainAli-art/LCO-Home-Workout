package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ChooseWorkoutFragment extends Fragment implements View.OnClickListener {
    private Spinner pushUpsSpinner;
    private Spinner crunchesSpinner;
    private Spinner benchPressSpinner;
    private Spinner barbellCurlSpinner;
    private Spinner shoulderPressSpinner;
    private Button startButton;

    private WorkoutViewModel viewModel;

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

        pushUpsSpinner = view.findViewById(R.id.pushUpsSpinner);
        crunchesSpinner = view.findViewById(R.id.cruchesSpinner);
        benchPressSpinner = view.findViewById(R.id.benchPressSpinner);
        barbellCurlSpinner = view.findViewById(R.id.barbellCurlSpinner);
        shoulderPressSpinner = view.findViewById(R.id.shoulderPressSpinner);
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
            viewModel.setWorkoutSession(new Workout.SessionBuilder()
                    .addPushUpsOfSets(Integer.parseInt(pushUpsSpinner.getSelectedItem().toString()))
                    .addCrunchesOfSets(Integer.parseInt(crunchesSpinner.getSelectedItem().toString()))
                    .addBenchPressOfSets(Integer.parseInt(benchPressSpinner.getSelectedItem().toString()))
                    .addBarbellCurlOfSets(Integer.parseInt(barbellCurlSpinner.getSelectedItem().toString()))
                    .addShoulderPressOfSets(Integer.parseInt(shoulderPressSpinner.getSelectedItem().toString()))
                    .build());
            viewModel.moveToNextWorkout();

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_chooseWorkoutFragment_to_workoutFragment);
        }
    }
}
