import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
	
	
	String url = "jdbc:mysql://localhost:3306/elearning";
	String username = "root";
	String password = "root";
	String dbDriver = "com.mysql.jdbc.Driver";
	//String sql="select * from user1 where User_Name=? and User_Password=?";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() 
	{
		Connection con=null;
		
		try {
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public String insert(Member member) 
	{
		loadDriver(dbDriver);
		Connection con=getConnection();
		String result="Registered Successfully";
		
		String sql = "Insert into elearning.user1(User_Name, User_Email, User_Password, User_Phone) values(?,?,?,?)";
		
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getUname());
		ps.setString(2, member.getEmail());
		ps.setString(3, member.getPassword());
		ps.setString(4, member.getPhone());
		ps.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			result="Data not entered";
			
		}
		
		return result;
	}
	
	
}
