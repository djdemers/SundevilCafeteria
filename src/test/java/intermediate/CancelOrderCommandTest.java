package intermediate;

import com.domain.controller.CancelOrderCommand;
import com.domain.model.Order;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CancelOrderCommandTest {
    @Test
    public void testExecuteAndUndo() {
        ArrayList<Order> orderList = new ArrayList<>();
        Order order = new Order("orderToCancel");
        orderList.add(order);

        CancelOrderCommand command = new CancelOrderCommand(orderList, order.getOrderId());
        command.execute();

        // Verify the order was cancelled
        assertEquals(0, orderList.size(), "Order should have been removed from the list");

        command.undo();

        // Verify the order was restored
        assertEquals(1, orderList.size(), "Order should have been restored to the list after undo");
    }
}
