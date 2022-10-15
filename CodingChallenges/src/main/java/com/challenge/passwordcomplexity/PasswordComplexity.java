package com.challenge.passwordcomplexity;

import java.util.Scanner;

/**
 * In order for a password to be secure enough, it must have certain properties.
 * So in this challenge, you'll create a function that verifies password complexity.
 * In order for a password to be valid, it must have at least six characters, one upper case letter, one lowercase letter, and one number.
 * The function will take a string as input and output a Boolean, representing whether or not the password is complex enough.
 */
public class PasswordComplexity {

    public static boolean hasUppercase(String password) {
        // check if there exists at least one uppercase
        return password.chars().anyMatch(Character::isUpperCase);
    }

    public static boolean hasLowercase(String password) {
        // check if there exists at least one lowercase
        return password.chars().anyMatch(Character::isLowerCase);
    }

    public static boolean hasNumber(String password) {
        // check if there exists at least one number
        return password.chars().anyMatch(Character::isDigit);
    }

    public static boolean hasLengthN(String password, int length) {
        // check the length
        return password.length() >= length;
    }

    public static boolean isPasswordComplex(String password) {
        // validate all conditions
        return hasLengthN(password, 6)
                && hasUppercase(password)
                && hasLowercase(password)
                && hasNumber(password);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a password: ");
        String userInput = scanner.nextLine();
        System.out.println("Is the password complex? " +
                isPasswordComplex(userInput));
    }
}
