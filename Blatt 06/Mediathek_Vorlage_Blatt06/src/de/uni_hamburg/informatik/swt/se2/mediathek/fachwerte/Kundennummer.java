package de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte;

/**
 * Mit Kundennummern können Kunden eindeutig indentifiziert werden. Jede
 * Kundennummer ist 6 Zeichen lang.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public final class Kundennummer
{
    /**
     * int-Repräsentation der Kundenummer
     */
    private final int _kundennummer;

    /**
     * Wählt eine Kundennummer, mit der angebenen Zahl, aus.
     * 
     * @param kundennummer Eine gültige Zahl.
     * 
     * @require istGueltig(kundennummer)
     */
    public Kundennummer(int kundennummer)
    {
        assert istGueltig(
                kundennummer) : "Vorbedingung verletzt: istGueltig(kundennummer)";

        _kundennummer = kundennummer;
    }

    /**
     * Prüft, ob eine gegebene Zahl eine gültige Kundennummer ist. Eine
     * Kundennummer ist gültig, wenn sie 6-stellig (dezimal) ist. Der verwendete
     * Reguläre Ausdruck zur Überprüfung lautet:"[0-9]{6}"
     * 
     * @param kundennummer Ein zu übeprüfende Zahl.
     * @return true, wenn der Reguläre Ausdruck passt, ansonsten false.
     */
    public static boolean istGueltig(int kundennummer)
    {
        return String.valueOf(kundennummer)
            .matches("[0-9]{6}");
    }

    /**
     * Zwei Kundennummern sind gleich wenn ihre Zahlenkombinationen gleich sind.
     * 
     * @param obj Ein anderes Objekt.
     * @return true, wenn die die Zahlenkombinationen gleich sind, ansonsten
     *         false.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Kundennummer)
        {
            Kundennummer vergleichsKundennummer = (Kundennummer) obj;
            result = (_kundennummer == vergleichsKundennummer._kundennummer);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        return _kundennummer;
    }

    @Override
    public String toString()
    {
        return String.valueOf(_kundennummer);
    }

}
