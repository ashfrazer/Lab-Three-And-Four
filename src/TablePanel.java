import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public TablePanel(List<Student> students) {
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"ID", "Gender", "Ethnicity", "Status", "Major", "Classification", "Athlete", "GPA"};

        // Create table model and set columns
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Override isCellEditable to make table cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        // Enable sorting
        table.setAutoCreateRowSorter(true);

        // Add student data to table
        addStudents(students);

        // Add table to panel
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Add students to table
    private void addStudents(List<Student> students) {
        // Clear existing rows in the table
        tableModel.setRowCount(0);

        // Add each student as a row in the table
        for (Student student : students) {
            Object[] rowData = {
                    student.id(),
                    student.gender(),
                    student.ethnicity(),
                    student.status(),
                    student.major(),
                    student.classification(),
                    student.isAthlete(),
                    student.gpa()
            };
            tableModel.addRow(rowData);
        }
    }

    public void updateStudents(List<Student> students) {
        addStudents(students);
    }

    public JTable getTable() {
        return table;
    }
}
