import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {
            ConsoleBasedUI obj = new ConsoleBasedUI();
            obj.start();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Startup failed");
        }
    }
}