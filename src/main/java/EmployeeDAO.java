import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final DatabaseConnector dbConnector;

    public EmployeeDAO(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    public void addEmployee(String name, int age, String position, float salary) throws SQLException {
        String query = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, position);
            stmt.setFloat(4, salary);
            stmt.executeUpdate();
        }
    }


    public void updateEmployee(int id, String name, int age, String position, float salary) throws SQLException {
        String query = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, position);
            stmt.setFloat(4, salary);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }


    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    public List<String> getAllEmployees() throws SQLException {
        String query = "SELECT * FROM employees";
        List<String> employees = new ArrayList<>();
        try (Connection conn = dbConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String employee = String.format("ID: %d, Name: %s, Age: %d, Position: %s, Salary: %.2f",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getFloat("salary"));
                employees.add(employee);
            }
        }
        return employees;
    }
}