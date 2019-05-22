package de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte;

/**
 * Ein Geldbetrag in Euro, gespeichert als ganze Euro- und ganze Cent-Beträge.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public final class Geldbetrag
{

    private final int _euroAnteil;
    private final int _centAnteil;

    /**
     * Wählt einen Geldbetrag aus.
     * 
     * @param eurocent Der Betrag in ganzen Euro-Cent
     * 
     * @require eurocent >= 0;
     */
    public Geldbetrag(int eurocent)
    {
        assert eurocent >= 0 : "Vorbedingung verletzt: eurocent >= 0";
        _euroAnteil = eurocent / 100;
        _centAnteil = eurocent % 100;
    }

    /**
     * Gibt den Eurobetrag ohne Cent zurück.
     * 
     * @return Den Eurobetrag ohne Cent.
     */
    public int getEuroAnteil()
    {
        return _euroAnteil;
    }

    /**
     * Gibt den Centbetrag ohne Eurobetrag zurück.
     */
    public int getCentAnteil()
    {
        return _centAnteil;
    }

    /**
     * Liefert einen formatierten String des Geldbetrags in der Form "10,23"
     * zurück.
     * 
     * @return eine String-Repräsentation.
     */
    public String getFormatiertenString()
    {
        return _euroAnteil + "," + getFormatiertenCentAnteil();
    }

    /**
     * Liefert einen zweistelligen Centbetrag zurück.
     * 
     * @return eine String-Repräsentation des Cent-Anteils.
     */
    private String getFormatiertenCentAnteil()
    {
        String result = "";
        if (_centAnteil < 10)
        {
            result += "0";
        }
        result += _centAnteil;
        return result;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime + _centAnteil;
        result = prime * result + _euroAnteil;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Geldbetrag)
        {
            Geldbetrag other = (Geldbetrag) obj;
            result = (_centAnteil == other._centAnteil)
                    && (_euroAnteil == other._euroAnteil);
        }
        return result;
    }

    /**
     * Gibt diesen Geldbetrag in der Form "10,21" zurück.
     */
    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
