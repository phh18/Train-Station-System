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
import trainstation.model.*;
import trainstation.help.*;

import javax.servlet.annotation.WebServlet;
@WebServlet("/admin_profit")


public class AdminServlet_Profit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  ProfitHelp profitHelp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
		profitHelp = new ProfitHelp();
        
    }
	
    public AdminServlet_Profit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Profit> profitByUsername = null;
		ArrayList<Profit> profitByTransitLine = null;
		ArrayList<Profit> mostActivelines = null;
		ArrayList<Profit> salesPerMonth = null;
		try {
		profitByUsername = profitHelp.getProfitUsername();
		profitByTransitLine = profitHelp.getProfitByTransitLine();
		mostActivelines = profitHelp.getMostActiveLines();
		salesPerMonth = profitHelp.getSalesReportPerMonth();
		request.setAttribute("profitByUsername", profitByUsername);
		request.setAttribute("profitByTransitLine", profitByTransitLine);
		request.setAttribute("mostActivelines", mostActivelines);
		request.setAttribute("salesPerMonth", salesPerMonth);
		
	
		}
		catch (Exception e) {
			request.setAttribute("error", e);
			e.printStackTrace();
			
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Admin_Profit.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

