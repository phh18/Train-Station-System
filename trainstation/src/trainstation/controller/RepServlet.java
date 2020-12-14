package trainstation.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.ArrayList;

import trainstation.help.QuestionHelp;
import trainstation.help.StationHelp;
import trainstation.model.Question;
import trainstation.model.Station;
import trainstation.model.TrainSchedule;
import trainstation.model.User;

import javax.servlet.annotation.WebServlet;
@WebServlet("/rep")

public class RepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
    }
	
    public RepServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String role = ((User)session.getAttribute("user")).getUserRole();
		if(role.equals("customer")) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		String schedule = request.getParameter("schedule");
		if(schedule != null && schedule.equals("station")) {
			getStationSchedule(request, response);
			return;
		}
		
		ArrayList<Question> questions;
		String keywords = request.getParameter("keywords");
		//Handle keyword search
		if(keywords != null && !keywords.equals("")) {
			try {
				questions = QuestionHelp.searchByKeyword((String) request.getParameter("keywords"));
				request.setAttribute("questions", questions);
		
			}
			catch (Exception e) {
				request.setAttribute("error", e);
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Customer/question.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		try {
		questions = QuestionHelp.getQuestions();
		request.setAttribute("questions", questions);
	
		}
		catch (Exception e) {
			request.setAttribute("error", e);
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Rep/question.jsp");
		dispatcher.forward(request, response);
	}

	protected void getStationSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Station> stations = null;
		
		try {
			stations = StationHelp.getStations();
			request.setAttribute("stations", stations);
		}
		catch (Exception e) {
			request.setAttribute("error", e);
			e.printStackTrace();
		}
		
		ArrayList<TrainSchedule> schedule = null;
		String stationId = (String) request.getParameter("stationId");
		if(stationId != null) {
			try {
				schedule = StationHelp.getTrainSchedulebyStation(stationId);
				request.setAttribute("schedule", schedule);
			}
			catch (Exception e) {
				request.setAttribute("error", e);
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Rep/stationSchedule.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User rep = (User) session.getAttribute("user");
		if (rep == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String role = rep.getUserRole();
		if(role.equals("customer")) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		String ans = request.getParameter("ans");
		Integer qid = Integer.parseInt(request.getParameter("qid"));
		
		//Handle update answer
		if(ans.equals("1")) {
			String answer = request.getParameter("answer");
			try {
				QuestionHelp.updateAnswer(qid, answer, rep.getUsername());
				ArrayList<Question> questions = QuestionHelp.getQuestions();
				request.setAttribute("questions", questions);
			}
			catch (Exception e) {
				request.setAttribute("error", e);
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Rep/question.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//Send answer page
		Question question = null;
		try {
			question = QuestionHelp.getQuestionByQid(qid);
			request.setAttribute("question", question);
		}
		catch (Exception e) {
			request.setAttribute("error", e);
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Rep/answer.jsp");
		dispatcher.forward(request, response);
		return;
	}
}
