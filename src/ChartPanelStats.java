import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanelStats extends JPanel {

    public ChartPanelStats(List<Student> students) {
        setLayout(new BorderLayout());
        updateChart(students);
    }

    public void updateChart(List<Student> students) {
        // Create new histogram dataset
        HistogramDataset dataset = new HistogramDataset();

        // Array to hold GPAs
        double[] gpas = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            gpas[i] = students.get(i).gpa();
        }

        // Adds GPAs to dataset
        dataset.addSeries("GPA Distribution", gpas, 50);

        // Create histogram
        JFreeChart histogram = ChartFactory.createHistogram(
                "GPA Histogram",
                "GPA",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Chart settings
        ChartPanel chartPanel = new ChartPanel(histogram);
        chartPanel.setPreferredSize(new Dimension(800, 300));

        removeAll();
        add(chartPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
