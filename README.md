
# Project Features Summary

## Core Features Recently Added

### 1. Order Management System
- Orders are saved and loaded from a json file (`orders.json`) to ensure data persistence. The `OrderService`, `OrderManager`, 
`OrderFactory` and `OrderRepository` classes work together to manage orders.
- Operators are able to view all orders and update the status of orders. 
- Customers can view their order history and stay up to date with the status of their orders from the new order history view.
- Once orders have been completed, operators have the option of removing them from the dashboard. 

### 2. Enhanced Role Specific Views
- **Customer View**: 
  - Added an order history view for customers to view past orders and track order status. 
  - In the order history view customers are able to cancel orders that are not yet completed.
  - Checkout functionality now works as expected and creates orders using the `OrderService` and `OrderManager` classes.
  - Added logout functionality for customers to sign out of their accounts.
- **Operator View**:
  - Added functionality for operators to view and manage orders. Operators view details of all incoming orders, and can update the order status of 
  order to either `PENDING`, `PREPARING`, or `COMPLETED`.
  - Added logout functionality for operators to sign out of their accounts.
- **Manager View**:
  - Added functionality for managers to add and remove users dynamically.
  - Added logout functionality for managers to sign out of their accounts.

### 3. Admin Dashboard
- The manager dashboard has new functionality to manage users. 
- Customers are able to sign up for accounts from the login screen, but operators and managers
can only be added by a manager.
- Managers are able to remove any user from the system, the system will always prompt for confirmation.

---

## Developer Operations

### Maven Commands
- `mvn clean`: Cleans the project.
- `mvn compile`: Compiles the source code.
- `mvn test`: Runs all tests.
- `mvn dependency:tree`: Displays project dependency tree.
- `mvn javafx:run`: Runs the JavaFX application.

### User Profiles
Customer users can be created from the login screen. Operators and Managers can be added from the `Manage Users` view of an existing Manager. 
Existing user profiles are available for use. The following are the default user profiles:
- **Admin**:
  - Username: `admin`
  - Password: `admin123`
  - Role: `Manager`
- **Operator**:
  - Username: `operator1`
  - Password: `op123`
  - Role: `Operator`
- **Customers**:
  - Username: `sparky`, Password: `pass123`
  - Username: `devil`, Password: `pass123`

### Generating a Hashed Password
To generate a hashed password and salt for secure storage, use the following Maven command:

```bash
mvn exec:java "-Dexec.mainClass=com.domain.util.PasswordHasher" "-Dexec.args=admin123"
```

**Explanation of Command:**
1. **`exec:java`**: Invokes the `maven-exec-plugin` to run a Java class directly.
2. **`-Dexec.mainClass`**: Specifies the fully qualified name of the Java class to execute. In this case, it runs the `PasswordHasher` utility.
3. **`-Dexec.args`**: Passes runtime arguments to the main method of the specified class. Here, the argument is `admin123`, representing the plaintext password.

**Steps to Use:**
1. Ensure that the Maven `exec` plugin is configured in your `pom.xml` file:
    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.0.0</version>
            </plugin>
        </plugins>
    </build>
    ```
2. Run the above command in your terminal.
3. The hashed password and salt will be displayed in the console output.


