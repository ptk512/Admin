package com.xadmin.coursemangent.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.coursemangent.dao.CourseDao;
import com.xadmin.coursemangent.model.Course;

@WebServlet("/")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDao courseDAO;
	public void init() {
		courseDAO = new CourseDao();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Course> listUser = courseDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int courseId= Integer.parseInt(request.getParameter("courseId"));
		Course existingUser = courseDAO.selectUser(courseId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("course", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String course_name = request.getParameter("course_name");
		String course_desc = request.getParameter("course_desc");
		String course_fee = request.getParameter("course_fee");
		Course newCourse = new Course(course_name, course_desc,course_fee);
		courseDAO.insertUser(newCourse);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		String course_name = request.getParameter("course_name");
		String course_desc = request.getParameter("course_desc");
		String course_fee = request.getParameter("course_fee");

		Course book = new Course(courseId,course_name, course_desc,course_fee);
		courseDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		courseDAO.deleteUser(courseId);
		response.sendRedirect("list");

	}


   
}