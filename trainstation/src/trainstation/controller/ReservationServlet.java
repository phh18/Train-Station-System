package trainstation.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import trainstation.help.UserHelp;
import trainstation.help.ReservationHelp;
import trainstation.model.TrainRoute;
import trainstation.model.TrainSchedule;
import trainstation.model.Reservation;


@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserHelp userHelp;
	private ReservationHelp reservationHelp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
        userHelp = new UserHelp();
        reservationHelp = new ReservationHelp();
    }
	
    public ReservationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		HttpSession session = request.getSession();
//		if (session.getAttribute("user") != null) {
//			request.getRequestDispatcher("/WEB-INF/view/userDetails.jsp").forward(request,response);
//			System.out.println(((User) session.getAttribute("user")).getUsername());
//			return;
//		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userLogin.jsp");
//		dispatcher.forward(request, response);
//		return;
		String trainID = request.getParameter("trainID");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        
        ArrayList<TrainSchedule> schedule = new ArrayList<TrainSchedule>();
        
        try {
            schedule = userHelp.getTrainSchedule(trainID, origin, destination);
            System.out.println(schedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(schedule == null) {
        	request.setAttribute("message", "No schedule found");
        	request.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(request,response);
        	return;
        }
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
        request.setAttribute("schedule", schedule);
        request.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(request,response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String trainID = request.getParameter("trainId");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        int fare = Integer.parseInt(request.getParameter("fare"));
        String departTime = request.getParameter("departTime");
        String arrivalTime = request.getParameter("arrivalTime");
        String ticketType = request.getParameter("ticketType");
        String tripType = request.getParameter("tripType");
        String travelDate = request.getParameter("date");
        
        if(ticketType.equals("children")) {
        	fare *= 0.75;
        }
        else if(ticketType.equals("senior")) {
        	fare *= 0.65;
        }
        else if(ticketType.equals("disabled")) {
        	fare *= 0.5;
        }
        
        if(tripType.equals("roundtrip")) {
        	fare *= 2;
        }
        
        Reservation reserve = new Reservation(userName, trainID, origin, destination, tripType, travelDate, departTime, arrivalTime, fare, ticketType);
        
        try {
        	reservationHelp.reservation(reserve);
//            System.out.println(schedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        if(schedule == null) {
//        	request.setAttribute("message", "No schedule found");
//        	request.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(request,response);
//        	return;
//        }
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
//        request.setAttribute("schedule", schedule);
        request.getRequestDispatcher("/WEB-INF/view/myReservation.jsp").forward(request,response);
		return;
	}
}
