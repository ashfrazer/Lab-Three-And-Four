import java.util.*;

public class StatsCalculator {
    private final List<Double> modes;

    public StatsCalculator() {
        // Initialize modes list
        this.modes = new ArrayList<>();
    }

    public double[] sortGPAs(List<Student> students) {
        double[] GPAs = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            GPAs[i] = students.get(i).gpa();
        }
        Arrays.sort(GPAs);
        return GPAs;
    }

    public double getMean(List<Student> students) {
        double sum = 0.0;

        // Sum up total GPA
        for (Student student : students) {
            sum += student.gpa();
        }

        // Calculate and return the mean
        // Calculates numeric data
        return sum / students.size();
    }

    public double getMedian(List<Student> students) {
        // Create array of sorted GPAs
        double[] sorted_GPAs = sortGPAs(students);
        int length = sorted_GPAs.length;

        // If there are an even number of GPAs, the average of the two middle elements becomes the median
        double median;
        if (length % 2 == 0) {
            median = (sorted_GPAs[(length / 2) - 1] + sorted_GPAs[length / 2]) / 2.0;
        }
        // If there are an odd number of GPAs, the middle element becomes the median
        else {
            median = sorted_GPAs[length / 2];
        }
        return median;
    }

    public List<Double> getMode(List<Student> students) {
        // Create array of sorted GPAs
        double[] sorted_GPAs = sortGPAs(students);

        // Create frequency map
        Map<Double, Integer> gpaFrequencyMap = new HashMap<>();

        // Count occurrences of each GPA
        for (double gpa : sorted_GPAs) {
            gpaFrequencyMap.put(gpa, gpaFrequencyMap.getOrDefault(gpa, 0) + 1);
        }

        // Clear modes list for each call
        modes.clear();

        // Find the mode
        int highestCount = 0;

        for (Map.Entry<Double, Integer> entry : gpaFrequencyMap.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                modes.clear();
                modes.add(entry.getKey());
            } else if (entry.getValue() == highestCount) {
                modes.add(entry.getKey());
            }
        }
        return modes;
    }

    public int getCount(List<Student> students) {
        return students.size();
    }

    public double getMin(List<Student> students) {
        double min = Double.MAX_VALUE;
        for (Student student : students) {
            if (min > student.gpa()) {
                min = student.gpa();
            }
        }
        return min;
    }
    public double getMax(List<Student> students) {
        double max = Double.MIN_VALUE;
        for (Student student : students) {
            if (max < student.gpa()) {
                max = student.gpa();
            }
        }
        return max;
    }
    public double getSampleStandardDeviation(List<Student> students) {
        double mean = getMean(students);
        double sumSquaredDiffs = 0.0;
        for (Student student : students) {
            double difference = student.gpa() - mean;
            sumSquaredDiffs += difference * difference;
        }

        double variance = sumSquaredDiffs / (students.size() - 1); // NOTE: This is SAMPLE std. dev, NOT population
        return Math.sqrt(variance);
    }
}
