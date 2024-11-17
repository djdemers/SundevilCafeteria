
# Project Features Summary

## Core Features Recently Added

### 1. User Authentication and Management
- Users are authenticated based on **username** and **hashed passwords**.
- Passwords are hashed and salted for security using a custom utility class (`PasswordUtils`).
- Admins manage users dynamically, while customers can self-register via the signup feature.

#### Existing Users
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

### 2. Role-Based Navigation
- **Manager View**: Allows managing menu items (adding, editing, removing).
- **Operator View**: To manage orders (currently in development).
- **Customer View**: Displays the menu for browsing and allows adding items to a cart for order placement.

### 3. Dynamic Menu System
- Menu items are loaded from a JSON file (`menu_items.json`) or use fallback defaults if the file is missing.
- Managers can add, edit, or remove menu items dynamically.
- Customers can browse menu items and add them to their cart.

### 4. Order System
- Orders are created dynamically using an `OrderFactory` and stored with customer-specific details.
- Command pattern (`CreateOrderCommand`) supports modular order handling and undo operations.
- Customers can view the total cost of their cart and place orders.

### 5. File-Based Persistence
- **Users** and **menu items** are persisted in JSON files (`users.json` and `menu_items.json`) to ensure data is retained across application sessions.

---

## Developer Operations

### Maven Commands
- `mvn clean`: Cleans the project.
- `mvn compile`: Compiles the source code.
- `mvn test`: Runs all tests.
- `mvn dependency:tree`: Displays project dependency tree.
- `mvn javafx:run`: Runs the JavaFX application.

#### Generating a Hashed Password
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

---

## Upcoming Tasks

### 1. Enhancing Role-Specific Views
- Complete the operator view for managing orders.
- Improve customer view to display order history.

### 2. Persistent Order Management
- Save orders to a file/database for long-term storage and retrieval.

### 3. Admin Dashboard
- Implement features for managing users dynamically.

