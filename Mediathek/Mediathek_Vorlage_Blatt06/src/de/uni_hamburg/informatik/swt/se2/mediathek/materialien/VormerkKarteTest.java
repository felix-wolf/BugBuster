package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Diese Testklasse testet die VormerkKarte
 * @author Gruppe BugBuster
 *
 */
public class VormerkkarteTest
{

    private Vormerkkarte _karte;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;
    private Kunde _kunde4;
    private Medium _medium;

    public VormerkkarteTest()
    {
        _kunde1 = new Kunde(new Kundennummer(123456), "Gernhart", "Reinholzen");
        _kunde2 = new Kunde(new Kundennummer(444444), "Volker", "Racho");
        _kunde3 = new Kunde(new Kundennummer(555555), "Claire", "Grube");
        _kunde4 = new Kunde(new Kundennummer(555666), "Christian", "Steifen");
        _medium = new CD("bar", "baz", "foo", 123);

    }

    @Test
    public void testgetMedium()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);
        assertTrue(_karte.getMedium()
            .equals(_medium));
    }

    @Test
    public void testKonstruktor()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);
        _karte.AddVormerker(_kunde1);
        assertEquals(_kunde1, _karte.getErsteVormerker());
        assertEquals(1, _karte.gibAnzahlVormerker());
        assertTrue(_karte.istDerKundeVormerker(_kunde1));
    }

    @Test
    public void testaddVormerker()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);
        _karte.AddVormerker(_kunde1);
        _karte.AddVormerker(_kunde2);
        _karte.AddVormerker(_kunde3);

        assertEquals(_kunde1, _karte.getErsteVormerker());
        assertEquals(_kunde2, _karte.getZweiteVormerker());
        assertEquals(_kunde3, _karte.getDritteVormerker());
        assertEquals(3, _karte.gibAnzahlVormerker());
    }

    @Test
    public void testgetAnzahlVormerker()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);
        _karte.AddVormerker(_kunde1);
        assertEquals(1, _karte.gibAnzahlVormerker());

        _karte.AddVormerker(_kunde2);
        assertEquals(2, _karte.gibAnzahlVormerker());

        _karte.AddVormerker(_kunde3);
        assertEquals(3, _karte.gibAnzahlVormerker());

        _karte.RemoveErsteVormerk();
        assertEquals(2, _karte.gibAnzahlVormerker());
    }

    @Test
    public void testremoveVormerker()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);
        _karte.AddVormerker(_kunde1);
        assertEquals(1, _karte.gibAnzahlVormerker());

        _karte.AddVormerker(_kunde2);
        assertEquals(2, _karte.gibAnzahlVormerker());

        _karte.AddVormerker(_kunde3);
        assertEquals(3, _karte.gibAnzahlVormerker());

        _karte.RemoveErsteVormerk();
        assertEquals(2, _karte.gibAnzahlVormerker());

        _karte.RemoveErsteVormerk();
        assertEquals(1, _karte.gibAnzahlVormerker());

        assertEquals(_kunde3, _karte.getErsteVormerker());
    }

    @Test
    public void testgetVormerker()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);
        _karte.AddVormerker(_kunde1);
        _karte.AddVormerker(_kunde2);
        _karte.AddVormerker(_kunde3);

        assertEquals(_kunde1, _karte.getErsteVormerker());
        assertEquals(_kunde2, _karte.getZweiteVormerker());
        assertEquals(_kunde3, _karte.getDritteVormerker());
    }

    @Test
    public void testistVorgemerktVon()
    {
        _karte = new Vormerkkarte(_kunde1, _medium);

        _karte.AddVormerker(_kunde1);
        _karte.AddVormerker(_kunde2);
        _karte.AddVormerker(_kunde3);
        _karte.AddVormerker(_kunde4);
        assertTrue(_karte.istDerKundeVormerker(_kunde1));
        assertTrue(_karte.istDerKundeVormerker(_kunde2));
        assertTrue(_karte.istDerKundeVormerker(_kunde3));
        assertFalse(_karte.istDerKundeVormerker(_kunde4));

        _karte.RemoveErsteVormerk();
        assertEquals(_kunde2, _karte.getErsteVormerker());
        assertFalse(_karte.istDerKundeVormerker(_kunde1));
    }

}
