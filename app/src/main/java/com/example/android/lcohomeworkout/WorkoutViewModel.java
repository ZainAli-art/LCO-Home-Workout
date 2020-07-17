package com.example.android.lcohomeworkout;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WorkoutViewModel extends ViewModel {
    private static final String TAG = "WorkoutViewModel";

    private MutableLiveData<Workout> curWorkout = new MutableLiveData<>();
    private MutableLiveData<String> curTime = new MutableLiveData<>();
    private int curWorkoutIndex;
    private List<Workout> workoutSession;
    private CountDownTimer timer;
    private long millisRemaining;

    private ServiceConnection connection;
    private MutableLiveData<MediaService.LocalBinder> mBinder = new MutableLiveData<>();

    public LiveData<Workout> getCurWorkout() {
        return curWorkout;
    }

    public LiveData<String> getCurTime() {
        return curTime;
    }

    public LiveData<MediaService.LocalBinder> getBinder() {
        return mBinder;
    }

    public ServiceConnection getConnection() {
        if (connection == null) {
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MediaService.LocalBinder binder = (MediaService.LocalBinder) service;
                    mBinder.postValue(binder);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mBinder.postValue(null);
                }
            };
        }
        return connection;
    }

    public void setWorkoutSession(List<Workout> workoutSession) {
        curWorkoutIndex = 0;
        this.workoutSession = workoutSession;
    }

    public void moveToNextWorkout() {
        Log.d(TAG, "moveToNextWorkout: called");

        if (curWorkoutIndex < workoutSession.size())
            curWorkout.setValue(workoutSession.get(curWorkoutIndex++));
        startTimer(curWorkout.getValue().getDuration());
    }

    public void resumeTimer() {
        startTimer(millisRemaining);
    }

    private void startTimer(long duration) {
        Log.d(TAG, "resumeTimer: timer initialized with duration " + duration);
        if (timer != null)
            pauseTimer();

        millisRemaining = duration;
        timer = new CountDownTimer(millisRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisRemaining = millisUntilFinished;
                curTime.setValue(durationToTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                moveToNextWorkout();
            }
        };
        timer.start();
    }

    public void pauseTimer() {
        timer.cancel();
        timer = null;
        Log.d(TAG, "pauseTimer: timer cancelled");
    }

    private String durationToTime(long duration) {
        int minutes = (int) (duration / 60000);
        int seconds = (int) ((duration / 1000) % 60);
        return (minutes > 9 ? minutes : "0" + minutes) +
                ":" + (seconds > 9 ? seconds : "0" + seconds);
    }
}
