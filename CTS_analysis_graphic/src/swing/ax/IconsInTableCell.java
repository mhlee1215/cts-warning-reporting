package swing.ax;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class IconsInTableCell extends JPanel {

    private static final String[] COLUMN_NAMES = { "Icons" };
    private MyTableModel tableModel;
    private JTable table;
    private JFrame frame = new JFrame();

    public IconsInTableCell() {
        super(new BorderLayout(0, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tableModel = new MyTableModel();
        table = new JTable(tableModel);
        //table.setDefaultEditor(Icon.class, new IconEditor());
        table.setDefaultRenderer(Icon.class, new IconRenderer());
        table.setRowHeight(60);
        add(new JScrollPane(table), BorderLayout.CENTER);
        tableModel.add(new TableEntry());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.pack();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new IconsInTableCell();
            }
        });
    }

    private enum Icon {
        Delete, Sort;
    }

    private class IconPanel extends JPanel {

        private JLabel icon1;
        private JLabel icon2;

        IconPanel() {
            super(new GridLayout(0, 1));
            setOpaque(true);

            ImageIcon icon = new ImageIcon(getClass().getResource(
					"green_circle_check.png"));
			icon1 = new JLabel(icon);
			icon2 = new JLabel(icon);

			
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            add(icon1, c);
            add(new JLabel("TEXT"), c);
            add(icon2, c);
        }

        
    }

    private class TableEntry {

        private Icon theIcons;

        TableEntry() {
        }

        TableEntry(Icon aIcons) {
            theIcons = aIcons;
        }

        public Icon getIcons() {
            return theIcons;
        }

        public void setIcons(Icon aIcon) {
            theIcons = aIcon;
        }
    }

    private class MyTableModel extends AbstractTableModel {

    	
        private Vector<Object> theEntries;

        MyTableModel() {
            theEntries = new Vector<Object>();
        }

        @SuppressWarnings("unchecked")
        public void add(TableEntry anEntry) {
            int index = theEntries.size();
            theEntries.add(anEntry);
            fireTableRowsInserted(index, index);
        }

        public void remove(int aRowIndex) {
            if (aRowIndex < 0 || aRowIndex >= theEntries.size()) {
                return;
            }
            theEntries.removeElementAt(aRowIndex);
            fireTableRowsDeleted(aRowIndex, aRowIndex);
        }

        public int getRowCount() {
            return theEntries.size();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Icon.class;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            //TableEntry entry = (TableEntry) theEntries.elementAt(rowIndex);
            //entry.setIcons((Icon) aValue);
            //fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TableEntry entry = (TableEntry) theEntries.elementAt(rowIndex);
            switch (columnIndex) {
            case 0:
                return "1";//entry.getIcons();
            }
            return null;
        }
    }



    private class IconRenderer extends IconPanel implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }
            return this;
        }
    }
}