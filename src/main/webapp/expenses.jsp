<%@page import="java.util.ArrayList"%>
<%@page import="com.hameed.expensetracker.model.Category"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Expenses Page</title>
<link rel="stylesheet" href="css/expenses_style.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
</head>

<body>
	<header class="header-bar">
		<div class="logo-container">
			<div class="logo">
				<span class="logo-text">Expensia</span>
			</div>
		</div>
		<div class="search-area">
			<input type="text" placeholder="Search..." class="search-input">
			<button class="search-button">Search</button>
		</div>
		<nav class="navigation">
			<ul>
				<li><a href="#">Expenses</a></li>
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Planning</a></li>
				<li><a href="#">Reports</a></li>
				<li><a href="#">About Us</a></li>
			</ul>
		</nav>
		<div class="user-info">
			<span class="username">${sessionScope.username}</span> <img
				src="images/user-icon.png" alt="User Icon" class="user-icon">
		</div>
	</header>

	<div class="content">
		<div class="expenses-container">
			<div class="expenses-filter">
				<label for="year">Year:</label> <select id="year">
					<option value="2024">2024</option>
					<!-- Add more options for years -->
				</select> <label for="month">Month:</label> <select id="month">
					<option value="1">January</option>
					<!-- Add more options for months -->
				</select> <label for="date">Date:</label> <input type="date" id="date">
				<label for="category">Category:</label> <select id="category">
					<option value="food">Food</option>
					<!-- Add more options for categories -->
				</select>
				<button id="clear-all">Clear All</button>
				<button id="find">Find</button>
			</div>
			<div class="expenses-list">
				<!-- Internal boxes for displaying expenses will be dynamically added here -->
				<c:forEach var="tempExpense" items="${expenses_list}">
					<div class="expense-box">
						<form action="/updateExpense" method="post">
							<input type="hidden" name="expenseId" value="${tempExpense.id}">
							<label for="title">Title:</label> <input type="text" id="title"
								name="title" value="${tempExpense.title}"> <label
								for="amount">Amount:</label> <input type="text" id="amount"
								name="amount" value="${tempExpense.amount}"> <label
								for="category">Category:</label> <select id="category"
								name="category">
								<!-- Assuming you have a list of categories available -->
								<c:forEach var="category" items="${categories_list}">
									<option value="${category.id}"
										${tempExpense.category.id eq category.id ? 'selected' : ''}>${category.categoryName}</option>
								</c:forEach>
							</select> <label for="subCategory">Sub-category:</label> <select
								id="subCategory" name="subCategory">
								<!-- Assuming you have a list of sub-categories available -->
								<c:forEach var="subCategory" items="${subCategories_list}">
									<option value="${subCategory.id}"
										${tempExpense.subCategory.id eq subCategory.id ? 'selected' : ''}>${subCategory.subCategoryName}</option>
								</c:forEach>
							</select>
							<button type="submit">Update Expense</button>
						</form>
					</div>
				</c:forEach>
			</div>

		</div>
		<div class="sidebar">
			<div class="add-expense-box">
				<form id="add-expense-form" action="ExpenseServlet" method="GET">
					<h2>Add Expense</h2>
					<input type="hidden" name="command" value="ADD">
					<div class="input-group">
						<label for="expense-title">Title:</label> <input type="text"
							id="expense-title" name="expense-title" required>
					</div>
					<div class="input-group">
						<label for="expense-amount">Amount:</label> <input type="number"
							step="0.01" id="expense-amount" name="expense-amount" required>
					</div>
					<div class="input-group">
						<label for="expense-category">Category:</label> <select
							id="expense-category" name="expense-category" required>
							<c:forEach var="tempCategory" items="${categories_list}">
								<option value="${tempCategory.id}">${tempCategory.categoryName}</option>
							</c:forEach>
							<!-- Add more options for categories -->
						</select>
					</div>
					<div class="input-group">
						<label for="expense-subcategory">Sub-category:</label> <select
							id="expense-subcategory" name="expense-subcategory" required>
							<c:forEach var="tempSubCategory" items="${subCategories_list}">
								<option value="${tempSubCategory.id}">${tempSubCategory.subCategoryName }</option>
							</c:forEach>
							<!-- Add more options for sub-categories -->
						</select>
					</div>
					<button type="submit">Add Expense</button>
				</form>
			</div>
			<div class="recent-expenses-box">
				<h2>Recent Expenses</h2>
				<div class="expense-box">
					<!-- Recent expense details -->
				</div>
				<!-- Repeat for the second recent expense box -->
				<div class="expense-box">
					<!-- Recent expense details -->
				</div>
				<!-- Repeat for the third recent expense box -->
				<div class="expense-box">
					<!-- Recent expense details -->
				</div>
			</div>
		</div>
	</div>

	<footer class="footer">
		<div class="footer-content">
			<p>&copy; 2024 Expensia. All rights reserved.</p>
			<div class="social-icons">
				<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i
					class="fab fa-twitter"></i></a> <a href="#"><i
					class="fab fa-instagram"></i></a>
			</div>
		</div>
	</footer>
</body>
</html>
