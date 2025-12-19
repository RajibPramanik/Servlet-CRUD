import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteSeemantaUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);

            SeemantaUserDao.delete(id);
            response.sendRedirect("ViewServlet");

        } catch (Exception e) {
            throw new ServletException(e);  // Convert to servlet-friendly exception
        }
    }
}