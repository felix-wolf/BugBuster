package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.mediendetailanzeiger;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * MedienDetailAnzeigerUI beschreibt die grafische Benutzungsoberfläche für das
 * MedienDetailAnzeigerWerkzeug.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class MedienDetailAnzeigerUI
{
    private JPanel _hauptPanel;
    private JTextArea _medienAnzeigerTextArea;

    /**
     * Initialisiert eine neue MedienDetailAnzeigerUI.
     */
    public MedienDetailAnzeigerUI()
    {
        erzeugeHauptPanel();
        erzeugeMedienAnzeiger();
    }

    /**
     * Ereugt das Haupt-Panel.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel(new BorderLayout());
    }

    /**
     * Erzeugt das Panel in dem die Mediendetails angezeigt werden.
     */
    private void erzeugeMedienAnzeiger()
    {
        JScrollPane medienAnzeigerScrollPane = new JScrollPane();
        medienAnzeigerScrollPane.setPreferredSize(new Dimension(-1, 160));
        medienAnzeigerScrollPane.setSize(-1, -1);
        medienAnzeigerScrollPane.setBorder(BorderFactory.createTitledBorder(
                null, "Ausgewählte Medien", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        medienAnzeigerScrollPane.setBackground(UIConstants.BACKGROUND_COLOR);
        medienAnzeigerScrollPane.getVerticalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        medienAnzeigerScrollPane.getHorizontalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);

        _medienAnzeigerTextArea = new JTextArea();
        _medienAnzeigerTextArea.setBackground(UIConstants.BACKGROUND_COLOR);
        medienAnzeigerScrollPane.setViewportView(_medienAnzeigerTextArea);
        _medienAnzeigerTextArea.setEditable(false);
        _medienAnzeigerTextArea.setFont(UIConstants.TEXT_FONT);

        _hauptPanel.add(medienAnzeigerScrollPane, BorderLayout.CENTER);
    }

    /**
     * Gibt die JTextArea, die zur Anzeige der ausgewählten Medien verwendet
     * wird.
     * 
     * @ensure result != null
     */
    public JTextArea getMedienAnzeigerTextArea()
    {
        return _medienAnzeigerTextArea;
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
