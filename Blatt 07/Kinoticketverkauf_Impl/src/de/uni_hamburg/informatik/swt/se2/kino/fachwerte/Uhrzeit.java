package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Eine Uhrzeit, angegeben in Stunden und Minuten.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
public final class Uhrzeit implements Comparable<Uhrzeit>
{
    private final int _stunden;
    private final int _minuten;

    /**
     * Wählt eine Uhrzeit aus.
     * 
     * @param stunden der Stundenanteil der Uhrzeit.
     * @param minuten der Minutenanteil der Uhrzeit.
     * 
     * @require stunden >= 0 && stunden < 24
     * @require minuten >= 0 && minuten < 60
     * @ensure getStunden() == stunden
     * @ensure getMinuten() == minuten
     */
    public Uhrzeit(int stunden, int minuten)
    {
        assert (stunden >= 0) && (stunden < 24) : "Vorbedingung verletzt: stunden >= 0 && stunden < 24";
        assert (minuten >= 0) && (minuten < 60) : "Vorbedingung verletzt: minuten >= 0 && minuten < 60";

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
        assert (_stunden >= 0) && (_stunden < 24) : "Nachbedingung verletzt: (result >= 0) && (result < 24)";

        return _stunden;
    }

    /**
     * Gibt den Minuten-Anteil dieser Uhrzeit zurück.
     * 
     * @ensure (result >= 0) && (result < 60)
     */
    public int getMinuten()
    {
        assert (_minuten >= 0) && (_minuten < 60) : "Nachbedingung verletzt: (result >= 0) && (result < 60)";

        return _minuten;
    }

    /**
     * Berechnet die Zeitdauer zwischen der angegebenen Startzeit und dieser
     * Uhrzeit in Minuten. Wenn die Startzeit später als diese Uhrzeit ist, wird
     * angenommen, dass der Zeitraum über Mitternacht geht. Wenn die Startzeit
     * gleich dieser Uhrzeit ist, wird Null zurückgegeben.
     * 
     * @param startzeit die Startzeit.
     * 
     * @require startzeit != null
     * @ensure result >= 0
     */
    public int minutenSeit(Uhrzeit startzeit)
    {
        assert startzeit != null : "Vorbedingung verletzt: startzeit != null";

        boolean amSelbenTag = (_stunden > startzeit._stunden)
                || ((_stunden == startzeit._stunden) && (_minuten >= startzeit._minuten));

        Uhrzeit u2 = amSelbenTag ? this : startzeit;
        Uhrzeit u1 = amSelbenTag ? startzeit : this;

        int result = (u2._stunden - u1._stunden) * 60 + u2._minuten
                - u1._minuten;

        if (!amSelbenTag)
        {
            result = 24 * 60 - result;
        }

        assert result >= 0 : "Nachbedingung verletzt: result >= 0";
        return result;
    }

    @Override
    public int compareTo(Uhrzeit u)
    {
        return (_stunden - u._stunden) * 60 + _minuten - u._minuten;
    }

    /**
     * Vergleicht diese Uhrzeit auf Gleichheit mit einem anderen Objekt. Zwei
     * Uhrzeiten sind gleich, wenn ihre Stunden und Minuten übereinstimmen.
     */
    @Override
    public boolean equals(Object o)
    {
        boolean ergebnis = false;
        if (o instanceof Uhrzeit)
        {
            Uhrzeit u = (Uhrzeit) o;
            ergebnis = (_stunden == u._stunden) && (_minuten == u._minuten);
        }
        return ergebnis;
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
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
        String result = _stunden + ":";
        if (_minuten < 10)
        {
            result = result + "0";
        }
        return result + _minuten;
    }
}
