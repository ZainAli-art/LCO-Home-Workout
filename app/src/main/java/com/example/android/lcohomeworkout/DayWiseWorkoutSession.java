package com.example.android.lcohomeworkout;

import java.util.Calendar;
import java.util.List;

public class DayWiseWorkoutSession {
    private static List<Workout> mondaySession;
    private static List<Workout> tuesdaySession;
    private static List<Workout> wednesdaySession;
    private static List<Workout> thursdaySession;
    private static List<Workout> fridaySession;
    private static List<Workout> saturdaySession;
    private static List<Workout> sundaySession;

    public static List<Workout> getTodaySession() {
        List<Workout> todaySession;
        
        switch (Calendar.DAY_OF_WEEK) {
            case Calendar.MONDAY:
                todaySession = getMondaySession();
                break;
            case Calendar.TUESDAY:
                todaySession = getTuesdaySession();
                break;
            case Calendar.WEDNESDAY:
                todaySession = getWednesdaySession();
                break;
            case Calendar.THURSDAY:
                todaySession = getThursdaySession();
                break;
            case Calendar.FRIDAY:
                todaySession = getFridaySession();
                break;
            case Calendar.SATURDAY:
                todaySession = getSaturdaySession();
                break;
            case Calendar.SUNDAY:
                todaySession = getSundaySession();
                break;
            default:
                todaySession = null;
        }
        return todaySession;
    }

    public static List<Workout> getMondaySession() {
        if (mondaySession == null) {
            mondaySession = new Workout.SessionBuilder(3)
                    .addBenchPressOfSets(3)
                    .addShoulderPressOfSets(3)
                    .addPushUpsOfSets(3)
                    .build();
        }
        return mondaySession;
    }

    public static List<Workout> getTuesdaySession() {
        if (tuesdaySession == null) {
            tuesdaySession = new Workout.SessionBuilder(2)
                    .addPlankOfSets(3)
                    .addCrunchesOfSets(2)
                    .addSLegRaiseOfSets(2)
                    .build();
        }
        return tuesdaySession;
    }

    public static List<Workout> getWednesdaySession() {
        if (wednesdaySession == null) {
            wednesdaySession = new Workout.SessionBuilder(3)
                    .addPlankOfSets(2)
                    .addSquatOfSets(3)
                    .addLungesOfSets(3)
                    .addShoulderPressOfSets(3)
                    .build();
        }
        return wednesdaySession;
    }

    public static List<Workout> getThursdaySession() {
        if (thursdaySession == null) {
            thursdaySession = new Workout.SessionBuilder(3)
                    .addPushUpsOfSets(3)
                    .addBarbellCurlOfSets(3)
                    .build();
        }
        return thursdaySession;
    }

    public static List<Workout> getFridaySession() {
        if (fridaySession == null) {
            fridaySession = new Workout.SessionBuilder(3)
                    .addBenchPressOfSets(3)
                    .addPushUpsOfSets(3)
                    .addLungesOfSets(3)
                    .build();
        }
        return fridaySession;
    }

    public static List<Workout> getSaturdaySession() {
        if (saturdaySession == null) {
            saturdaySession = new Workout.SessionBuilder(2)
                    .addShoulderPressOfSets(2)
                    .addSquatOfSets(2)
                    .addBarbellCurlOfSets(2)
                    .build();
        }
        return saturdaySession;
    }

    public static List<Workout> getSundaySession() {
        if (sundaySession == null) {
            sundaySession = new Workout.SessionBuilder(3)
                    .addCrunchesOfSets(3)
                    .addSLegRaiseOfSets(3)
                    .build();
        }
        return sundaySession;
    }
}
