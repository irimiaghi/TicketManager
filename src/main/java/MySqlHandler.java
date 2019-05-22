import java.sql.*;
import java.util.List;

public class MySqlHandler {
    private Connection dbConnection;

    public MySqlHandler() throws SQLException {
        dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ticket_manager", "root", "admin");
    }

    boolean addUser(User user) {
        String sqlCmd = user.getInsertStatement();
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute(sqlCmd);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean loginUser(User user){
        String sqlCmd = user.getLogInStatement();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCmd);
            if (resultSet.next()){
                System.out.println("Login successful!");
                return true;
            }
            else{
                System.out.println("Wrong username/password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    int getUserID(User user) {
        String sqlCmd = user.getUserIDStatement();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCmd);
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    boolean updatePassword(User user) {
        String sqlCmd = user.getUpdatePasswordStatement();
        System.out.println(sqlCmd);
        try {
            Statement statement = dbConnection.createStatement();
            return statement.execute(sqlCmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean updateEmail(User user) {
        String sqlCmd = user.getUpdateEmailStatement();
        try {
            Statement statement = dbConnection.createStatement();
            return statement.execute(sqlCmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean addTicket(Ticket ticket) {
        String sqlCmd = ticket.getAddTicketStatement();
        try {
            Statement statement = dbConnection.createStatement();
            return statement.execute(sqlCmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    int geTicketID(Ticket ticket) {
        String sqlCmd = ticket.getTicketIDStatement();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCmd);
            while(resultSet.next()){
                int ticketID = resultSet.getInt("idticket");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                User authorID = resultSet.getInt("author_id");
                User responsableID = resultSet.getInt("responsable_id");
                Ticket.ticketStatus status = resultSet.getString("status");
                System.out.println("%s, %s, %s, %s, %s, %s\n", ticketID, title, description, authorID, responsableID, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}