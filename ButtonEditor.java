import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private JTable table;
    private DefaultTableModel model;
    private int editingRow;

    public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
        super(checkBox);
        this.model = model;
        button = new JButton("Delete");
        button.setOpaque(true);

        // Trigger deletion when the button is clicked
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        this.editingRow = row;
        label = (value == null) ? "Delete" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            // Delete the row safely
            if (editingRow >= 0 && editingRow < model.getRowCount()) {
                model.removeRow(editingRow);
            }
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
