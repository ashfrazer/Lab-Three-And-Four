import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JLabel[] labels; // Array to hold labels

    public StatsPanel(List<Student> students) {
        // Initialize statsCalculator object
        StatsCalculator statsCalculator = new StatsCalculator();
        // Display stats
        setLayout(new GridLayout(7, 1));

        String[] statLabels = {
                "Number of Students: ",
                "GPA Mean: ",
                "GPA Median: ",
                "GPA Mode(s): ",
                "GPA Minimum: ",
                "GPA Maximum: ",
                "GPA Sample Std. Dev: "
        };

        // Initialize labels array
        labels = new JLabel[statLabels.length];

        // Store labels and corresponding statistics into labels array and add to GUI
        for (int i = 0; i < statLabels.length; i++) {
            labels[i] = new JLabel(statLabels[i] + getStat(i, students));
            add(labels[i]);
        }
    }

    // Update StatsPanel if user changes sample of students (tabs)
    public void updateStudents(List<Student> students) {
        for (int i = 0; i < labels.length; i++) {
            // Extracts first part of label's text (such as "GPA Mean") and retrieves new stat value
            labels[i].setText(labels[i].getText().split(": ")[0] + ": " + getStat(i, students));
        }
        revalidate();
        repaint();
    }

    private String getStat(int index, List<Student> students) {
        // Create statsCalculator object
        StatsCalculator statsCalculator = new StatsCalculator();
        // Return corresponding stat value and format to appropriate num of decimal places
        return switch (index) {
            case 0 -> String.valueOf(statsCalculator.getCount(students));
            case 1 -> String.format("%.3f", statsCalculator.getMean(students));
            case 2 -> String.format("%.3f", statsCalculator.getMedian(students));
            case 3 -> statsCalculator.getMode(students).toString();
            case 4 -> String.valueOf(statsCalculator.getMin(students));
            case 5 -> String.valueOf(statsCalculator.getMax(students));
            case 6 -> String.format("%.3f", statsCalculator.getSampleStandardDeviation(students));
            default -> "";
        };
    }
}
