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

    private ArrayList<Beobachter> _beobachter = new ArrayList<Beobachter>();

    /**
     * Fuegt einen neuen Beobachter zu _beobachter hinzu
     * 
     * @require beobachter != null
     * @param beobachter : der neue Beobachter
     * @ensure beobachter wurde hinzugefügt
     */
    public void fuegeBeobachterHinzu(Beobachter beobachter)
    {
        assert _beobachter != null : "_beobachter ist noch nicht registriert";

        _beobachter.add(beobachter);

    }

    /**
     * Informiert die an diesem Subwerkzeug registrierten Beobachter über eine
     * Änderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Änderung geschehen ist, die für Beobachter interessant ist.
     * 
     * @param artDerAenderung die Art der Aenderung
     * @require artDerAenderung != null
     * @require _beobachter != null
     */
    public void meldeAenderung(String artDerAenderung)
    {
        assert _beobachter != null : "Vorbedingung verletzt: _beobachter != null";
        assert artDerAenderung != null : "Vorbedingung verletzt: artDerAenderung != null";
        
        for (Beobachter beobachter : _beobachter)
        {
            beobachter.beachteAenderung(artDerAenderung);
        }
    }
}
