package de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PLZ ist eine Postleitzahl mit einer Länge von 5 Zeichen und optional "D-"
 * oder "d-" davor.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public final class PLZ
{
    // der benutzte reguläre Ausdruck zur Überprüfung der Gültigkeit von
    // Postleitzahlen.
    private static final Pattern PLZ_PATTERN = Pattern
        .compile("([dD]-)?([0-9]{5})");

    /**
     * Die Postleitzahl als Stringrepräsentation (z.B. 22761)
     */
    private final String _plz;

    /**
     * Ein möglicher, erlaubter prefix;
     */
    private final String _prefix;

    /**
     * Wählt eine PLZ aus.
     * 
     * @param plz Die Postleitzahl als String.
     * 
     * @require istGueltig(plz)
     */
    public PLZ(String plz)
    {
        assert istGueltig(plz) : "Vorbedingung verletzt: istGueltig(plz) ";
        Matcher m = PLZ_PATTERN.matcher(plz);
        m.matches();
        _prefix = m.group(1);
        _plz = m.group(2);
    }

    /**
     * Liefert true, wenn die Postleitzahl 5 Zeichen und optional "D-" oder "d-"
     * davor hat, andernfalls false.
     * 
     * @param plz Eine Postleitzahl zur Überprüfung
     * @return true, wenn die Postleitzahl 5 Zeichen und optional "D-" oder "d-"
     *         davor hat, andernfalls false.
     */
    public static boolean istGueltig(String plz)
    {
        Matcher m = PLZ_PATTERN.matcher(plz);
        return m.matches();
    }

    /**
     * Zwei Postleitzahlen sind gleich wenn ihre Ziffernkombinationen gleich
     * sind. Ein eventuelles Prefix wird nicht überprüft.
     * 
     * @param obj Ein anderes Objekt.
     * @return true, wenn die die ziffernkombinationen gleich sind, ansonsten
     *         false.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof PLZ)
        {
            PLZ vergleichsPLZ = (PLZ) obj;
            result = _plz.equals(vergleichsPLZ._plz);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        return _plz.hashCode();
    }

    @Override
    public String toString()
    {
        String result = "";
        if (_prefix != null)
        {
            result += _prefix;
        }
        result += _plz;
        return result;
    }
}
