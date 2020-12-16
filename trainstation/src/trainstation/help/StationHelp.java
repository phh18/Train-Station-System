package trainstation.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainstation.model.Station;
import trainstation.model.TrainRoute;
import trainstation.model.TrainSchedule;
import trainstation.model.User;

public class StationHelp {
	public static ArrayList<Station> getStations() throws ClassNotFoundException {
		ArrayList<Station> stations = new ArrayList<>();
		String SELECT_STATION_SQL = "SELECT stationId, stationName FROM station;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATION_SQL)) {
            System.out.println(preparedStatement);
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	String stationId = result.getString("stationId");
            	String stationName = result.getString("stationName");
            	
            	stations.add(new Station(stationId, stationName));
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return stations;
	}
	
	public static ArrayList<String> getTrains() throws ClassNotFoundException {
		ArrayList<String> trains = new ArrayList<>();
		String SELECT_STATION_SQL = "SELECT trainId FROM train;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATION_SQL)) {
            System.out.println(preparedStatement);
            result = preparedStatement.executeQuery();
            while (result.next()) {
            	trains.add(result.getString("trainId"));
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return trains;
	}
	
	public static ArrayList<TrainSchedule> getTrainSchedulebyStation(String stationId) throws ClassNotFoundException {
		ArrayList<TrainSchedule> schedule = new ArrayList<>();
		String SELECT_STOPS_SQL = "SELECT * FROM stop WHERE stationId= ? ORDER BY arrivalTime";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOPS_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, stationId);
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	String trainId = result.getString("trainId");
            	String arrivalTime = result.getString("arrivalTime");
            	String departTime = result.getString("departTime");
            	
            	schedule.add(new TrainSchedule(trainId, stationId, arrivalTime, departTime, 0));
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return schedule;
	}
	
	public static ArrayList<TrainSchedule> getTrainSchedulebyId(String trainId) throws ClassNotFoundException {
		ArrayList<TrainSchedule> schedule = new ArrayList<>();
		String SELECT_STOPS_SQL = "SELECT * FROM stop WHERE trainId= ? ORDER BY arrivalTime";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOPS_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, trainId);
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	String stationId = result.getString("stationId");
            	String arrivalTime = result.getString("arrivalTime");
            	String departTime = result.getString("departTime");
            	
				schedule.add(new TrainSchedule(trainId, stationId, arrivalTime, departTime, 0));
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return schedule;
	}
	
	public static TrainRoute getSchedule(String trainId) throws ClassNotFoundException {
		TrainRoute schedule = null;
		String SELECT_SCHEDULE_SQL = "SELECT * FROM trainSchedule WHERE trainId= ?";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SCHEDULE_SQL)) {
           
            preparedStatement.setString(1, trainId);
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	String arrivalTime = result.getString("originTime");
            	String departTime = result.getString("destinationTime");
            	String origin = result.getString("origin");
            	String destination = result.getString("destination");
            	String lineName = result.getString("lineName");
            	int fare = result.getInt("fare");
            	System.out.println(lineName);
				schedule = new TrainRoute(trainId, arrivalTime, departTime, origin, destination, fare);
				schedule.setLineName(lineName);
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return schedule;
	}
	
	public static void updateInfo(String trainId, int fare, String lineName, String origin, String destination, String originTime, String destinationTime) throws ClassNotFoundException {
		String UPDATE_SCHEDULE_SQL = "UPDATE trainSchedule SET fare= ? ,lineName= ? ,origin= ? ,destination= ? ,originTime= ? ,destinationTime=? WHERE trainId= ?";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCHEDULE_SQL)) {
        	preparedStatement.setInt(1, fare);;
        	preparedStatement.setString(2, lineName);
        	preparedStatement.setString(3, origin);
        	preparedStatement.setString(4, destination);
        	preparedStatement.setString(5, originTime);
        	preparedStatement.setString(6, destinationTime);
        	preparedStatement.setString(7, trainId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate(); 
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
	}
	
	public static void deleteStop(String trainId, String stationId, String arrivalTime) throws ClassNotFoundException {
		String DELETE_STOPS_SQL = "DELETE FROM stop WHERE trainId= ? AND stationId= ? AND arrivalTime= ?";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STOPS_SQL)) {
        	preparedStatement.setString(1, trainId);
        	preparedStatement.setString(2, stationId);
        	preparedStatement.setString(3, arrivalTime);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate(); 
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
	}
	
	public static void addStop(String trainId, String stationId, String arrivalTime, String departTime) throws ClassNotFoundException {
		String INSERT_STOPS_SQL = "INSERT INTO stop (trainId, stationId, arrivalTime, departTime) VALUES ( ? , ? , ? , ? )";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STOPS_SQL)) {
        	preparedStatement.setString(1, trainId);
        	preparedStatement.setString(2, stationId);
        	preparedStatement.setString(3, arrivalTime);
        	preparedStatement.setString(4, departTime);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
	}
	
	public static ArrayList<User> getUsersByReservationLineAndDate(String lineName, String date) throws ClassNotFoundException {
		ArrayList<User> users = new ArrayList<>();
		String SELECT_USER_SQL = "SELECT DISTINCT u.userName, u.firstName, u.lastName, u.email " + 
				"FROM users u, reservation r, trainSchedule t " + 
				"WHERE u.userName = r.userName AND t.trainId = r.trainId " + 
				"AND t.lineName= ? " + 
				"AND r.travelDate= ? ;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            preparedStatement.setString(1, lineName);
            preparedStatement.setString(2, date);
            result = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (result.next()) {
            	String userName = result.getString("userName");
            	String firstName = result.getString("firstName");
            	String lastName = result.getString("lastName");
            	String email = result.getString("email");
            	
				users.add(new User(userName, firstName, lastName, null, null, email, null));
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return users;
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