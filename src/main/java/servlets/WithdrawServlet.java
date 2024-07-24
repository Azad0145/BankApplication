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

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet{
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	PrintWriter out = response.getWriter();
	int accountNumber = (int)session.getAttribute("accountNumber");
	double withdrawAmount = Double.parseDouble(request.getParameter("withdrawAmount"));
	String pin = request.getParameter("pin");
	response.setContentType("text/html");
	
	 try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDB", "root", "password");
         con.setAutoCommit(false);
         String checkBalanceQuery = "SELECT balance FROM users WHERE account_number = ? AND pin = ?";
         PreparedStatement ps1 = con.prepareStatement(checkBalanceQuery);
         ps1.setInt(1, accountNumber);
         ps1.setString(2, pin);
         ResultSet rs1 = ps1.executeQuery();
         
         if (rs1.next() ) {
        	 
        	 if (rs1.getDouble("balance") >= withdrawAmount){
             String deductAmountQuery = "UPDATE users SET balance = balance - ? WHERE account_number = ? ";
             PreparedStatement ps2 = con.prepareStatement(deductAmountQuery);
             ps2.setDouble(1, withdrawAmount);
             ps2.setInt(2, accountNumber);
             int rowsAffected = ps2.executeUpdate();
             
             if (rowsAffected > 0) {
                 con.commit();
                 con.setAutoCommit(true);
                 out.println("Rs."+withdrawAmount+" Withdrawal successful!");
         } else {
        	 out.println("Transaction Failed!");
             con.rollback();
             con.setAutoCommit(true);
         }
         }
          else{
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
		