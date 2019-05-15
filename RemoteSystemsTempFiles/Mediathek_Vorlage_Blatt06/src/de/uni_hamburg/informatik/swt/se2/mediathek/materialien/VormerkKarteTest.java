package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihServiceImpl;

public class VormerkKarteTest {

	private VormerkKarte _karte;
	private Kunde _kunde1;
	private Kunde _kunde2;
	private Kunde _kunde3;
	private Kunde _kunde4;
    private Medium _medium;

    
    public VormerkKarteTest()
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
    	_karte = new VormerkKarte(_kunde1, _medium);
    	assertTrue(_karte.getMedium().equals(_medium));
    }
}
