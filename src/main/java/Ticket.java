public class Ticket {
    private int ticketID;
    private String title;
    private String description;
    User authorID;
    private User responsableID;
    private ticketStatus status;

    public Ticket(String title, String description, User authorID, ticketStatus status) {
        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.status = status;
    }
    public Ticket(Ticket ticket, int ticketID) {
        this(ticket.title, ticket.description, ticket.authorID, ticket.status);
        this.ticketID = ticketID;
    }
    enum ticketStatus {
        UNASSINGED, ASSIGNED, FIXED
    }
    String getAddTicketStatement() {
        return "INSERT INTO ticket(title, description, author_id, status)" +
                "VALUES ('" + title + "','" + description + "','" + authorID.getUserID() + "','" + status + "')";
    }

    String getTicketIDStatement() {
        return "SELECT * FROM ticket_manager.ticket;";
    }
}
