import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ButtonEditor extends DefaultCellEditor {
    public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
        super(checkBox);
        // Disable the action when the button is clicked (by doing nothing here)
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JButton button = new JButton("Delete");
        // Set the button action to null, or just do nothing.
        button.addActionListener(e -> {
            // Do nothing here (disable the delete action)
        });
        return button;
    }
}
