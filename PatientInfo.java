import javax.swing.*;
import java.awt.*;

public class PatientInfo {
    private JFrame frame;
    private JTextField patientNameField, patientAgeField, patientContactField;
    private JLabel patientIdLabel;
    private JButton printButton;

    public PatientInfo() {
        frame = new JFrame("Patient Information");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new GridLayout(4, 2, 5, 5));

        patientPanel.add(new JLabel("Patient ID:"));
        patientIdLabel = new JLabel(generateRandomId());
        patientPanel.add(patientIdLabel);

        patientPanel.add(new JLabel("Patient Name:"));
        patientNameField = new JTextField(8); 
        patientPanel.add(patientNameField);

        patientPanel.add(new JLabel("Age:"));
        patientAgeField = new JTextField(3);
        patientPanel.add(patientAgeField);

        patientPanel.add(new JLabel("Contact:"));
        patientContactField = new JTextField(8);
        patientPanel.add(patientContactField);

        printButton = new JButton("Print");
        printButton.addActionListener(e -> confirmAndPrint());

        frame.add(patientPanel, BorderLayout.CENTER);
        frame.add(printButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private String generateRandomId() {
        int id = (int) (Math.random() * 9000) + 1000;
        return "PID-" + id;
    }

    private void confirmAndPrint() {
        int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to print this prescription?", "Confirm Print", JOptionPane.OK_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION) {
            showPrintPreview();
        }
    }

    private void showPrintPreview() {
        JTextArea textArea = new JTextArea();
        textArea.append("Patient Information:\n");
        textArea.append("--------------------\n");
        textArea.append("Patient ID: " + patientIdLabel.getText() + "\n");
        textArea.append("Patient Name: " + patientNameField.getText() + "\n");
        textArea.append("Age: " + patientAgeField.getText() + "\n");
        textArea.append("Contact: " + patientContactField.getText() + "\n");

        JFrame printFrame = new JFrame("Print Preview");
        printFrame.setSize(400, 300);
        printFrame.add(new JScrollPane(textArea));
        printFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientInfo::new);
    }
}
