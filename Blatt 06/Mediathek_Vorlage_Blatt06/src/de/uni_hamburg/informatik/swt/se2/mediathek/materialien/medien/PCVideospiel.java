package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

/**
 * PCVideospiel ist ein Medium mit einer zusätzlichen Informationen zum
 * kompatiblen System. Ein PCVideospiel unterscheidet sich außerdem in der
 * Berechnung des Mietpreises vom KonsolenVideospiel.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class PCVideospiel extends AbstractVideospiel implements Medium
{
    /**
     * Konstruktor. Initialisiert ein neues Exemplar von PCVideospiel.
     * 
     * @param titel Der Titel des Videospiels
     * @param kommentar Ein Kommentar zum Videospiel
     * @param system Das System, auf dem das Spiel lauffähig ist
     * 
     * @require titel != null
     * @require kommentar != null
     * @require system != null
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     * @ensure getSystem() == system
     */
    public PCVideospiel(String titel, String kommentar, String system)
    {
        super(titel, kommentar, system);
    }

    @Override
    public String getMedienBezeichnung()
    {
        return "PCVideospiel";
    }

    @Override
    protected int getPreisNachTagen(int tage)
    {
        int kostenpflichtigeTage = Math.max(0, tage - 7);
        return (int) (Math.ceil(kostenpflichtigeTage / 5.0) * 500);
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
