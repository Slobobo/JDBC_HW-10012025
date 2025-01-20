import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        EmployeeDAO employeeDAO = new EmployeeDAO(dbConnector);

        try {
            employeeDAO.addEmployee("John Doe", 30, "Manager", 60000.0f);
            employeeDAO.addEmployee("Jane Smith", 25, "Developer", 50000.0f);

            System.out.println("Employee list after adding:");
            for (String employee : employeeDAO.getAllEmployees()) {
                System.out.println(employee);
            }

            employeeDAO.updateEmployee(1, "John Doe", 31, "Senior Manager", 70000.0f);

            employeeDAO.deleteEmployee(2);

            System.out.println("\nEmployee list after updating and deleting:");
            for (String employee : employeeDAO.getAllEmployees()) {
                System.out.println(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
