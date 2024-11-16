import java.util.*;

public class StatsCalculator {
    /**
     *  This is the context.
     *  It calls `calculateStat` on the linked strategy.
     *  The type of strategy and details of the algorithm are
     *  unknown by the context.
     */
    private GPAStrategyInterface gpaStrategy;

    public void setGPAStrategy(GPAStrategyInterface gpaStrategy) {
        this.gpaStrategy = gpaStrategy;
    }

    public double calculateStat(List<Student> students) {
        return gpaStrategy.calculateStat(students);
    }
}