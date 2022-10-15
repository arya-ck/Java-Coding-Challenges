package com.challenge.waterbill;

import java.util.Scanner;

/**
 * Most of us have to pay a water bill each month.
 * So in this challenge, you'll calculate the amount owed, given the water usage. (electronic bleeps)
 * It's time to calculate the water bill for your apartment. Your water bill consists of two parts.
 * First, there is a minimum charge of $18.84. This includes 1,496 gallons of water, or two CCFs. One CCF is equal to 748 gallons.
 * If you use more than the included two CCFs, you are charged an extra $3.90 per additional CCF.
 * This means if you use 1800 gallons of water in a given month, you will be charged the minimum $18.84, as well as an additional $3.90 for the extra volume of water used.
 */
public class WaterBill {

    public static double calculateWaterBill(double gallonsUsage) {
        final double ONE_CCF = 748;
        final double MIN_CHARGE = 18.84;
        final double ADD_CHARGE = 3.90;

        if(gallonsUsage < (2 * ONE_CCF)){   // if consumption is less than 1 CCF, base charde
            return MIN_CHARGE;
        } else {    // else base charge + cost of additional CCF
            Double surplus = Math.ceil((gallonsUsage - 2 * ONE_CCF)/ONE_CCF);
            return MIN_CHARGE + surplus * ADD_CHARGE;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many gallons of water " +
                "did you use this month?");
        double usage = scanner.nextDouble();
        System.out.println("Your water bill is " +
                calculateWaterBill(usage));
    }
}
