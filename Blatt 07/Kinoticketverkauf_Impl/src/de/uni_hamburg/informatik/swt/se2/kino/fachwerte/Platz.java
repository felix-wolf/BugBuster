package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Eine Sitzplatzangabe in einem Kinosaal. Der Platz setzt sich zusammen aus der
 * Reihe und dem Sitz in dieser Reihe.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
public final class Platz
{
    private final int _reihe;
    private final int _sitz;

    /**
     * Wählt einen Platz aus.
     * 
     * @param reihe die Sitzreihe.
     * @param sitz die Nummer des Sitzes in seiner Sitzreihe.
     * 
     * @require reihe >=0
     * @require sitz >= 0
     */
    public Platz(int reihe, int sitz)
    {
        assert reihe >= 0 : "Vorbedingung verletzt: reihe <=0";
        assert sitz >= 0 : "Vorbedingung verletzt: sitz <=0";

        _reihe = reihe;
        _sitz = sitz;
    }

    /**
     * Gibt die Sitzreihe zurueck, in der sich dieser Platz befindet.
     */
    public int getReihe()
    {
        return _reihe;
    }

    /**
     * Gibt die Nummer dieses Sitzes in seiner Sitzreihe zurueck.
     */
    public int getSitz()
    {
        return _sitz;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean ergebnis = false;
        if (o instanceof Platz)
        {
            Platz platz = (Platz) o;
            ergebnis = ((platz.getReihe() == this.getReihe()) && (platz
                    .getSitz() == this.getSitz()));
        }
        return ergebnis;
    }

    @Override
    public int hashCode()
    {
        return 1000 * getReihe() + getSitz();
    }

    @Override
    public String toString()
    {
        return _reihe + "-" + _sitz;
    }
}
