package com.challenge.redactor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * You have to send a secret text file to a fellow employee but they don't have the same security clearance as you.
 * So in this challenge, you'll write a program that redacts a list of words from a given text file.
 * The program should read in the original file and create a new file with the same contents.
 * But the redacted words should be overwritten with the word redacted in all uppercase.
 */
public class RedactSecretMessage {

    public static void redactTextFile(String fileName,
                                      String[] redactedWordsArray) {

        // if file is not txt, exit
        if (!fileName.contains(".txt")) {
            System.out.println("This is not a text file.");
            return;
        }

        // read the file and process
        try {
            File originalFile = new File (fileName);
            BufferedReader originalFileReader =
                    new BufferedReader(new FileReader(originalFile));

            File redactedFile = new File("redacted-" +
                    fileName);
            BufferedWriter redactedFileWriter = new BufferedWriter(
                    new FileWriter(redactedFile));

            String currentLine = originalFileReader.readLine();

            while(currentLine != null) {
                for (String redactedWord : redactedWordsArray) {
                    // remove sensitive words from each line
                    currentLine = currentLine
                            .replaceAll(redactedWord, "REDACTED");
                }

                redactedFileWriter.append(currentLine).append("\n");
                currentLine = originalFileReader.readLine();
            }

            originalFileReader.close();
            redactedFileWriter.close();

        } catch (IOException e) {
            System.out.println("Trouble reading or writing to file"
                    + e);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What file would you like to " +
                "redact information from?");
        String fileName = scanner.nextLine();

        System.out.println("What words would you like to redact? " +
                "Separate each word or phrase with a comma. " +
                "If you phrase includes punctuation, include " +
                "that in your input.");
        String redactedWords = scanner.nextLine();
        String[] redactedWordsList = redactedWords.split(",");

        redactTextFile(fileName, redactedWordsList);
    }
}

