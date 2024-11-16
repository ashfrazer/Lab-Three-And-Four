import java.util.List;

public class GPAMedianConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the median of student GPAs
     *  for the context to use.
     */
    @Override
    public double calculateStat(List<Student> students) {
        // Create array of sorted GPAs
        double[] sorted_GPAs = students.stream()
                .mapToDouble(Student::gpa)
                .sorted()
                .toArray();

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
}
