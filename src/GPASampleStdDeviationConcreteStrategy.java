import java.util.List;

public class GPASampleStdDeviationConcreteStrategy implements GPAStrategyInterface {
    /**
     *  This is one of the concrete strategies.
     *  This class will calculate the sample standard
     *  deviation of student GPAs for the context to use.
     */
    private final GPAStrategyInterface meanStrategy;

    public GPASampleStdDeviationConcreteStrategy(GPAStrategyInterface meanStrategy) {
        this.meanStrategy = meanStrategy;
    }

    @Override
    public double calculateStat(List<Student> students) {
        double mean = meanStrategy.calculateStat(students);
        double sumSquaredDiffs = 0.0;
        for (Student student : students) {
            double difference = student.gpa() - mean;
            sumSquaredDiffs += difference * difference;
        }

        double variance = sumSquaredDiffs / (students.size() - 1); // NOTE: This is SAMPLE std. dev, NOT population
        return Math.sqrt(variance);
    }
}
