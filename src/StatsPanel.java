import java.util.List;

public class StatsPanel {
    // Shows numeric data (stats)
    private double mean;
    private double median;
    private double mode;
    private int count;
    private double min;
    private double max;

    public StatsPanel() {

    }

    public double getMean(List<Student> students) {
        double sum = 0.0;

        // Sum up total GPA
        for (Student student : students) {
            sum += student.gpa();
        }
        
        // Calculate and return the mean
        mean = sum / students.size();
        return mean;
    }

    public double getMedian(List<Student> students) {

        return median;
    }
}
