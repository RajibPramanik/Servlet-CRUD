import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditSeemantaUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Update Seemanta College User</h1>");

        // Retrieve the user ID from the query string (URL)
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            out.println("<p>Invalid ID provided.</p>");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idParam); // Convert the ID to an integer
        } catch (NumberFormatException e) {
            out.println("<p>Invalid ID format.</p>");
            return;
        }

        SeemantaUser s = null;
        try {
            s = SeemantaUserDao.getSeemantaUserById(id); // Fetch user from DB using the ID
        } catch (Exception e) {
            out.println("<p>Error fetching user details. Please try again later.</p>");
            return;
        }

        if (s == null) {
            out.println("<p>User not found.</p>");
            return;
        }

        // Display form with the user's details for editing
        out.println("<form action='SaveServlet' method='post'>");
        out.println("<table>");
        out.print("<tr><td><input type='hidden' name='id' value='" + s.getId() + "'/></td></tr>");
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + s.getName() + "'/></td></tr>");
        out.print("<tr><td>Number Of Posts:</td><td><input type='text' name='numberOfPosts' value='" + s.getNumberOfPosts() + "'/></td></tr>");
        out.print("<tr><td>Technologies Preferred:</td><td><input type='text' name='technologiesPreferred' value='" + s.getTechnologiesPreferred() + "'/></td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save'/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}
