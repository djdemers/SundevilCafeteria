package intermediate;

import com.domain.model.Order;
import com.domain.model.Observer;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class OrderTest {
    @Test
    public void testOrderObserverNotification() {
        Order order = new Order("orderId");
        Observer observer = mock(Observer.class);
        order.addObserver(observer);
        order.updateStatus("Shipped");

        verify(observer, times(1)).update("Shipped");
    }
}
