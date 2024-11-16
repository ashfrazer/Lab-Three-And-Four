import java.util.List;

public class GPAMinConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the min of student GPAs
     *  for the context to use.
     */
    @Override
    public double calculateStat(List<Student> students) {
        double min = Double.MAX_VALUE;
        for (Student student : students) {
            if (min > student.gpa()) {
                min = student.gpa();
            }
        }
        return min;
    }
}
