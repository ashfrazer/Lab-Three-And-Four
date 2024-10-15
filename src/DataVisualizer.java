import java.io.IOException;
import java.util.List;

public class DataVisualizer {
    public static void main(String[] args) throws IOException {
        LoadData loadData = new LoadData();

        // Declare file paths for fall and spring datasets
        String filepath_fall = "src/fall23_filtered_checkin_data.txt";
        String filepath_spring = "src/spr23_filtered_checkin_data.txt";

        // Create list of students from fall semester dataset
        List<Student> students_fall = loadData.loadStudents(filepath_fall);

        // Create list of students from spring semester dataset
        List<Student> students_spring = loadData.loadStudents(filepath_spring);

        // Initialize StatsPanel object
        StatsPanel statsPanel = new StatsPanel();

        // Initialize StatsCalculator object
        StatsCalculator statsCalculator = new StatsCalculator();

    }
}