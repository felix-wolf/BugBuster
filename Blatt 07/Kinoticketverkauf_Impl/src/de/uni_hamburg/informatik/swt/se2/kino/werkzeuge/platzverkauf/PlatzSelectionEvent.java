package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

/**
 * Event, das über eine Änderung bei der Auswahl von Plätzen informiert. Dieses
 * Event wird von einem {@link JPlatzplan} ausgelöst, wenn der Benutzer in dem
 * Platzplan Plätze auswählt. Das Event enthält die Menge der Plätze, die jetzt
 * ausgewählt sind.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
class PlatzSelectionEvent extends EventObject
{
    private Set<Platz> _ausgewaehltePlaetze;

    /**
     * Erstellt ein neues PlatzSelectionEvent.
     * 
     * @param source Das Objekt, von dem das Ereignis ausgelöst wurde.
     * @param ausgewaehltePlaetze die Menge der ausgewählten Plätze.
     */
    public PlatzSelectionEvent(Object source, Set<Platz> ausgewaehltePlaetze)
    {
        super(source);
        _ausgewaehltePlaetze = new HashSet<Platz>(ausgewaehltePlaetze);
    }

    /**
     * Gibt die Menge der nach diesem Ereignis ausgewählten Plätze zurück.
     */
    public Set<Platz> getAusgewaehltePlaetze()
    {
        return _ausgewaehltePlaetze;
    }

    @Override
    public String toString()
    {
        return "PlatzSelectionEvent[source=" + source + "]";
    }

    private static final long serialVersionUID = 1L;
}
