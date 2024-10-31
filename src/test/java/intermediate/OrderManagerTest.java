package intermediate;

import com.domain.controller.OrderManager;
import com.domain.model.OrderFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderManagerTest {
    @Test
    public void testUndoLastCommand() {
        OrderFactory orderFactory = new OrderFactory();
        OrderManager orderManager = new OrderManager(orderFactory);

        String orderId = "testOrder456";
        orderManager.createOrder(orderId);

        // Check that the order was created
        assertNotNull(orderManager.findOrderById(orderId));

        orderManager.undoLastCommand();

        // Verify the last command was undone
        assertNull(orderManager.findOrderById(orderId), "Order should be null after undoing the creation");
    }
}
