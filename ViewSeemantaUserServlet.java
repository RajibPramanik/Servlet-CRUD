import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewSeemantaUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<a href='index.html'>Add New Seemanta User</a>");
        out.println("<h1>Seemanta Users List</h1>");

        List<SeemantaUser> list = null;

        try {
            list = SeemantaUserDao.getAllSeemantaUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print("<table border='1' style='border-collapse: collapse;' width='70%'>");
        out.print("<tr>"
                + "<th>ID</th>"
                + "<th>Name</th>"
                + "<th>Number of Posts</th>"
                + "<th>Preferred Technology</th>"
                + "<th>Edit</th>"
                + "<th>Delete</th>"
                + "</tr>");

        if (list != null) {
            for (SeemantaUser user : list) {
                out.print("<tr>"
                        + "<td>" + user.getId() + "</td>"
                        + "<td>" + user.getName() + "</td>"
                        + "<td>" + user.getNumberOfPosts() + "</td>"
                        + "<td>" + user.getTechnologiesPreferred() + "</td>"
                        + "<td><a href='EditServlet?id=" + user.getId() + "'>Edit</a></td>"
                        + "<td><a href='DeleteServlet?id=" + user.getId() + "'>Delete</a></td>"
                        + "</tr>");
            }
        }

        out.print("</table>");
        out.close();
    }
}
