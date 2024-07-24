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
import javax.servlet.http.HttpSession;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int SenderAccountNumber = (int) session.getAttribute("accountNumber");
		int ReceiverAccountNumber = Integer.parseInt(request.getParameter("ReceiverAccountNumber"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String pin = request.getParameter("pin");
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDB", "root", "password");
	         con.setAutoCommit(false);
	         String CheckBalanceQuery = "SELECT balance FROM users WHERE account_number = ? AND pin = ? ";
	         PreparedStatement ps1 = con.prepareStatement(CheckBalanceQuery);
	         ps1.setInt(1, SenderAccountNumber);
	         ps1.setString(2, pin);
	         ResultSet rs = ps1.executeQuery();
	         
	         if (rs.next()) {
	        	 if (SenderAccountNumber == ReceiverAccountNumber) {
	        		 out.println("You cannot transfer money to your own account");
	        		 out.print("<a href = home.jsp> Home </a>");
	        		 return;
	        	 }
	   
	        	 if (rs.getDouble("balance") >= amount) {
	        		 String CreditAmountQuery = "UPDATE users SET balance = balance + ? WHERE account_number = ?";
	    	         PreparedStatement ps2 = con.prepareStatement(CreditAmountQuery);
	    	         ps2.setInt(1, amount);
	    	         ps2.setInt(2, ReceiverAccountNumber);
	    	         int rowsAffected1 = ps2.executeUpdate();
	    	         
	    	         String DebitAmountQuery = "UPDATE users SET balance = balance - ? WHERE account_number = ?";
	    	         PreparedStatement ps3 = con.prepareStatement(DebitAmountQuery);
	    	         ps3.setInt(1, amount);
	    	         ps3.setInt(2, SenderAccountNumber);
	    	         int rowsAffected2 = ps3.executeUpdate();
	    	         
	    	         if (rowsAffected1 > 0 && rowsAffected2 > 0) {
	    	        	 con.commit();
	                     con.setAutoCommit(true);
	                     out.println("Money Transfer successful!");
	    	         }
	    	         else {
	    	        	 out.println("Invalid account Number!");
	    	             con.rollback();
	    	             con.setAutoCommit(true);
	    	         }
	        	 }
	        	 else {
	        		 out.println("Insufficient Balance!");
	        	 }
	 
	         }
	         else {
	        	 out.println("Invalid Pin!");
	         }
	         con.close();
		}
        catch (Exception e) {
            out.println("Database connection error: " + e.getMessage());
        }
       out.println("<br> <a href = homepage.jsp> Home </a> ");
            out.close();
	}

}
