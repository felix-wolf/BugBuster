package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge;

import java.io.Serializable;
import java.util.Comparator;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Comparator-Klasse um zwei Medien zu vergleichen, damit sie an der UI sortiert
 * angezeigt werden können.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class MedienComparator implements Comparator<Medium>, Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Vergleicht zwei gegebene Medien anhand der Medienbezeichnung und des
     * Medientitels.
     * 
     * @return einen Wert <0 falls medium1 vor medium2 kommmt, einen Wert>0, im
     *         umgekehrten Fall. Bei gleicher Medienbezeichnung und Titel ist
     *         das Ergebnis 0.
     */
    @Override
    public int compare(Medium medium1, Medium medium2)
    {
        int result = 0;
        if ((medium1 != null) && (medium2 != null))
        {
            result = medium1.getMedienBezeichnung()
                .compareTo(medium2.getMedienBezeichnung());
            if (result == 0)
            {
                result = medium1.getTitel()
                    .compareTo(medium2.getTitel());
            }
        }
        return result;
    }

}
