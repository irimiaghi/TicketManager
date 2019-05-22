import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleBasedUI {
    private Ticketing app;
    private String prompt;
    private Scanner in;
    static User currentUser;

    public ConsoleBasedUI() throws SQLException {
        app = new TicketManager();
        in = new Scanner(System.in);
        prompt = ">";
    }

    private void printMainMenu() {
        System.out.println("##########");
        System.out.println(prompt + " Welcome! Please choose an option:");
        System.out.println(prompt + " 1.Register");
        System.out.println(prompt + " 2.Login");
        System.out.println(prompt + " 3.Exit");
        System.out.println("##########");
    }

    private void printLoggedMenu() {
        System.out.println("##########");
        System.out.println(prompt + " Main menu:");
        System.out.println(prompt + " 1.Create ticket");
        System.out.println(prompt + " 2.Access tickets");
        System.out.println(prompt + " 3.Change password");
        System.out.println(prompt + " 4.Change email");
        System.out.println(prompt + " 5.Logout");
        System.out.println("##########");
    }

    void start() {
        boolean quit = false;
        String username;
        while (!quit) {
            printMainMenu();
            int choice = in.nextInt();
            in.nextLine(); //eats the Enter from the nextInt()
            switch (choice) {
                case 1:
                    System.out.println("##########");
                    System.out.println(prompt + " Register menu");
                    System.out.println("##########");
                    register();
                    break;
                case 2:
                    boolean terminat = false;
                    System.out.println("##########");
                    System.out.println(prompt + " Login menu");
                    System.out.println("##########");
                    while (true) {
                        username = login();
                        if (username != null) {
                            prompt = username + ">";
                            break;
                        } else {
                            System.out.println(prompt + " Re-type your data:");
                        }
                    }
                    while (!terminat) {
                        printLoggedMenu();
                        System.out.print(prompt + " ");
                        choice = in.nextInt();
                        in.nextLine(); //eats the Enter from the nextInt()
                        switch (choice) {
                            case 1:
                                createTicket();
                                break;
                            case 2:
                                accessTicket();
                                break;
                            case 3:
                                changePassword(username);
                                break;
                            case 4:
                                changeEmail(username);
                                break;
                            case 5:
                                terminat = logout();
                                break;
                            default:
                                System.out.println(prompt + " Option unavailable");
                                break;
                        }
                    }
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println(prompt + " Option unavailable");
                    printMainMenu();
                    break;
            }
        }
    }

    boolean register() {
        String password;
        System.out.println(prompt + " Username:");
        System.out.print(prompt + " ");
        String username = in.nextLine();
        while (true) {
            System.out.println(prompt + " Password:");
            System.out.print(prompt + " ");
            password = in.nextLine();
            System.out.println(prompt + " Re-type password:");
            if (password.equals(in.nextLine())) {
                break;
            } else {
                System.out.println(prompt + " Passwords did not match!");
            }
        }
        System.out.println(prompt + " Email address:");
        System.out.print(prompt + " ");
        String email = in.nextLine();
        System.out.println(prompt + " First name:");
        System.out.print(prompt + " ");
        String firstName = in.nextLine();
        System.out.println(prompt + " Last name:");
        System.out.print(prompt + " ");
        String lastName = in.nextLine();
        User x = new User(username, password, email, firstName, lastName);
        return app.register(x);
    }

    String login() {
        System.out.println(prompt + " Username:");
        System.out.print(prompt + " ");
        String username = in.nextLine();
        System.out.println(prompt + " Password:");
        System.out.print(prompt + " ");
        String password = in.nextLine();
        User user = new User(username, password, null, null, null);
        if (app.login(user)) {
            currentUser = user;
            return username;
        } else {
            return null;
        }
    }

    boolean createTicket() {
        System.out.println(prompt + " Ticket title:");
        System.out.print(prompt + " ");
        String title = in.nextLine();
        System.out.println(prompt + " Describe the issue:");
        System.out.print(prompt + " ");
        String description = in.nextLine();
        Ticket x = new Ticket(title, description, null, Ticket.ticketStatus.UNASSINGED);
        return app.createTicket(x);
    }

    boolean accessTicket() {
        return app.accessTicket();
    }

    /*  - acceseaza ticket (va deschide un meniu dedicat, detaliat mai jos)
        - afisare tickete asignate unui user dat
        - afisare tickete cu un anumit status
    - meniul care se afiseaza dupa ce s-a accecsat un ticket:
        - asignarea unui responsabil de rezolvare
        - schimbare status*/

    void changePassword(String username) {
        System.out.println(prompt + " Type new password:");
        System.out.print(prompt + " ");
        String password = in.nextLine();
        User x = new User(username, password, null, null, null);
        System.out.println(prompt + " Re-type new password:");
        if (password.equals(in.nextLine())) {
            app.changePassword(x);
            System.out.println(prompt + " Password changed successfully!");
        } else {
            System.out.println(prompt + " Passwords did not match.");
        }
    }

    void changeEmail(String username) {
        System.out.println(prompt + " Type your new email:");
        System.out.print(prompt + " ");
        String email = in.nextLine();
        User x = new User(username, null, email, null, null);
        app.changeEmail(x);
        System.out.println(prompt + " Password changed successfully!");
    }

    boolean logout() {
        prompt = ">";
        System.out.println(prompt + " You have logged out!");
        return true;
    }
}
