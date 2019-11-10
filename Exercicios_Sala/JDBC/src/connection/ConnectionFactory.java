import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String DATABASE = "persistencia2019";
	private static final String URL 
					= "jdbc:postgresql://localhost/"+DATABASE;
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager
							.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
