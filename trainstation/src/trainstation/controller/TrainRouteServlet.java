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
import trainstation.model.TrainRoute;
import trainstation.model.User;

import javax.servlet.annotation.WebServlet;

@WebServlet("/trainroute")
public class TrainRouteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserHelp userHelp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
        userHelp = new UserHelp();
    }
	
    public TrainRouteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        
        ArrayList<TrainRoute> routes = new ArrayList<TrainRoute>();
        
        try {
            routes = userHelp.getTrainRoute(origin, destination);
            System.out.println(routes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(routes == null) {
        	request.setAttribute("message", "No schedule found");
        	request.getRequestDispatcher("/WEB-INF/view/userDetails.jsp").forward(request,response);
        	return;
        }
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
        request.setAttribute("routes", routes);
        request.getRequestDispatcher("/WEB-INF/view/userDetails.jsp").forward(request,response);
		return;
	}
}
