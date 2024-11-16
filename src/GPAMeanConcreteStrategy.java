import java.util.List;

public class GPAMeanConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the mean of student GPAs
     *  for the context to use.
     */
    @Override
    public double calculateStat(List<Student> students) {
        double sum = 0.0;

        // Sum up total GPA
        for (Student student : students) {
            sum += student.gpa();
        }

        // Calculate and return the mean
        return sum / students.size();
    }
}
