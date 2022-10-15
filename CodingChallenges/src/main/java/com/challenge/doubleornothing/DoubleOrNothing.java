package com.challenge.doubleornothing;

import java.util.Random;
import java.util.Scanner;

/**
 * Traditionally, double or nothing is about doubling or canceling out a debt.
 * But this challenge will work a little differently. A user will start off with 10 points and throughout the game, the user has the option to take a chance to double their points.
 * Or, walk away with their current point value.
 * If the user decides to gamble their points in the chance of doubling, the program should use randomness to decide whether the points get doubled or the user loses it all.
 */
public class DoubleOrNothing {
    private boolean isStillPlaying = true;
    private Long score = 1l;

    public boolean getIsStillPlaying() {
        return isStillPlaying;
    }

    public void setStillPlaying(boolean stillPlaying) {
        isStillPlaying = stillPlaying;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public void playGame() {
        Random random = new Random();
        Boolean decision = random.nextBoolean();

        if(decision){   // if won, double score
            score *= 2;
        } else { // else reset score to zero
            score = 0l;
        }
    }

    public void askUserToContinue(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to continue " +
                "and try to double your winnings? " +
                "Enter YES to continue");
        this.isStillPlaying = scanner.nextLine()
                .toUpperCase().equals("YES");
    }

    public static void main(String[] args) {

        DoubleOrNothing doubleOrNothing = new DoubleOrNothing();

        while(true){

            // ask confirmation
            doubleOrNothing.askUserToContinue();

            if(doubleOrNothing.getIsStillPlaying()){    // play game & update score
                doubleOrNothing.playGame();
            } else {    // end game
                break;
            }

            // display score
            System.out.println("Your Score is: " + doubleOrNothing.getScore());
        }
    }
}
