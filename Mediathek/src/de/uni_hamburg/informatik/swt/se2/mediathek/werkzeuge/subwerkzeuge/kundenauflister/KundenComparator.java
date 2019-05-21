package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister;

import java.io.Serializable;
import java.util.Comparator;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

/**
 * Comparator-Klasse um zwei Kunden zu vergleichen, damit sie an der UI sortiert
 * angezeigt werden können.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class KundenComparator implements Comparator<Kunde>, Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Vergleicht zwei gegebene Kunden anhand des Nachnamens.
     * 
     * @return einen Wert <0 falls der Nachname von kunde1 lexikographisch vor
     *         dem Nachnamen von kunde2 kommt, einen Wert>0, im umgekehrten
     *         Fall. Bei gleichen Nachnamen ist das Ergebnis 0.
     * 
     */
    @Override
    public int compare(Kunde kunde1, Kunde kunde2)
    {
        int result = 0;
        if ((kunde1 != null) && (kunde2 != null))
        {
            result = kunde1.getNachname()
                .compareTo(kunde2.getNachname());
        }
        return result;
    }

}
