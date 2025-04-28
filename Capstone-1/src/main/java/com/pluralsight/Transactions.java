package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Transactions {
    private LocalDateTime localDateTime;
    private String description;
    private String vendor;
    private double price;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Transactions(LocalDateTime localDateTime, String description, String vendor, double price) {
        this.localDateTime = localDateTime;
        this.description = description;
        this.vendor = vendor;
        this.price = price;
    }
    public String display() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd, yyyy hh:mm a");
        StringBuilder builder = new StringBuilder();
        builder.append(localDateTime.format(formatter)).append("\n").append(description).append("\n").append(vendor)
                .append("\n").append(price).append("\n")
                .append("__________________________________________________________________________");
        return builder.toString();
 }
    @Override
    public String toString() {
        return "Transactions-" +
                "localDateTime: " + localDateTime +
                ", description: " + description +
                ", vendor=: " + vendor+
                ", price: " + price ;
    }
}