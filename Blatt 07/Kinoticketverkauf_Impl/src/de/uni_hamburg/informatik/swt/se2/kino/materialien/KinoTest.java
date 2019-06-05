package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;

public class KinoTest
{
    private Kino _kino;

    private Kinosaal _saal0;
    private Kinosaal _saal1;
    private Kinosaal _saal2;
    private Kinosaal[] _alleSaele;
    private Film _film0;
    private Film _film1;
    private Film _film2;
    private String _filmTitel0;
    private String _filmTitel1;
    private String _filmTitel2;

    private Uhrzeit _u1;
    private Uhrzeit _u2;
    private Uhrzeit _u3;
    private Uhrzeit _u4;

    private Datum _d1;

    private Vorstellung _vorstellungSaal1Film0;
    private Vorstellung _vorstellungSaal2Film1;
    private Vorstellung _vorstellungSaal2Film2a;
    private Vorstellung _vorstellungSaal2Film2b;

    private Vorstellung[] _alleVorstellungen;
    private Kinosaal _saal3;

    @Before
    public void setUp()
    {
        _saal0 = new Kinosaal("Standard", 25, 40);
        _saal1 = new Kinosaal("Gehoben", 20, 32);
        _saal2 = new Kinosaal("Luxus", 10, 16);
        _saal3 = new Kinosaal("Nix", 10, 20);

        _alleSaele = new Kinosaal[] { _saal0, _saal1, _saal2 };

        _filmTitel0 = "Die wilden Kerle 3";
        _filmTitel1 = "Underworld Evolution";
        _filmTitel2 = "The New World";

        _film0 = new Film(_filmTitel0, 90, FSK.FSK0, false);
        _film1 = new Film(_filmTitel1, 108, FSK.FSK16, false);
        _film2 = new Film(_filmTitel2, 135, FSK.FSK12, true);

        _u1 = new Uhrzeit(17, 30);
        _u2 = new Uhrzeit(20, 0);
        _u3 = new Uhrzeit(22, 30);
        _u4 = new Uhrzeit(1, 30);

        _d1 = new Datum(11, 07, 2008);

        _vorstellungSaal1Film0 = new Vorstellung(_saal1, _film0, _u1, _u2, _d1,
                900);
        _vorstellungSaal2Film1 = new Vorstellung(_saal2, _film1, _u1, _u2, _d1,
                1000);
        _vorstellungSaal2Film2a = new Vorstellung(_saal2, _film2, _u2, _u3,
                _d1, 900);
        _vorstellungSaal2Film2b = new Vorstellung(_saal2, _film2, _u3, _u4,
                _d1, 900);

        _alleVorstellungen = new Vorstellung[] { _vorstellungSaal1Film0,
                _vorstellungSaal2Film1, _vorstellungSaal2Film2a,
                _vorstellungSaal2Film2b };
        _kino = new Kino(_alleSaele, _alleVorstellungen);
    }

    @Test
    public void testGibTagesplan()
    {
        Tagesplan tagesplan = _kino.getTagesplan(_d1);

        assertEquals(_d1, tagesplan.getDatum());
        assertEquals(4, tagesplan.getVorstellungen().size());
        assertTrue(tagesplan.getVorstellungen().containsAll(
                Arrays.asList(_vorstellungSaal2Film1, _vorstellungSaal2Film2a,
                        _vorstellungSaal2Film2b, _vorstellungSaal1Film0)));
    }

    @Test
    public void testeHatKinosaal()
    {
        assertTrue(_kino.hatKinosaal(_saal0));
        assertTrue(_kino.hatKinosaal(_saal1));
        assertFalse(_kino.hatKinosaal(_saal3));
    }

    @Test
    public void testGibKinosaele()
    {
        List<Kinosaal> l = _kino.getKinosaele();
        assertEquals(3, l.size());
        assertTrue(l.contains(_saal0));
        assertTrue(l.contains(_saal1));
        assertTrue(l.contains(_saal2));
    }

    @Test
    public void testeGibAnzahlReihen()
    {
        assertEquals(25, _saal0.getAnzahlReihen());
        assertEquals(20, _saal1.getAnzahlReihen());
        assertEquals(10, _saal2.getAnzahlReihen());
    }

    @Test
    public void testeGibAnzahlSitzeProReihe()
    {
        assertEquals(40, _saal0.getAnzahlSitzeProReihe());
        assertEquals(32, _saal1.getAnzahlSitzeProReihe());
        assertEquals(16, _saal2.getAnzahlSitzeProReihe());
    }

    @Test
    public void testeHatPlatz()
    {
        assertTrue(_saal0.hatPlatz(new Platz(0, 0)));
        assertTrue(_saal0.hatPlatz(new Platz(24, 39)));
        assertFalse(_saal0.hatPlatz(new Platz(0, 40)));
        assertFalse(_saal0.hatPlatz(new Platz(25, 0)));
        assertFalse(_saal0.hatPlatz(new Platz(25, 40)));
    }
}
