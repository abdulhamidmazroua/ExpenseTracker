<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/login_style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
    <header class="header-bar">
        <div class="logo-container">
            <div class="logo">
                <span class="logo-text">Expensia</span>
            </div>
        </div>
        <div class="welcome-message">
	        <p>Welcome!</p>
	    </div>
    </header>

    <div class="content">
       <div class="login-container">
           <form class="login-form" action="LoginServlet" method="GET">
               <h2>Login</h2>
               <input type="hidden" name="command" value="INIT">
				<c:if test="${not empty errorMessage}">
					<p style="color: red;">${errorMessage}</p>
				</c:if>
				<div class="input-group">
                   <label for="username">Username</label>
                   <input type="text" id="username" name="username" required>
               </div>
               <div class="input-group">
                   <label for="password">Password</label>
                   <input type="password" id="password" name="password" required>
               </div>
               <button type="submit">Login</button>
                <p class="register-link">Don't have an account? <a href="register.html">Register</a></p>
           </form>
       </div>
    </div>
</body>
</html>