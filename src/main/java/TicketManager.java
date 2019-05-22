import java.sql.SQLException;
import java.util.List;

public class TicketManager implements Ticketing{
    private MySqlHandler sql;
    User currentuser;

    TicketManager() throws SQLException {
        sql = new MySqlHandler();
        currentuser = null;
    }
    @Override
    public boolean createTicket(Ticket ticket) {
        ticket.authorID = currentuser;
        if(sql.addTicket(ticket)) {
            //Ticket ticketFinal = new Ticket(ticket, ticket.ticketID);
            return true;
        }
        return false;
    }
   @Override
    public boolean accessTicket(Ticket ticket) {
        if(sql.getTicketID(ticket)){
            return true;
        }
        return false;
    }
    @Override
    public boolean assignTo(Ticket t, String username) {
        return false;
    }
    @Override
    public boolean statusUpdate(Ticket t) {
        return false;
    }
    @Override
    public List<Ticket> getAllByAuthor(String username) {
        return null;
    }
    @Override
    public List<Ticket> getAllByResponsible(String username) {
        return null;
    }
    @Override
    public boolean register(User x) {
        return sql.addUser(x);
    }
    @Override
    public boolean login(User x) {
        if(sql.loginUser(x)) {
            currentuser = new User(x, sql.getUserID(x));
            return true;
        }
        return false;
    }
    @Override
    public boolean changePassword(User x) {
        return sql.updatePassword(x);
    }
    @Override
    public boolean changeEmail(User x) {
        return sql.updateEmail(x);
    }
    @Override
    public boolean logout() {
        return false;
    }
}
