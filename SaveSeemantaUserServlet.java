import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveSeemantaUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get form data from the request
            String name = request.getParameter("name");
            String numberPostsStr = request.getParameter("numberOfPosts");
            String technologiesPreferred = request.getParameter("technologiesPreferred");

            // Get the user ID (for editing)
            String idParam = request.getParameter("id");  // Check for ID in form
            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

            // Validate numberOfPosts
            int numberOfPosts = 0;
            try {
                numberOfPosts = Integer.parseInt(numberPostsStr);
            } catch (NumberFormatException e) {
                out.println("<p style='color:red;'>Invalid number of posts. Please enter a valid number.</p>");
                request.getRequestDispatcher("index.html").include(request, response);
                return;
            }

            // Create SeemantaUser object and set properties
            SeemantaUser su = new SeemantaUser();
            su.setName(name);
            su.setNumberOfPosts(numberOfPosts);
            su.setTechnologiesPreferred(technologiesPreferred);

            // Set the ID if it's an edit (update) operation
            if (id > 0) {
                su.setId(id); // Set the existing user's ID for updating
            }

            // Save or update the SeemantaUser object to the database
            int status = (id > 0) ? SeemantaUserDao.update(su) : SeemantaUserDao.save(su); // Update or Save

            if (status > 0) {
                // Success
                out.print("<p style='color:green;'>Record saved successfully!</p>");
                request.getRequestDispatcher("index.html").include(request, response);
            } else {
                out.println("<p style='color:red;'>Sorry! Unable to save record. Check server console for details.</p>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();  // Show the real error in console
            out.println("<p style='color:red;'>Error: " + ex.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }
}
