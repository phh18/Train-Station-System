package trainstation.controller;

import java.io.IOException;
import java.sql.ResultSet;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserHelp userHelp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
        userHelp = new UserHelp();
    }
	
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userLogin.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = null;
        
        try {
            user = userHelp.login(userName, password);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(user == null) {
        	request.setAttribute("message", "Incorrect username or password");
        	request.getRequestDispatcher("/WEB-INF/view/userLogin.jsp").forward(request,response);
        	return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if(user.getUserRole().equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		else if(user.getUserRole().equals("rep")) {
			response.sendRedirect(request.getContextPath() + "/rep");
			return;
		}
        request.getRequestDispatcher("/WEB-INF/view/userDetails.jsp").forward(request,response);
		return;
	}
}