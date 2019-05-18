package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge;

/**
 * Interface für Beobachter, die sich für Änderungen eines ObservableSubWerkzeug
 * interessieren.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public interface SubWerkzeugObserver
{
    /**
     * Diese Operation wird aufgerufen, sobald sich an an dem beobachteten
     * Werkzeug etwas relevantes geändert hat.
     * 
     * Implementierende Klassen müssen in dieser Operation auf die Aenderung
     * reagieren.
     */
    void reagiereAufAenderung();
}
