package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.Button;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class WorkoutFinishedFragment extends Fragment implements View.OnClickListener {
    private Button finishWorkoutButton;
    private NavController navController;

    public WorkoutFinishedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_finished, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        finishWorkoutButton = view.findViewById(R.id.finishWorkoutButton);
        finishWorkoutButton.setOnClickListener(this);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new CustomOnBackPressed(true));
    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.action_workoutFinishedFragment_to_chooseWorkoutFragment);
    }

    private class CustomOnBackPressed extends OnBackPressedCallback {
        public CustomOnBackPressed(boolean enabled) {
            super(enabled);
        }

        @Override
        public void handleOnBackPressed() {
            navController.navigate(R.id.action_workoutFinishedFragment_to_chooseWorkoutFragment);
        }
    }

}
