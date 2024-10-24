import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JLabel detailsLabel;

    public DetailsPanel() {
        setLayout(new BorderLayout());
        detailsLabel = new JLabel("Select a student to see details.");
        add(detailsLabel, BorderLayout.CENTER);
    }

    public void updateDetails(Student student) {
        detailsLabel.setText(student.toString());
    }
}
