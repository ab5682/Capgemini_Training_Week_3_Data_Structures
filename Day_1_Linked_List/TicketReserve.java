class Ticket {
    String ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(String ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }
}

class TicketSystem {
    private Ticket head = null;
    private Ticket tail = null;

    public void addTicket(String ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);

        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        System.out.println("Ticket booked successfully.");
    }

    public void removeTicket(String ticketId) {
        if (head == null) return;

        Ticket current = head;
        Ticket prev = tail;
        boolean found = false;

        do {
            if (current.ticketId.equals(ticketId)) {
                found = true;
                if (current == head && current == tail) {
                    head = tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) head = current.next;
                    if (current == tail) tail = prev;
                }
                System.out.println("Ticket removed successfully.");
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found) System.out.println("Ticket not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        System.out.println("Booked Tickets:");
        do {
            System.out.println("TicketID: " + current.ticketId + ", Customer: " + current.customerName +
                    ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    public void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets to search.");
            return;
        }

        boolean found = false;
        Ticket current = head;
        do {
            if (current.customerName.equalsIgnoreCase(keyword) || current.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Found: TicketID: " + current.ticketId + ", Customer: " + current.customerName +
                        ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) System.out.println("No matching ticket found.");
    }

    public void totalTickets() {
        if (head == null) {
            System.out.println("Total Tickets: 0");
            return;
        }

        int count = 0;
        Ticket current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);

        System.out.println("Total Tickets: " + count);
    }
}

public class TicketReserve {
    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();

        system.addTicket("T101", "Alice", "Avatar", "A1", "10:00 AM");
        system.addTicket("T102", "Bob", "Inception", "B2", "11:00 AM");
        system.addTicket("T103", "Charlie", "Avatar", "C3", "12:00 PM");

        system.displayTickets();

        system.searchTicket("Avatar");

        system.removeTicket("T102");

        system.displayTickets();

        system.totalTickets();
    }
}
