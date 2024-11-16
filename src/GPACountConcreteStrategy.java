import java.util.List;

public class GPACountConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the count of student GPAs
     *  for the context to use.
     */
    @Override
    public double calculateStat(List<Student> students) {
            return students.size();
    }
}
