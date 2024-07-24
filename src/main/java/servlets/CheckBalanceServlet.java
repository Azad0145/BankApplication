package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String pin = request.getParameter("pin");
		response.setContentType("text/html");
		
		try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDB", "root", "password");
	         String CheckBalanceQuery = "SELECT balance FROM users WHERE account_number = ? AND pin = ? ";
	         PreparedStatement ps = con.prepareStatement(CheckBalanceQuery);
	         ps.setInt(1, accountNumber);
	         ps.setString(2, pin);
	         ResultSet rs = ps.executeQuery();
	         
	         if (rs.next()) {
	        	 out.println("Available balance Rs."+rs.getDouble("balance"));
	         }
	         else {
	        	 out.println("Invalid username or Password");
	         }
	}
		 catch (Exception e) {
             out.println("Database connection error: " + e.getMessage());
         }
out.println("<br><a href = homepage.jsp> Home </a>");
         out.close();
	}

}