package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

/**
 * Ein Kinosaal. Ein Kinosaal hat einen Namen und kennt die Anzahl seiner
 * Sitzplätze.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
public class Kinosaal
{
    private String _name;
    private int _anzahlReihen;
    private int _anzahlSitzeProReihe;

    /**
     * Initialisiert einen neuen Kinosaal.
     * 
     * @param name der Name des Kinosaals.
     * @param anzahlReihen die Anzahl der Reihen.
     * @param anzahlSitzeProReihe die Anzahl der Sitze Pro Reihe.
     * 
     * @require name != null
     * @require anzahlReihen > 0
     * @require anzahlSitzeProReihe > 0
     * 
     * @ensure getName() == name
     * @ensure getAnzahlReihen() == anzahlReihen
     * @ensure getAnzahlSitzeProReihe() == anzahlSitzeProReihe
     */
    public Kinosaal(String name, int anzahlReihen, int anzahlSitzeProReihe)
    {
        assert name != null : "Vorbedingung verletzt: name != null";
        assert anzahlReihen > 0 : "Vorbedingung verletzt: anzahlReihen > 0";
        assert anzahlSitzeProReihe > 0 : "Vorbedingung verletzt: anzahlSitzeProReihe > 0";

        _name = name;
        _anzahlReihen = anzahlReihen;
        _anzahlSitzeProReihe = anzahlSitzeProReihe;
    }

    /**
     * Gibt den Namen dieses Kinosaals zurück.
     * 
     * @ensure result != null
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Gibt die Anzahl der Reihen in diesem Kinosaal zurück.
     * 
     * @ensure result > 0
     */
    public int getAnzahlReihen()
    {
        return _anzahlReihen;
    }

    /**
     * Gibt die Anzahl der Sitze pro Reihe in diesem Kinosaal zurück.
     * 
     * @ensure result > 0
     */
    public int getAnzahlSitzeProReihe()
    {
        return _anzahlSitzeProReihe;
    }

    /**
     * Gibt eine Liste der Plätze in diesem Kinosaal zurück.
     * 
     * @ensure result != null
     */
    public List<Platz> getPlaetze()
    {
        List<Platz> kinoPlaetze = new ArrayList<Platz>();
        int reihen = getAnzahlReihen();
        int plaetze = getAnzahlSitzeProReihe();
        for (int i = 0; i < reihen; i++)
        {
            for (int j = 0; j < plaetze; j++)
            {
                Platz platz = new Platz(i, j);
                kinoPlaetze.add(platz);
            }
        }
        return kinoPlaetze;
    }

    /**
     * Prüft, ob es den angegebenen Platz in dem Kinosaal gibt.
     * 
     * @param platz der Platz.
     * 
     * @return <code>true</code>, falls der Platz existiert, <code>false</code>
     *         sonst.
     * 
     * @require platz != null
     */
    public boolean hatPlatz(Platz platz)
    {
        assert platz != null : "Vorbedingung verletzt: platz != null";

        return ((platz.getReihe() >= 0) && (platz.getReihe() < _anzahlReihen))
                && ((platz.getSitz() >= 0) && (platz.getSitz() < _anzahlSitzeProReihe));
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Kinosaal)
        {
            Kinosaal saal = (Kinosaal) obj;
            result = getName().equals(saal.getName())
                    && getAnzahlReihen() == saal.getAnzahlReihen()
                    && getAnzahlSitzeProReihe() == saal
                            .getAnzahlSitzeProReihe();
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + _anzahlReihen;
        result = prime * result + _anzahlSitzeProReihe;
        result = prime * result + ((_name == null) ? 0 : _name.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Kinosaal: Name=" + _name;
    }
}
