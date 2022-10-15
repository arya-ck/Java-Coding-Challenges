package com.challenge.jokegenerator;

public class Joke {
    String setup;
    String punchline;

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "setup='" + setup + '\'' +
                ", punchline='" + punchline + '\'' +
                '}';
    }
}
