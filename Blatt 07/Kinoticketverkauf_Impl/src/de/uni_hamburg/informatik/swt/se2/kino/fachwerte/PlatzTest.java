package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlatzTest
{
    @Test
    public void testPlatzNullNullIstGueltig()
    {
        Platz p = new Platz(0, 0);
        assertEquals(0, p.getReihe());
        assertEquals(0, p.getSitz());
    }

    @Test
    public void testPlatzGibtReiheUndSitzZurueck()
    {
        Platz p = new Platz(123, 456);
        assertEquals(123, p.getReihe());
        assertEquals(456, p.getSitz());
    }

    @Test
    public void testEqualsUndHashCode()
    {
        Platz p1 = new Platz(1, 2);
        Platz p2 = new Platz(1, 2);
        Platz p3 = new Platz(1, 3); // Sitz unterschiedlich
        Platz p4 = new Platz(2, 2); // Reihe unterschiedlich

        assertTrue(p1.equals(p2));
        assertTrue(p1.hashCode() == p2.hashCode());

        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
    }
}
