package org.interview;

import java.util.ArrayList;

public class Controller {
    private static final ArrayList<Ticket> bookedTickets = new ArrayList<>();
    private static final ArrayList<Passenger> passengers = new ArrayList<>();
    private static int acSeats = 1;
    private static int nonAcSeats = 1;
    private static int sitterSeats = 1;
    private static int waitingList = 1;

    public static void bookTicket(Passenger p) {
        String requestedCoach = p.getRequestedCoach();
        Ticket ticket = bookedTickets.stream().filter(
                ticket1 -> ticket1.getAllocatedCoach().equalsIgnoreCase(requestedCoach)
                        && ticket1.getStatus().equalsIgnoreCase("Cancelled")
                        && ticket1.getTicketNumber() != -1
        ).findFirst().orElse(null);
        if (ticket != null) {
            ticket.setName(p.getName());
            ticket.setUserId(p.getUserId());
            ticket.setStatus("Booked");
        }
        if (((requestedCoach.equalsIgnoreCase("Ac") && acSeats > 0)
                || (requestedCoach.equalsIgnoreCase("NonAc") && nonAcSeats > 0)
                || (requestedCoach.equalsIgnoreCase("seater") && sitterSeats > 0))
                && ticket == null) {
            if (requestedCoach.equalsIgnoreCase("Ac")) {
                ticket = new Ticket(p.getName(), acSeats, p.getUserId(), "Booked", "Ac", requestedCoach);
                acSeats--;
            } else if (requestedCoach.equalsIgnoreCase("NonAc")) {
                ticket = new Ticket(p.getName(), nonAcSeats, p.getUserId(), "Booked", "NonAc", requestedCoach);
                nonAcSeats--;
            } else {
                ticket = new Ticket(p.getName(), sitterSeats, p.getUserId(), "Booked", "Seater", requestedCoach);
                sitterSeats--;
            }
        } else if (waitingList > 0) {
            ticket = new Ticket(p.getName(), -1, p.getUserId(), "Waiting", "W/L", requestedCoach);
            waitingList--;
        }

        if (ticket == null) {
            System.out.println("Booking not available");
        } else {
            passengers.add(p);
            bookedTickets.add(ticket);
            System.out.println("---------------------------------------------------");
            System.out.println("Booking success for " + p.getName());
            System.out.println("Passenger Id " + p.getUserId());
            System.out.println("Booking Id " + ticket.getBookingId());
            System.out.println("Seat Number " + (ticket.getTicketNumber() == -1 ? "-" : ticket.getTicketNumber()));
            if (ticket.getTicketNumber() == -1) {
                System.out.println("Note: Ticket under waiting");
            }
            System.out.println("---------------------------------------------------");
        }
    }

    public static void checkAvailability() {
        System.out.println("---------------------------------------------------");
        bookedTickets.stream().filter(ticket -> !ticket.getStatus().equalsIgnoreCase("Cancelled")).forEach(System.out::println);
        System.out.println("---------------------------------------------------");
        System.out.println("Ticket Availability");
        System.out.println("Available AC Tickets " + acSeats);
        System.out.println("Available Non AC Tickets " + nonAcSeats);
        System.out.println("Available Seater Tickets " + sitterSeats);
        System.out.println("Available Waiting List " + waitingList);
        System.out.println("---------------------------------------------------");
    }

    public static void cancelTicket(long userId) {
        Ticket ticket = bookedTickets.stream().filter(ticket1 -> ticket1.getUserId() == userId).findFirst().orElse(null);
        if (ticket == null) {
            System.out.println("Invalid passenger id");
            return;
        }
        String availableCoach = "W/L";
        if (ticket.getAllocatedCoach().equalsIgnoreCase("Ac")) {
            acSeats++;
            availableCoach = "Ac";
        } else if (ticket.getAllocatedCoach().equalsIgnoreCase("NonAc")) {
            nonAcSeats++;
            availableCoach = "NonAc";
        } else if (ticket.getAllocatedCoach().equalsIgnoreCase("Seater")) {
            sitterSeats++;
            availableCoach = "Seater";
        } else if (ticket.getAllocatedCoach().equalsIgnoreCase("W/L")) waitingList++;

        int seatNumber = ticket.getTicketNumber();
        ticket.setTicketNumber(-1);
        ticket.setStatus("Cancelled");
        System.out.println("Cancellation success for " + ticket.getName());
        if (waitingList < 10 && !availableCoach.equalsIgnoreCase("W/L")) {
            String finalAvailableCoach = availableCoach;
            Ticket waitingTicket = bookedTickets.stream().filter(
                    ticket1 -> ticket1.getRequestedCoach().equalsIgnoreCase(finalAvailableCoach)
                            && ticket1.getStatus().equalsIgnoreCase("Waiting")
            ).findFirst().orElse(null);
            if (waitingTicket == null) return;

            Passenger p = passengers.stream().filter(passenger -> passenger.getUserId() == waitingTicket.getUserId()).findFirst().orElse(null);
            if (p == null) return;

            waitingTicket.setName(p.getName());
            waitingTicket.setUserId(p.getUserId());
            waitingTicket.setTicketNumber(seatNumber);
            waitingTicket.setStatus("Booked");
            waitingTicket.setAllocatedCoach(finalAvailableCoach);

            waitingList++;
            if (waitingTicket.getAllocatedCoach().equalsIgnoreCase("Ac")) acSeats--;
            if (waitingTicket.getAllocatedCoach().equalsIgnoreCase("NonAc")) nonAcSeats--;
            if (waitingTicket.getAllocatedCoach().equalsIgnoreCase("Seater")) sitterSeats--;

            System.out.println("Seat allocated for " + p.getName());
        }
    }
}
