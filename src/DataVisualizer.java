import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class DataVisualizer extends JFrame {
    /*
     * PLEASE READ:
     *
     * UPDATE: PLEASE READ THE READ_ME FILE FOR LAB 4 SO I CAN EXPLAIN THE DESIGN PATTERN IMPLEMENTATIONS!!!!!!
     *
     * This is a program that processes information about students who check in to Study Hall (UNOFFICIAL DATA).
     * If you want to sort columns by ascending or descending order, please click on the column itself and it will be
     * sorted. Navigate through the panes (Spring and Fall) to explore different sets of students. Please note that
     * each student ID is pseudonymous for anonymity, and again, this data is UNOFFICIAL and solely used for
     * educational purposes.
     *
     * Thank you!
     */

    private JTabbedPane tabbedPane;
    private StatsPanel statsPanelFall;
    private StatsPanel statsPanelSpring;
    private JPanel statsPanelContainer;
    private ChartPanelStats chartPanelStats;
    private DetailsPanel detailsPanel;

    // Store filtered students
    private List<Student> filteredStudentsFall;
    private List<Student> filteredStudentsSpring;

    // Constants
    private int TITLELABELSIZE = 16;
    private int SUBTITLELABELSIZE = 18;
    private String LABELFONT = "Arial";
    private int DIVIDERLOCATION = 800;
    private int WINDOWWIDTH = 1200;
    private int WINDOWHEIGHT = 800;

    public DataVisualizer() throws IOException {
        LoadData loadData = new LoadData();

        String filepath_fall = "src/fall23_filtered_checkin_data.txt";
        String filepath_spring = "src/spr23_filtered_checkin_data.txt";

        List<Student> students_fall = loadData.loadStudents(filepath_fall);
        List<Student> students_spring = loadData.loadStudents(filepath_spring);

        // Initialize filtered lists
        filteredStudentsFall = students_fall;
        filteredStudentsSpring = students_spring;

        // Initialize components
        tabbedPane = new JTabbedPane();
        TablePanel tablePanelFall = new TablePanel(students_fall);
        TablePanel tablePanelSpring = new TablePanel(students_spring);
        statsPanelFall = new StatsPanel(filteredStudentsFall);
        statsPanelSpring = new StatsPanel(filteredStudentsSpring);

        chartPanelStats = new ChartPanelStats(filteredStudentsSpring);

        detailsPanel = new DetailsPanel();

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel("DISCLAIMER: UNOFFICIAL DATA", JLabel.CENTER);
        titleLabel.setFont(new Font(LABELFONT, Font.BOLD, TITLELABELSIZE));
        titleLabel.setForeground(Color.RED);

        JLabel subTitleLabel = new JLabel("UCA Tutoring Center Study Hall Data", JLabel.CENTER);
        subTitleLabel.setFont(new Font(LABELFONT, Font.BOLD, SUBTITLELABELSIZE));

        labelPanel.add(subTitleLabel);
        labelPanel.add(titleLabel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(labelPanel, BorderLayout.NORTH);

        statsPanelContainer = new JPanel(new BorderLayout());
        statsPanelContainer.add(statsPanelSpring, BorderLayout.CENTER);
        topPanel.add(statsPanelContainer, BorderLayout.CENTER);

        // Create tabs for Spring and Fall data
        tabbedPane.addTab("Spring 2023", tablePanelSpring);
        tabbedPane.addTab("Fall 2023", tablePanelFall);

        // Set up layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, detailsPanel);
        splitPane.setDividerLocation(DIVIDERLOCATION);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH); // Keeps StatsPanel at the top
        add(splitPane, BorderLayout.CENTER); // Keeps TablePanel and DetailsPanel in the center
        add(chartPanelStats, BorderLayout.SOUTH); // Keeps ChartPanel at the bottom

        // Update stats and chart when user clicks different tab
        tabbedPane.addChangeListener(e -> {
            statsPanelContainer.removeAll();

            if (tabbedPane.getSelectedIndex() == 0) { // Spring tab
                statsPanelSpring.update(filteredStudentsSpring);
                statsPanelContainer.add(statsPanelSpring, BorderLayout.CENTER);
                chartPanelStats.updateChart(filteredStudentsSpring); // Update chart for spring data
            } else { // Fall tab
                statsPanelFall.update(filteredStudentsFall);
                statsPanelContainer.add(statsPanelFall, BorderLayout.CENTER);
                chartPanelStats.updateChart(filteredStudentsFall); // Update chart for fall data
            }

            statsPanelContainer.revalidate();
            statsPanelContainer.repaint();
        });

        // Add listener to update details panel
        tablePanelSpring.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selection = tablePanelSpring.getTable().getSelectedRow();
            if (selection != -1) {
                Student selectedStudent = filteredStudentsSpring.get(tablePanelSpring.getTable().
                        convertRowIndexToModel(selection));
                // Update details panel
                detailsPanel.updateDetails(selectedStudent);
            }
        });
        tablePanelFall.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selection = tablePanelFall.getTable().getSelectedRow();
            if (selection != -1) {
                Student selectedStudent = filteredStudentsFall.get(tablePanelFall.getTable().
                        convertRowIndexToModel(selection));
                // Update details panel
                detailsPanel.updateDetails(selectedStudent);
            }
        });

        // Update ChartPanel and StatsPanel when filters are applied
        tablePanelSpring.addFilterListener(filteredStudents -> {
            filteredStudentsSpring = filteredStudents; // Update filtered data for Spring
            statsPanelSpring.update(filteredStudentsSpring);
            chartPanelStats.updateChart(filteredStudentsSpring);
        });

        tablePanelFall.addFilterListener(filteredStudents -> {
            filteredStudentsFall = filteredStudents; // Update filtered data for Fall
            statsPanelFall.update(filteredStudentsFall);
            chartPanelStats.updateChart(filteredStudentsFall);
        });

        // Window settings
        setTitle("Student Statistics and Data");
        setSize(WINDOWWIDTH, WINDOWHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new DataVisualizer();
    }
}
