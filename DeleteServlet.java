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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");  // Get the ID of the record to delete

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/simplecrud";  // Change to your database name
        String username = "root";                    // Change to your database username
        String password = "Hema@123123";                    // Change to your database password

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM names WHERE id = ?")) {
             
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception as needed
        }

        response.sendRedirect("index.jsp");  // Redirect after deletion
    }
}
