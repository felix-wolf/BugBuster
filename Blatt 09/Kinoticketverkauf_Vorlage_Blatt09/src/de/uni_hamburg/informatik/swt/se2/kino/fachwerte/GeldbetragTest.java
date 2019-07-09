package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest {

    @Test
    public void testeAddiere() throws Exception
    {
        Geldbetrag geld1 = Geldbetrag.get(530);
        Geldbetrag geld2 = Geldbetrag.get(260);
        geld1 = geld1.addiere(geld2);
        assertEquals("7,90", geld1.toString());
        geld2 = geld2.addiere(geld2);
        assertEquals("5,20", geld2.toString());
    }

    @Test
    public void testeSubtrahiere() throws Exception
    {
        Geldbetrag geld1 = Geldbetrag.get(530);
        Geldbetrag geld2 = Geldbetrag.get(260);
        geld1 = geld1.subtrahiere(geld2);
        assertEquals("2,70", geld1.toString());
        geld2 = geld2.subtrahiere(geld1);
        assertEquals("-0,10", geld2.toString());
    }

    @Test
    public void testeMultipliziere() throws Exception
    {
        Geldbetrag geld1 = Geldbetrag.get(530);
        geld1 = geld1.multipliziere(5);
        assertEquals("26,50", geld1.toString());
    }

    @Test
    public void testeGet() throws Exception
    {
        String string = "3,65";
        Geldbetrag geld3 = Geldbetrag.get(string);
        assertEquals("3,65", geld3.toString());
        Geldbetrag geld4 = Geldbetrag.get(119);
        assertEquals("1,19", geld4.toString());
    }

    @Test
    public void testeToString() throws Exception
    {
        Geldbetrag geld1 = Geldbetrag.get(119);
        assertEquals("1,19", geld1.toString());
        Geldbetrag geld2 = Geldbetrag.get(3009);
        assertEquals("30,09", geld2.toString());
    }

    @Test
    public void testeEqualsUndHashCode() throws Exception
    {
        Geldbetrag geld1 = Geldbetrag.get(190);
        Geldbetrag geld2 = Geldbetrag.get(190);
        Geldbetrag geld3 = Geldbetrag.get(490);
        Geldbetrag geld4 = Geldbetrag.get(160);
        assertTrue(geld1.equals(geld2));
        assertTrue(geld1.hashCode() == geld2.hashCode());
        assertFalse(geld1.equals(geld3));
        assertFalse(geld1.equals(geld4));
        assertTrue(geld1.hashCode() != geld4.hashCode());
    }

    @Test
    public void testeIstNegativ() throws Exception
    {
        Geldbetrag geld1 = Geldbetrag.get(530);
        Geldbetrag geld2 = Geldbetrag.get(-260);

        assertFalse(geld1.istNegativ());
        assertTrue(geld2.istNegativ());

    }



}
