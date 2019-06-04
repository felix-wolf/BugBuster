package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

import java.util.ArrayList;

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
public abstract class Beobachtbar
{

    public ArrayList<Beobachter> _beobachter = new ArrayList<Beobachter>();

    /**
     * Methode, einen neuen Beobachter zu registrieren
     * 
     * @require beobachter != null
     * @param beobachter : der neue Beobachter
     */
    public void fuegeBeobachterHinzu(Beobachter beobachter)
    {
        assert _beobachter != null : "_beobachter ist noch nicht registriert";

        _beobachter.add(beobachter);

    }

    /**
     * Informiert an diesem Subwerkzeug registriertem Beobachter über eine
     * Änderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Änderung geschehen ist, die für Beobachter interessant ist.
     * 
     */
    public void meldeAenderung(String artDerAenderung)
    {
        //Hier gibt es noch Fehler
        assert _beobachter != null : "_beobachter ist noch nicht registriert";
        for (Beobachter beobachter : _beobachter)
        {
            beobachter.beachteAenderung(artDerAenderung);
        }
    }
}
