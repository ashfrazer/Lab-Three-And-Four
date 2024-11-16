import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JLabel[] labels; // Array to hold labels
    private StatsCalculator statsCalculator;

    public StatsPanel(List<Student> students) {
        // Initialize statsCalculator object
        statsCalculator = new StatsCalculator();
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
        if (students.isEmpty()) {
            for (int i = 0; i < labels.length; i++) {
                labels[i].setText(labels[i].getText().split(": ")[0] + ": none");
            }
        } else {
            for (int i = 0; i < labels.length; i++) {
                labels[i].setText(labels[i].getText().split(": ")[0] + ": " + getStat(i, students));
            }
        }
        revalidate();
        repaint();
    }

    private String getStat(int index, List<Student> students) {
        return switch (index) {
            case 0 -> String.valueOf(getCount(students)); // Count
            case 1 -> String.format("%.3f", getMean(students)); // Mean
            case 2 -> String.format("%.3f", getMedian(students)); // Median
            case 3 -> String.valueOf(getMode(students)); // Mode
            case 4 -> String.valueOf(getMin(students)); // Minimum
            case 5 -> String.valueOf(getMax(students)); // Maximum
            case 6 -> String.format("%.3f", getSampleStdDeviation(students)); // Sample StdDev
            default -> "";
        };
    }

    // Methods to execute corresponding strategy

    private double getMean(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPAMeanConcreteStrategy());
        return statsCalculator.calculateStat(students);
    }

    private double getMedian(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPAMedianConcreteStrategy());
        return statsCalculator.calculateStat(students);
    }

    private Object getMode(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPAModeConcreteStrategy());
        return statsCalculator.calculateStat(students);
    }

    private double getCount(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPACountConcreteStrategy());
        return statsCalculator.calculateStat(students);
    }

    private double getMin(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPAMinConcreteStrategy());
        return statsCalculator.calculateStat(students);
    }

    private double getMax(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPAMaxConcreteStrategy());
        return statsCalculator.calculateStat(students);
    }

    private double getSampleStdDeviation(List<Student> students) {
        statsCalculator.setGPAStrategy(new GPASampleStdDeviationConcreteStrategy(new GPAMeanConcreteStrategy()));
        return statsCalculator.calculateStat(students);
    }
}
