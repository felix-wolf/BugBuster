package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

/**
 * Testklasse für den KundenComparator
 * 
 * @author SE2-Team
 * 
 */
public class KundenComparatorTest
{
    private KundenComparator _comparator;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;

    public KundenComparatorTest()
    {
        _kunde1 = new Kunde(new Kundennummer(111111), "Hans", "Meier");
        _kunde2 = new Kunde(new Kundennummer(543453), "Heidi", "Klum");
        _kunde3 = new Kunde(new Kundennummer(432343), "Lara", "Klum");
        _comparator = new KundenComparator();
    }

    @Test
    public void testCompare()
    {
        assertTrue(_comparator.compare(_kunde1, _kunde2) > 0);
        assertTrue(_comparator.compare(_kunde2, _kunde1) < 0);
        assertEquals(0, _comparator.compare(_kunde2, _kunde3));
    }

}
