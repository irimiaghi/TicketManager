import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class UnitTestUI {
    private Ticketing app;

    public UnitTestUI() throws SQLException {
        app = new TicketManager();
    }

    @Test
    public void testRegisterAndLogin() {
        User x = new User("zulaman", "123", "zul@zulaman.zul", "Zul", "Zul");
        assertEquals(true, app.register(x));
        assertEquals(true, app.login(x));
    }
}
