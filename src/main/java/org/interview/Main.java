package org.interview;

import java.util.Scanner;

/**
 * <p>
 * Here we have to develop a simple railway ticket booking application. <br>
 * Develop a Railway Reservation Application contains <br>
 * 1.AC coach <br>
 * 2.Non AC coach <br>
 * 3. Seater <br>
 * </p>
 *
 * <p>
 * each should contain 60 seats and 10 waiting list max allowed rest request should be
 * cancelled. <br>
 * you should have <br>
 * 1.Ticket Booking <br>
 * 2.Ticket Cancellation <br>
 * 3.Status Checking <br>
 * </p>
 *
 * <p>
 * Here they asked us to create a “Railway reservation system” and gave us 4 modules. <br>
 * The modules were: <br>
 * 1. Booking <br>
 * 2. Availability checking <br>
 * 3. Cancellation <br>
 * 4. Prepare chart <br>
 * </p>
 */


public class Main {
    public static void main(String[] args) {
        System.out.println("Railway Reservation Application");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 -> Book a ticket"
                    + "\n2 -> Check availability"
                    + "\n3 -> Cancel ticket"
                    + "\n4 -> Exit");
            String name;
            String coach;
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("name");
                    name = sc.next();
                    System.out.println("Coach (AC/NonAc/Seater)");
                    coach = sc.next();
                    Passenger p = new Passenger(name, coach);
                    Controller.bookTicket(p);
                    break;
                case 2:
                    Controller.checkAvailability();
                    break;
                case 3:
                    System.out.println("Enter Passenger Id ");
                    Controller.cancelTicket(sc.nextLong());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}