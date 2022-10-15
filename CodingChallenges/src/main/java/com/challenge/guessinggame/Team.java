package com.challenge.guessinggame;


import java.util.ArrayList;
import java.util.List;

public class Team {
    
    private final String player1;
    private final String player2;
    private final List<Integer> scores;

    public Team(String player1, String player2) {

        this.player1 = player1;
        this.player2 = player2;
        scores = new ArrayList<>();
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public List<Integer> getScores() {
        return scores;
    }
}

