package com.example.android.lcohomeworkout;

import java.util.ArrayList;
import java.util.List;

public abstract class Workout {
    public static long PUSH_UPS_DURATION = 10000;
    public static long CRUNCHES_DURATION = 10000;
    public static long BENCH_PRESS_DURATION = 10000;
    public static long BARBELL_CURL_DURATION = 10000;
    public static long SHOULDER_PRESS_DURATION = 10000;
    public static long REST_DURATION = 10000;
    public static long WORKOUT_FINISHED_DURATION = 10000;

    private long duration;
    private String name;
    private int resId;

    public Workout(long duration, String name, int resId) {
        this.duration = duration;
        this.name = name;
        this.resId = resId;
    }

    public long getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public int getResId() {
        return resId;
    }

    public static class SessionBuilder {
        List<Workout> workoutSession;

        public SessionBuilder() {
            workoutSession = new ArrayList<>();
        }

        public SessionBuilder addPushUpsOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new PushsUps());
                addRest();
            }
            return this;
        }

        public SessionBuilder addCrunchesOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new Crunches());
                addRest();
            }
            return this;
        }

        public SessionBuilder addBenchPressOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new BenchPress());
                addRest();
            }
            return this;
        }

        public SessionBuilder addBarbellCurlOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new BarbellCurl());
                addRest();
            }
            return this;
        }

        public SessionBuilder addShoulderPressOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new ShoulderPress());
                addRest();
            }
            return this;
        }

        protected void addRest() {
            workoutSession.add(new Rest());
        }

        public List<Workout> build() {
            workoutSession.add(new WorkoutFinished());
            return workoutSession;
        }
    }

    public static class PushsUps extends Workout {
        public PushsUps() {
            super(PUSH_UPS_DURATION, "Push Ups", R.drawable.push_ups);
        }
    }

    public static class Crunches extends Workout {
        public Crunches() {
            super(CRUNCHES_DURATION, "Crunches", R.drawable.crunches);
        }
    }

    public static class BenchPress extends Workout {
        public BenchPress() {
            super(BENCH_PRESS_DURATION, "Bench Press", R.drawable.bench_press);
        }
    }

    public static class BarbellCurl extends Workout {
        public BarbellCurl() {
            super(BARBELL_CURL_DURATION, "Barbell Curl", R.drawable.barbell_curl);
        }
    }

    public static class ShoulderPress extends Workout {
        public ShoulderPress() {
            super(SHOULDER_PRESS_DURATION, "Shoulder Press", R.drawable.shoulder_press);
        }
    }

    public static class Rest extends Workout {
        public Rest() {
            super(REST_DURATION, "Rest", R.drawable.rest);
        }
    }

    public static class WorkoutFinished extends Workout {
        public WorkoutFinished() {
            super(WORKOUT_FINISHED_DURATION, "Workout Finished", R.drawable.rest);
        }
    }
}
