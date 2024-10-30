package basic;

import com.domain.model.Customer;
import com.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer("cust001", "Mac Dre");
    }

    @Test
    public void testAddOrder() {
        Order order1 = new Order("order001", customer);
        customer.addOrder(order1);
        List<Order> orders = customer.getOrderHistory();
        assertEquals(1, orders.size());
        assertEquals(order1, orders.get(0));
    }
}
