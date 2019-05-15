package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * AusleiheMedienauflisterUI beschreibt die grafische Benutzungsoberfläche für
 * das AusleiheMedienauflisterWerkzeug.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class AusleiheMedienauflisterUI
{
    private AusleiheMedienTableModel _ausleiheMedienTableModel;
    private JPanel _hauptPanel;
    private JTable _medienTable;

    /**
     * Initialisiert eine neue AusleiheMedienauflisterUI.
     */
    public AusleiheMedienauflisterUI()
    {
        erzeugeHauptPanel();
        erzeugeMedienTable();
    }

    /**
     * Ereugt das Haupt-Panel.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel(new BorderLayout());
    }

    /**
     * Erzeugt die Tabelle für die Anzeige der Medien.
     */
    private void erzeugeMedienTable()
    {
        JScrollPane medienAuflisterScrollPane = new JScrollPane();
        medienAuflisterScrollPane.setBorder(BorderFactory.createTitledBorder(
                null, "Medien", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        medienAuflisterScrollPane.setBackground(UIConstants.BACKGROUND_COLOR);
        medienAuflisterScrollPane.getVerticalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        medienAuflisterScrollPane.getHorizontalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);

        _ausleiheMedienTableModel = new AusleiheMedienTableModel();
        _medienTable = new JTable();
        medienAuflisterScrollPane.setViewportView(_medienTable);
        _medienTable.setModel(_ausleiheMedienTableModel);
        JTableHeader tableHeader = _medienTable.getTableHeader();
        tableHeader.setFont(UIConstants.HEADER_FONT);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        _medienTable.setFont(UIConstants.TEXT_FONT);
        // Text in der 3. Spalte mittig ausrichten
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        String columnName = _medienTable.getColumnName(2);
        TableColumn column = _medienTable.getColumn(columnName);
        column.setCellRenderer(renderer);

        _hauptPanel.add(medienAuflisterScrollPane, BorderLayout.CENTER);
    }

    /**
     * Gibt die Medienauflistertabelle (JTable) zurück.
     * 
     * @ensure result != null
     */
    public JTable getMedienAuflisterTable()
    {
        return _medienTable;
    }

    /**
     * Gibt das AusleiheMedienTableModel der UI zurück.
     * 
     * @ensure result != null
     */
    public AusleiheMedienTableModel getMedienAuflisterTableModel()
    {
        return _ausleiheMedienTableModel;
    }

    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
}
