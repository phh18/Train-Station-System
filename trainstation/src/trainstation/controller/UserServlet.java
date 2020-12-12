package trainstation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainstation.model.User;
import trainstation.help.UserHelp;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/register")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserHelp userHelp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
        userHelp = new UserHelp();
    }
	
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			String role = ((User)session.getAttribute("user")).getUserRole();
			 if(role.equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/admin");
				return;
			}
			else if(role.equals("rep")) {
				response.sendRedirect(request.getContextPath() + "/rep");
				return;
			}
			request.getRequestDispatcher("/WEB-INF/view/userDetails.jsp").forward(request,response);
			System.out.println(((User) session.getAttribute("user")).getUsername());
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userRegister.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String SSN = request.getParameter("SSN");
        String email = request.getParameter("email");
        String userRole = "customer";
        
        User user = new User(userName, firstName, lastName, password, SSN, email, userRole);
        
        try {
            userHelp.registerUser(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userDetails.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
