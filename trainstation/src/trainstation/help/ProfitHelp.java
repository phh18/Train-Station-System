package trainstation.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainstation.model.Question;
import trainstation.model.*;

public class ProfitHelp {
	public static ArrayList<Profit> getProfitByTransitLine() throws ClassNotFoundException {
		ArrayList<Profit> profits = new ArrayList<>();
		String SELECT_QUESTION_SQL = "select trainId, sum(fare) as profit from reservation\r\n"
				+ "group by trainId\r\n"
				+ "order by profit desc;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
            System.out.println(preparedStatement);
            
            result = preparedStatement.executeQuery();
            while (result.next()) {
            	Integer profit = result.getInt("profit");
            	String trainId = result.getString("trainId");
            	profits.add(new Profit(trainId, profit));
            	}

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		
		return profits;
	}
	public static ArrayList<Profit> getProfitUsername() throws ClassNotFoundException {
		ArrayList<Profit> profits = new ArrayList<>();
		String SELECT_QUESTION_SQL = "select userName, sum(fare) as profit from reservation\r\n"
				+ "group by userName\r\n"
				+ "order by profit desc;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
            System.out.println(preparedStatement);
            
            result = preparedStatement.executeQuery();
            while (result.next()) {
            	Integer profit = result.getInt("profit");
            	String userName = result.getString("userName");
            	profits.add(new Profit(userName, profit));
            	}

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		
		return profits;
	}
	public static ArrayList<Profit> getMostActiveLines() throws ClassNotFoundException {
		ArrayList<Profit> profits = new ArrayList<>();
		String SELECT_QUESTION_SQL = "select trainId, count(*) as reservations\r\n"
				+ "from reservation\r\n"
				+ "group by trainId\r\n"
				+ "order by reservations desc;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
            System.out.println(preparedStatement);
            
            result = preparedStatement.executeQuery();
            while (result.next()) {
            	Integer profit = result.getInt("reservations");
            	String trainId = result.getString("trainId");
            	profits.add(new Profit(trainId, profit));
            	}

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		
		return profits;
	}
	
	
	private static void printSQLException(SQLException ex) {
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

