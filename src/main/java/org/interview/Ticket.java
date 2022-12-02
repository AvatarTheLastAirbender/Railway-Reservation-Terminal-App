package org.interview;

public class Ticket {
    private static long id = 0;
    private final long bookingId;

    private String name;
    private int ticketNumber;
    private long userId;
    private String status;
    private String allocatedCoach;

    public String getRequestedCoach() {
        return requestedCoach;
    }

    private final String requestedCoach;

    public Ticket(String name, int ticketNumber, long userId, String status, String allocatedCoach, String requestedCoach) {
        this.userId = userId;
        this.requestedCoach = requestedCoach;
        this.bookingId = ++id;
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.status = status;
        this.allocatedCoach = allocatedCoach;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAllocatedCoach() {
        return allocatedCoach;
    }

    public void setAllocatedCoach(String allocatedCoach) {
        this.allocatedCoach = allocatedCoach;
    }

    @Override
    public String toString() {
        return "Name : " + this.name + ", Passenger Id : " + this.userId + ", Seat Number : " + this.ticketNumber + ", Allocated Coach : " + this.allocatedCoach + ", Booking Status : " + this.status;
    }
}
