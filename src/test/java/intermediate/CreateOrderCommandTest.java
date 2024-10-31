package intermediate;

import com.domain.controller.CreateOrderCommand;
import com.domain.model.Order;
import com.domain.model.OrderFactory;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CreateOrderCommandTest {
    @Test
    public void testExecuteAndUndo() {
        OrderFactory orderFactory = new OrderFactory();
        ArrayList<Order> orderList = new ArrayList<>();
        String orderId = "testOrder123";

        CreateOrderCommand command = new CreateOrderCommand(orderList, orderFactory, orderId);
        command.execute();

        // Verify the order was added
        assertEquals(1, orderList.size(), "Order should have been added to the list");
        assertEquals(orderId, orderList.get(0).getOrderId());

        command.undo();

        // Verify the order was removed
        assertEquals(0, orderList.size(), "Order should have been removed from the list after undo");
    }
}