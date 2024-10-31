package intermediate;

import com.domain.controller.UpdateOrderCommand;
import com.domain.model.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateOrderCommandTest {
    @Test
    public void testExecuteAndUndo() {
        Order order = new Order("order123");
        UpdateOrderCommand command = new UpdateOrderCommand(order, "Shipped");

        command.execute();
        assertEquals("Shipped", order.getStatus(), "Order status should be updated to 'Shipped'");

        command.undo();
        assertEquals("Pending", order.getStatus(), "Order status should revert to 'Pending' after undo");
    }
}
