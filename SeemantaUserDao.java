import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeemantaUserDao {
    private static final String url = "jdbc:mysql://localhost:3306/seemanta";
    private static final String user = "root";
    private static final String password = "Rajib@8018#";

    // Get database connection
    public static Connection getConnection() throws Exception {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection successful");
            return con;
        } catch (Exception e) {
            System.out.println("Database connection failed:");
            e.printStackTrace();
            throw e;
        }
    }

    // Save a new Seemanta user
    public static int save(SeemantaUser e) throws Exception {
        String sql = "INSERT INTO stu(student_name, numberOfPosts, technologiesPreferred) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getName());
            ps.setInt(2, e.getNumberOfPosts());
            ps.setString(3, e.getTechnologiesPreferred());

            return ps.executeUpdate();
        }
    }

    // Retrieve all Seemanta users
    public static List<SeemantaUser> getAllSeemantaUser() throws Exception {
        List<SeemantaUser> list = new ArrayList<>();
        String sql = "SELECT * FROM stu";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SeemantaUser s = new SeemantaUser();
                s.setId(rs.getInt("student_id"));
                s.setName(rs.getString("student_name"));
                s.setNumberOfPosts(rs.getInt("numberOfPosts"));
                s.setTechnologiesPreferred(rs.getString("technologiesPreferred"));
                list.add(s);
            }
        }
        return list;
    }

    // Retrieve SeemantaUser by ID
    public static SeemantaUser getSeemantaUserById(int id) throws Exception {
        SeemantaUser s = null;
        String sql = "SELECT * FROM stu WHERE student_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new SeemantaUser();
                    s.setId(rs.getInt("student_id"));
                    s.setName(rs.getString("student_name"));
                    s.setNumberOfPosts(rs.getInt("numberOfPosts"));
                    s.setTechnologiesPreferred(rs.getString("technologiesPreferred"));
                }
            }
        }
        return s;
    }

    // Update a Seemanta user
    public static int update(SeemantaUser s) throws Exception {
        String sql = "UPDATE stu SET student_name = ?, numberOfPosts = ?, technologiesPreferred = ? WHERE student_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getNumberOfPosts());
            ps.setString(3, s.getTechnologiesPreferred());
            ps.setInt(4, s.getId());

            return ps.executeUpdate();
        }
    }

    // Delete a Seemanta user
    public static int delete(int id) throws Exception {
        String sql = "DELETE FROM stu WHERE student_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}
