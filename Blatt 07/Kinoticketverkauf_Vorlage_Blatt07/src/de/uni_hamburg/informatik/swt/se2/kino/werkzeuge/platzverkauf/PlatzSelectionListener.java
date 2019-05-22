package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.util.EventListener;

/**
 * Interface eines Listeners, der bei Änderungen der Platzauswahl benachrichtigt
 * wird.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
interface PlatzSelectionListener extends EventListener
{
    /**
     * Wird aufgerufen, wenn sich die Auswahl geändert hat.
     * 
     * @param event das Event, das die Änderung beschreibt.
     */
    void auswahlGeaendert(PlatzSelectionEvent event);
}
