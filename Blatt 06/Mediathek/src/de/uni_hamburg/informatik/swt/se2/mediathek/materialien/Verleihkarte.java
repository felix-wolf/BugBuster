package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Mit Hilfe von Verleihkarten werden beim Verleih eines Mediums alle relevanten
 * Daten notiert.
 * 
 * Sie beantwortet die folgenden Fragen: Welches Medium wurde ausgeliehen? Wer
 * hat das Medium ausgeliehen? Wann wurde das Medium ausgeliehen?
 * 
 * Wenn Medien zurück gegeben werden, kann die zugehörige Verleihkarte entsorgt
 * werden. Um die Verwaltung der Karten kümmert sich der VerleihService
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class Verleihkarte
{

    // Eigenschaften einer Verleihkarte
    private final Datum _ausleihdatum;
    private final Kunde _entleiher;
    private final Medium _medium;

    /**
     * Initialisert eine neue Verleihkarte mit den gegebenen Daten.
     * 
     * @param entleiher Ein Kunde, der das Medium ausgeliehen hat.
     * @param medium Ein verliehene Medium.
     * @param ausleihdatum Ein Datum, an dem der Kunde das Medium ausgeliehen
     *            hat.
     * 
     * @require entleiher != null
     * @require medium != null
     * @require ausleihdatum != null
     * 
     * @ensure #getEntleiher() == entleiher
     * @ensure #getMedium() == medium
     * @ensure #getAusleihdatum() == ausleihdatum
     */
    public Verleihkarte(Kunde entleiher, Medium medium, Datum ausleihdatum)
    {
        assert entleiher != null : "Vorbedingung verletzt: entleiher != null";
        assert medium != null : "Vorbedingung verletzt: medium != null";
        assert ausleihdatum != null : "Vorbedingung verletzt: ausleihdatum != null";

        _entleiher = entleiher;
        _medium = medium;
        _ausleihdatum = ausleihdatum;
    }

    /**
     * Gibt das Ausleihdatum zurück.
     * 
     * @return Das Ausleihdatum.
     * 
     * @ensure result != null
     */
    public Datum getAusleihdatum()
    {
        return _ausleihdatum;
    }

    /**
     * Gibt den Entleiher zurück.
     * 
     * @return den Kunden, der das Medium entliehen hat.
     * 
     * @ensure result != null
     */
    public Kunde getEntleiher()
    {
        return _entleiher;
    }

    /**
     * Gibt eine String-Darstellung der Verleihkarte (enhält Zeilenumbrüche)
     * zurück.
     * 
     * @return Eine formatierte Stringrepäsentation der Verleihkarte. Enthält
     *         Zeilenumbrüche.
     * 
     * @ensure result != null
     */
    public String getFormatiertenString()
    {
        return _medium.getFormatiertenString() + "am "
                + _ausleihdatum.toString() + " verliehen an\n"
                + _entleiher.getFormatiertenString();
    }

    /**
     * Gibt das Medium, dessen Ausleihe auf der Karte vermerkt ist, zurück.
     * 
     * @return Das Medium, dessen Ausleihe auf dieser Karte vermerkt ist.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Gibt die aktuelle Mietgebühr zurück.
     * 
     * @return Die aktuelle Mietgebühr bis zum heutigen Tag.
     * 
     * @ensure result != null
     */
    public Geldbetrag getMietgebuehr()
    {
        return _medium.berechneMietgebuehr(getAusleihdauer());
    }

    /**
     * Berechnet die Ausleihdauer in Tagen. Der erste Tag der Ausleihe zählt
     * vollständig als Ausleihtag
     * 
     * @return Die Ausleihdauer in Tagen.
     */
    public int getAusleihdauer()
    {
        return Datum.heute()
            .tageSeit(getAusleihdatum()) + 1;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_ausleihdatum == null) ? 0 : _ausleihdatum.hashCode());
        result = prime * result
                + ((_entleiher == null) ? 0 : _entleiher.hashCode());
        result = prime * result + ((_medium == null) ? 0 : _medium.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Verleihkarte)
        {
            Verleihkarte other = (Verleihkarte) obj;

            if (other.getAusleihdatum()
                .equals(_ausleihdatum)
                    && other.getEntleiher()
                        .equals(_entleiher)
                    && other.getMedium()
                        .equals(_medium))

                result = true;
        }
        return result;
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
