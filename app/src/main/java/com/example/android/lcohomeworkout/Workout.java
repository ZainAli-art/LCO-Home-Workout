package com.example.android.lcohomeworkout;

import java.util.*;

public abstract class Workout {
    public static final String MUSIC_KEY = "com.example.android.lcohomeworkout.Workout.MUSIC_KEY";

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
    private Integer imageResId;
    private Integer musicResId;

    public Workout(long duration, String name, Integer imageResId, Integer musicResId) {
        this.duration = duration;
        this.name = name;
        this.imageResId = imageResId;
        this.musicResId = musicResId;
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

    public Integer getMusicResId() {
        return musicResId;
    }

    public static List<Workout> getRandomSession() {
        List<Workout> workoutSession = new ArrayList<>();

        Map<Integer, Workout> workoutMap = new HashMap<>();
        workoutMap.put(0, PushUps.getInstance());
        workoutMap.put(1, Crunches.getInstance());
        workoutMap.put(2, BenchPress.getInstance());
        workoutMap.put(3, BarbellCurl.getInstance());
        workoutMap.put(4, ShoulderPress.getInstance());
        workoutMap.put(5, LegRaise.getInstance());
        workoutMap.put(6, Lunges.getInstance());
        workoutMap.put(7, Plank.getInstance());
        workoutMap.put(8, Squat.getInstance());

        Random rand = new Random();
        int maxCycles = rand.nextInt(4);
        int noOfWorkouts = 5;
        int maxSets = rand.nextInt(4);

        for (int cycles = 0; cycles < 1 + maxCycles; cycles++) {     // no of cycles: 1-5
            Set<Workout> added = new HashSet<>();
            for (int workouts = 0; workouts < noOfWorkouts; workouts++) {       // no of workouts per cycle: 5 (fixed)
                Workout workout;
                do {
                    workout = workoutMap.get(rand.nextInt(workoutMap.size()));
                } while (added.contains(workout));

                for (int sets = 0; sets < 1 + maxSets; sets++) {    // no of sets per workout: 1-5
                    workoutSession.add(workout);
                    workoutSession.add(new Rest());
                }
                added.add(workout);
            }
        }
        workoutSession.add(WorkoutFinished.getInstance());

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
                workoutSession.add(PushUps.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addCrunchesOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(Crunches.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addBenchPressOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(BenchPress.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addBarbellCurlOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(BarbellCurl.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addShoulderPressOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(ShoulderPress.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addSLegRaiseOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(LegRaise.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addLungesOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(Lunges.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addPlankOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(Plank.getInstance());
                addRest();
            }
            return this;
        }

        public SessionBuilder addSquatOfSets(int sets) {
            for (int i = 0; i < sets; i++) {
                workoutSession.add(Squat.getInstance());
                addRest();
            }
            return this;
        }

        protected void addRest() {
            workoutSession.add(Rest.getInstance());
        }

        public List<Workout> build() {
            List<Workout> temp = workoutSession;
            workoutSession = new ArrayList<>();
            for (int i = 0; i < cycles; i++)
                for (Workout workout : temp)
                    workoutSession.add(workout);

            workoutSession.add(WorkoutFinished.getInstance());
            return workoutSession;
        }
    }

    public static class PushUps extends Workout {
        private static PushUps INSTANCE;

        private PushUps() {
            super(PUSH_UPS_DURATION, "Push Ups", R.drawable.push_ups, R.raw._50_up_from_the_ashes);
        }

        public static PushUps getInstance() {
            if (INSTANCE == null)
                INSTANCE = new PushUps();
            return INSTANCE;
        }
    }

    public static class Crunches extends Workout {
        private static Crunches INSTANCE;

        private Crunches() {
            super(CRUNCHES_DURATION, "Crunches", R.drawable.crunches, R.raw.song_34_pop);
        }

        public static Crunches getInstance() {
            if (INSTANCE == null)
                INSTANCE = new Crunches();
            return INSTANCE;
        }
    }

    public static class BenchPress extends Workout {
        private static BenchPress INSTANCE;

        private BenchPress() {
            super(BENCH_PRESS_DURATION, "Bench Press", R.drawable.bench_press, R.raw.song_14_rock);
        }

        public static BenchPress getInstance() {
            if (INSTANCE == null)
                INSTANCE = new BenchPress();
            return INSTANCE;
        }
    }

    public static class BarbellCurl extends Workout {
        private static BarbellCurl INSTANCE;

        private BarbellCurl() {
            super(BARBELL_CURL_DURATION, "Barbell Curl", R.drawable.barbell_curl, R.raw.song_18_rock);
        }

        public static BarbellCurl getInstance() {
            if (INSTANCE == null)
                INSTANCE = new BarbellCurl();
            return INSTANCE;
        }
    }

    public static class ShoulderPress extends Workout {
        private static ShoulderPress INSTANCE;

        private ShoulderPress() {
            super(SHOULDER_PRESS_DURATION, "Shoulder Press", R.drawable.shoulder_press, R.raw.song_34_pop);
        }

        public static ShoulderPress getInstance() {
            if (INSTANCE == null)
                INSTANCE = new ShoulderPress();
            return INSTANCE;
        }
    }

    public static class LegRaise extends Workout {
        private static LegRaise INSTANCE;

        private LegRaise() {
            super(LEG_RAISE_PRESS_DURATION, "Leg Raise", R.drawable.leg_raise, R.raw._50_up_from_the_ashes);
        }

        public static LegRaise getInstance() {
            if (INSTANCE == null)
                INSTANCE = new LegRaise();
            return INSTANCE;
        }
    }

    public static class Lunges extends Workout {
        private static Lunges INSTANCE;

        private Lunges() {
            super(LUNGES_DURATION, "Lunges", R.drawable.lunges, R.raw.song_18_rock);
        }

        public static Lunges getInstance() {
            if (INSTANCE == null)
                INSTANCE = new Lunges();
            return INSTANCE;
        }
    }

    public static class Plank extends Workout {
        private static Plank INSTANCE;

        public Plank() {
            super(PLANK_DURATION, "Plank", R.drawable.plank, R.raw.song_14_rock);
        }

        private static Plank getInstance() {
            if (INSTANCE == null)
                INSTANCE = new Plank();
            return INSTANCE;
        }
    }

    public static class Squat extends Workout {
        private static Squat INSTANCE;

        private Squat() {
            super(SQUAT_DURATION, "Squat", R.drawable.squat, R.raw.song_34_pop);
        }

        public static Squat getInstance() {
            if (INSTANCE == null)
                INSTANCE = new Squat();
            return INSTANCE;
        }
    }

    public static class Rest extends Workout {
        private static Rest INSTANCE;

        private Rest() {
            super(REST_DURATION, "Rest", R.drawable.rest, null);
        }

        public static Rest getInstance() {
            if (INSTANCE == null)
                INSTANCE = new Rest();
            return INSTANCE;
        }
    }

    public static class WorkoutFinished extends Workout {
        private static WorkoutFinished INSTANCE;

        private WorkoutFinished() {
            super(WORKOUT_FINISHED_DURATION, "Workout Finished", R.drawable.workout_finished, null);
        }

        public static WorkoutFinished getInstance() {
            if (INSTANCE == null)
                INSTANCE = new WorkoutFinished();
            return INSTANCE;
        }
    }
}
