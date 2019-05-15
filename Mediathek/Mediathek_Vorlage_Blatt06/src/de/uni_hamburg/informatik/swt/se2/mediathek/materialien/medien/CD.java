package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

/**
 * Eine CD ist ein Medium. Zusätzlich zu den Eigenschaften eines Mediums erfasst
 * sie Informationen zu Spiellänge und Interpret.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class CD extends AbstractMedium implements Medium
{
    /**
     * Der Interpret der CD
     */
    private String _interpret;

    /**
     * Die Spiellänge der CD in Minuten
     */
    private int _spiellaenge;

    /**
     * Initialisiert ein neues Exemplar.
     * 
     * @param titel Der Titel der CD
     * @param kommentar Ein Kommentar zu der CD
     * @param interpret Der Interpret der CD
     * @param spiellaenge Die Spiellaenge der CD in Minuten
     * 
     * @require titel != null
     * @require kommentar != null
     * @require interpret != null
     * @require spiellaenge > 0
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     * @ensure getInterpret() == interpret
     * @ensure getSpiellaenge() == spiellaenge
     */
    public CD(String titel, String kommentar, String interpret, int spiellaenge)
    {
        super(titel, kommentar);
        assert spiellaenge > 0 : "Vorbedingung verletzt: spiellaenge > 0";
        assert interpret != null : "Vorbedingung verletzt: interpret != null";
        _spiellaenge = spiellaenge;
        _interpret = interpret;
    }

    @Override
    public String getFormatiertenString()
    {
        return super.getFormatiertenString() + SPACE + "Interpret: "
                + _interpret + "\n" + SPACE + "Spiellänge: " + _spiellaenge
                + "\n";
    }

    /**
     * Gibt den Interpreten der CD zurück.
     * 
     * @return Den Interpreten der CD.
     * 
     * @ensure result != null
     */
    public String getInterpret()
    {
        return _interpret;
    }

    /**
     * Ändert den Interpreten
     * 
     * @param interpret Der Interpret des Mediums
     * 
     * @require interpret != null
     * @ensure getInterpret() == interpret
     */
    public void setInterpret(String interpret)
    {
        assert interpret != null : "Vorbedingung verletzt: interpret != null";
        _interpret = interpret;
    }

    @Override
    public String getMedienBezeichnung()
    {
        return "CD";
    }

    /**
     * Gibt die Spiellänge (in Minuten) der CD zurück.
     * 
     * @return Die Spiellänge der CD.
     * 
     * @ensure result > 0
     */
    public int getSpiellaenge()
    {
        return _spiellaenge;
    }

    /**
     * Ändert die Spiellänge
     * 
     * @param spiellaenge Spiellänge des Medium
     * 
     * @require spiellaenge > 0
     * @ensure getSpielaenge() == spiellaenge
     */
    public void setSpiellaenge(int spiellaenge)
    {
        assert spiellaenge > 0 : "Vorbedingung verletzt: spiellaenge > 0";
        _spiellaenge = spiellaenge;
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
