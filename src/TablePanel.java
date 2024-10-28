import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private List<FilterListener> filterListeners = new ArrayList<>();

    public interface FilterListener {
        // Implementing this interface notifies classes when filters are applied
        void onFilterApplied(List<Student> filteredStudents);
    }

    public void addFilterListener(FilterListener listener) {
        filterListeners.add(listener);
    }

    public TablePanel(List<Student> students) {
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"ID", "Gender", "Ethnicity", "Status", "Major", "Classification", "Athlete", "GPA"};

        // Create table and set columns
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Override isCellEditable to make table cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        // Enable sorting
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Add student data to table
        addStudents(students);

        // Create filter panel
        JPanel filterPanel = new JPanel(new GridLayout(1, columnNames.length));
        JComboBox<String>[] filters = new JComboBox[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            Vector<String> GPARanges = new Vector<>();
            if (i == 7) { // GPA column
                GPARanges.add("0-0.99");
                GPARanges.add("1-1.99");
                GPARanges.add("2-2.99");
                GPARanges.add("3-3.99");
                GPARanges.add("4+");
            } else {
                // Displays filter options for other columns
                for (Student student : students) {
                    String value = getColumnValue(student, i);
                    if (!GPARanges.contains(value)) {
                        GPARanges.add(value);
                    }
                }
            }
            filters[i] = new JComboBox<>(GPARanges);
            filters[i].insertItemAt("All", 0);
            filters[i].setSelectedIndex(0);
            filters[i].addActionListener(e -> applyFilters(filters, students));
            filterPanel.add(filters[i]);
        }

        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }

    private void applyFilters(JComboBox<String>[] filters, List<Student> allStudents) {
        // Store currently filtered values
        String[] selectedFilters = new String[filters.length];
        for (int i = 0; i < filters.length; i++) {
            selectedFilters[i] = (String) filters[i].getSelectedItem();
        }

        // JAVA STREAMS REQUIREMENT: Filters students based on selected filters
        List<Student> filteredStudents = allStudents.stream()
                .filter(student -> {
                    boolean is_match = true;
                    for (int i = 0; i < filters.length; i++) {
                        if (!selectedFilters[i].equals("All")) {
                            String columnValue = getColumnValue(student, i);
                            if (i == 7) { // Filters GPA column
                                is_match = is_match && inGPARange(columnValue, selectedFilters[i]);
                            } else { // Filters other columns
                                is_match = is_match && columnValue.equals(selectedFilters[i]);
                            }
                        }
                    }
                    return is_match;
                })
                // Convert stream into list
                .collect(Collectors.toList());

        // Update table with filtered data
        updateTable(filteredStudents);

        // Notify listeners upon filtering
        filterListeners.forEach(listener -> listener.onFilterApplied(filteredStudents));
    }

    private boolean inGPARange(String gpa, String range) {
        double gpaValue = Double.parseDouble(gpa);
        return switch (range) {
            case "0-0.99" -> gpaValue < 1.0;
            case "1-1.99" -> gpaValue >= 1.0 && gpaValue < 2.0;
            case "2-2.99" -> gpaValue >= 2.0 && gpaValue < 3.0;
            case "3-3.99" -> gpaValue >= 3.0 && gpaValue < 4.0;
            case "4+" -> gpaValue == 4.0;
            default -> false;
        };
    }

    private void updateTable(List<Student> filteredStudents) {
        tableModel.setRowCount(0); // Clear existing data
        addStudents(filteredStudents);
    }

    private void addStudents(List<Student> students) {
        // Add each student as a row in the table
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.id(),
                    student.gender(),
                    student.ethnicity(),
                    student.status(),
                    student.major(),
                    student.classification(),
                    student.isAthlete(),
                    student.gpa()
            });
        }
    }

    private String getColumnValue(Student student, int index) {
        // Returns column value based on index number
        return switch (index) {
            case 0 -> student.id();
            case 1 -> student.gender();
            case 2 -> student.ethnicity();
            case 3 -> student.status();
            case 4 -> student.major();
            case 5 -> student.classification();
            case 6 -> String.valueOf(student.isAthlete());
            case 7 -> String.valueOf(student.gpa());
            default -> "";
        };
    }
}
