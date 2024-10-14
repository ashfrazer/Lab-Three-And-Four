public class Student {
    private String id;
    private String gender;
    private String ethnicity;
    private String status;
    private String major;
    private String classification;
    private boolean is_athlete;
    private double gpa;

    // Constructor
    public Student(String id, String gender, String ethnicity, String status, String major, String classification,
                   boolean is_athlete, double gpa) {
        this.id = id;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.status = status;
        this.major = major;
        this.classification = classification;
        this.is_athlete = is_athlete;
        this.gpa = gpa;
    }
    // Getters and setters for ID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters for Gender
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getters and setters for Ethnicity
    public String getEthnicity() {
        return ethnicity;
    }
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    // Getters and setters for Status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and setters for Major(s)
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    // Getters and setters for Classification
    public String getClassification() {
        return classification;
    }
    public void setClassification(String classification) {
        this.classification = classification;
    }

    // Getters and setters for Athlete Status
    public boolean isIs_athlete() {
        return is_athlete;
    }
    public void setIs_athlete(boolean is_athlete) {
        this.is_athlete = is_athlete;
    }

    // Getters and setters for GPA
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        // Formats string to include commas and round GPA (similar to Python f-strings)
        return String.format("%s, %s, %s, %s, %s, %s, %b, %.2f", id, gender, ethnicity, status, major, classification,
                is_athlete, gpa);
    }
}
