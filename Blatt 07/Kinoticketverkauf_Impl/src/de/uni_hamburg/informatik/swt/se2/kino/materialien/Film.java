package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;

/**
 * Ein Film. Ein Film besteht aus einem Titel, einer Länge in Minuten, einer
 * Altersfreigabe und einer Angabe darueber, ob der Film Überlänge hat.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
public class Film
{
    private String _titel;
    private int _laenge;
    private FSK _fsk;
    private boolean _ueberlaenge;

    /**
     * Initialisiert einen neuen Film.
     * 
     * @param titel der Titel.
     * @param laenge die Laenge in Minuten.
     * @param fsk die Altersfreigabe fuer diesen Film.
     * @param ueberlaenge hat der Film Überlaenge?
     * 
     * @require titel != null
     * @require laenge > 0
     * @require fsk != null
     * 
     * @ensure getTitel() == titel
     * @ensure getLaenge() == laenge
     * @ensure getFSK() == fsk
     * @ensure hatUeberlaenge() == ueberlaenge
     */
    public Film(String titel, int laenge, FSK fsk, boolean ueberlaenge)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        assert laenge > 0 : "Vorbedingung verletzt: laenge > 0";
        assert fsk != null : "Vorbedingung verletzt: fsk != null";

        _titel = titel;
        _laenge = laenge;
        _fsk = fsk;
        _ueberlaenge = ueberlaenge;
    }

    /**
     * Gibt den Titel dieses Films zurück.
     * 
     * @ensure result != null
     */
    public String getTitel()
    {
        return _titel;
    }

    /**
     * Gibt die Länge dieses Films in Minuten zurück.
     */
    public int getLaenge()
    {
        return _laenge;
    }

    /**
     * Gibt die Altersfreigabe fuer diesen Film zurück.
     * 
     * @ensure result != null
     */
    public FSK getFSK()
    {
        return _fsk;
    }

    /**
     * Gibt zurück, ob dieser Film Überlaenge hat.
     */
    public boolean hatUeberlaenge()
    {
        return _ueberlaenge;
    }

    @Override
    public String toString()
    {
        return "Film: Titel=" + _titel;
    }

    public String getFormatiertenString()
    {
        return getTitel() + ", " + getFSK();
    }
}
