package pl.kmo2008;



import java.sql.*;

public class DatabaseService {

    private Connection connect = null;
    private Statement statement = null;


    /**
     * Database structure:
     * CREATE TABLE IF NOT EXISTS `persons` (
     * `id` int(11) NOT NULL,
     *   `name` varchar(32) NOT NULL,
     *   `phone` varchar(32) NOT NULL
     * );
     * @throws Exception
     */
    public DatabaseService() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://ipaddress/phonebook?"
                            + "user=username&password=xxxxxxx");
            statement = connect.createStatement();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Person person, String name) throws SQLException {
        String query = "SELECT * FROM `persons` WHERE name='"+name+"'";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) person.setId(rs.getInt("id"));
        query = "UPDATE `persons` SET name='"+person.getName()+"', phone='"+person.getPhone()+"' WHERE id='"+person.getId()+"'";
        doQuery(query);
    }

    public void save(Person person) throws SQLException {
        String query = "INSERT INTO `persons` (name,phone) VALUES ('"+person.getName()+"','"+person.getPhone()+"')";
        doQuery(query);
    }

    public void findAll() throws SQLException {
        String query = "SELECT * FROM `persons` ORDER BY name ASC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Person p = new Person(rs.getString("name"),rs.getString("phone"));
            System.out.println(p.toStringView());
        }
    }

    public void findAny(String name) throws SQLException {
        String query = "SELECT * FROM `persons` WHERE name LIKE '%"+name+"%'";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Person p = new Person(rs.getString("name"),rs.getString("phone"));
            System.out.println(p.toStringView());
        }
    }

    public void delete(String name) throws SQLException {
        String query = "DELETE FROM `persons` WHERE name LIKE '%"+name+"%'";
        doQuery(query);
    }

    public void doQuery(String query) throws SQLException {
        if(!statement.execute(query)) System.out.println("Wykonano.");

    }


}
