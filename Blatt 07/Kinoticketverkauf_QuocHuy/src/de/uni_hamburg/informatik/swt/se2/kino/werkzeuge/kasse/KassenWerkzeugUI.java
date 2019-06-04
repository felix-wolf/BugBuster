package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * Das UI des {@link KassenWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
class KassenWerkzeugUI
{
    /*
     * Die in der Oberflaeche verwendeten Icons stammen aus dem Tango-Desktop-
     * Project und dürfen frei verwendet werden. Siehe
     * http://tango.freedesktop.org/
     */

    // Die Widgets, aus denen das UI sich zusammensetzt
    private JFrame _frame;
    private JButton _beendenButton;

    /**
     * Initialisert die Oberfläche. Die Parameter sind die UIs der Subwerkzeuge,
     * die eingebettet werden.
     */
    public KassenWerkzeugUI(JPanel platzVerkaufsPanel,
            JPanel datumAuswaehlPanel, JPanel vorstellungAuswaehlPanel)
    {
        _frame = new JFrame("SE2-Kinokartenverkauf Vorlage Blatt 07 SoSe 2018");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.getContentPane().setLayout(new BorderLayout());

        JComponent topPanel = erstelleUeberschriftPanel();
        JComponent leftPanel = erstelleVorstellungsauswahlPanel(
                datumAuswaehlPanel, vorstellungAuswaehlPanel);
        JComponent rightPanel = platzVerkaufsPanel;
        JComponent bottomPanel = erstelleBeendenPanel();

        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftPanel, rightPanel);
        _frame.getContentPane().add(splitter, BorderLayout.CENTER);
        _frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        _frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Zeigt das Fenster an.
     */
    public void zeigeFenster()
    {
        _frame.setSize(1200, 900);
        _frame.setVisible(true);
    }

    /**
     * Schließt das Fenster.
     */
    public void schliesseFenster()
    {
        _frame.dispose();
    }

    /**
     * Erzeugt das Panel, in dem das Datum, der Kinosaal und die Vorstellung
     * ausgewählt werden.
     * 
     * Als Parameter werden mehrere Panel übergeben, diese werden in das hier
     * erzeugte VorstellungsauswahlPanel eingebettet.
     */
    private JPanel erstelleVorstellungsauswahlPanel(JPanel datumAuswaehlPanel,
            JPanel vorstellungAuswaehlPanel)
    {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        datumAuswaehlPanel.setBorder(BorderFactory
                .createEmptyBorder(5, 5, 5, 5));
        vorstellungAuswaehlPanel.setBorder(BorderFactory.createEmptyBorder(5,
                5, 5, 5));

        leftPanel.add(datumAuswaehlPanel, BorderLayout.NORTH);
        leftPanel.add(vorstellungAuswaehlPanel, BorderLayout.CENTER);

        return leftPanel;
    }

    /**
     * Erzeugt das Panel mit der Überschrift fuer das Programm.
     */
    private JPanel erstelleUeberschriftPanel()
    {
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel(
                "SE2-Kinokartenverkauf Vorlage Blatt 07 SoSe 2018",
                SwingConstants.CENTER);

        Font font = label.getFont().deriveFont(Font.BOLD + Font.ITALIC, 20);
        label.setFont(font);
        label.setForeground(Color.blue);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(label, BorderLayout.CENTER);

        return topPanel;
    }

    /**
     * Erzeugt das Panel mit dem Beenden-Button.
     */
    private JPanel erstelleBeendenPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        _beendenButton = new JButton("Beenden");
        bottomPanel.add(_beendenButton);

        return bottomPanel;
    }

    /**
     * Gibt den Beenden-Button zurück.
     */
    public JButton getBeendenButton()
    {
        return _beendenButton;
    }
}
