package com.domain.repository;

import com.domain.exception.GlobalExceptionHandler;
import com.domain.model.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing order-related data.
 *
 * This class acts as the data access layer for orders,
 * handling operations like retrieving orders, saving updates, and managing
 * persistence with the underlying storage system (database, JSON file).
 *
 * It separates data persistence logic from business logic, promoting a clean architecture.
 *
 * TODO:
 * - Implement methods for CRUD (Create, Read, Update, Delete) operations on orders.
 * - Connect this class to a data source (JSON file, database, or API).
 * - Integrate with the `OrderManager` class for accessing and managing orders.
 */
public class OrderRepository {

    private static final String ORDER_DATA_FILE = "src/main/resources/com/orders.json"; // Path to JSON file
    private List<Order> orders;
    private Gson gson = new Gson(); // Gson instance for JSON serialization/deserialization

    public OrderRepository() {
        loadOrders();
    }

    /**
     * Returns a collection of all orders.
     *
     *
     * - Return a collection of orders (List<Order>).
     *
     * @return A collection of all orders.
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders); // Return a copy of the orders list
    }

    /**
     * Loads orders from the JSON file into memory.
     *
     * - If the file exists, the data is deserialized into the `orders` list.
     * - If the file does not exist or is empty, initializes an empty list.
     */
     private void loadOrders() {
        try (FileReader reader = new FileReader(ORDER_DATA_FILE)) {
            Type orderListType = new TypeToken<ArrayList<Order>>() {}.getType();
            orders = gson.fromJson(reader, orderListType);
            if (orders == null) { // Handle case where JSON is valid but empty
                orders = new ArrayList<>();
            }
        } catch (Exception e) {
            orders = new ArrayList<>(); // Handle case where file is missing or invalid
        }
     }

    /**
     * Retrieves an order by its ID.
     *
     * TODO:
     * - Implement logic to fetch a specific order by ID.
     * - Ensure null safety if the order is not found.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The matching order, or null if not found.
     */
    public Order getOrderById(String orderId) {
        loadOrders();
        for (Order order : orders) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Adds a new order to the data source.
     *
     * TODO:
     * - Implement logic to add a new order to the storage system.
     * - Ensure there are no duplicate IDs before adding.
     *
     * @param order The order to add.
     * @return True if the order was added successfully; false otherwise.
     */
    public boolean addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
            saveChanges();
            return true;
        }
        return false;
    }

    /**
     * Updates an existing order in the data source.
     *
     * TODO:
     * - Implement logic to update an existing order.
     * - Ensure the order exists before performing the update.
     *
     * @param order The order with updated data.
     * @return True if the order was updated successfully; false otherwise.
     */
    public boolean updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(order.getOrderId())) {
                orders.set(i, order);
                saveChanges();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an order by its ID.
     *
     * TODO:
     * - Implement logic to remove an order from the storage system.
     * - Handle the case where the order does not exist.
     *
     * @param orderId The ID of the order to remove.
     * @return True if the order was removed successfully; false otherwise.
     */
    public boolean removeOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                orders.remove(order);
                saveChanges();
                return true;
            }
        }
        return false;
    }

    /**
     * Saves all changes to the data source.
     *
     * TODO:
     * - Implement logic to persist changes to the underlying storage.
     * - Optimize for efficiency when dealing with large data sets.
     */
    public void saveChanges() {
        try (FileWriter writer = new FileWriter(ORDER_DATA_FILE)) {
            gson.toJson(orders, writer);
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e); // Handle file write errors
        }
    }
}
