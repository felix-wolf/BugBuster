package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundendetailanzeiger;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * KundenDetailAnzeigerUI beschreibt die grafische Benutzungsoberfläche für das
 * KundenDetailAnzeigerWerkzeug.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class KundenDetailAnzeigerUI
{
    private JPanel _hauptPanel;
    private JTextArea _kundenAnzeigerTextArea;

    /**
     * Initialisiert eine neue KundenDetailAnzeigerUI.
     */
    public KundenDetailAnzeigerUI()
    {
        erzeugeHauptPanel();
        erzeugeKundenAnzeiger();
    }

    /**
     * Ereugt das Haupt-Panel.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel(new BorderLayout());
    }

    /**
     * Erzeugt das Panel in dem die Kundendetails angezeigt werden.
     */
    private void erzeugeKundenAnzeiger()
    {
        JScrollPane medienAnzeigerScrollPane = new JScrollPane();
        medienAnzeigerScrollPane.setPreferredSize(new Dimension(-1, 160));
        medienAnzeigerScrollPane.setSize(-1, -1);
        medienAnzeigerScrollPane.setBorder(BorderFactory.createTitledBorder(
                null, "Ausgewählter Kunde", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        medienAnzeigerScrollPane.setBackground(UIConstants.BACKGROUND_COLOR);
        medienAnzeigerScrollPane.getVerticalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        medienAnzeigerScrollPane.getHorizontalScrollBar()
            .setBackground(UIConstants.BACKGROUND_COLOR);

        _kundenAnzeigerTextArea = new JTextArea();
        _kundenAnzeigerTextArea.setBackground(UIConstants.BACKGROUND_COLOR);
        medienAnzeigerScrollPane.setViewportView(_kundenAnzeigerTextArea);
        _kundenAnzeigerTextArea.setEditable(false);
        _kundenAnzeigerTextArea.setFont(UIConstants.TEXT_FONT);

        _hauptPanel.add(medienAnzeigerScrollPane, BorderLayout.CENTER);
    }

    /**
     * Gibt die JTextArea, die zur Anzeige des ausgewählten Kunden verwendet
     * wird.
     * 
     * @ensure result != null
     */
    public JTextArea getKundenAnzeigerTextArea()
    {
        return _kundenAnzeigerTextArea;
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
