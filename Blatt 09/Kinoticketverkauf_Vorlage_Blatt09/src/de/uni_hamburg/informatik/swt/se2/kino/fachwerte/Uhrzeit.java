package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Eine Uhrzeit, angegeben in Stunden und Minuten.
 * 
 * @author SE2-Team
 * @version SoSe 2016
 */
public final class Uhrzeit implements Comparable<Uhrzeit>
{
    private static final int MINUTEN_PRO_TAG = 24 * 60;
    
    private final int _stunden;
    private final int _minuten;
    
    /**
     * Wählt eine Uhrzeit aus.
     * 
     * @param stunden der Stundenanteil der Uhrzeit.
     * @param minuten der Minutenanteil der Uhrzeit.
     * @require istGueltig(stunden, minuten)
     * 
     * @ensure getStunden() == stunden
     * @ensure getMinuten() == minuten
     */
    public static Uhrzeit get(int stunden, int minuten)
    {
        assert istGueltig(stunden, minuten) : "Vorbedingung verletzt: istGueltig(stunden, minuten)";

        return new Uhrzeit(stunden, minuten);
    }

    /**
     * Prüft, ob die übergebenen Stunden im Bereich 0..23 liegen und die übergebenen Minuten im Bereich 0..59
     */
    public static boolean istGueltig(int stunden, int minuten)
    {
        return (stunden >= 0) && (stunden < 24) && (minuten >= 0) && (minuten < 60);
    }

    private Uhrzeit(int stunden, int minuten)
    {
        _stunden = stunden;
        _minuten = minuten;
    }

    /**
     * Gibt den Stunden-Anteil dieser Uhrzeit zurück.
     * 
     * @ensure (result >= 0) && (result < 24)
     */
    public int getStunden()
    {
        return _stunden;
    }

    /**
     * Gibt den Minuten-Anteil dieser Uhrzeit zurück.
     * 
     * @ensure (result >= 0) && (result < 60)
     */
    public int getMinuten()
    {
        return _minuten;
    }

    /**
     * Berechnet die Zeitdauer zwischen der angegebenen Startzeit und dieser
     * Uhrzeit in Minuten. Wenn die Startzeit später als diese Uhrzeit ist, wird
     * angenommen, dass der Zeitraum über Mitternacht geht. Wenn die Startzeit
     * gleich dieser Uhrzeit ist, wird Null zurückgegeben.
     * 
     * @param start die Startzeit.
     * 
     * @require start != null
     * @ensure result >= 0
     */
    public int minutenSeit(Uhrzeit start)
    {
        assert start != null : "Vorbedingung verletzt: start != null";

        int differenz = this.minutenSeitMitternacht() - start.minutenSeitMitternacht();
        if (differenz < 0)
        {
            differenz += MINUTEN_PRO_TAG;
        }
        return differenz;
    }

    private int minutenSeitMitternacht()
    {
        return _stunden * 60 + _minuten;
    }
    
    @Override
    public int compareTo(Uhrzeit u)
    {
        return this.minutenSeitMitternacht() - u.minutenSeitMitternacht();
    }

    /**
     * Vergleicht diese Uhrzeit auf Gleichheit mit einem anderen Objekt. Zwei
     * Uhrzeiten sind gleich, wenn ihre Stunden und Minuten übereinstimmen.
     */
    @Override
    public boolean equals(Object o)
    {
        return (o instanceof Uhrzeit) && equals((Uhrzeit)o);
    }
    
    private boolean equals(Uhrzeit andereUhrzeit)
    {
        return (_stunden == andereUhrzeit._stunden) && (_minuten == andereUhrzeit._minuten);
    }

    @Override
    public int hashCode()
    {
        return minutenSeitMitternacht();
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }

    /**
     * Gibt diese Uhrzeit formatiert zurück in der Schreibweise Stunden:Minuten.
     * 
     * @ensure result != null
     */
    public String getFormatiertenString()
    {
        return String.format("%02d:%02d", _stunden, _minuten);
    }
}
