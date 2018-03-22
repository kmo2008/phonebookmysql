package pl.kmo2008;



import java.sql.*;
import java.util.stream.Stream;

public class DatabaseService {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DatabaseService() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://techlab24.pl/phonebook?"
                            + "user=phonebook&password=12345");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Stream<Person> getData()
    {
        
    }
}
