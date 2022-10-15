package com.challenge.checkticket;

/**
 * Ticket holder model class
 */
public class TicketHolder {
    String name;
    int quantity;

    public TicketHolder(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
