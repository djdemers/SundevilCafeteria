package basic;
import com.domain.model.Customer;
import com.domain.model.MenuItem;
import com.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private Order order;
    private MenuItem burger;
    private MenuItem fries;

    @BeforeEach
    public void setup() {
        Customer customer = new Customer("cust002", "John Wayne");
        order = new Order("order002", customer);
        burger = new MenuItem("Burger", 5.99, "Burger");
        fries = new MenuItem("Fries", 2.49, "Fries");
    }

    @Test
    public void testAddItem() {
        order.addItem(burger);
        assertEquals(1, order.getItems().size());
        assertEquals(burger, order.getItems().get(0));
    }

    @Test
    public void testCalculateTotal() {
        order.addItem(burger);
        order.addItem(fries);
        assertEquals(8.48, order.calculateTotal(), 0.001);
    }
}