package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.rueckgabe;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * RueckgabeUIbeschreibt die grafische Benutzungsoberfläche für das
 * RueckgabeWerkzeug. Sie beinhaltet einen Verleihkartenauflister, einen
 * Verleihkartenanzeiger und einen Rücknahmebutton.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
class RueckgabeUI
{
    // UI-Komponenten
    private JTextArea _verleihkartenAnzeigerTextArea;
    private JButton _ruecknahmeButton;
    private JPanel _ruecknahmePanel;
    private JTable _verleihkartenAuflisterTable;
    private VerleihkartenTableModel _verleihkartenAuflisterTableModel;
    private JPanel _hauptPanel;

    /**
     * Erzeugt die Elemente der Benutzungsoberfläche.
     */
    public RueckgabeUI()
    {
        erzeugeHauptPanel();
        erzeugeVerleihkartenauflister();
        erzeugeRuecknahme();
    }

    /**
     * Erzeugt das Hauptpanel, in dem sich Verleihkartenauflister,
     * Verleihkartenanzeiger und Rücknahme-Button befinden.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel();
        _hauptPanel.setLayout(new BorderLayout());
        _hauptPanel.setPreferredSize(new java.awt.Dimension(-1, -1));
        _hauptPanel.setSize(-1, -1);
        _hauptPanel.setAutoscrolls(true);
        _hauptPanel.setBackground(UIConstants.BACKGROUND_COLOR);
    }

    /**
     * Erzeuge das Tabellen-Panel, in dem die Verleihkarten angezeigt werden.
     */
    private void erzeugeVerleihkartenauflister()
    {
        JPanel verleihkartenAuflisterPanel = new JPanel();
        _hauptPanel.add(verleihkartenAuflisterPanel, BorderLayout.CENTER);
        verleihkartenAuflisterPanel.setLayout(new BorderLayout());
        verleihkartenAuflisterPanel
            .setPreferredSize(new java.awt.Dimension(-1, -1));
        verleihkartenAuflisterPanel.setSize(-1, -1);
        verleihkartenAuflisterPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        JScrollPane verleihkartenAuflisterScrollPane = new JScrollPane();
        verleihkartenAuflisterPanel.add(verleihkartenAuflisterScrollPane,
                BorderLayout.CENTER);
        verleihkartenAuflisterScrollPane
            .setPreferredSize(new java.awt.Dimension(-1, -1));
        verleihkartenAuflisterScrollPane.setSize(-1, -1);
        verleihkartenAuflisterScrollPane.setBorder(BorderFactory
            .createTitledBorder(null, "Verleihkarten", TitledBorder.LEADING,
                    TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        verleihkartenAuflisterScrollPane
            .setBackground(UIConstants.BACKGROUND_COLOR);
        verleihkartenAuflisterScrollPane.getVerticalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        verleihkartenAuflisterScrollPane.getHorizontalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);

        _verleihkartenAuflisterTableModel = new VerleihkartenTableModel();
        _verleihkartenAuflisterTable = new JTable();
        verleihkartenAuflisterScrollPane
            .setViewportView(_verleihkartenAuflisterTable);
        _verleihkartenAuflisterTable
            .setModel(_verleihkartenAuflisterTableModel);
        JTableHeader tableHeader = _verleihkartenAuflisterTable
            .getTableHeader();
        tableHeader.setFont(UIConstants.HEADER_FONT);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        _verleihkartenAuflisterTable.setFont(UIConstants.TEXT_FONT);
    }

    /**
     * Erzeuge das Rücknahme-Panel, in dem der Verleihkartenanzeiger und der
     * Rüchnahme-Button liegen.
     */
    private void erzeugeRuecknahme()
    {
        _ruecknahmePanel = new JPanel();
        BorderLayout ausleiheDetailsPanelLayout = new BorderLayout();
        _hauptPanel.add(_ruecknahmePanel, BorderLayout.EAST);
        _ruecknahmePanel.setLayout(ausleiheDetailsPanelLayout);
        _ruecknahmePanel.setPreferredSize(new java.awt.Dimension(240, -1));
        _ruecknahmePanel.setSize(240, -1);
        _ruecknahmePanel.setBackground(UIConstants.BACKGROUND_COLOR);
        erzeugeVerleihkartenAnzeiger();
        erzeugeRuecknahmeButton();
    }

    /**
     * Erzeuge das Zusammenfassung-Panel, in dem die ausgewählten Verleihkarten
     * im Detail angezeigt werden.
     */
    private void erzeugeVerleihkartenAnzeiger()
    {
        JPanel verleihkartenAnzeigerPanel = new JPanel();
        BorderLayout detailsPanelLayout = new BorderLayout();
        _ruecknahmePanel.add(verleihkartenAnzeigerPanel, BorderLayout.CENTER);
        verleihkartenAnzeigerPanel.setLayout(detailsPanelLayout);
        verleihkartenAnzeigerPanel
            .setPreferredSize(new java.awt.Dimension(-1, -1));
        verleihkartenAnzeigerPanel.setSize(-1, -1);
        verleihkartenAnzeigerPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        JScrollPane verleihkartenAnzeigerScrollPane = new JScrollPane();
        verleihkartenAnzeigerPanel.add(verleihkartenAnzeigerScrollPane,
                BorderLayout.CENTER);
        verleihkartenAnzeigerScrollPane
            .setPreferredSize(new java.awt.Dimension(-1, -1));
        verleihkartenAnzeigerScrollPane
            .setBorder(BorderFactory.createTitledBorder(null,
                    "Ausgewählte Verleihkarten", TitledBorder.LEADING,
                    TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        verleihkartenAnzeigerScrollPane.setSize(-1, -1);
        verleihkartenAnzeigerScrollPane
            .setBackground(UIConstants.BACKGROUND_COLOR);
        verleihkartenAnzeigerScrollPane.getVerticalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        verleihkartenAnzeigerScrollPane.getHorizontalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);

        _verleihkartenAnzeigerTextArea = new JTextArea();
        _verleihkartenAnzeigerTextArea
            .setBackground(UIConstants.BACKGROUND_COLOR);
        verleihkartenAnzeigerScrollPane
            .setViewportView(_verleihkartenAnzeigerTextArea);
        _verleihkartenAnzeigerTextArea.setEditable(false);
        _verleihkartenAnzeigerTextArea.setFont(UIConstants.TEXT_FONT);
    }

    /**
     * Erzeuge den Rücknahme-Button.
     */
    private void erzeugeRuecknahmeButton()
    {
        JPanel buttonPanel = new JPanel();
        _ruecknahmePanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setPreferredSize(new java.awt.Dimension(-1, 110));
        buttonPanel.setSize(-1, -1);
        buttonPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        _ruecknahmeButton = new JButton();
        buttonPanel.add(_ruecknahmeButton);
        _ruecknahmeButton.setText("zurücknehmen");
        _ruecknahmeButton.setPreferredSize(new java.awt.Dimension(140, 100));
        _ruecknahmeButton.setSize(-1, -1);
        _ruecknahmeButton.setEnabled(false);
        _ruecknahmeButton.setFont(UIConstants.BUTTON_FONT);
    }

    /**
     * Gibt das VerleihkartenTableModel der UI zurück.
     * 
     * @return Das VerleihkartenTableModel der UI.
     * 
     * @ensure result != null
     */
    public VerleihkartenTableModel getVerleihkartenAuflisterTableModel()
    {
        return _verleihkartenAuflisterTableModel;
    }

    /**
     * Gibt den Rücknahme-Button zurück.
     * 
     * @return Den Rücknahme-Button.
     * 
     * @ensure result != null
     */
    public JButton getRuecknahmeButton()
    {
        return _ruecknahmeButton;
    }

    /**
     * Gibt die JTextArea zur Darstellung der ausgewählten Verleihkarte zurück.
     * 
     * @return Die JTextArea zur darstellung der ausgewählten Verleihkarte.
     * 
     * @ensure result != null
     */
    public JTextArea getVerleihkartenAnzeigerTextArea()
    {
        return _verleihkartenAnzeigerTextArea;
    }

    /**
     * Gibt die Tabelle zur Darstellung der Verleihkarten zurück.
     * 
     * @return Die Tabelle zur Darstellung der Verleihkarten.
     * 
     * @ensure result != null
     */
    public JTable getVerleihkartenAuflisterTable()
    {
        return _verleihkartenAuflisterTable;
    }

    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @return Das Haupt-Panel der UI.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
}
