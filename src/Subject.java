public interface Subject {
    /**
     * This is the Subject for the Observer Design Pattern.
     * It contains methods that will register, unregister, and notify
     * observes of changes in the state.
     */
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}