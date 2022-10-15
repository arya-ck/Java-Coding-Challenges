package com.challenge.studentvolunteers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Let's say you work for a school district. Each year every student must volunteer for at least two events.
 * In this challenge, the school needs to find out which students still need to finish their volunteering.
 */
public class StudentVolunteers {

    public static List<String> findWithIncompleteEvents(
            List<String> students,
            Map<String, List<String>> attendeesMapping) {

            List<String> incompleteEvents = students.stream()
                .filter(s -> {
                    long eventCount = 0;

                    // count the number of events volunteered by the student
                    eventCount = attendeesMapping.entrySet().stream()
                            .filter(event -> event.getValue().contains(s))
                            .count();
                    // return true if count grater than 2
                    return eventCount <2;
                }).collect(Collectors.toList());
        return incompleteEvents;
    }

    public static void main(String[] args) {

        List<String> students = List.of("Sally", "Polly", "Molly",
                "Tony", "Harry");

        Map<String, List<String>> attendeesMapping =
                Map.of("Farmer's Market",
                        List.of("Sally", "Polly"),
                        "Car Wash Fundraiser",
                        List.of("Molly", "Tony", "Polly"),
                        "Cooking Workshop",
                        List.of("Sally", "Molly", "Polly"),
                        "Midnight Breakfast",
                        List.of("Polly", "Molly"));

        System.out.println(findWithIncompleteEvents(students,
                attendeesMapping));
    }

}
