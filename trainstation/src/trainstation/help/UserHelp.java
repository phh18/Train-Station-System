package trainstation.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import trainstation.model.User;

public class UserHelp {
	public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO users" +
            "  (userName, firstName, lastName, password, SSN, email, userRole) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            
        	preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());    
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getSSN());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getUserRole());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
	
	/*
	 * Verifies user logins.
	 * Parameter:
	 * 	String userName, String password
	 * Return:
	 * 	null - if username and password combination does not exist in the database
	 * 	User object - if username and password combination exist in the database
	 */
	public User login(String userName, String password) throws ClassNotFoundException {
		
        String SELECT_USER_SQL = "SELECT userName, firstName, lastName, password, ssn, email, userRole from users" +
            "  WHERE userName = ? and password = ?;";
        
        ResultSet result = null;
        User user = null;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement query = connection.prepareStatement(SELECT_USER_SQL)) {
        	query.setString(1, userName);
        	query.setString(2, password);
            System.out.println(query);
            result = query.executeQuery();
            
            if(!result.next()) {
            	return null;
            }
            user = new User(
            		result.getString(1),	//userName
            		result.getString(2), 	//firstName
            		result.getString(3), 	//lastName
            		result.getString(4), 	//password
            		result.getString(5), 	//ssn
            		result.getString(6),	//email
            		result.getString(7)		//userRole
            );
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return user;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
