<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if (session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		}

		String url = "jdbc:mysql://localhost:3306/elearning";
		String username = "root";
		String password = "root";
		String sql = "Insert into elearning.enroll(enrollUser_Name,enroll_Course) values('ramu','java programming')";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);

		Statement st=con.createStatement();
	
		st.executeUpdate(sql);
		
		
	%>


	Congratulations...you have successfully enrolled the course
</body>
</html>