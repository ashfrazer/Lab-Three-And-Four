import java.util.List;

public interface Observer {
    /**
     * This is the Observer for the Observer Design Pattern.
     * It ensures a consistent way for concrete observers to receieve updates
     * from the subject.
     */
    void update(List<Student> students);
}