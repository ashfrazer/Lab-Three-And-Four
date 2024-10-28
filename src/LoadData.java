import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LoadData {
    private static final String NA_FIELD = "#N/A";
    public List<Student> loadStudents(String filePath) throws IOException {
        // List of students
        List<Student> students = new ArrayList<>();

        // Set of student IDs (to avoid duplicate entries)
        Set<String> uniqueIds = new HashSet<>();

        // Read in file
        BufferedReader br = Files.newBufferedReader(Paths.get(filePath));
            String line;

            // Process each line
            while ((line = br.readLine()) != null) {
                // Regex to handle fields with commas and quotation marks
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (fields.length < 8) {
                    // Skip lines that do not have at least 8 fields (the first 8 fields are all that matter)
                    continue;
                }

                // Store fields into corresponding variables
                String id = fields[0].trim();
                String gender = fields[1].trim();
                String ethnicity = fields[2].trim();
                String status = fields[3].trim();
                String major = fields[4].trim().replace("\"", ""); // Remove quotes from majors
                String classification = fields[5].trim();
                boolean isAthlete = fields[6].trim().equalsIgnoreCase("y"); // Athlete status: y/n

                String gpaString = fields[7].trim(); // Read GPA as a string
                double gpa = 0.0;

                // Check for invalid entries
                if (id.equals(NA_FIELD) || gender.equals(NA_FIELD) || major.isEmpty() || gpaString.isEmpty() ||
                        gpaString.equals(NA_FIELD)) {
                    // Skip entry if important field is empty
                    continue;
                }

                try {
                    // Parse the GPA
                    gpa = Double.parseDouble(gpaString);

                    // Round GPA to two decimal places
                    gpa = Math.round(gpa * 100.0) / 100.0;
                } catch (NumberFormatException e) {
                    // Skip student if GPA is invalid
                    continue;
                }

                // Add student to the list and set if they are not already added
                if (!uniqueIds.contains(id)) {
                    uniqueIds.add(id);
                    students.add(new Student(id, gender, ethnicity, status, major, classification, isAthlete, gpa));
                }
            }

        return students;
    }
}
