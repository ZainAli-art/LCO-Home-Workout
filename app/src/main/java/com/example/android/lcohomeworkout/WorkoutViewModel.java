package com.example.android.lcohomeworkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WorkoutViewModel extends ViewModel {
    private MutableLiveData<Workout> curWorkout;
    private List<Workout> workoutSession;

    public LiveData<Workout> getCurWorkout() {
        return curWorkout;
    }

    public void setWorkoutSession(List<Workout> workoutSession) {
        this.workoutSession = workoutSession;
    }
}
