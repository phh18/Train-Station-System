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
import trainstation.model.TrainSchedule;
import trainstation.model.User;
import trainstation.model.Reservation;
import trainstation.help.ReservationHelp;

@WebServlet("/reserve")
public class ResHistoryServlet {
	private static final long serialVersionUID = 1L;
	private ReservationHelp reservationHelp;
	
	public void init() {
        reservationHelp = new ReservationHelp();
    }
	
    public ResHistoryServlet() {
        super();
    }
    
    
}
