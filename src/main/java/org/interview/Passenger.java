package org.interview;

public class Passenger {
    private static long id = 0;
    private final String name;
    private final long userId;
    private final String requestedCoach;

    public Passenger(String name, String requestedCoach) {
        this.name = name;
        this.userId = ++id;
        this.requestedCoach = requestedCoach;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public String getRequestedCoach() {
        return requestedCoach;
    }
}
