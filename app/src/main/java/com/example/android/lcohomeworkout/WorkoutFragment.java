package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {
    private WorkoutViewModel viewModel;
    private TextView workoutTimerText;
    private TextView workoutNameText;
    private ImageView workoutImage;

    public WorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        NavController navController = Navigation.findNavController(view);

        workoutTimerText = view.findViewById(R.id.workoutTimerText);
        workoutImage = view.findViewById(R.id.workoutImage);
        workoutNameText = view.findViewById(R.id.workoutNameText);

        viewModel.getCurTime().observe(getViewLifecycleOwner(), time -> workoutTimerText.setText(time));
        viewModel.getCurWorkout().observe(getViewLifecycleOwner(), workout -> {
            if (workout instanceof Workout.WorkoutFinished) {
                navController.navigate(R.id.action_workoutFragment_to_workoutFinishedFragment);
            } else {
                workoutImage.setImageResource(workout.getResId());
                workoutNameText.setText(workout.getName());
            }
        });
    }
}
