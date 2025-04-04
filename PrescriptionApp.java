import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

public class PrescriptionApp {
    private JFrame frame;
    private JTextField patientIdField, patientNameField, patientAgeField, patientContactField, patientAddressField;
    private JComboBox<String> genderComboBox;
    private JTable complaintTable, prescriptionTable, testTable, followUpTable;
    private DefaultTableModel complaintTableModel, prescriptionTableModel, testTableModel, followUpTableModel;
    private JButton addComplaintButton, addMedicineButton, addTestButton, addFollowUpButton;

    public PrescriptionApp() {
        frame = new JFrame("Prescription Software");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(20, 20));

        JPanel patientPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        stylePanel(patientPanel);

        patientPanel.add(createBlackLabel("Patient ID:"));
        patientIdField = createStyledTextField(generateRandomId());
        patientIdField.setEditable(false);
        patientPanel.add(patientIdField);

        patientPanel.add(createBlackLabel("Patient Name:"));
        patientNameField = createStyledTextField("");
        patientPanel.add(patientNameField);

        patientPanel.add(createBlackLabel("Age:"));
        patientAgeField = createStyledTextField("");
        patientPanel.add(patientAgeField);

        patientPanel.add(createBlackLabel("Contact:"));
        patientContactField = createStyledTextField("");
        patientPanel.add(patientContactField);

        patientPanel.add(createBlackLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        patientPanel.add(genderComboBox);

        patientPanel.add(createBlackLabel("Address:"));
        patientAddressField = createStyledTextField("");
        patientPanel.add(patientAddressField);


        complaintTableModel = new DefaultTableModel(new String[]{"Chief Complaint", "Action"}, 0);
        complaintTable = new JTable(complaintTableModel);
        setupDeleteButton(complaintTable, complaintTableModel);
        JPanel complaintPanel = createSectionPanel("Chief Complaints", complaintTable);


        prescriptionTableModel = new DefaultTableModel(new String[]{"Medicine", "Dosage", "Strength", "Action"}, 0);
        prescriptionTable = new JTable(prescriptionTableModel);
        setupDeleteButton(prescriptionTable, prescriptionTableModel);
        JPanel medicinePanel = createSectionPanel("Prescriptions", prescriptionTable);


        testTableModel = new DefaultTableModel(new String[]{"Test Name", "Action"}, 0);
        testTable = new JTable(testTableModel);
        setupDeleteButton(testTable, testTableModel);
        JPanel testPanel = createSectionPanel("Tests", testTable);

        followUpTableModel = new DefaultTableModel(new String[]{"Follow-up Instructions", "Action"}, 0);
        followUpTable = new JTable(followUpTableModel);
        setupDeleteButton(followUpTable, followUpTableModel);
        JPanel followUpPanel = createSectionPanel("Follow-up Instructions", followUpTable);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        stylePanel(mainPanel);
        mainPanel.add(complaintPanel);
        mainPanel.add(medicinePanel);
        mainPanel.add(testPanel);
        mainPanel.add(followUpPanel);

        JPanel buttonPanel = new JPanel();
        stylePanel(buttonPanel);
        addComplaintButton = createButton("Add Complaint");
        addMedicineButton = createButton("Add Medicine");
        addTestButton = createButton("Add Test");
        addFollowUpButton = createButton("Add Follow-up");

        buttonPanel.add(addComplaintButton);
        buttonPanel.add(addMedicineButton);
        buttonPanel.add(addTestButton);
        buttonPanel.add(addFollowUpButton);

        frame.add(patientPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addComplaintButton.addActionListener(e -> addComplaint());
        addMedicineButton.addActionListener(e -> addPrescription());
        addTestButton.addActionListener(e -> addTest());
        addFollowUpButton.addActionListener(e -> addFollowUp());

        frame.setVisible(true);
    }

    private String generateRandomId() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    private void addComplaint() {
        String complaint = JOptionPane.showInputDialog("Enter Chief Complaint:");
        if (complaint != null && !complaint.trim().isEmpty()) {
            complaintTableModel.addRow(new Object[]{complaint, "Delete"});
        }
    }

    private void addPrescription() {
        String medicine = JOptionPane.showInputDialog("Enter Medicine Name:");
        if (medicine != null && !medicine.trim().isEmpty()) {
            String dosage = JOptionPane.showInputDialog("Enter Dosage:");
            String strength = JOptionPane.showInputDialog("Enter Strength:");
            prescriptionTableModel.addRow(new Object[]{medicine, dosage, strength, "Delete"});
        }
    }

    private void addTest() {
        String testName = JOptionPane.showInputDialog("Enter Test Name:");
        if (testName != null && !testName.trim().isEmpty()) {
            testTableModel.addRow(new Object[]{testName, "Delete"});
        }
    }

    private void addFollowUp() {
        String followUp = JOptionPane.showInputDialog("Enter Follow-up Instructions:");
        if (followUp != null && !followUp.trim().isEmpty()) {
            followUpTableModel.addRow(new Object[]{followUp, "Delete"});
        }
    }

    private void setupDeleteButton(JTable table, DefaultTableModel model) {
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), model));
    }

    private void stylePanel(JPanel panel) {
        panel.setBackground(new Color(175, 219, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private JLabel createBlackLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return label;
    }

    private JTextField createStyledTextField(String text) {
        JTextField textField = new JTextField(text, 15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        textField.setMargin(new Insets(5, 5, 5, 5));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JPanel createSectionPanel(String title, JTable table) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), title));
        panel.setBackground(new Color(175, 219, 245));
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrescriptionApp::new);
    }
}
