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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	private UserDBService userService;
	
    public LoginServlet() {
        super();
    }
    

	@Override
	public void init() throws ServletException {
		super.init();
		userService = new UserDBService(dataSource);
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get login form parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			// authenticate user/password
			boolean isAuthenticated = userService.authenticateUser(username, password);
			// send to error page or to expenses page
			RequestDispatcher dispatcher;
			if (isAuthenticated) {
				User user = userService.getUserByName(username);
				request.setAttribute("errorMessage", "");
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("userId", user.getId());
				dispatcher = request.getRequestDispatcher("ExpenseServlet");
			} else {
				request.setAttribute("errorMessage", "Incorrect username or password");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
