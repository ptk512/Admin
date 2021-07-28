

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Course Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: black">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Course Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Course</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${course != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${course == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${course != null}">
            			Edit Course
            		</c:if>
						<c:if test="${course == null}">
            			Add New Course
            		</c:if>
					</h2>
				</caption>

				<c:if test="${course != null}">
					<input type="hidden" name="id" value="<c:out value='${course.courseId}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Course Name</label> <input type="text"
						value="<c:out value='${course.course_name}' />" class="form-control"
						name="course_name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Course Description</label> <input type="text"
						value="<c:out value='${course.course_desc}' />" class="form-control"
						name="course_desc">
				</fieldset>

				<fieldset class="form-group">
					<label>Course Fee</label> <input type="text"
						value="<c:out value='${course.course_fee}' />" class="form-control"
						name="course_fee">
				</fieldset>

				<button type="submit" class="btn btn-success">Course ADD</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>