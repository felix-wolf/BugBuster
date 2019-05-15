package de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author SE2-Team
 * @version SoSe 2019
 * 
 */
public class KundennummerTest
{

    @Test
    public void testEqualsUndHashcode()
    {
        Kundennummer kundennummer1 = new Kundennummer(123456);
        assertEquals("123456", kundennummer1.toString());

        Kundennummer kundennummer2 = new Kundennummer(123456);
        assertTrue(kundennummer1.equals(kundennummer2));
        assertTrue(kundennummer1.hashCode() == kundennummer2.hashCode());

        Kundennummer kundennummer3 = new Kundennummer(654321);
        assertFalse(kundennummer1.equals(kundennummer3));
        assertFalse(kundennummer1.hashCode() == kundennummer3.hashCode());
    }

    @Test
    public void testIstGueltig()
    {
        assertTrue(Kundennummer.istGueltig(111111));
        assertFalse(Kundennummer.istGueltig(0));
        assertFalse(Kundennummer.istGueltig(1111111));
    }
}
