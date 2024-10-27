public record Student(String id,
                      String gender,
                      String ethnicity,
                      String status,
                      String major,
                      String classification,
                      boolean isAthlete,
                      double gpa) {

    @Override
    public String toString() {
        // Formats string to include commas and round GPA (similar to Python f-strings)
        return String.format("""
                ID: %s\s
                Gender: %s\s
                Ethnicity: %s\s
                Status: %s\s
                Major(s): %s\s
                Classification: %s\
                
                Athlete?: %b\s
                GPA: %.2f""",
                id, gender, ethnicity, status, major, classification, isAthlete, gpa);
    }
}