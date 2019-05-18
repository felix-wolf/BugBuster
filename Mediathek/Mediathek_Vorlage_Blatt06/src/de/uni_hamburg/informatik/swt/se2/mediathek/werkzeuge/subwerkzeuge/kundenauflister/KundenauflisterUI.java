package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * KundenauflisterUI beschreibt die grafische Benutzungsoberfläche für das
 * KundenauflisterWerkzeug.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class KundenauflisterUI
{
    private KundenTableModel _kundenTableModel;
    private JPanel _hauptPanel;
    private JTable _kundenTable;

    /**
     * Initialisiert eine neue KundenauflisterUI.
     */
    public KundenauflisterUI()
    {
        erzeugeHauptPanel();
        erzeugeKundenTable();
    }

    /**
     * Ereugt das Haupt-Panel.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel(new BorderLayout());
    }

    /**
     * Erzeugt die Tabelle für die Anzeige der Kunden.
     */
    private void erzeugeKundenTable()
    {
        JScrollPane kundenAuflisterScrollPane = new JScrollPane();
        kundenAuflisterScrollPane.setBorder(BorderFactory.createTitledBorder(
                null, "Kunden", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        kundenAuflisterScrollPane.setBackground(UIConstants.BACKGROUND_COLOR);
        kundenAuflisterScrollPane.getVerticalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        kundenAuflisterScrollPane.getHorizontalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);

        _kundenTableModel = new KundenTableModel();
        _kundenTable = new JTable();
        kundenAuflisterScrollPane.setViewportView(_kundenTable);
        _kundenTable.setModel(_kundenTableModel);
        _kundenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader tableHeader = _kundenTable.getTableHeader();
        tableHeader.setFont(UIConstants.HEADER_FONT);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        _kundenTable.setFont(UIConstants.TEXT_FONT);

        _hauptPanel.add(kundenAuflisterScrollPane, BorderLayout.CENTER);
    }

    /**
     * Gibt die Kundentabelle (JTable) zurück.
     * 
     * @ensure result != null
     */
    public JTable getKundenTable()
    {
        return _kundenTable;
    }

    /**
     * Gibt das KundenTableModel der UI zurück.
     * 
     * @ensure result != null
     */
    public KundenTableModel getKundenAuflisterTableModel()
    {
        return _kundenTableModel;
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
