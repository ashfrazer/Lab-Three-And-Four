import java.io.IOException;
import java.util.List;

public class DataVisualizer {
    public static void main(String[] args) throws IOException {
        LoadData loadData = new LoadData();

        // TO-DO: Fix this so that the filepath is universal for others
        String filepath = "C:\\Users\\Ash\\IdeaProjects\\Lab Three\\src\\fall23_filtered_checkin_data.txt";

        // Create list of students
        List<Student> students = loadData.loadStudents(filepath);

        for (Student student : students) {
            System.out.println(student);
        }
    }
}