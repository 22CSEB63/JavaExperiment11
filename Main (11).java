Certainly! You can use JDBC (Java Database Connectivity) to interact with a database in a Java program. Below is a simple Java program that performs the operations you've specified:

```java
import java.sql.*;

public class BookDatabase {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/your_database_name";
    static final String USER = "your_username";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute functions
            stmt = conn.createStatement();

            // SELECT * FROM books
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                // Process the result set
                System.out.println("ID: " + resultSet.getInt("id")
                        + ", Title: " + resultSet.getString("title")
                        + ", Author: " + resultSet.getString("author")
                        + ", Price: " + resultSet.getDouble("price")
                        + ", Quantity: " + resultSet.getInt("qty"));
            }

            // Perform other operations (UPDATE, DELETE, INSERT) as needed

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
```

Make sure to replace `your_database_name`, `your_username`, and `your_password` with your actual database details. Also, you need to have the MySQL JDBC driver in your classpath.

This is a basic example. In a real-world scenario, you'd want to handle exceptions more gracefully, use prepared statements to prevent SQL injection, and possibly use a connection pool for better performance.