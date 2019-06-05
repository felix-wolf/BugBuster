package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.vorstellungsauswaehler;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Die UI des {@link VorstellungsAuswaehlWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
class VorstellungsAuswaehlWerkzeugUI
{
    // Die Widgets, aus denen das UI sich zusammensetzt
    private JPanel _hauptPanel;
    private JList<VorstellungsFormatierer> _vorstellungAuswahlList;

    /**
     * Initialisiert die UI.
     */
    public VorstellungsAuswaehlWerkzeugUI()
    {
        _hauptPanel = erstellePanel();
    }

    /**
     * Erstellt das Panel mit den Widgets.
     */
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 4));
        panel.add(new JLabel("Vorstellung:"), BorderLayout.NORTH);
        _vorstellungAuswahlList = new JList<VorstellungsFormatierer>();
        _vorstellungAuswahlList.setBorder(BorderFactory.createEmptyBorder(2, 2,
                2, 2));
        _vorstellungAuswahlList
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(_vorstellungAuswahlList), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }

    /**
     * Gibt die JList zurück, in der die Vorstellungen angezeigt werden.
     */
    public JList<VorstellungsFormatierer> getVorstellungAuswahlList()
    {
        return _vorstellungAuswahlList;
    }
}
