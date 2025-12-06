public class SeemantaUser {
    private int student_id;  
    private int numberOfPosts;
    private String student_name, technologiesPreferred;  

    // Getter for student_id
    public int getId() {
        return student_id;
    }

    // Setter for student_id
    public void setId(int id) {
        this.student_id = id;
    }

    // Getter for student_name
    public String getName() {
        return student_name;
    }

    // Setter for student_name
    public void setName(String name) {
        this.student_name = name;
    }

    // Getter for numberOfPosts
    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    // Setter for numberOfPosts
    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    // Corrected getter method for technologiesPreferred
    public String getTechnologiesPreferred() {
        return technologiesPreferred;
    }

    // Setter for technologiesPreferred
    public void setTechnologiesPreferred(String technologiesPreferred) {
        this.technologiesPreferred = technologiesPreferred;
    }
}
