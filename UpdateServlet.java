package simpleCRUD.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");           // Get the ID of the record to update
        String newName = request.getParameter("name");    // Get the new name

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/simplecrud";  // Change to your database name
        String username = "root";                    // Change to your database username
        String password = "Hema@123123";                    // Change to your database password

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("UPDATE names SET name = ? WHERE id = ?")) {
             
            stmt.setString(1, newName);
            stmt.setInt(2, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception as needed
        }

        response.sendRedirect("index.jsp");  // Redirect after updating
    }
}
