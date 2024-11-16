import java.util.List;

public interface GPAStrategyInterface {
    /**
     *  This is the strategy interface.
     *  It declares the method `calculateStat` that the
     *  context will use to execute a strategy.
     */
    double calculateStat(List<Student> students);
}
