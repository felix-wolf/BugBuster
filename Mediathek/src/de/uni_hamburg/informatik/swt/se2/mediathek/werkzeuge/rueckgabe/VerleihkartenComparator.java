package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.rueckgabe;

import java.io.Serializable;
import java.util.Comparator;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.MedienComparator;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister.KundenComparator;

/**
 * Comparator-Klasse um zwei Verleihkarten zu vergleichen, damit sie an der GUI
 * sortiert angezeigt werden können.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VerleihkartenComparator
        implements Comparator<Verleihkarte>, Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Vergleicht die gegebenen Verleihkarten anhand des Kunden. Verleihkarten
     * mit gleichem Kunden werden anhand des Mediums verglichen. Für den
     * Vergleich werden die Klassen KundenComparator und MedienComparator
     * verwendet.
     * 
     * @return einen Wert <0 falls verleihkarte1 vor verleihkarte2 kommt, einen
     *         Wert>0, im umgekehrten Fall. Bei gleichem Kunden und Medium ist
     *         das Ergebnis 0.
     * 
     */
    @Override
    public int compare(Verleihkarte verleihkarte1, Verleihkarte verleihkarte2)
    {
        int result = 0;
        if ((verleihkarte1 != null) && (verleihkarte2 != null))
        {
            KundenComparator kundenComparator = new KundenComparator();
            result = kundenComparator.compare(verleihkarte1.getEntleiher(),
                    verleihkarte2.getEntleiher());

            if (result == 0)
            {
                MedienComparator medienComparator = new MedienComparator();
                result = medienComparator.compare(verleihkarte1.getMedium(),
                        verleihkarte2.getMedium());

            }
        }
        return result;
    }

}
