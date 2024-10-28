import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanelStats extends JPanel {
    private int PANELWIDTH = 800;
    private int PANELHEIGHT = 300;
    private int NO_BINS = 50;

    public ChartPanelStats(List<Student> students) {
        setLayout(new BorderLayout());
        updateChart(students);
    }

    public void updateChart(List<Student> students) {
        // Clear chart if no students are returned by TablePanel (no results for filters)
        if (students.isEmpty()) {
            clearChart();
            return;
        }

        // Create new histogram dataset
        HistogramDataset dataset = new HistogramDataset();
        // Array to hold GPAs
        double[] gpas = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            gpas[i] = students.get(i).gpa();
        }

        // Adds GPAs to dataset
        dataset.addSeries("GPA Distribution", gpas, NO_BINS);

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
        chartPanel.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        removeAll();
        add(chartPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void clearChart() {
        // Clear chart if no data is found
        removeAll();
        revalidate();
        repaint();
    }

}
