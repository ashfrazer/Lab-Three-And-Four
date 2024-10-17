import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class DataVisualizer extends JFrame {
    private JTabbedPane tabbedPane; // Tabs to switch between spring and fall data
    private StatsPanel statsPanelFall;
    private StatsPanel statsPanelSpring;
    private JPanel statsPanelContainer;

    public DataVisualizer() throws IOException {
        LoadData loadData = new LoadData();

        // Declare file paths for fall and spring datasets
        String filepath_fall = "src/fall23_filtered_checkin_data.txt";
        String filepath_spring = "src/spr23_filtered_checkin_data.txt";

        // Create list of students from fall semester dataset
        List<Student> students_fall = loadData.loadStudents(filepath_fall);

        // Create list of students from spring semester dataset
        List<Student> students_spring = loadData.loadStudents(filepath_spring);

        // Initialize components
        tabbedPane = new JTabbedPane();
        TablePanel tablePanelFall = new TablePanel(students_fall);
        TablePanel tablePanelSpring = new TablePanel(students_spring);
        statsPanelFall = new StatsPanel(students_fall);
        statsPanelSpring = new StatsPanel(students_spring);

        /*
        * TO-DO:
        * ChartPanel chartPanel = new ChartPanel(students_fall);
        * Sort columns?
        * Filters
        */

        // Add tabs for spring and fall
        tabbedPane.addTab("Spring 2023 Data", tablePanelSpring);
        tabbedPane.addTab("Fall 2023 Data", tablePanelFall);

        // Container for StatsPanel
        statsPanelContainer = new JPanel(new BorderLayout());
        statsPanelContainer.add(statsPanelSpring, BorderLayout.CENTER);

        // Set up layout
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(statsPanelContainer, BorderLayout.NORTH);
        //add(chartPanel, BorderLayout.SOUTH);

        // Update stats when user clicks different tab
        tabbedPane.addChangeListener(e -> {
            statsPanelContainer.removeAll(); // Remove existing panel

            if (tabbedPane.getSelectedIndex() == 0) { // Spring tab
                statsPanelSpring.updateStudents(students_spring);
                statsPanelContainer.add(statsPanelSpring, BorderLayout.CENTER);
            } else { // Fall tab
                statsPanelFall.updateStudents(students_fall);
                statsPanelContainer.add(statsPanelFall, BorderLayout.CENTER);
            }

            // Display new panel
            statsPanelContainer.revalidate();
            statsPanelContainer.repaint();
        });

        // Window settings
        setTitle("Student Statistics and Data");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new DataVisualizer();
    }

}
