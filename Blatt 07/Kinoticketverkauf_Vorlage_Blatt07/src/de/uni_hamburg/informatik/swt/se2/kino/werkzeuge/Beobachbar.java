package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

/**
 * Basisklasse für Subwerkzeuge, die ihr Kontextwerkzeug bei Änderungen
 * benachrichtigen möchten.
 * 
 * Diese Klasse implementiert die Schnittstelle, über die sich Beobachter an dem
 * Subwerkzeug registrieren können. In der Regel wird es genau ein beobachtendes
 * Kontextwerkzeug geben. In Ausnahmen koennen es auch mehrere sein, wenn
 * indirekte Kontextwerkzeuge ebenfalls beobachten. Diese Klasse erlaubt beides.
 * 
 * Erbende Klassen rufen die Methode #informiereUeberAenderung() auf, um alle
 * Beobachter zu benachrichtigen. Erbende Klassen müssen dokumentieren, in
 * welchen Fällen sie ihre Beobachter informieren.
 * 
 * 
 * @author Bugbuster
 * @version SoSe 2019
 */
public abstract class Beobachbar
{
    public Beobachter _beobachter; //In diesen Programm gibt es nur einen Beobachter

    /**
     * Methode, einen neuen Beobachter zu registrieren
     * 
     * @require beobachter != null
     * @param beobachter : der neue Beobachter
     */
    public void registerBeobachter(Beobachter beobachter)
    {
        //assertTrue("_beobachter ist noch nicht registriert",_beobachter == null);

        _beobachter = beobachter;

    }

    // Das kann gelöscht werden, wird nicht benutzt
    /**
     * Gib den Beobachter zurück
     * K
     * @return der registrierte Beobachter
     */
    public Beobachter getBeobachter()
    {
        return _beobachter;
    }

    /**
     * Informiert an diesem Subwerkzeug registriertem Beobachter über eine
     * Änderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Änderung geschehen ist, die für Beobachter interessant ist.
     */
    public void meldeAenderung()
    {
        //assertTrue("_beobachter ist noch nicht registriert",_beobachter == null);
        _beobachter.beachteAenderung();
    }
}
