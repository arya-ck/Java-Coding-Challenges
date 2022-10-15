package com.challenge.jokegenerator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JokeGenerator {

    public static void generateJokeFromResponse(String response) {
//        System.out.println(response);

        try {
            Joke[] jokes = new Gson().fromJson(response, Joke[].class);

            if(jokes.length == 0){
                System.out.println("Must be out of jokes for now.");
            } else {
                    tellJoke(jokes[0]);
            }
        } catch (InterruptedException e) {
            System.out.println("Must be out of jokes for now.");
        } catch (JsonSyntaxException e) {
            System.out.println("Must be out of jokes for now.");
        }
    }

    public static void tellJoke(Joke joke) throws InterruptedException {
        System.out.println(joke.getSetup());
        Thread.sleep(4000);
        System.out.println(joke.getPunchline());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // get Joke JSON from API end point
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("https://official-joke-api.appspot.com/jokes/programming/random"))
                .header("accept", "application/json")
                .build();
        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        // display joke
        generateJokeFromResponse(response);
    }
}
