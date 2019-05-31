package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.DVD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.KonsolenVideospiel;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.PCVideospiel;

/**
 * Testklasse für den MedienComparator
 * 
 * @author SE2-Team
 * 
 */
public class MedienComparatorTest
{

    private CD _cd1;
    private CD _cd2;
    private CD _cd3;
    private DVD _dvd;
    private KonsolenVideospiel _konsolenSpiel;
    private MedienComparator _medienComparator;
    private PCVideospiel _pcSpiel;

    public MedienComparatorTest()
    {

        _cd1 = new CD("Titel a", "Kommentar", "Interpret", 100);
        _cd2 = new CD("Titel b", "Kommentar", "Interpret", 100);
        _cd3 = new CD("Titel b", "Kommentar", "Interpret", 100);

        _dvd = new DVD("Titel a", "Kommentar", "Regisseur", 100);
        _pcSpiel = new PCVideospiel("Titel b", "Windows Vista", "Kommentar");
        _konsolenSpiel = new KonsolenVideospiel("Titel a", "Kommentar", "Wii");

        _medienComparator = new MedienComparator();
    }

    @Test
    public void testCompareEinMedium()
    {
        assertTrue(_medienComparator.compare(_cd3, _cd1) > 0);
        assertTrue(_medienComparator.compare(_cd1, _cd2) < 0);
        assertEquals(0, _medienComparator.compare(_cd3, _cd2));
    }

    @Test
    public void testCompareVerschiedeneMedien()
    {
        assertTrue(_medienComparator.compare(_cd2, _dvd) < 0);
        assertTrue(_medienComparator.compare(_dvd, _pcSpiel) < 0);
        assertTrue(_medienComparator.compare(_pcSpiel, _konsolenSpiel) > 0);
    }
}
