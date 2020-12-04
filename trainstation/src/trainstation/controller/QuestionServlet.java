package trainstation.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import trainstation.help.QuestionHelp;
import trainstation.model.Question;

import javax.servlet.annotation.WebServlet;
@WebServlet("/question")

public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
    }
	
    public QuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Question> questions = null;
		
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Customer/question.jsp");
		dispatcher.forward(request, response);
		return;
	}
}