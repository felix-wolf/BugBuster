package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.vormerken;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * VormerkUI beschreibt die grafische Benutzungsoberfläche für das
 * VormerkWerkzeug. Sie beinhaltet einen Kundenauflister, einen Kundenanzeiger,
 * einen Medienauflister, einen Medienanzeiger und einen Vormerkbutton.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
class VormerkUI
{
    // UI-Komponenten
    private JButton _vormerkenButton;
    private JPanel _ausleihePanel;
    private JSplitPane _auflisterSplitpane;
    private JPanel _anzeigerPanel;
    private JPanel _hauptPanel;

    private final JPanel _kundenauflisterPanel;
    private final JPanel _medienauflisterPanel;
    private final JPanel _mediendetailAnzeigerPanel;
    private final JPanel _kundendetailAnzeigerPanel;

    /**
     * Initialisiert die Elemente der Benutzungsoberfläche.
     * 
     * @param kundenauflisterPanel Das UI-Panel des Kundenauflisters.
     * @param medienauflisterPanel Das UI-Panel des Medienauflisters.
     * @param mediendetailAnzeigerPanel Das UI-Panel des Mediendetailanzeigers.
     * 
     * @require (kundenauflisterPanel != null)
     * @require (medienauflisterPanel != null)
     * @require (kundendetailAnzeigerPanel != null)
     * @require (mediendetailAnzeigerPanel != null)
     */
    public VormerkUI(JPanel kundenauflisterPanel, JPanel medienauflisterPanel,
            JPanel kundendetailAnzeigerPanel, JPanel mediendetailAnzeigerPanel)
    {
        assert kundenauflisterPanel != null : "Vorbedingung verletzt: (kundenauflisterPanel != null)";
        assert medienauflisterPanel != null : "Vorbedingung verletzt: (medienauflisterPanel != null)";
        assert kundendetailAnzeigerPanel != null : "Vorbedingung verletzt: (kundendetailAnzeigerPanel != null)";
        assert mediendetailAnzeigerPanel != null : "Vorbedingung verletzt: (mediendetailAnzeigerPanel != null)";

        _kundenauflisterPanel = kundenauflisterPanel;
        _medienauflisterPanel = medienauflisterPanel;
        _mediendetailAnzeigerPanel = mediendetailAnzeigerPanel;
        _kundendetailAnzeigerPanel = kundendetailAnzeigerPanel;

        erzeugeHauptPanel();
        erzeugeAuflisterPanel();
        erzeugeAusleihePanel();
    }

    /**
     * Erzeugt das Hauptpanel, in das die Auflister und Materialanzeiger gepackt
     * werden.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel();
        _hauptPanel.setLayout(new BorderLayout());
        setNoSize(_hauptPanel);
        _hauptPanel.setAutoscrolls(true);
        _hauptPanel.setBackground(UIConstants.BACKGROUND_COLOR);
    }

    /**
     * Erzeugt das Panel für die Anzeige der Kunden- und Medien-Tabelle, die
     * durch eine Splittpane voneinander getrennt sind.
     * 
     */
    private void erzeugeAuflisterPanel()
    {
        JPanel auflisterPanel = new JPanel();
        _hauptPanel.add(auflisterPanel, BorderLayout.CENTER);
        auflisterPanel.setLayout(new BorderLayout());
        setNoSize(auflisterPanel);
        auflisterPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        _auflisterSplitpane = new JSplitPane();
        auflisterPanel.add(_auflisterSplitpane, BorderLayout.CENTER);
        _auflisterSplitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        _auflisterSplitpane.setOneTouchExpandable(true);
        _auflisterSplitpane.setDividerLocation(300);

        setNoSize(_auflisterSplitpane);
        _auflisterSplitpane.setContinuousLayout(true);
        _auflisterSplitpane.setDoubleBuffered(true);
        _auflisterSplitpane.setResizeWeight(0.5);
        _auflisterSplitpane.setBackground(UIConstants.BACKGROUND_COLOR);
        _auflisterSplitpane
            .setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        // Kundendarstellung
        _auflisterSplitpane.add(_kundenauflisterPanel, JSplitPane.TOP);
        // Mediendarstellung
        _auflisterSplitpane.add(_medienauflisterPanel, JSplitPane.BOTTOM);
    }

    /**
     * Erzeugt das Ausleih-Panel, in dem die Zusammenfassung und der
     * Ausleih-Button angezeigt werden.
     */
    private void erzeugeAusleihePanel()
    {
        _ausleihePanel = new JPanel();
        BorderLayout ausleiheDetailsPanelLayout = new BorderLayout();
        _hauptPanel.add(_ausleihePanel, BorderLayout.EAST);
        _ausleihePanel.setLayout(ausleiheDetailsPanelLayout);
        _ausleihePanel.setPreferredSize(new Dimension(240, -1));
        _ausleihePanel.setSize(240, -1);
        _ausleihePanel.setBackground(UIConstants.BACKGROUND_COLOR);
        erzeugeAnzeigerPanel();
        erzeugeAusleihButton();
    }

    /**
     * Erzeugt das Anzeige-Panel, in dem die ausgewählte Medien und der
     * ausgewählte kunde angezeigt werden.
     */
    private void erzeugeAnzeigerPanel()
    {
        _anzeigerPanel = new JPanel();
        _ausleihePanel.add(_anzeigerPanel, BorderLayout.CENTER);
        _anzeigerPanel.setLayout(new BorderLayout());
        setNoSize(_anzeigerPanel);
        _anzeigerPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        _anzeigerPanel.add(_mediendetailAnzeigerPanel, BorderLayout.CENTER);
        _anzeigerPanel.add(_kundendetailAnzeigerPanel, BorderLayout.NORTH);
    }

    /**
     * Erzeugt den Ausleih-Button.
     */
    private void erzeugeAusleihButton()
    {
        JPanel _buttonPanel = new JPanel();
        _ausleihePanel.add(_buttonPanel, BorderLayout.SOUTH);
        _buttonPanel.setPreferredSize(new Dimension(-1, 110));
        _buttonPanel.setSize(-1, -1);
        _buttonPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        _vormerkenButton = new JButton();
        _buttonPanel.add(_vormerkenButton);
        _vormerkenButton.setText("vormerken");
        _vormerkenButton.setPreferredSize(new Dimension(140, 100));
        _vormerkenButton.setSize(-1, -1);
        _vormerkenButton.setFont(UIConstants.BUTTON_FONT);
        _vormerkenButton.setEnabled(false);
    }

    /**
     * Setzt die Größe einer übergebenen Widget-Komponente auf -1.
     */
    private void setNoSize(Component component)
    {
        component.setPreferredSize(new Dimension(-1, -1));
        component.setSize(-1, -1);
    }

    /**
     * Gibt den Vormerken-Button zurück.
     * 
     * @ensure result != null
     */
    public JButton getVormerkenButton()
    {
        return _vormerkenButton;
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
