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
        _karte = new Vormerkkarte(_medium);
        assertTrue(_karte.getMedium()
            .equals(_medium));
    }

    @Test
    public void testKonstruktor()
    {
        _karte = new Vormerkkarte(_medium);
        _karte.addVormerker(_kunde1);
        assertEquals(_kunde1, _karte.getVormerker(0));
        assertEquals(1, _karte.getAnzahlVormerker());
        assertTrue(_karte.istVorgemerktVon(_kunde1));
    }

    @Test
    public void testaddVormerker()
    {
        _karte = new Vormerkkarte(_medium);
        _karte.addVormerker(_kunde1);
        _karte.addVormerker(_kunde2);
        _karte.addVormerker(_kunde3);

        assertEquals(_kunde1, _karte.getVormerker(0));
        assertEquals(_kunde2, _karte.getVormerker(1));
        assertEquals(_kunde3, _karte.getVormerker(2));
        assertEquals(3, _karte.getAnzahlVormerker());
    }

    @Test
    public void testgetAnzahlVormerker()
    {
        _karte = new Vormerkkarte(_medium);
        _karte.addVormerker(_kunde1);
        assertEquals(1, _karte.getAnzahlVormerker());

        _karte.addVormerker(_kunde2);
        assertEquals(2, _karte.getAnzahlVormerker());

        _karte.addVormerker(_kunde3);
        assertEquals(3, _karte.getAnzahlVormerker());

        _karte.removeVormerker(_kunde1);
        assertEquals(2, _karte.getAnzahlVormerker());
    }

    @Test
    public void testremoveVormerker()
    {
        _karte = new Vormerkkarte(_medium);
        _karte.addVormerker(_kunde1);
        assertEquals(1, _karte.getAnzahlVormerker());

        _karte.addVormerker(_kunde2);
        assertEquals(2, _karte.getAnzahlVormerker());

        _karte.addVormerker(_kunde3);
        assertEquals(3, _karte.getAnzahlVormerker());

        _karte.removeVormerker(_kunde1);
        assertEquals(2, _karte.getAnzahlVormerker());

        _karte.removeVormerker(_kunde2);;
        assertEquals(1, _karte.getAnzahlVormerker());

        assertEquals(_kunde3, _karte.getVormerker(0));
    }

    @Test
    public void testgetVormerker()
    {
        _karte = new Vormerkkarte(_medium);
        _karte.addVormerker(_kunde1);
        _karte.addVormerker(_kunde2);
        _karte.addVormerker(_kunde3);

        assertEquals(_kunde1, _karte.getVormerker(0));
        assertEquals(_kunde2, _karte.getVormerker(1));
        assertEquals(_kunde3, _karte.getVormerker(2));
    }

    @Test
    public void testistVorgemerktVon()
    {
        _karte = new Vormerkkarte(_medium);

        _karte.addVormerker(_kunde1);
        _karte.addVormerker(_kunde2);
        _karte.addVormerker(_kunde3);
        _karte.addVormerker(_kunde4);
        assertTrue(_karte.istVorgemerktVon(_kunde1));
        assertTrue(_karte.istVorgemerktVon(_kunde2));
        assertTrue(_karte.istVorgemerktVon(_kunde3));
        assertFalse(_karte.istVorgemerktVon(_kunde4));

        _karte.removeVormerker(_kunde1);
        assertEquals(_kunde2, _karte.getVormerker(0));
        assertFalse(_karte.istVorgemerktVon(_kunde1));
    }

}
