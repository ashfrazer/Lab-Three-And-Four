import java.util.List;

public class GPAMaxConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the max of student GPAs
     *  for the context to use.
     */
    @Override
    public double calculateStat(List<Student> students) {
        double max = Double.MIN_VALUE;
        for (Student student : students) {
            if (max < student.gpa()) {
                max = student.gpa();
            }
        }
        return max;
    }
}
