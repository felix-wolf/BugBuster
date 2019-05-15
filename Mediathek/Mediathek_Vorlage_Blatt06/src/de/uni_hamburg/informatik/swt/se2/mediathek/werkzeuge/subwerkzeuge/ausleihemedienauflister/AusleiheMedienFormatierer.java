package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Der Formatierer für Medien in der Ausleiheansicht.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class AusleiheMedienFormatierer
{

    private final Medium _medium;

    private final boolean _istVerliehen;

    private final Kunde _ersterVormerker;

    /**
     * Initialisiert einen neuen AusleiheMedienformatierer für das angegebene
     * Medium.
     * 
     * @param medium Das adaptierte Medium.
     * @param istVerliehen Der Verleihstatus des Mediums.
     * @param ersterVormerker Der erste Vormerker des Mediums, sofern es
     *            vorgemerkt ist.
     * 
     * @require medium != null
     */
    public AusleiheMedienFormatierer(Medium medium, boolean istVerliehen,
            Kunde ersterVormerker)
    {
        assert medium != null : "Vorbedingung verletzt: medium != null";

        _medium = medium;
        _istVerliehen = istVerliehen;
        _ersterVormerker = ersterVormerker;
    }

    /**
     * Gibt das Medium des Formatieres zurück.
     * 
     * @return Das Medium des Formatieres.
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Gibt die Medienbezeichnung zurück.
     */
    public String getMedienBezeichnung()
    {
        return _medium.getMedienBezeichnung();
    }

    /**
     * Gibt den Medientitel zurück.
     */
    public String getTitel()
    {
        return _medium.getTitel();
    }

    /**
     * Gibt den Ausleihstatus an.
     */
    public String getAusleihstatus()
    {
        String result = "ja";
        if (_istVerliehen)
        {
            result = "nein";
        }
        if (_ersterVormerker != null)
        {
            result += " (vorgemerkt für " + _ersterVormerker.getVorname() + " "
                    + _ersterVormerker.getNachname() + ")";
        }
        return result;
    }

}
