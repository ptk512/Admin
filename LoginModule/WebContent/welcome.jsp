<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.sql.*" %>    
    <%@ page import="java.util.*" %>   
     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="orange">

<%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if(session.getAttribute("username")==null)
	{
		response.sendRedirect("login.jsp");
	}
	
	
	String url="jdbc:mysql://localhost:3306/elearning";
	String username = "root";
	String password = "root";
	String sql = "select * from Course";
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,username,password);

	Statement st=con.createStatement();
	
	ResultSet rs = st.executeQuery(sql);
	
	 %>
	 <h1>
	 
	<% 
	while (rs.next()) {
		//int i=1;
		out.print("Course :"+  rs.getString(1) +" : "+rs.getString(2)+" | "+rs.getString(3)+" at Rs "+rs.getString(4));
		
		
		%>
		
		
		<form action="enroll.jsp" method="post">
 	<input type="submit" value="Enroll">
		</form>
		
		<%
		out.print("<br/>");
		
	}
	
	%>
	</h1>
	


<p>
<a href="videos.jsp">Videos here</a>
</p>

<form action="Logout" method="post">
	<input type="submit" value="Logout">
</form>

</body>
</html>