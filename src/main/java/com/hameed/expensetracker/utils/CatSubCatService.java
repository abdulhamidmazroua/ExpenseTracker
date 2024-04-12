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
import com.hameed.expensetracker.model.SubCategory;

public class CatSubCatService {
	private DataSource dataSource;

	public CatSubCatService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	public void closeConnections(Connection conn, Statement stmt, ResultSet res) throws SQLException {
		if (conn != null)	conn.close();
		if (stmt != null)	stmt.close();
		if (res != null)	res.close();
	}
	// categories methods
	public List<Category> getAllCategories() throws SQLException {
		List<Category> categoriesList = new ArrayList<>();
		String sql = "select * from categories";
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				categoriesList.add(new Category(res.getInt(1), res.getString(2)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
		return categoriesList;
	}
	
	public Category getCategoryById(int categoryId) throws SQLException {
		Category category = null;
		String sql = "select * from categories where id = " + categoryId;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				category = new Category(res.getInt(1), res.getString(2));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
		return category;
	}
	
	public void addCategory(Category category) throws SQLException{
		String sql = "insert into categories (category_name)"
				+ "values (?);";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setString(1, category.getCategoryName());
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	public void updateCategory(Category category) throws SQLException {
		String sql = "update categories set category_name = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setString(1, category.getCategoryName());
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	public void deleteCategory(int categoryId) throws SQLException {
		String sql = "delete from categories where id = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setInt(1, categoryId);
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	// subcategories methods
	public List<SubCategory> getAllSubCategories() throws SQLException {
		List<SubCategory> subCategoriesList = new ArrayList<>();
		String sql = "select scat.id sub_category_id, scat.SUB_CATEGORY_NAME, cat.id category_id, cat.category_name "
				+ "from categories cat, sub_categories scat where scat.category_id = cat.id";
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				subCategoriesList.add(new SubCategory(res.getInt(1), res.getString(2), new Category(res.getInt(3), res.getString(4))));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
		return subCategoriesList;
	}
	
	public SubCategory getSubCategoryById(int subCategoryId) throws SQLException {
		SubCategory subCategory = null;
		String sql = "select scat.id sub_category_id, scat.SUB_CATEGORY_NAME, cat.id category_id, cat.category_name"
				+ " from categories cat, sub_categories scat where scat.category_id = cat.id and scat.id = " + subCategoryId;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				subCategory= new SubCategory(res.getInt(1), res.getString(2), new Category(res.getInt(3), res.getString(4)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
		return subCategory;
	}
	
	public void addSubCategory(SubCategory subCategory) throws SQLException {
		String sql = "insert into sub_categories (sub_category_name, category_id)"
				+ "values (?, ?);";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setString(1, subCategory.getSubCategoryName());
			stmt.setInt(1, subCategory.getCategory().getId());
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	public void updateSubCategory(SubCategory subCategory) throws SQLException{
		String sql = "update sub_categories set sub_category_name = ?, id = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setString(1, subCategory.getSubCategoryName());
			stmt.setInt(1, subCategory.getCategory().getId());
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
	public void deleteSubCategory(int subCategoryId) throws SQLException {
		String sql = "delete from sub_categories where id = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			// setting parameters
			stmt.setInt(1, subCategoryId);
			
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnections(conn, stmt, res);
		}
	}
	
}
