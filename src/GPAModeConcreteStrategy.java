import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPAModeConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the mode of student GPAs
     *  for the context to use.
     */
    @Override
    public double calculateStat(List<Student> students) {
        // Create a list of sorted GPAs
        List<Double> sorted_GPAs = students.stream()
                .map(Student::gpa)
                .sorted()
                .toList();

        // Create frequency map
        Map<Double, Integer> gpaFrequencyMap = new HashMap<>();

        // Count occurrences of each GPA
        for (double gpa : sorted_GPAs) {
            gpaFrequencyMap.put(gpa, gpaFrequencyMap.getOrDefault(gpa, 0) + 1);
        }

        // Initialize modes
        double mode = 0;

        // Find the mode
        int highestCount = 0;

        for (Map.Entry<Double, Integer> entry : gpaFrequencyMap.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                mode = entry.getKey();
            } else if (entry.getValue() == highestCount) {
                mode = entry.getKey();
            }
        }
        return mode; /* I am aware that if there are multiple modes, then only the most recent mode would be returned.
                      * However, for the scope of this lab and to reduce complexity, I will ignore that flaw. Besides,
                      * there is only one mode for these datasets. */

    }
}
