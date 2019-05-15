package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 * Ein AbstractVideospiel bietet seine Standardimplemntation für Videospiele an.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
abstract class AbstractVideospiel extends AbstractMedium
{
    /**
     * Basispreis eines Videospiels in Cent
     */
    private static final int BASISPREIS = 200;

    /**
     * Das System, auf dem das Spiel lauffähig ist
     */
    private String _system;

    /**
     * Initialisiert ein neues Videospiel.
     * 
     * @param titel Der Titel des Spiels
     * @param system Die Bezeichnung des System
     * @param kommentar Ein Kommentar zum Spiel
     * 
     * @require titel != null
     * @require kommentar != null
     * @require system != null
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     * @ensure getSystem() == system
     */
    public AbstractVideospiel(String titel, String kommentar, String system)
    {
        super(titel, kommentar);

        assert system != null : "Vorbedingung verletzt: system != null";
        _system = system;
    }

    @Override
    public Geldbetrag berechneMietgebuehr(int mietTage)
    {
        return new Geldbetrag(BASISPREIS + getPreisNachTagen(mietTage));
    }

    @Override
    public String getFormatiertenString()
    {
        return super.getFormatiertenString() + SPACE + "System: " + getSystem()
                + "\n";
    }

    /**
     * Gibt das System zurück, auf dem das Spiel lauffähig ist.
     * 
     * @return Das System, auf dem das Spiel ausgeführt werden kann.
     * 
     * @ensure result != null
     */
    public String getSystem()
    {
        assert _system != null : "Nachbedingung verletzt: result != null";
        return _system;
    }

    /**
     * Ändert das System
     * 
     * @param system Das System des Videospiels
     * 
     * @require system != null
     * @ensure getSystem() == system
     */
    public void setSystem(String system)
    {
        assert system != null : "Vorbedingung verletzt: system != null";
        _system = system;
    }

    /**
     * Berechnet den Preis für ein Videospiel mit einer Verleihdauer von tage.
     * 
     * @param tage Verleihdauer des Videospiels in Tagen.
     */
    protected abstract int getPreisNachTagen(int tage);

}
