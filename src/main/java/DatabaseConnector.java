import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/company";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Lvbnhbq&1025";

    private Connection connection;

    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void executeUpdate(String query) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
        }
    }
}
