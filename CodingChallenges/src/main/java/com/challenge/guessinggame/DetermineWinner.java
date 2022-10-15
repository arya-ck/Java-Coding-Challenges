package com.challenge.guessinggame;

import java.util.List;

/**
 * It's the holidays, your family's in town and you just played a few rounds of a guessing game.
 * Can you write a program that identifies the winner and also prints out the final scores in order?
 * The game had a series of rounds and during each round, a given player and their partner got a series of points.
 * Given the team results from each round, your challenge is to write a program that automatically determines the overall winner and prints out the final score results in descending order.
 */
public class DetermineWinner {

    public static void main(String[] args) {

        Team team1 = new Team("Sally", "Roger");
        Team team2 = new Team("Eric", "Rebecca");
        Team team3 = new Team("Tony", "Shannon");

        List<Team> teams= List.of(team1, team2, team3);
        int numberOfRounds = 4;

        TeamUtils.generateTeamsScores(teams, numberOfRounds);

        TeamUtils.revealResults(teams);
    }
}
