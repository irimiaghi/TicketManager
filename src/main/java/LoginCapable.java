public interface LoginCapable {
    boolean register(User x);
    boolean login(User x);
    boolean logout();
    boolean changePassword(User x);
    boolean changeEmail(User x);
}