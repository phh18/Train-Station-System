package trainstation.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainstation.model.Question;
import trainstation.model.User;

public class QuestionHelp {
	public static ArrayList<Question> getQuestions() throws ClassNotFoundException {
		ArrayList<Question> questions = new ArrayList<>();
		String SELECT_QUESTION_SQL = "SELECT * FROM question ORDER BY answer;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
            System.out.println(preparedStatement);
            
            result = preparedStatement.executeQuery();
            while (result.next()) {
            	Integer qid = result.getInt("qid");
            	String question = result.getString("question");
            	String custid = result.getString("custid");
            	String answer = result.getString("answer");
            	String repid = result.getString("repid");
            	
            	questions.add(new Question(qid, question, custid, answer, repid));
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		System.out.println(questions.size());
		return questions;
	}
	
	public static Question getQuestionByQid(Integer qid) throws ClassNotFoundException {
		Question question = null;
		String SELECT_QUESTION_SQL = "SELECT * FROM question WHERE qid= ? ;";
		ResultSet result = null;
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
	        	preparedStatement.setInt(1, qid);
	            System.out.println(preparedStatement);
	            
	            result = preparedStatement.executeQuery();
	            result.next();
            	String q = result.getString("question");
            	String custid = result.getString("custid");
            	String answer = result.getString("answer");
            	String repid = result.getString("repid");
            	
            	question = new Question(qid, q, custid, answer, repid);
	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
		
		return question;
	}
	
	public static ArrayList<Question> searchByKeyword(String keywords) throws ClassNotFoundException {
		ArrayList<Question> questions = new ArrayList<>();
		String SELECT_QUESTION_SQL = "SELECT * FROM question WHERE MATCH(question) AGAINST( ? ) ORDER BY answer DESC;";
		ResultSet result = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
        	preparedStatement.setString(1, keywords);
            System.out.println(preparedStatement);
            
            result = preparedStatement.executeQuery();
            while (result.next()) {
            	Integer qid = result.getInt("qid");
            	String question = result.getString("question");
            	String custid = result.getString("custid");
            	String answer = result.getString("answer");
            	String repid = result.getString("repid");
            	
            	questions.add(new Question(qid, question, custid, answer, repid));
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		System.out.println(questions.size());
		return questions;
	}
	
	public static void updateAnswer(Integer qid, String answer) throws ClassNotFoundException {
		String SELECT_QUESTION_SQL = "UPDATE question SET answer= ? WHERE qid = ?";
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://database-1.cjsw9rqqllkz.us-east-2.rds.amazonaws.com:3306/trainstation", "admin", "group28tlp");

	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTION_SQL)) {
				preparedStatement.setString(1, answer);
	        	preparedStatement.setInt(2, qid);
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
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
