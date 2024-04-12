package com.hameed.expensetracker.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hameed.expensetracker.model.User;

public class UserDBService {
	private DataSource dataSource;

	public UserDBService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void closeConnections(Connection conn, Statement stmt, ResultSet res) throws SQLException {
		if (conn != null)	conn.close();
		if (stmt != null)	stmt.close();
		if (res != null)	res.close();
	}
	
	public boolean authenticateUser(String username, String password) throws SQLException {
		
		String sql = "select username, password from users where username = ? and password = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);	// you must add a hashing functionality
			
			res = stmt.executeQuery();
			
			if (res.next()) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally  {
			closeConnections(conn, stmt, res);
		}
		
		return false;
	}
	
	public void addUser(User user) throws SQLException {
		String sql = "insert into users (username, password, first_name, last_name, email)"
				+ "values (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword()); // you must add a hashing functionality
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	public void updateUser(User user) throws SQLException {
		String sql = "update users set username = ?, password = ?, first_name = ?, last_name = ?, email = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword()); // you must add a hashing functionality
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	public void deleteUser(int userId) throws SQLException {
		String sql = "delete from users where id = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setInt(1, userId);
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	
	// Admin's service methods
	public List<User> getAllUsers() throws SQLException {
		String sql = "select id, username, password, first_name, last_name, email from users";
		List<User> usersList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				usersList.add(new User(res.getInt(1), res.getString(2), res.getString(3),
									res.getString(4), res.getString(5), res.getString(6)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally  {
			closeConnections(conn, stmt, res);
		}
		return usersList;
	}
	
	public User getUserById(int userId) throws SQLException {
		String sql = "select id, username, password, first_name, last_name, email from users"
				+ " where id = " + userId;
		User user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				user = new User(res.getInt(1), res.getString(2), res.getString(3),
									res.getString(4), res.getString(5), res.getString(6));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally  {
			closeConnections(conn, stmt, res);
		}
		return user;
	}
	
	public User getUserByName(String username) throws SQLException {
		String sql = "select id, username, password, first_name, last_name, email from users"
				+ " where username = '" + username + "'";
		User user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				user = new User(res.getInt(1), res.getString(2), res.getString(3),
									res.getString(4), res.getString(5), res.getString(6));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally  {
			closeConnections(conn, stmt, res);
		}
		return user;
	}
	
}