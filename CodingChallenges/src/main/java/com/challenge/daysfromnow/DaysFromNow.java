package com.challenge.daysfromnow;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

/**
 * All of us have goals we want to accomplish.
 * Sometimes you might have a goal that takes 100 days to accomplish.
 * So in this challenge, let's create a program that tells us what day it is 100 days from today.
 */
public class DaysFromNow {

    public static LocalDate calculateHundredDaysFromNow(LocalDate today) {

        // calculate the date 100 days from now
        Period hundredDays = Period.ofDays(100);
        return today.plus(hundredDays);
    }

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        System.out.println("100 days from now is... " +
                calculateHundredDaysFromNow(today));
    }

}
