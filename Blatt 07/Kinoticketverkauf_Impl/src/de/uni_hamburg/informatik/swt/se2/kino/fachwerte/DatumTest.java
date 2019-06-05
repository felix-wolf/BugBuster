package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatumTest
{
    @Test
    public void testeCompareTo()
    {
        Datum datum1 = new Datum(1, 12, 2005);
        Datum datum2 = new Datum(1, 12, 2006);
        assertTrue(datum1.compareTo(datum2) < 0);
        assertTrue(datum2.compareTo(datum1) > 0);
        assertTrue(datum1.compareTo(datum1) == 0);
    }

    @Test
    public void testeKonstruktoren()
    {
        Datum datum = new Datum(1, 12, 2006);
        assertEquals(1, datum.getTag());
        assertEquals(12, datum.getMonat());
        assertEquals(2006, datum.getJahr());
        assertNotNull(datum.toString());
    }

    @Test
    public void testeTageSeit()
    {
        Datum datum1 = new Datum(31, 12, 2006);
        Datum datum2 = new Datum(1, 1, 2007);
        assertEquals(1, datum2.tageSeit(datum1));
    }

    @Test
    public void testeVorherigerTag()
    {
        Datum datum1 = new Datum(1, 1, 2007);
        Datum datum2 = new Datum(31, 12, 2006);
        assertEquals(datum2, datum1.vorherigerTag());
    }

    @Test
    public void testeNaechsterTag()
    {
        Datum datum1 = new Datum(31, 12, 2006);
        Datum datum2 = new Datum(1, 1, 2007);
        assertEquals(datum2, datum1.naechsterTag());
    }

    @Test
    public void testeMinus()
    {
        Datum datum1 = new Datum(1, 1, 2007);
        Datum datum2 = new Datum(31, 12, 2006);
        assertEquals(datum2, datum1.minus(1));

        Datum datum3 = new Datum(15, 12, 2006);
        assertEquals(datum3, datum2.minus(16));
    }

    @Test
    public void testePlus()
    {
        Datum datum1 = new Datum(31, 12, 2006);
        Datum datum2 = new Datum(1, 1, 2007);
        assertEquals(datum2, datum1.plus(1));

        Datum datum3 = new Datum(15, 1, 2007);
        assertEquals(datum3, datum2.plus(14));
    }

    @Test
    public void testEqualsUndHashCode()
    {
        Datum datum1 = new Datum(1, 12, 2005);
        Datum datum2 = new Datum(1, 12, 2005);
        assertEquals(datum1, datum2);
        assertTrue(datum1.hashCode() == datum2.hashCode());

        Datum datum3 = new Datum(1, 12, 2006);
        assertFalse(datum1.equals(datum3));
        assertFalse(datum1.hashCode() == datum3.hashCode());

        Datum datum4 = new Datum(1, 11, 2005);
        assertFalse(datum1.equals(datum4));
        assertFalse(datum1.hashCode() == datum4.hashCode());

        Datum datum5 = new Datum(2, 12, 2005);
        assertFalse(datum1.equals(datum5));
        assertFalse(datum1.hashCode() == datum5.hashCode());
    }

    @Test
    public void testIstGueltig()
    {
        assertFalse(Datum.istGueltig(32, 12, 2006));
        assertFalse(Datum.istGueltig(-1, 12, 2006));
        assertTrue(Datum.istGueltig(1, 12, 2006));
        assertTrue(Datum.istGueltig(31, 12, 2006));

        assertFalse(Datum.istGueltig(1, -1, 2006));
        assertFalse(Datum.istGueltig(1, 13, 2006));
        assertTrue(Datum.istGueltig(1, 1, 2006));
        assertTrue(Datum.istGueltig(1, 12, 2006));
    }

    @Test
    public void testHeute()
    {
        assertNotNull(Datum.heute());
    }
}
