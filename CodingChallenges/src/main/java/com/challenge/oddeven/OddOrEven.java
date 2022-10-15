package com.challenge.oddeven;

import java.util.Scanner;

/**
 * A number is even if the number is divisible by two, meaning when divided by two there's no remainder.
 * In this challenge, you'll create a function that verifies if a number is even.
 * If the number is even, the function should return true. If odd, the function should return false.
 * The input will always be an integer.
 */
public class OddOrEven {
    public static boolean isEven(int n){
        if(n % 2 == 0 ){    // divisible by 2
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = sc.nextInt();

        if(isEven(n)){
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }
    }
}
