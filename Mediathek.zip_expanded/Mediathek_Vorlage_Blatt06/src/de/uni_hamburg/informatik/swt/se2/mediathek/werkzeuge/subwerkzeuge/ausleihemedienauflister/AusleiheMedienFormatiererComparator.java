package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister;

import java.io.Serializable;
import java.util.Comparator;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Comparator-Klasse um zwei MedienF zu vergleichen, damit sie an der UI
 * sortiert angezeigt werden können.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class AusleiheMedienFormatiererComparator
        implements Comparator<AusleiheMedienFormatierer>, Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Vergleicht zwei gegebene Medien anhand der Medienbezeichnung und des
     * Medientitels.
     * 
     * @return einen Wert < 0 falls mediumFormatierer1 < mediumFormatierer2,
     *         einen Wert > 0, im umgekehrten Fall, oder 0 wenn beide gleich
     *         sind.
     */
    @Override
    public int compare(AusleiheMedienFormatierer mediumFormatierer1,
            AusleiheMedienFormatierer mediumFormatierer2)
    {
        int result = 0;
        if ((mediumFormatierer1 != null) && (mediumFormatierer2 != null))
        {
            Medium medium1 = mediumFormatierer1.getMedium();
            Medium medium2 = mediumFormatierer2.getMedium();
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
