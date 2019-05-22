import java.util.List;

public interface Ticketing extends LoginCapable {
    boolean createTicket(Ticket ticket);
    boolean assignTo(Ticket t, String username);
    boolean statusUpdate(Ticket t);
    List<Ticket> getAllByAuthor(String username);
    List<Ticket> getAllByResponsible(String username);
}
