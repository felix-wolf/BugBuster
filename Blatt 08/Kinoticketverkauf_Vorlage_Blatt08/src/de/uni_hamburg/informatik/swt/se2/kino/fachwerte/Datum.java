package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

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
 * @version SoSe 2016
 */
public final class Datum implements Comparable<Datum>
{
    /*
     * WICHTIGER HINWEIS AN DIE STUDIERENDEN
     * 
     * Alle Exemplare der Klasse Datum teilen sich ein veränderliches Calender-Objekt.
     * Dieses Vorgehen ist extrem untypisch für Fachwerte.
     * Also nehmt diese Klasse bitte nicht als Musterbeispiel für Fachwerte!
     * Orientiert euch lieber an den Klassen Uhrzeit oder Geldbetrag. 
     * 
     * Die Klassen java.util.Date und java.util.Calendar sind so schrecklich,
     * dass sie in Java 8 durch das Paket java.time abgelöst wurden:
     * http://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html 
     */
    private static final Calendar kalender = Calendar.getInstance();
    private static final long MILLISEKUNDEN_PRO_TAG = 24L * 60 * 60 * 1000;

    private final int _jahr;
    private final int _monat;
    private final int _tag;

    // Statische Initialisierungsblöcke dienen der Initialisierung von Klassenvariablen.
    static
    {
        kalender.setLenient(false);
        kalender.setTimeZone(TimeZone.getTimeZone("GMT"));
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
    public static Datum get(int tag, int monat, int jahr)
    {
        assert istGueltig(tag, monat, jahr) : "Vorbedingung verletzt: istGueltig(tag, monat, jahr)";

        return new Datum(tag, monat, jahr);
    }

    private Datum(int tag, int monat, int jahr)
    {
        _tag = tag;
        _monat = monat;
        _jahr = jahr;
    }

    /**
     * Liefert das heutige Datum zurück.
     */
    public static Datum heute()
    {
        synchronized (kalender)
        {
            kalender.clear();
            kalender.setTimeInMillis(System.currentTimeMillis());

            return aktuellesDatumDesKalenders();
        }
    }

    private static Datum aktuellesDatumDesKalenders()
    {
        int tag = kalender.get(Calendar.DAY_OF_MONTH);
        int monat = kalender.get(Calendar.MONTH) + 1;
        int jahr = kalender.get(Calendar.YEAR);

        return get(tag, monat, jahr);
    }

    /**
     * Prüft, ob das durch Tag, Monat und Jahr angegebene Datum gültig ist.
     * 
     * @param tag Der Tag im Monat (1..31).
     * @param monat Der Monat im Jahr (1..12).
     * @param jahr Das Jahr.
     * 
     * @return true wenn drei übergebene Zahlen ein gültiges Datum ergeben,
     *         ansonsten false.
     */
    public static boolean istGueltig(int tag, int monat, int jahr)
    {
        return istGueltigerMonat(monat) && istGueltigerTag(tag, monat, jahr);
    }

    private static boolean istGueltigerMonat(int monat)
    {
        return (monat >= 1) && (monat <= 12);
    }

    private static boolean istGueltigerTag(int tag, int monat, int jahr)
    {
        synchronized (kalender)
        {
            kalender.clear();
            kalender.set(Calendar.YEAR, jahr);
            kalender.set(Calendar.MONTH, monat - 1);

            return (tag >= 1) && (tag <= kalender.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    /**
     * Addiert auf dieses Datum eine übergebene Anzahl von Tage und gibt das
     * Ergebnis als neues Datum zurück.
     * 
     * @param tage Die zu addierenden Tage
     * 
     * @return den Tag, der um die angegebene Anzahl Tage nach diesem Tag liegt.
     */
    public Datum plus(int tage)
    {
        synchronized (kalender)
        {
            kalender.clear();
            kalender.set(_jahr, _monat - 1, _tag);
            kalender.add(Calendar.DAY_OF_MONTH, tage);
            
            return aktuellesDatumDesKalenders();
        }
    }

    /**
     * Subtrahiert von diesem Datum eine übergebene Anzahl an Tagen und gibt das
     * Ergebnis als neues Datum zurück.
     * 
     * @param tage Die abzuziehenden Tage
     * 
     * @return den Tag, der um die angegebene Anzahl Tage vor diesem Tag liegt.
     */
    public Datum minus(int tage)
    {
        return plus(-tage);
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
     * @param start das Startdatum des Zeitraums.
     * 
     * @require start != null
     */
    public int tageSeit(Datum start)
    {
        assert start != null : "Vorbedingung verletzt: start != null";

        long millisekunden = this.inMillisekunden() - start.inMillisekunden();
        long tage = millisekunden / MILLISEKUNDEN_PRO_TAG;
        
        return (int) tage;
    }

    private long inMillisekunden()
    {
        synchronized (kalender)
        {
            kalender.clear();
            kalender.set(_jahr, _monat - 1, _tag);
            
            return kalender.getTimeInMillis();
        }
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof Datum) && equals((Datum) o);
    }

    private boolean equals(Datum anderesDatum)
    {
        return (_tag == anderesDatum._tag) && (_monat == anderesDatum._monat) && (_jahr == anderesDatum._jahr);
    }

    @Override
    public int hashCode()
    {
        return _jahr * 366 + _monat * 31 + _tag;
    }

    @Override
    public int compareTo(Datum anderesDatum)
    {
        return tageSeit(anderesDatum);
    }

    /**
     * Gibt eine String-Repräsentation dieses Datums zurück.
     */
    public String getFormatiertenString()
    {
        return String.format("%02d.%02d.%4d", _tag, _monat, _jahr);
    }

    /**
     * Gibt das Jahr dieses Datums zurück.
     */
    public int getJahr()
    {
        return _jahr;
    }

    /**
     * Gibt den Monat (im Jahr) dieses Datums zurück.
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
}
