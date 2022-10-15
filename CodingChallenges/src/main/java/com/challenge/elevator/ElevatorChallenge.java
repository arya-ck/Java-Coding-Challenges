package com.challenge.elevator;

import java.util.Scanner;

/**
 * An elevator can move up and down visiting floors to pick people up and drop people off along the way.
 * Think you can simulate an elevator? Most of the time in software development, you won't be creating the program from scratch.
 * So in this challenge, you'll be tasked to simulate an elevator.
 */
public class ElevatorChallenge {

    static void automaticElevator() throws InterruptedException {
        Elevator elevator = new Elevator();
        elevator.lunchtimeElevatorRush();
        elevator.start();
    }

    static void manualElevator() throws InterruptedException {
        Elevator elevator = new Elevator();

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter a starting floor 0 - 10");
//        int start = sc.nextInt();
//        System.out.println("Enter a destination floor 0 - 10");
//        int end = sc.nextInt();

//        elevator.callElevator(start, end);

        elevator.callElevator(0, 4);
        elevator.start();

        elevator.callElevator(0, 5);
        elevator.start();

        elevator.callElevator(1, 5);
        elevator.start();

        elevator.callElevator(4, 10);
        elevator.start();

        elevator.callElevator(10, 7);
        elevator.start();
    }

    public static void main(String[] args) throws InterruptedException {
        manualElevator();
    }

}
