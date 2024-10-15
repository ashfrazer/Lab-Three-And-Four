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
        return String.format("%s, %s, %s, %s, %s, %s, %b, %.2f", id, gender, ethnicity, status, major, classification,
                isAthlete, gpa);
    }
}