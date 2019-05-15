package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

/**
 * KonsolenVideospiel ist ein Medium mit einer zusätzlichen Informationen zum
 * kompatiblen System. Ein KonsolenVideospiel unterscheidet sich außerdem in der
 * Berechnung des Mietpreises vom PCVideospiel.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class KonsolenVideospiel extends AbstractVideospiel implements Medium
{

    /**
     * Initialisiert ein neues KonsolenVideospiel.
     * 
     * @param titel Der Titel des Spiels
     * @param kommentar Ein Kommentar zum Spiel
     * @param system Die Bezeichnung des System
     * 
     * @require titel != null
     * @require kommentar != null
     * @require system != null
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     * @ensure getSystem() == system
     */
    public KonsolenVideospiel(String titel, String kommentar, String system)
    {
        super(titel, kommentar, system);
    }

    @Override
    public String getMedienBezeichnung()
    {
        return "KonsolenVideospiel";
    }

    @Override
    protected int getPreisNachTagen(int tage)
    {
        return (int) (Math.floor(tage / 3.0) * 700);
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
