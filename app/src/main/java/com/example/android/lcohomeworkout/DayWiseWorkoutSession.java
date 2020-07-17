package com.example.android.lcohomeworkout;

import java.util.List;

public class DayWiseWorkoutSession {
    private static List<Workout> MONDAY_SESSION;
    private static List<Workout> TUESDAY_SESSION;
    private static List<Workout> WEDNESDAY_SESSION;
    private static List<Workout> THURSDAY_SESSION;
    private static List<Workout> FRIDAY_SESSION;
    private static List<Workout> SATURDAY_SESSION;
    private static List<Workout> SUNDAY_SESSION;

    public static List<Workout> getMondaySession() {
        if (MONDAY_SESSION == null) {
            MONDAY_SESSION = new Workout.SessionBuilder(3)
                    .addBenchPressOfSets(3)
                    .addShoulderPressOfSets(3)
                    .addPushUpsOfSets(3)
                    .build();
        }
        return MONDAY_SESSION;
    }

    public static List<Workout> getTuesdaySession() {
        if (TUESDAY_SESSION == null) {
            TUESDAY_SESSION = new Workout.SessionBuilder(2)
                    .addPlankOfSets(3)
                    .addCrunchesOfSets(2)
                    .addSLegRaiseOfSets(2)
                    .build();
        }
        return TUESDAY_SESSION;
    }

    public static List<Workout> getWednesdaySession() {
        if (WEDNESDAY_SESSION == null) {
            WEDNESDAY_SESSION = new Workout.SessionBuilder(3)
                    .addPlankOfSets(2)
                    .addSquatOfSets(3)
                    .addLungesOfSets(3)
                    .addShoulderPressOfSets(3)
                    .build();
        }
        return WEDNESDAY_SESSION;
    }

    public static List<Workout> getThursdaySession() {
        if (THURSDAY_SESSION == null) {
            THURSDAY_SESSION = new Workout.SessionBuilder(3)
                    .addPushUpsOfSets(3)
                    .addBarbellCurlOfSets(3)
                    .build();
        }
        return THURSDAY_SESSION;
    }

    public static List<Workout> getFridaySession() {
        if (FRIDAY_SESSION == null) {
            FRIDAY_SESSION = new Workout.SessionBuilder(3)
                    .addBenchPressOfSets(3)
                    .addPushUpsOfSets(3)
                    .addLungesOfSets(3)
                    .build();
        }
        return FRIDAY_SESSION;
    }

    public static List<Workout> getSaturdaySession() {
        if (SATURDAY_SESSION == null) {
            SATURDAY_SESSION = new Workout.SessionBuilder(2)
                    .addShoulderPressOfSets(2)
                    .addSquatOfSets(2)
                    .addBarbellCurlOfSets(2)
                    .build();
        }
        return SATURDAY_SESSION;
    }

    public static List<Workout> getSundaySession() {
        if (SUNDAY_SESSION == null) {
            SUNDAY_SESSION = new Workout.SessionBuilder(3)
                    .addCrunchesOfSets(3)
                    .addSLegRaiseOfSets(3)
                    .build();
        }
        return SUNDAY_SESSION;
    }
}
