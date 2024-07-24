

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pin = request.getParameter("pin");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDB", "root", "password");

            String query = "SELECT * FROM users WHERE username = ? AND email = ? AND pin = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("username", rs.getString("username"));
                request.setAttribute("accountNumber", rs.getInt("account_number"));
                request.setAttribute("balance", rs.getDouble("balance"));
                HttpSession session = request.getSession();
                session.setAttribute("username",rs.getString("username"));
				session.setAttribute("accountNumber", rs.getInt("account_number"));
                RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
                rd.forward(request, response);
            } else {
                out.println("Invalid credentials!");
            }

            con.close();
        } catch (Exception e) {
            out.println("Database connection error: " + e.getMessage());
        }

        out.close();
    }
}


