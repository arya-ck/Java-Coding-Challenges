package com.challenge.checkticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * It's Friday night and there's a concert downtown.
 * Before they let anyone in, security needs to make sure each person reserved a ticket in advance.
 * Let's help them out with this challenge. Security has a CSV file with each ticket holder name along with the number of tickets they bought.
 * Your challenge is to create a program that imports the CSV and checks if a given person bought a ticket, as well as how many tickets they bought.
 * If the person is not on the list or they did not buy enough tickets for their whole party, they should not be let in.
 */
public class TicketUtils {
    public static List<TicketHolder> importTicketHoldersFromCSV(String filename) throws IOException {
        List<TicketHolder> ticketHolderList = new ArrayList<>();
        File inputCsv = new File(filename);

        // return empty list if file is not CSV
        if(!inputCsv.getPath().endsWith("csv")){
            System.out.println("This is not a CSV file: sample.jpg");
            return ticketHolderList;
        }

        BufferedReader csvReader = new BufferedReader(new FileReader(inputCsv));
        TicketHolder ticketHolder;
        String line = csvReader.readLine();
        line = csvReader.readLine();
        while (line != null){
            try {
                // get next line
                String ticketData[] = line.split(",");

                if(ticketData.length ==2){
                    // create ticket holder object
                    ticketHolder = new TicketHolder(ticketData[0], Integer.parseInt(ticketData[1]));
                    ticketHolderList.add(ticketHolder);
                } else {
                    // invalid line
                    System.out.println("Incorrect format: "+ line);
                }

            } catch(NumberFormatException e){
                // error in format
                System.out.println("Error occurred while parsing line: "+ line);
            }

            // continue reading line
            line = csvReader.readLine();
        }

        return ticketHolderList;
    }

    static public Optional<TicketHolder> findTicketHolder(String name, List<TicketHolder> ticketHolderList){
        // find the ticket holder by name
        return ticketHolderList.stream().filter((TicketHolder ticketHolder) ->
            ticketHolder.getName().equalsIgnoreCase(name)
        ).findFirst();
    }

    public static boolean processTickets(TicketHolder ticketHolder, int quantity, List<TicketHolder> ticketHolderList){
        Optional<TicketHolder> holderOp = findTicketHolder(ticketHolder.getName(), ticketHolderList);
        boolean allQtyUsed = false;

        if(holderOp.isPresent()){
            TicketHolder holder = holderOp.get();
            int quantityAllocated = holder.getQuantity();

            if(quantityAllocated > quantity){   // all booked tickets not utilized
                System.out.println("I see you still have more coming. I'm keeping your name on the list for them!");
                holder.setQuantity(quantityAllocated - quantity);
            } else if(quantityAllocated < quantity){ // booked tickets are less
                System.out.println("You don't have enough tickets for your whole party. You only have "+ quantityAllocated);
                return false;
            } else {
                allQtyUsed = true;
            }

            // remove user from list once tickets are validated
            if(allQtyUsed){
                ticketHolderList.remove(holder);
            }

            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        List<TicketHolder> list = importTicketHoldersFromCSV("ticketholders.csv");
        System.out.println(
                processTickets(new TicketHolder("Jessica Jones", 2), 1, list)
        );
    }
}
