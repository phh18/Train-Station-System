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

import trainstation.help.UserHelp;
import trainstation.model.Reservation;
import trainstation.model.User;
import trainstation.help.ReservationHelp;

import javax.servlet.annotation.WebServlet;
@WebServlet("/admin_reservations")


public class AdminServlet_Reservations extends HttpServlet {
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
	
    public AdminServlet_Reservations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<User> users = null;
		/*try {
		users = userHelp.getAllRep();
		request.setAttribute("users", users);
	
		}
		catch (Exception e) {
			request.setAttribute("error", e);
			e.printStackTrace();
			
		}*/
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Admin_Reservations.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		ArrayList<Reservation> reservations = null;
		String transitLine = request.getParameter("transitLine");
		String userName = request.getParameter("userName");
		if (transitLine !=null) {
			try {
				reservations = reservationHelp.getReservationByTransitLine(transitLine);
						}
			catch(Exception e) {
				request.setAttribute("error", e);
				e.printStackTrace();
				return;
				
			}
		}else {
			try {
				reservations = reservationHelp.getReservationByUsername(userName);
						}
			catch(Exception e) {
				request.setAttribute("error", e);
				e.printStackTrace();
				return;
			
		}
		}
		
		request.setAttribute("reservations", reservations);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Admin_Reservations.jsp");
		dispatcher.forward(request, response);
		return;

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String userName = request.getParameter("userName");
        System.out.println(userName);
        try {
            userHelp.deleteUser(userName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath()+"/admin");
		return;
	}
}
