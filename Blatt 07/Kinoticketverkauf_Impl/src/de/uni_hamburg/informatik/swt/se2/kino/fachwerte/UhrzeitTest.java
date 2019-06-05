package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UhrzeitTest
{
    @Test
    public void testeKonstruktoren()
    {
        Uhrzeit u = new Uhrzeit(15, 13);
        assertEquals(15, u.getStunden());
        assertEquals(13, u.getMinuten());
    }

    @Test
    public void testeCompareTo()
    {
        Uhrzeit u1_12_00 = new Uhrzeit(12, 00);
        Uhrzeit u2_15_20 = new Uhrzeit(15, 20);
        Uhrzeit u3_15_20 = new Uhrzeit(15, 20);

        assertTrue(u1_12_00.compareTo(u2_15_20) < 0);
        assertTrue(u2_15_20.compareTo(u1_12_00) > 0);
        assertTrue(u1_12_00.compareTo(u1_12_00) == 0);
        assertTrue(u2_15_20.compareTo(u3_15_20) == 0);
    }

    @Test
    public void testeMinutenSeit()
    {
        Uhrzeit u1_11_50 = new Uhrzeit(11, 50);
        Uhrzeit u2_15_20 = new Uhrzeit(15, 20);
        Uhrzeit u3_15_40 = new Uhrzeit(15, 40);

        assertEquals(210, u2_15_20.minutenSeit(u1_11_50));
        assertEquals(1230, u1_11_50.minutenSeit(u2_15_20));

        assertEquals(20, u3_15_40.minutenSeit(u2_15_20));
        assertEquals(1420, u2_15_20.minutenSeit(u3_15_40));

        assertEquals(230, u3_15_40.minutenSeit(u1_11_50));
        assertEquals(1210, u1_11_50.minutenSeit(u3_15_40));
    }

    @Test
    public void testeEquals()
    {
        Uhrzeit u1 = new Uhrzeit(20, 15);
        Uhrzeit u2 = new Uhrzeit(20, 15);
        Uhrzeit u3 = new Uhrzeit(20, 17);
        Uhrzeit u4 = new Uhrzeit(12, 15);

        assertEquals(u1, u2);
        assertFalse("Uhrzeit(20,15) ungleich Uhrzeit(20,17)", u1.equals(u3));
        assertFalse("Uhrzeit(20,15) ungleich Uhrzeit(12,15)", u1.equals(u4));
    }

    @Test
    public void testeHashCode()
    {
        Uhrzeit u1 = new Uhrzeit(20, 15);
        Uhrzeit u2 = new Uhrzeit(20, 15);

        assertEquals("HashCode bleibt bei zwei Aufrufen gleich", u1.hashCode(),
                u1.hashCode());
        assertEquals("HashCodes mit gleicher Uhrzeit sind gleich",
                u1.hashCode(), u2.hashCode());
    }
}
