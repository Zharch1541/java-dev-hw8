package db;
import props.PropertyReader;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();

    private static Connection conn;

    private Database() {
        String url = PropertyReader.getConnectionUrlForH2();

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            throw new RuntimeException("Can not create connection. Reason: " + ex.getMessage());
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public static Connection getConn() {
        return conn;
    }

/*    public void executeUpdate(String file) {
        try (Statement statement = conn.createStatement()) {
            String content = new String(Files.readAllBytes(Paths.get(file)));
            statement.executeUpdate(content);
        } catch (SQLException e) {
            throw new RuntimeException("Can not run query." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

  /*  public static ResultSet executeResult(String file) throws SQLException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(file)));
        PreparedStatement statement = conn.prepareStatement(content);
        return statement.executeQuery();
    }*/
}
