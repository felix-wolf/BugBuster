package de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Ein Kalenderdatum, bestehend aus Tag, Monat und Jahr.
 * 
 * Das Klassenobjekt stellt zwei Hilfsmethoden zur Verfügung, um das heutige
 * Datum zu ermitteln und zu überprüfen, ob drei Ganzzahlen ein gültiges Datum
 * bilden.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public final class Datum implements Comparable<Datum>
{
    // Dieses Calendar-Exemplar wird für die Gültigkeitsprüfung verwendet.
    private static final Calendar CALENDAR = Calendar.getInstance();

    // Die Anzahl von Millesekunden an einem Tag
    private static final long MILLISECONDS_IN_ONE_DAY = 1000 * 60 * 60 * 24;

    // Dieses Feld ist das Jahr
    private final int _jahr;

    // Dieses Feld ist der Monat im Jahr (1..12)
    private final int _monat;

    // Dieses Feld ist der Tag im Monat (1..31)
    private final int _tag;

    // "Static initializer", initialisiert Klassenvariablen nach der Erzeugung
    // des Klassenobjekts.
    static
    {
        CALENDAR.setLenient(false);
        CALENDAR.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * Wählt ein Datum aus (tag, monat, jahr).
     * 
     * @param tag Der Tag im Monat (1..31)
     * @param monat Der Monat im Jahr (1..12)
     * @param jahr Das Jahr
     * 
     * @require istGueltig(tag, monat, jahr)
     * 
     * @ensure getTag() == tag
     * @ensure getMonat() == monat
     * @ensure getJahr() == jahr
     */
    public Datum(int tag, int monat, int jahr)
    {
        assert istGueltig(tag, monat,
                jahr) : "Vorbedingung verletzt: istGueltig(tag, monat, jahr)";

        _tag = tag;
        _monat = monat;
        _jahr = jahr;
    }

    /**
     * Liefert das heutige Datum zurück.
     * 
     * @ensure result != null
     */
    public static Datum heute()
    {
        Datum datum = null;
        synchronized (CALENDAR)
        {
            CALENDAR.clear();
            CALENDAR.setTimeInMillis(System.currentTimeMillis());
            datum = new Datum(CALENDAR.get(Calendar.DAY_OF_MONTH),
                    CALENDAR.get(Calendar.MONTH) + 1,
                    CALENDAR.get(Calendar.YEAR));
        }
        return datum;
    }

    /**
     * Prüft, ob das durch Tag, Monat und Jahr angegebene Datum gültig ist.
     * 
     * @param tag Der Tag im Monat (1..31).
     * @param monat Der Monat im Jahr (1..12).
     * @param jahr Das Jahr.
     * @return true wenn drei übergebene Zahlen ein gültiges Datum ergeben,
     *         ansonsten false.
     */
    public static boolean istGueltig(int tag, int monat, int jahr)
    {
        boolean gueltig = ((monat >= 1) && (monat <= 12));
        if (gueltig)
        {
            synchronized (CALENDAR)
            {
                CALENDAR.clear();
                CALENDAR.set(Calendar.YEAR, jahr);
                CALENDAR.set(Calendar.MONTH, monat - 1);
                gueltig = ((tag >= 1) && (tag <= CALENDAR
                    .getActualMaximum(Calendar.DAY_OF_MONTH)));
            }
        }
        return gueltig;
    }

    /**
     * Vergleicht dieses Datum mit einem anderen Datum.
     * 
     * @param datum das andere Datum.
     * @return einen Wert < 0 falls dieses Datum kleiner als datum ist, einen
     *         Wert > 0, falls dieses Datum größer als datum ist, sonst 0.
     */
    @Override
    public int compareTo(Datum datum)
    {
        return tageSeit(datum);
    }

    /**
     * Vergleicht dieses Datum auf Gleichheit. Zwei Datum-Objekte sind gleich,
     * wenn der Tag, der Monat und das Jahr gleich sind.
     * 
     * @param o ein anderes Objekt.
     * @return true, wenn beide Objekte gleich sind, ansonsten false.
     */
    @Override
    public boolean equals(Object o)
    {
        boolean result = false;
        if (o instanceof Datum)
        {
            Datum vergleichsdatum = (Datum) o;
            result = ((getTag() == vergleichsdatum.getTag())
                    && (getMonat() == vergleichsdatum.getMonat())
                    && (getJahr() == vergleichsdatum.getJahr()));
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        // Hash-Code ist ca. die Anzahl der Tage seit dem Jahr 0
        return getJahr() * 365 + getMonat() * 31 + getTag();
    }

    /**
     * Gibt das Jahr dieses Datums zurück.
     */
    public int getJahr()
    {
        return _jahr;
    }

    /**
     * Gibt den Monat (im Jahr) dieses Datums zurück (1..12).
     */
    public int getMonat()
    {
        return _monat;
    }

    /**
     * Gibt den Tag (im Monat) dieses Datums zurück.
     */
    public int getTag()
    {
        return _tag;
    }

    /**
     * Subtrahiert von diesem Datum eine übergebene Anzahl an Tagen und gibt das
     * Ergebnis als neues Datum zurück.
     * 
     * @param tage Die abzuziehenden Tage
     * @return den Tag, der um die angegebene Anzahl Tage vor diesem Tag liegt.
     * 
     * @require tage >= 0
     * @ensure result != null
     */
    public Datum minus(int tage)
    {
        assert tage >= 0 : "Vorbedingung verletzt: tage >= 0";
        Datum datum = null;
        synchronized (CALENDAR)
        {
            CALENDAR.clear();
            CALENDAR.set(_jahr, _monat - 1, _tag);
            CALENDAR.add(Calendar.DAY_OF_MONTH, -tage);
            datum = new Datum(CALENDAR.get(Calendar.DAY_OF_MONTH),
                    CALENDAR.get(Calendar.MONTH) + 1,
                    CALENDAR.get(Calendar.YEAR));
        }
        return datum;
    }

    /**
     * Addiert auf dieses Datum eine übergebene Anzahl von Tage und gibt das
     * Ergebnis als neues Datum zurück.
     * 
     * @param tage Die zu addierenden Tage
     * @return den Tag, der um die angegebene Anzahl Tage nach diesem Tag liegt.
     * 
     * @require tage >= 0
     * @ensure result != null
     */
    public Datum plus(int tage)
    {
        assert tage >= 0 : "Vorbedingung verletzt: tage >= 0";
        Datum datum = null;
        synchronized (CALENDAR)
        {
            CALENDAR.clear();
            CALENDAR.set(_jahr, _monat - 1, _tag);
            CALENDAR.add(Calendar.DAY_OF_MONTH, tage);
            datum = new Datum(CALENDAR.get(Calendar.DAY_OF_MONTH),
                    CALENDAR.get(Calendar.MONTH) + 1,
                    CALENDAR.get(Calendar.YEAR));
        }
        return datum;
    }

    /**
     * Gibt den Tag vor diesem Tag zurück.
     * 
     * @return den Tag vor diesem Tag.
     */
    public Datum vorherigerTag()
    {
        return this.minus(1);
    }

    /**
     * Gibt den Tag nach diesem Tag zurück.
     * 
     * @return den Tag nach diesem Tag.
     */
    public Datum naechsterTag()
    {
        return this.plus(1);
    }

    /**
     * Berechnet, wie viele Tage seit dem angegebenen Datum bis zu diesem Datum
     * vergangen sind.
     * 
     * @param startDatum das Startdatum des Zeitraums.
     * 
     * @require startDatum != null
     */
    public int tageSeit(Datum startDatum)
    {
        assert startDatum != null : "Vorbedingung verletzt: startDatum != null";

        long startMillis = startDatum.inMillisekunden();
        long endMillis = this.inMillisekunden();

        return (int) ((endMillis - startMillis) / MILLISECONDS_IN_ONE_DAY);
    }

    /**
     * Gibt dieses Datum in Millisekunden zurück.
     */
    private long inMillisekunden()
    {
        synchronized (CALENDAR)
        {
            CALENDAR.clear();
            CALENDAR.set(_jahr, _monat - 1, _tag);
            long endMillis = CALENDAR.getTimeInMillis();
            return endMillis;
        }
    }

    /**
     * Gibt eine String-Repräsentation dieses Datums zurück.
     * 
     * @ensure result != null
     */
    @Override
    public String toString()
    {
        return String.valueOf(_tag) + "." + String.valueOf(_monat) + "."
                + String.valueOf(_jahr);
    }
}
