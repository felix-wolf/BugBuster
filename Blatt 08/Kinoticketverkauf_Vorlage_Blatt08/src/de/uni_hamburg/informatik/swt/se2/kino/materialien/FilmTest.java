package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;

public class FilmTest
{
    @Test
    public void testeKonstruktoren()
    {
        Film f = new Film("Titel", 90, FSK.FSK16, true);

        assertEquals("Titel", f.getTitel());
        assertEquals(90, f.getLaenge());
        assertEquals(FSK.FSK16, f.getFSK());
        assertTrue(f.hatUeberlaenge());
        assertNotNull(f.toString());
    }
}
