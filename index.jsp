<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
    <title>Simple CRUD</title>
</head>
<body>
    <h1>Simple CRUD Application</h1>
    <form action="CreateServlet" method="post">
        Name: <input type="text" name="name" required />
        <input type="submit" value="Add" />
    </form>

    <h2>List of Names</h2>
    <%
        List<String> names = new ArrayList<>();
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/simplecrud";  
        String username = "root";                    
        String password = "Hema@123123";                    

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT name FROM names");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                names.add(rs.getString("name")); // Add retrieved names to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        for (String name : names) {
            out.println(name + " <a href='UpdateServlet?id=" + name + "'>Edit</a> " +
                        "<a href='DeleteServlet?id=" + name + "'>Delete</a><br>");
        }
    %>
</body>
</html>
