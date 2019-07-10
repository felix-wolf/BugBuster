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
    private Uhrzeit _16_45 = Uhrzeit.get(16, 45);
    private Uhrzeit _20_15 = Uhrzeit.get(20, 15);
    private Datum _11_07_2008 = Datum.get(11, 07, 2008);

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

        assertTrue(v.hatPlatz(Platz.get(0, 0)));
        assertTrue(v.hatPlatz(Platz.get(19, 49)));
        assertFalse(v.hatPlatz(Platz.get(20, 50)));

        Set<Platz> s = new HashSet<Platz>();
        // Bei leerer Menge sollte hatPlaetze true zurückgeben
        assertTrue(v.hatPlaetze(s));

        s.add(Platz.get(0, 0));
        s.add(Platz.get(19, 49));
        assertTrue(v.hatPlaetze(s));

        // Bei mindestens einem ungültigen Platz muss false kommen
        s.add(Platz.get(20, 50));
        assertFalse(v.hatPlaetze(s));
    }

    @Test
    public void testeGibPreisFuerPlaetze()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);
        Set<Platz> s = new HashSet<Platz>();

        assertEquals(0, v.getPreisFuerPlaetze(s));

        s.add(Platz.get(5, 5));
        s.add(Platz.get(5, 6));
        s.add(Platz.get(5, 7));

        assertEquals(3690, v.getPreisFuerPlaetze(s));
    }

    @Test
    public void testeVerkaufen()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);
        Platz platz = Platz.get(5, 5);
        assertTrue(v.istVerkaufbar(platz));
        assertFalse(v.istStornierbar(platz));

        v.verkaufePlatz(platz);
        assertFalse(v.istVerkaufbar(platz));
        assertTrue(v.istStornierbar(platz));

        v.stornierePlatz(platz);
        assertTrue(v.istVerkaufbar(platz));
        assertFalse(v.istStornierbar(platz));
    }

    @Test
    public void testeVerkaufenMehrere()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        Platz platz1 = Platz.get(1, 1);
        Platz platz2 = Platz.get(1, 2);

        Set<Platz> plaetze = new HashSet<Platz>();
        plaetze.add(platz1);
        plaetze.add(platz2);

        assertTrue(v.sindVerkaufbar(plaetze));
        assertFalse(v.sindStornierbar(plaetze));

        v.verkaufePlaetze(plaetze);
        assertFalse(v.sindVerkaufbar(plaetze));
        assertTrue(v.sindStornierbar(plaetze));

        v.stornierePlaetze(plaetze);
        assertTrue(v.sindVerkaufbar(plaetze));
        assertFalse(v.sindStornierbar(plaetze));
    }

    @Test
    public void testeVerkaufbarStornierbar()
    {
        Vorstellung v = new Vorstellung(_kinoA, _hdR1, _16_45, _20_15,
                _11_07_2008, 1230);

        Platz platz1 = Platz.get(1, 1);
        Platz platz2 = Platz.get(1, 2);
        Platz platz3 = Platz.get(3, 3);

        Set<Platz> allePlaetze = new HashSet<Platz>();
        allePlaetze.add(platz1);
        allePlaetze.add(platz2);
        allePlaetze.add(platz3);

        Set<Platz> zweiPlaetze = new HashSet<Platz>();
        zweiPlaetze.add(platz1);
        zweiPlaetze.add(platz2);

        assertTrue(v.istVerkaufbar(platz1));
        assertTrue(v.istVerkaufbar(platz2));
        assertTrue(v.istVerkaufbar(platz3));
        assertFalse(v.istStornierbar(platz1));
        assertFalse(v.istStornierbar(platz2));
        assertFalse(v.istStornierbar(platz3));
        assertTrue(v.sindVerkaufbar(allePlaetze));
        assertTrue(v.sindVerkaufbar(zweiPlaetze));
        assertFalse(v.sindStornierbar(allePlaetze));
        assertFalse(v.sindStornierbar(zweiPlaetze));

        v.verkaufePlaetze(zweiPlaetze);

        assertFalse(v.istVerkaufbar(platz1));
        assertFalse(v.istVerkaufbar(platz2));
        assertTrue(v.istVerkaufbar(platz3));
        assertTrue(v.istStornierbar(platz1));
        assertTrue(v.istStornierbar(platz2));
        assertFalse(v.istStornierbar(platz3));
        assertFalse(v.sindVerkaufbar(allePlaetze));
        assertFalse(v.sindVerkaufbar(zweiPlaetze));
        assertFalse(v.sindStornierbar(allePlaetze));
        assertTrue(v.sindStornierbar(zweiPlaetze));

        v.stornierePlaetze(zweiPlaetze);

        assertTrue(v.istVerkaufbar(platz1));
        assertTrue(v.istVerkaufbar(platz2));
        assertTrue(v.istVerkaufbar(platz3));
        assertFalse(v.istStornierbar(platz1));
        assertFalse(v.istStornierbar(platz2));
        assertFalse(v.istStornierbar(platz3));
        assertTrue(v.sindVerkaufbar(allePlaetze));
        assertTrue(v.sindVerkaufbar(zweiPlaetze));
        assertFalse(v.sindStornierbar(allePlaetze));
        assertFalse(v.sindStornierbar(zweiPlaetze));
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
                Platz platz = Platz.get(i, j);
                v.verkaufePlatz(platz);
            }
        }
        assertEquals(30, v.getAnzahlVerkauftePlaetze());
    }
}
