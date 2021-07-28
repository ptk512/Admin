package com.xadmin.coursemangent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.coursemangent.model.Course;




public class CourseDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/elearning?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USERS_SQL = "INSERT INTO course" + "  (course_name, course_desc, course_fee) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select courseId,course_name, course_desc, course_fee from course where courseId =?";
	private static final String SELECT_ALL_USERS = "select * from course";
	private static final String DELETE_USERS_SQL = "delete from course where courseId = ?;";
	private static final String UPDATE_USERS_SQL = "update course set course_name = ?,course_desc= ?, course_fee =? where courseId = ?;";

	public CourseDao() {
	}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public void insertUser(Course user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getCourse_name());
			preparedStatement.setString(2, user.getCourse_desc());
			preparedStatement.setString(3, user.getCourse_fee());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public Course selectUser(int courseId) {
		Course user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, courseId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String course_name = rs.getString("course_name");
				String course_desc = rs.getString("course_desc");
				String course_fee = rs.getString("course_fee");
				user = new Course(courseId, course_name, course_desc, course_fee);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	public List<Course> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Course> course = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int courseId = rs.getInt("courseId");
				String course_name = rs.getString("course_name");
				String course_desc = rs.getString("course_desc");
				String course_fee = rs.getString("course_fee");
				course.add(new Course(courseId, course_name, course_desc, course_fee));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return course;
	}
	public boolean deleteUser(int courseId) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, courseId);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	public boolean updateUser(Course user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, user.getCourse_name());
			statement.setString(2, user.getCourse_desc());
			statement.setString(3, user.getCourse_fee());
			statement.setInt(4, user. getCourseId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}


}
