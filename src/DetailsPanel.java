import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JTextArea detailsTextArea;

    public DetailsPanel() {
        setLayout(new BorderLayout());
        detailsTextArea = new JTextArea("Select a student to see details.");
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);
    }

    public void updateDetails(Student student) {
        detailsTextArea.setText(student.toString());
    }
}
