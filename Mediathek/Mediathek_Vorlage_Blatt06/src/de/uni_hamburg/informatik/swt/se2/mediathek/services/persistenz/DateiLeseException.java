package de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz;

import java.io.IOException;

/**
 * Eine DateiLeseException signalisiert, dass das Lesen aus einer Datei nicht
 * geklappt hat.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class DateiLeseException extends IOException
{

    private static final long serialVersionUID = 1L;

    /**
     * Initialisiert eine neue DateiLeseException mit der übergebenen
     * Fehlermeldung.
     * 
     * @param message Eine beschreibung des Fehlers.
     * 
     */
    public DateiLeseException(String message)
    {
        super(message);
    }
}
