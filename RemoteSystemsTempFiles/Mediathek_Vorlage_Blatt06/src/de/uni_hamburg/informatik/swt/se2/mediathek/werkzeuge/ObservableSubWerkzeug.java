package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge;

import java.util.HashSet;
import java.util.Set;

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
 * Diese Klasse entspricht der Klasse "Beobachtbar" im Beobachter-Muster.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public abstract class ObservableSubWerkzeug
{
    private Set<SubWerkzeugObserver> _alleBeobachter;

    /**
     * Initialisiert ein beobachtbares Subwerkzeug.
     */
    public ObservableSubWerkzeug()
    {
        _alleBeobachter = new HashSet<SubWerkzeugObserver>();
    }

    /**
     * Registriert einen Beobachter an diesem Subwerkzeug. Der Beobachter wird
     * informiert, wenn sich bei diesem Werkzeug etwas ändert.
     * 
     * @require beobachter != null
     */
    public void registriereBeobachter(SubWerkzeugObserver beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        _alleBeobachter.add(beobachter);
    }

    /**
     * Entfernt einen Beobachter dieses Subwerkzeugs. Wenn der Beobachter gar
     * nicht registriert war, passiert nichts.
     */
    public void entferneBeobachter(SubWerkzeugObserver beobachter)
    {
        _alleBeobachter.remove(beobachter);
    }

    /**
     * Informiert alle an diesem Subwerkzeug registrierten Beobachter über eine
     * Änderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Änderung geschehen ist, die für Beobachter interessant ist.
     */
    protected void informiereUeberAenderung()
    {
        for (SubWerkzeugObserver beobachter : _alleBeobachter)
        {
            beobachter.reagiereAufAenderung();
        }
    }
}
