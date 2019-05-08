package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator-Klasse um zwei VormerkMedienFormatierer zu vergleichen, damit sie
 * an der UI sortiert angezeigt werden können.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VormerkMedienFormatiererComparator
        implements Comparator<VormerkMedienFormatierer>, Serializable
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
    public int compare(VormerkMedienFormatierer mediumFormatierer1,
            VormerkMedienFormatierer mediumFormatierer2)
    {
        int result = 0;
        if ((mediumFormatierer1 != null) && (mediumFormatierer2 != null))
        {
            String mediumBezeichnung1 = mediumFormatierer1
                .getMedienBezeichnung();
            String mediumBezeichnung2 = mediumFormatierer2
                .getMedienBezeichnung();
            result = mediumBezeichnung1.compareTo(mediumBezeichnung2);
            if (result == 0)
            {
                String mediumTitel1 = mediumFormatierer1.getTitel();
                String mediumTitel2 = mediumFormatierer2.getTitel();
                result = mediumTitel1.compareTo(mediumTitel2);
            }
        }
        return result;
    }

}
