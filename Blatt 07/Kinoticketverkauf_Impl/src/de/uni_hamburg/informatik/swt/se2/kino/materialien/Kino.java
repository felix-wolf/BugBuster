package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;

/**
 * Ein Kino mit mehreren Kinosälen, in denen Vorstellungen laufen koennen.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
public class Kino
{
    private List<Kinosaal> _kinosaele;

    // Die Vorstellungspläne, sortiert nach Tagen.
    private SortedMap<Datum, Tagesplan> _tagesplaene;

    /**
     * Initialisiert ein Kino.
     * 
     * @param saele die Kinosäle des Kinos.
     * @param vorstellungen die Vorstellungen, die in dem Kino laufen.
     * 
     * @require saele != null
     * @require vorstellungen != null
     * @require saele enthaelt keine Nullpointer
     * @require vorstellungen enthaelt keine Nullpointer
     * @require alle Vorstellungen laufen in uebergebenen Kinosälen
     * @require alle Vorstellungen fangen zu unterschiedlichen Zeiten an
     */
    public Kino(Kinosaal[] saele, Vorstellung[] vorstellungen)
    {
        assert saele != null : "Vorbedingung verletzt: saele != null";
        assert vorstellungen != null : "Vorbedingung verletzt: vorstellungen != null";

        _kinosaele = new ArrayList<Kinosaal>(saele.length);
        _tagesplaene = new TreeMap<Datum, Tagesplan>();

        for (Kinosaal saal : saele)
        {
            assert saal != null : "Vorbedingung verletzt: saele enthaelt keine Nullpointer";
            _kinosaele.add(saal);
        }

        for (Vorstellung vorstellung : vorstellungen)
        {
            assert vorstellung != null : "Vorbedingung verletzt: vorstellungen enthaelt keine Nullpointer";
            Kinosaal saal = vorstellung.getKinosaal();
            assert _kinosaele.contains(saal) : "Vorbedingung verletzt: alle Vorstellungen laufen in uebergebenen Kinosaelen";
            Datum datum = vorstellung.getDatum();
            Tagesplan tagesplan = _tagesplaene.get(datum);
            if (tagesplan == null)
            {
                tagesplan = new Tagesplan(datum);
                _tagesplaene.put(datum, tagesplan);
            }
            tagesplan.fuegeVorstellungHinzu(vorstellung);
        }
    }

    /**
     * Prüft, ob der angegebene Kinosaal zu diesem Kino gehört.
     * 
     * @param kinosaal der Kinosaal.
     */
    public boolean hatKinosaal(Kinosaal kinosaal)
    {
        return _kinosaele.contains(kinosaal);
    }

    /**
     * Gibt die Kinosäle dieses Kinos zurück.
     * 
     * @ensure result != null
     */
    public List<Kinosaal> getKinosaele()
    {
        return new ArrayList<Kinosaal>(_kinosaele);
    }

    /**
     * Gibt den Tagesplan fuer das angegebene Datum zurück.
     * 
     * @param tag das Datum.
     * 
     * @require tag != null
     * @ensure result != null
     */
    public Tagesplan getTagesplan(Datum tag)
    {
        assert tag != null : "Vorbedingung verletzt: tag != null";

        Tagesplan tagesplan = _tagesplaene.get(tag);
        if (tagesplan == null)
        {
            tagesplan = new Tagesplan(tag);
        }
        return tagesplan;
    }
}
