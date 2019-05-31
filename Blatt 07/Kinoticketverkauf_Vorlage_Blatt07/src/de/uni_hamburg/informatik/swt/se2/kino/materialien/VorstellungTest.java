package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;

public class VorstellungTest
{
    private Kinosaal _kinoA;
    private Film _hdR1;
    private Uhrzeit _16_45 = new Uhrzeit(16, 45);
    private Uhrzeit _20_15 = new Uhrzeit(20, 15);
    private Datum _11_07_2008 = new Datum(11, 07, 2008);

    @Before
    public void setUp()
    {
        _kinoA = new Kinosaal("A", 20, 50);
        _hdR1 = new Film("Der Herr der Ringe - Die Gefhrten", 178, FSK.FSK12,
                true);
    }

    @Test
    public void testeKonstruktor()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        assertSame(_kinoA, v.getKinosaal());
        assertSame(_hdR1, v.getFilm());
        assertEquals(_16_45, v.getAnfangszeit());
        assertEquals(_20_15, v.getEndzeit());
        assertEquals(_11_07_2008, v.getDatum());
        assertEquals(1230, v.getPreis());
        assertNotNull(v.toString());
    }

    @Test
    public void testHatPlatzHatPlaetze()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        assertTrue(v.hatPlatz(new Platz(0, 0)));
        assertTrue(v.hatPlatz(new Platz(19, 49)));
        assertFalse(v.hatPlatz(new Platz(20, 50)));

        Set<Platz> s = new HashSet<Platz>();
        // Bei leerer Menge sollte hatPlaetze true zurückgeben
        assertTrue(v.hatPlaetze(s));

        s.add(new Platz(0, 0));
        s.add(new Platz(19, 49));
        assertTrue(v.hatPlaetze(s));

        // Bei mindestens einem ungültigen Platz muss false kommen
        s.add(new Platz(20, 50));
        assertFalse(v.hatPlaetze(s));
    }

    @Test
    public void testeGibPreisFuerPlaetze()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);
        Set<Platz> s = new HashSet<Platz>();

        assertEquals(0, v.getPreisFuerPlaetze(s));

        s.add(new Platz(5, 5));
        s.add(new Platz(5, 6));
        s.add(new Platz(5, 7));

        assertEquals(3690, v.getPreisFuerPlaetze(s));
    }

    @Test
    public void testeVerkaufen()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);
        Platz platz = new Platz(5, 5);
        assertFalse(v.istPlatzVerkauft(platz));

        v.verkaufePlatz(platz);
        assertTrue(v.istPlatzVerkauft(platz));

        v.stornierePlatz(platz);
        assertFalse(v.istPlatzVerkauft(platz));
    }

    @Test
    public void testeVerkaufenMehrere()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        Platz platz1 = new Platz(1, 1);
        Platz platz2 = new Platz(1, 2);

        Set<Platz> plaetze = new HashSet<Platz>();
        plaetze.add(platz1);
        plaetze.add(platz2);

        assertFalse(v.sindStornierbar(plaetze));

        v.verkaufePlaetze(plaetze);
        assertTrue(v.sindStornierbar(plaetze));

        v.stornierePlaetze(plaetze);
        assertFalse(v.sindStornierbar(plaetze));
    }

    @Test
    public void testeSindVerkauft()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        Platz platz1 = new Platz(1, 1);
        Platz platz2 = new Platz(1, 2);
        Platz platz3 = new Platz(3, 3);

        Set<Platz> allePlaetze = new HashSet<Platz>();
        allePlaetze.add(platz1);
        allePlaetze.add(platz2);
        allePlaetze.add(platz3);

        Set<Platz> zweiPlaetze = new HashSet<Platz>();
        zweiPlaetze.add(platz1);
        zweiPlaetze.add(platz2);

        assertFalse(v.sindStornierbar(allePlaetze));
        assertFalse(v.sindStornierbar(zweiPlaetze));

        v.verkaufePlaetze(zweiPlaetze);

        assertTrue(v.sindStornierbar(zweiPlaetze));
        assertFalse(v.sindStornierbar(allePlaetze));
    }

    @Test
    public void testeSindNichtVerkauft()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        Platz platz1 = new Platz(1, 1);
        Platz platz2 = new Platz(1, 2);
        Platz platz3 = new Platz(3, 3);
        Platz platz4 = new Platz(3, 4);

        Set<Platz> allePlaetze = new HashSet<Platz>();
        allePlaetze.add(platz1);
        allePlaetze.add(platz2);
        allePlaetze.add(platz3);
        allePlaetze.add(platz4);

        Set<Platz> plaetze1und2 = new HashSet<Platz>();
        plaetze1und2.add(platz1);
        plaetze1und2.add(platz2);

        Set<Platz> plaetze3und4 = new HashSet<Platz>();
        plaetze3und4.add(platz3);
        plaetze3und4.add(platz4);

        assertTrue(v.sindVerkaufbar(allePlaetze));
        assertTrue(v.sindVerkaufbar(plaetze1und2));
        assertTrue(v.sindVerkaufbar(plaetze3und4));

        v.verkaufePlaetze(plaetze1und2);

        assertFalse(v.sindVerkaufbar(plaetze1und2));
        assertTrue(v.sindVerkaufbar(plaetze3und4));
        assertFalse(v.sindVerkaufbar(allePlaetze));
    }

    @Test
    public void testeGibAnzahlVerkauftePlaetze()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        assertEquals(0, v.getAnzahlVerkauftePlaetze());
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <= 6; j++)
            {
                Platz platz = new Platz(i, j);
                v.verkaufePlatz(platz);
            }
        }
        assertEquals(30, v.getAnzahlVerkauftePlaetze());
    }
}
