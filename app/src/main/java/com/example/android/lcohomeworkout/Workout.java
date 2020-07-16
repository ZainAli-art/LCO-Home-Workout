package com.example.android.lcohomeworkout;

import java.util.*;

public abstract class Workout {
    public static long PUSH_UPS_DURATION = 5000;
    public static long CRUNCHES_DURATION = 5000;
    public static long BENCH_PRESS_DURATION = 5000;
    public static long BARBELL_CURL_DURATION = 5000;
    public static long SHOULDER_PRESS_DURATION = 5000;
    public static long LEG_RAISE_PRESS_DURATION = 5000;
    public static long LUNGES_DURATION = 5000;
    public static long PLANK_DURATION = 5000;
    public static long SQUAT_DURATION = 5000;
    public static long REST_DURATION = 5000;
    public static long WORKOUT_FINISHED_DURATION = 5000;

    private long duration;
    private String name;
    private int imageResId;

    public Workout(long duration, String name, int imageResId) {
        this.duration = duration;
        this.name = name;
        this.imageResId = imageResId;
    }

    public long getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public static List<Workout> getRandomSession() {
        List<Workout> workoutSession = new ArrayList<>();

        Map<Integer, Workout> workoutMap = new HashMap<>();
        workoutMap.put(0, new PushUps());
        workoutMap.put(1, new Crunches());
        workoutMap.put(2, new BenchPress());
        workoutMap.put(3, new BarbellCurl());
        workoutMap.put(4, new ShoulderPress());
        workoutMap.put(5, new LegRaise());
        workoutMap.put(6, new Lunges());
        workoutMap.put(7, new Plank());
        workoutMap.put(8, new Squat());

        Random rand = new Random();
        int maxCycles = rand.nextInt(5);
        int noOfWorkouts = 5;
        int maxSets = rand.nextInt(5);

        for (int cycles = 0; cycles < maxCycles; cycles++) {     // no of cycles: 1-5
            for (int workouts = 0; workouts < noOfWorkouts; workouts++) {       // no of workouts per cycle: 5 (fixed)
                Workout workout = workoutMap.get(rand.nextInt(workoutMap.size()));
                for (int sets = 0; sets < maxSets; sets++) {    // no of sets per workout: 1-5
                    workoutSession.add(workout);
                    workoutSession.add(new Rest());
                }
            }
        }
        workoutSession.add(new WorkoutFinished());

        return workoutSession;
    }

    public static class SessionBuilder {
        List<Workout> workoutSession;
        private int cycles;

        public SessionBuilder(int cycles) {
            this.cycles = cycles;
            workoutSession = new ArrayList<>();
        }

        public SessionBuilder addPushUpsOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new PushUps());
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

        public SessionBuilder addSLegRaiseOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new LegRaise());
                addRest();
            }
            return this;
        }

        public SessionBuilder addLungesOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new Lunges());
                addRest();
            }
            return this;
        }

        public SessionBuilder addPlankOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new Plank());
                addRest();
            }
            return this;
        }

        public SessionBuilder addSquatOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(new Squat());
                addRest();
            }
            return this;
        }

        protected void addRest() {
            workoutSession.add(new Rest());
        }

        public List<Workout> build() {
            List<Workout> temp = workoutSession;
            workoutSession = new ArrayList<>();
            for (int i = 0; i < cycles; i++)
                for (Workout workout : temp)
                    workoutSession.add(workout);

            workoutSession.add(new WorkoutFinished());
            return workoutSession;
        }
    }

    public static class PushUps extends Workout {
        public PushUps() {
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

    public static class LegRaise extends Workout {
        public LegRaise() {
            super(LEG_RAISE_PRESS_DURATION, "Leg Raise", R.drawable.leg_raise);
        }
    }

    public static class Lunges extends Workout {
        public Lunges() {
            super(LUNGES_DURATION, "Lunges", R.drawable.lunges);
        }
    }

    public static class Plank extends Workout {
        public Plank() {
            super(PLANK_DURATION, "Plank", R.drawable.plank);
        }
    }

    public static class Squat extends Workout {
        public Squat() {
            super(SQUAT_DURATION, "Squat", R.drawable.squat);
        }
    }

    public static class Rest extends Workout {
        public Rest() {
            super(REST_DURATION, "Rest", R.drawable.rest);
        }
    }

    public static class WorkoutFinished extends Workout {
        public WorkoutFinished() {
            super(WORKOUT_FINISHED_DURATION, "Workout Finished", R.drawable.workout_finished);
        }
    }
}
