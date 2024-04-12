package com.hameed.expensetracker.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.hameed.expensetracker.model.User;
import com.hameed.expensetracker.utils.UserDBService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDBService userService;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }
       

	@Override
	public void init() throws ServletException {
		super.init();
		userService = new UserDBService(dataSource);
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request parameters
		User new_user = new User(
					request.getParameter("username"),
					request.getParameter("password"),
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("email")
				);
		// insert into database
		try {
			userService.addUser(new_user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// go back to login page when finished
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		
	}

}
