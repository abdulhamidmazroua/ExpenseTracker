package com.hameed.expensetracker.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hameed.expensetracker.model.Category;
import com.hameed.expensetracker.model.Expense;
import com.hameed.expensetracker.model.SubCategory;
import com.hameed.expensetracker.model.User;

public class ExpenseDBService {
	
	private DataSource dataSource;

	public ExpenseDBService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void closeConnections(Connection conn, Statement stmt, ResultSet res) throws SQLException {
		if (conn != null)	conn.close();
		if (stmt != null)	stmt.close();
		if (res != null)	res.close();
	}
	
	public List<Expense> getAllUserExpenses(int userId) throws SQLException{
		List<Expense> expenseList = new ArrayList<>(); 
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "select  ex.id expense_id, ex.title, ex.amount, cat.id category_id, cat.category_name, \r\n"
				+ "            sub.id sub_category_id, sub.sub_category_name, users.id user_id,\r\n"
				+ "            users.username, users.password, users.first_name, users.last_name, users.email\r\n"
				+ "from expenses ex, categories cat, sub_categories sub, users\r\n"
				+ "where ex.category_id = cat.id\r\n"
				+ "    and ex.sub_category_id = sub.id\r\n"
				+ "    and ex.owneruser_id = users.id\r\n"
				+ "    and cat.id = sub.category_id"
				+ "	and ex.owneruser_id = " + userId;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			Category tempCategory;
			SubCategory tempSubCategory;
			User tempUser;
			Expense tempExpense;
			while (res.next()) {
				tempCategory = new Category(res.getInt(4), res.getString(5));
				tempSubCategory = new SubCategory(res.getInt(6), res.getString(7), tempCategory);
				tempUser = new User(res.getInt(8), res.getString(9), res.getString(10),
									res.getString(11), res.getString(12), res.getString(13));
				tempExpense = new Expense(res.getInt(1), res.getString(2), res.getInt(3),
						tempCategory, tempSubCategory, tempUser);
				expenseList.add(tempExpense);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
		return expenseList;
	}
	
	public void addExpense(Expense expense) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO expenses (title, amount, category_id, sub_category_id, owneruser_id) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, expense.getTitle());
            stmt.setInt(2, expense.getAmount());
            stmt.setInt(3, expense.getCategory().getId());
            stmt.setInt(4, expense.getSubCategory().getId());
            stmt.setInt(5, expense.getOwnerUser().getId());
            stmt.executeUpdate();
        } finally {
            closeConnections(conn, stmt, null);
        }
    }

    public void updateExpense(Expense expense) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            String sql = "UPDATE expenses SET title = ?, amount = ?, category_id = ?, sub_category_id = ?, owneruser_id = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, expense.getTitle());
            stmt.setInt(2, expense.getAmount());
            stmt.setInt(3, expense.getCategory().getId());
            stmt.setInt(4, expense.getSubCategory().getId());
            stmt.setInt(5, expense.getOwnerUser().getId());
            stmt.setInt(6, expense.getId());
            stmt.executeUpdate();
        } finally {
            closeConnections(conn, stmt, null);
        }
    }

    public void deleteExpense(int expenseId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            String sql = "DELETE FROM expenses WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, expenseId);
            stmt.executeUpdate();
        } finally {
            closeConnections(conn, stmt, null);
        }
    }
	
	// Admin's service methods
	public List<Expense> getAllExpenses() throws SQLException{
		List<Expense> expenseList = new ArrayList<>(); 
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "select  ex.id expense_id, ex.title, ex.amount, cat.id category_id, cat.category_name, \r\n"
				+ "            sub.id sub_category_id, sub.sub_category_name, users.id user_id,\r\n"
				+ "            users.username, users.password, users.first_name, users.last_name, users.email\r\n"
				+ "from expenses ex, categories cat, sub_categories sub, users\r\n"
				+ "where ex.category_id = cat.id\r\n"
				+ "    and ex.sub_category_id = sub.id\r\n"
				+ "    and ex.owneruser_id = users.id\r\n"
				+ "    and cat.id = sub.category_id";
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			Category tempCategory;
			SubCategory tempSubCategory;
			User tempUser;
			Expense tempExpense;
			while (res.next()) {
				tempCategory = new Category(res.getInt(4), res.getString(5));
				tempSubCategory = new SubCategory(res.getInt(6), res.getString(7), tempCategory);
				tempUser = new User(res.getInt(8), res.getString(9), res.getString(10),
									res.getString(11), res.getString(12), res.getString(13));
				tempExpense = new Expense(res.getInt(1), res.getString(2), res.getInt(3),
						tempCategory, tempSubCategory, tempUser);
				expenseList.add(tempExpense);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
		return expenseList;
	}
  	
}
