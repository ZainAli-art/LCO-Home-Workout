package com.example.android.lcohomeworkout;

import android.content.Context;
import android.content.Intent;
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

    private MediaService mService;

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
            if (workout instanceof Workout.Rest) {
                unbindMedia();
                changeWorkoutInfo(workout);
            } else if (workout instanceof Workout.WorkoutFinished) {
                navController.navigate(R.id.action_workoutFragment_to_workoutFinishedFragment);
            } else {
                changeWorkoutInfo(workout);
                bindMedia();
            }
        });
        viewModel.getBinder().observe(getViewLifecycleOwner(), (binder) -> {
            if (binder == null) {
                mService = null;
            } else {
                mService = binder.getService();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.resumeTimer();
        if (mService != null) {
            mService.playMedia();
        }
    }

    @Override
    public void onStop() {
        viewModel.pauseTimer();
        if (mService != null) {
            mService.pauseMedia();
        }
        super.onStop();
    }

    //      == helper methods ==

    public void changeWorkoutInfo(Workout workout) {
        workoutImage.setImageResource(workout.getResId());
        workoutNameText.setText(workout.getName());
    }

    public void bindMedia() {
        Intent intent = new Intent(requireActivity(), MediaService.class);
        requireActivity().bindService(intent, viewModel.getConnection(), Context.BIND_AUTO_CREATE);
    }

    public void unbindMedia() {
        if (viewModel.getBinder().getValue() != null)
            requireActivity().unbindService(viewModel.getConnection());
    }
}
