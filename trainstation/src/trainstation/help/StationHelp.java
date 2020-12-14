package trainstation.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainstation.model.Station;
import trainstation.model.TrainSchedule;

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