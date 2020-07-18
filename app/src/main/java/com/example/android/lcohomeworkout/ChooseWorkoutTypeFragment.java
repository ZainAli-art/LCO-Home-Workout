package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ChooseWorkoutTypeFragment extends Fragment implements View.OnClickListener {
    private NavController navController;
    private WorkoutViewModel viewModel;

    public ChooseWorkoutTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_workout_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        Button customWorkoutButton = view.findViewById(R.id.customWorkoutButton);
        Button randomWorkoutButton = view.findViewById(R.id.randomWorkoutButton);
        Button dayWiseWorkoutButton = view.findViewById(R.id.dayWiseWorkoutButton);

        customWorkoutButton.setOnClickListener(this);
        randomWorkoutButton.setOnClickListener(this);
        dayWiseWorkoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {       // Acts as Factory Method
        switch (v.getId()) {
            case R.id.customWorkoutButton:
                navigateToCustomWorkout();
                break;
            case R.id.randomWorkoutButton:
                navigateToRandomWorkout();
                break;
            case R.id.dayWiseWorkoutButton:
                navigateToDayWiseWorkout();
                break;
            default:
                Toast.makeText(getContext(), "No Functionality added yet.", Toast.LENGTH_SHORT).show();
        }
    }

    public void navigateToCustomWorkout() {
        navController.navigate(R.id.action_chooseWorkoutTypeFragment_to_buildWorkoutFragment);
    }

    public void navigateToRandomWorkout() {
        viewModel.setWorkoutSession(Workout.getRandomSession());
        navController.navigate(R.id.action_chooseWorkoutTypeFragment_to_workoutListFragment);
    }

    public void navigateToDayWiseWorkout() {
        viewModel.setWorkoutSession(DayWiseWorkoutSession.getTodaySession());
        navController.navigate(R.id.action_chooseWorkoutTypeFragment_to_workoutListFragment);
    }
}
