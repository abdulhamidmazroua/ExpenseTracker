package com.hameed.expensetracker.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.hameed.expensetracker.model.Category;
import com.hameed.expensetracker.model.Expense;
import com.hameed.expensetracker.model.SubCategory;
import com.hameed.expensetracker.model.User;
import com.hameed.expensetracker.utils.CatSubCatService;
import com.hameed.expensetracker.utils.ExpenseDBService;
import com.hameed.expensetracker.utils.UserDBService;

/**
 * Servlet implementation class ExpenseServlet
 */
@WebServlet("/ExpenseServlet")
public class ExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ExpenseDBService expenseService;
	private CatSubCatService catSubCatService;
	private UserDBService userService;
	
	private List<Category> categories;
    private List<SubCategory> subCategories;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
       
    public ExpenseServlet() {
        super();
    }
    	
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// services initialization
		expenseService = new ExpenseDBService(dataSource);
		catSubCatService = new CatSubCatService(dataSource);
		userService = new UserDBService(dataSource);
		
		// lists initialization
		try {
			categories = catSubCatService.getAllCategories();
			subCategories = catSubCatService.getAllSubCategories();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create a choice for the action command
		String command = request.getParameter("command");
		switch(command) {
			case "INIT":
				initializeExpensePage(request, response);
				return;
			case "LIST":
				listExpense(request, response);
				return;
			case "ADD":
				addExpense(request, response);
				return;
			case "UPDATE":
				
			case "DELETE":
				
			default:
				listExpense(request, response);
				
		}
	}
	
	private void initializeExpensePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories_list", categories);
		request.setAttribute("subCategories_list", subCategories);
		listExpense(request, response);
	}
	
	private void listExpense(HttpServletRequest request, HttpServletResponse response) {
		List<Expense> expenses_list = new ArrayList<>();
		try {
			expenses_list  = expenseService.getAllUserExpenses((int)(request.getSession().getAttribute("userId")));
			request.setAttribute("expenses_list", expenses_list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/expenses.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addExpense(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Creating the new expense object
			Category category = catSubCatService.getCategoryById(Integer.valueOf(request.getParameter("expense-category")));
			SubCategory subCategory = catSubCatService.getSubCategoryById(Integer.valueOf(request.getParameter("expense-subcategory")));
			User ownerUser = userService.getUserById((int)(request.getSession().getAttribute("userId")));
			Expense expense = new Expense(
						request.getParameter("expense-title"),
						Integer.valueOf(request.getParameter("expense-amount")),
						category,
						subCategory,
						ownerUser
					);
			expenseService.addExpense(expense);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		listExpense(request, response);
	}

}
