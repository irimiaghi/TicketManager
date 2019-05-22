public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(User user, int userID) {
        this(user.username, user.password, user.email, user.firstName, user.lastName);
        this.userID = userID;
    }
    int getUserID() {
        return userID;
    }
    String getInsertStatement() {
        return "INSERT INTO user(username, password, email, firstName, lastName)" +
                "VALUES (\'" + username + "\', \'" + password + "\', \'" + email +
                "\', \'" + firstName + "\', \'" + lastName + "\')";
    }
    String getLogInStatement() {
        return "SELECT * FROM ticket_manager.user WHERE username=\'" + username +
                "\' and password = \'" + password + "\';";
    }
    String getUserIDStatement() {
        return "SELECT * FROM ticket_manager.user WHERE username=\'" + username + "\';";
    }
    String getUpdatePasswordStatement() {
        return "UPDATE user SET password=\"" + password + "\" WHERE username=\"" + username + "\"";
    }
    String getUpdateEmailStatement() {
        return "UPDATE user SET email=\"" + email + "\" WHERE username=\"" + username + "\"";
    }
}