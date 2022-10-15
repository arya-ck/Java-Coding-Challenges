package com.challenge.guessinggame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TeamUtils {
    static String nextMessage =  "Then we have... ";
    static String tieMessage = "It's a TIE!";
    static String scoreMessage = "With <x> points, it's team <p1> and <p2>!\n";
    
    public static void generateTeamsScores(List<Team> teams,
                                           int numberOfRounds) {
        Random random = new Random();
        teams.forEach(team -> {
            for (int i = 0; i < numberOfRounds; i++) {
                team.getScores().add(random.nextInt(11));
            }
        });
        //teams.forEach((team -> {System.out.println(team.getScores());}));
    }

    public static void revealResults(List<Team> teams) {

        boolean gameStarted = true;
        for(Team team: teams){
            if(team.getScores().size() == 0){
                gameStarted = false;
            }
        }
        if(!gameStarted || teams.size() == 0){
            System.out.println("The game hasn't started yet.");
            return;
        }
        List<Team> winnersList = teams.stream()
                .sorted(    // sort by desc order of total score
                        Comparator.comparing((Team team) ->
                            team.getScores().stream().reduce((sum, score) -> sum + score).get()
                        ).reversed()
                ).collect(Collectors.toList());

        Integer total;
        Team team;

        System.out.println("Now for the results, the WINNER is...");

        for(int i=0; i< winnersList.size(); i++){
            team = winnersList.get(i);
            total = team.getScores().stream().reduce((sum, score) -> sum + score).get();
            boolean printTie = false, printNext = false;

            // print tie only for teams from winner to second last team
            if(i < winnersList.size()-1){
                printTie = isScoreTie(team, winnersList.get(i+1));
            }

            // print next only for teams other than winner
            if(i > 0){
                printNext = !isScoreTie(team, winnersList.get(i-1));
            }

            if(printNext){
                printNextMessage();
            }

            if(printTie){
                printTieMessage();
            }

            // print score
            printMessage(i, team, total);

        }
    }

    static void printMessage(int i, Team team, Integer total){
        String message = scoreMessage
                .replace("<x>", total.toString())
                .replace("<p1>", team.getPlayer1())
                .replace("<p2>", team.getPlayer2());
        System.out.println(message);
    }

    static void printNextMessage(){
        System.out.println(nextMessage);
    }

    static void printTieMessage(){
        System.out.println(tieMessage);
    }

    static boolean isScoreTie(Team team1, Team team2){
        // calculate score of both teams
        Integer total1 = team1.getScores().stream().reduce((sum, score) -> sum + score).get();
        Integer total2 = team2.getScores().stream().reduce((sum, score) -> sum + score).get();

        // compare & determine tie
        if(total1.equals(total2)){
            return true;
        }
        return false;
    }

}