import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet2")
public class EditSeemantaUserServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve the ID from the form (hidden field)
            String sid = request.getParameter("id");
            if (sid == null || sid.isEmpty()) {
                out.println("<p style='color:red;'>Invalid user ID.</p>");
                return;
            }
            int id = Integer.parseInt(sid);

            // Retrieve the other form data
            String name = request.getParameter("name");
            String numberOfPostsStr = request.getParameter("numberOfPosts");
            String technologiesPreferred = request.getParameter("technologiesPreferred");

            // Validate numberOfPosts
            int numberOfPosts = 0;
            try {
                numberOfPosts = Integer.parseInt(numberOfPostsStr);
            } catch (NumberFormatException e) {
                out.println("<p style='color:red;'>Invalid number of posts. Please enter a valid number.</p>");
                return;
            }

            // Create SeemantaUser object and set values
            SeemantaUser su = new SeemantaUser();
            su.setId(id);
            su.setName(name);
            su.setNumberOfPosts(numberOfPosts);
            su.setTechnologiesPreferred(technologiesPreferred);

            // Call the update method to update the record in the database
            int status = SeemantaUserDao.update(su);

            // If the update was successful, redirect to ViewServlet
            if (status > 0) {
                response.sendRedirect("ViewServlet");
            } else {
                out.println("<p style='color:red;'>Sorry! Unable to update record.</p>");
            }

        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Error: Invalid input data.</p>");
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }
}
