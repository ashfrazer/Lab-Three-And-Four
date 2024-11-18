# LAB FOUR INFORMATION:
[Repository link](https://github.com/ashfrazer/Lab-Three-And-Four/tree/master)

This is an updated repository from Lab Three, but it contains the code for Lab Four.

The two design patterns that I implemented are:
* Strategy Design Pattern
* Observer Design Pattern
-----
## Strategy Design Pattern

### **Context**
The Context of this design pattern is implemented in `StatsCalculator`.

How it works:

`StatsCalculator` calls the method `calculateStat(List<Student> students)` on 
`gpaStrategy`, an instance of GPAStrategyInterface. The `setGPAStrategy` method
is a setter for `gpaStrategy`.

File(s):
* StatsCalculator.java

### **Strategy Interface**
The Strategy Interface of this design pattern is implemented in `GPAStrategyInterface`.

How it works:

`GPAStrategyInterface` declares the method `calculateStat(List<Student> students)` that
the context will use to execute a given strategy.

File(s):
* GPAStrategyInterface.java

### **Concrete Strategies**
The Concrete Strategies for this design pattern are listed below in the "File(s)" list.

How they work:

The Concrete Strategies have an overridden implementation of `calculateStat(List<Student> students)`
that is customized to perform the desired calculation. The result is to be used for the Context.

File(s):
* GPACountConcreteStrategy.java
* GPAMeanConcreteStrategy.java
* GPAMedianConcreteStrategy.java
* GPAModeConcreteStrategy.java
* GPASampleStdDeviationConcreteStrategy.java
* GPAMaxConcreteStrategy.java
* GPAMinConcreteStrategy.java

### **Client**
The Client of this design pattern is implemented in `StatsPanel`.

How it works:

`StatsPanel` configures the strategy and provides it to the Context. It does this by calling the 
`setGPAStrategy(GPAStrategyInterface gpaStrategy)` and `calculateStat(List<Student> students)` methods.

File(s):
* StatsPanel.java
-----
## Observer Design Pattern

Side note: I had a similar approach to the Observer pattern in Lab 3 through the FilterListener interface.
I made a more explicit Observer design pattern and only tied it in with `StatsPanel` to reduce the complexity
(only for the scope of this assignment). I understand that there is some redundancy in this. Again, I'm just
trying to make it more explicit and reduce the headache of rewriting the entire thing.

### **Subject**
The Subject of this design pattern is implemented in the `Subject` interface.

How it works:

`Subject` contains methods that will register, unregister, and notify observers of changes in the state.
It contains the methods `addObserver(Observer observer)`, `removeObserver(Observer observer)`, and
`notifyObservers()`, which perform the respective tasks.

File(s):
* Subject.java

### **Observer**
The Observer of this design pattern is implemented in the `Observer` interface.

How it works:

`Observer` ensures a consistent way for concrete observers to receive updated from the subject
It contains the method `update(List<Student> students)`, which is to be overridden to apply custom
logic to perform updates on the Concrete Observer.

File(s):
* Observer.java

### **Concrete Subject**
The Concrete Subject of this design pattern is implemented in `TablePanel`.

How it works:

`TablePanel` holds the actual state and data for the Observer to track. When the state changes,
it notifies the Observer. It contains overridden methods from the `Subject` interface, including
`addObserver(Observer observer)`, `removeObserver(Observer observer)`, and `notifyObservers()`.

File(s):
* TablePanel.java

### **Concrete Observer**
The Concrete Observer of this design pattern is implemented in `StatsPanel`.

How it works:

`StatsPanel` registers through `update(List<Student> students)` with the Concrete Subject and
reacts accordingly when notified of a state change. It contains the overridden 
`update(List<Student> students)` method to properly update the panel once filters are applied or
the tab is changed.

File(s):
* StatsPanel.java
