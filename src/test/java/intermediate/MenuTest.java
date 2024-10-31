package intermediate;

import com.domain.model.Menu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Test
    public void testSingletonInstance() {
        Menu menu1 = Menu.getInstance();
        Menu menu2 = Menu.getInstance();
        assertSame(menu1, menu2, "Menu should be a singleton");
    }
}
