package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.datumsauswaehler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;

/**
 * Mit diesem Werkzeug kann ein Datum ausgewählt werden.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es benachrichtigt sein
 * Kontextwerkzeug, wenn sich das ausgewählte Datum geändert hat.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
public class DatumAuswaehlWerkzeug
{
    private DatumAuswaehlWerkzeugUI _ui;
    private Datum _ausgewaehltesDatum;

    /**
     * Initialisiert dieses Werkzeug. Das initial ausgewählte Datum ist der
     * heutige Tag.
     */
    public DatumAuswaehlWerkzeug()
    {
        _ausgewaehltesDatum = Datum.heute();
        _ui = new DatumAuswaehlWerkzeugUI(
                _ausgewaehltesDatum.getFormatiertenString());
        registriereUIAktionen();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Zurueck-Button gedrueckt wurde.
     */
    private void zurueckButtonWurdeGedrueckt()
    {
        _ausgewaehltesDatum = _ausgewaehltesDatum.vorherigerTag();
        _ui.getDatumLabel()
                .setText(_ausgewaehltesDatum.getFormatiertenString());
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Weiter-Button gedrueckt wurde.
     */
    private void weiterButtonWurdeGedrueckt()
    {
        _ausgewaehltesDatum = _ausgewaehltesDatum.naechsterTag();
        _ui.getDatumLabel()
                .setText(_ausgewaehltesDatum.getFormatiertenString());
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
     * Kontextwerkzeug eingebettet werden.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }

    /**
     * Gibt das im Werkzeug ausgewählte Datum zurück.
     * 
     * @ensure result != null
     */
    public Datum getSelektiertesDatum()
    {
        return _ausgewaehltesDatum;
    }

    /**
     * Verbindet die fachlichen Aktionen mit den Interaktionselementen der
     * graphischen Benutzungsoberfläche.
     */
    private void registriereUIAktionen()
    {
        _ui.getZurueckButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                zurueckButtonWurdeGedrueckt();
            }
        });

        _ui.getWeiterButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                weiterButtonWurdeGedrueckt();
            }
        });
    }
}
