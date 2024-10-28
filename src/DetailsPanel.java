import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JTextArea detailsTextArea;
    private String TEXTFONT = "Arial";
    private int TEXTSIZE = 14;

    public DetailsPanel() {
        setLayout(new BorderLayout());

        // Display student information
        detailsTextArea = new JTextArea("Select a student to see details.");
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font(TEXTFONT, Font.PLAIN, TEXTSIZE));
        add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);
    }

    public void updateDetails(Student student) {
        detailsTextArea.setText(student.toString());
    }
}
