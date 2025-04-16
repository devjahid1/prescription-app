import javax.swing.*;
import javax.swing.table.*;

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus,
    int row, int column) {
        setText((value == null) ? "Delete" : value.toString());
        return this;
    }
}
