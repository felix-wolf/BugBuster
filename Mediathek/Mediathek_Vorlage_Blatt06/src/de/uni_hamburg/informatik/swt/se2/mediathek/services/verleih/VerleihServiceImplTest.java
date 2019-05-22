package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.DVD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandServiceImpl;

/**
 * @author SE2-Team
 */
public class VerleihServiceImplTest
{
    private Datum _datum;
    private Kunde _kunde;
    private VerleihService _service;
    private List<Medium> _medienListe;
    private Kunde _vormerkkunde1;
    private Kunde _vormerkkunde2;
    private Kunde _vormerkkunde3;

    private Medium neuesMedium;
    private Medium medium2;
    private MedienbestandService medienbestand;
    

    public VerleihServiceImplTest()
    {
        _datum = new Datum(3, 4, 2009);
        KundenstammService kundenstamm = new KundenstammServiceImpl(
                new ArrayList<Kunde>());
        _kunde = new Kunde(new Kundennummer(123456), "ich", "du");

        _vormerkkunde1 = new Kunde(new Kundennummer(666999), "paul", "panter");
        _vormerkkunde2 = new Kunde(new Kundennummer(123456), "Gernhart", "Reinholzen");
    	_vormerkkunde3 = new Kunde(new Kundennummer(444444), "Volker", "Racho");

    	kundenstamm.fuegeKundenEin(_vormerkkunde3);
        kundenstamm.fuegeKundenEin(_kunde);
        kundenstamm.fuegeKundenEin(_vormerkkunde1);
        medienbestand = new MedienbestandServiceImpl(new ArrayList<Medium>());
        Medium medium = new CD("CD1", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        medium = new CD("CD2", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        medium = new CD("CD3", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        medium = new CD("CD4", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        neuesMedium = new CD("Highway To Heaven", "kommentar", "ACDC", 50);
        medienbestand.fuegeMediumEin(neuesMedium);
        medium2 = new DVD("titanic", "kommentar", "Steve", 6);
        medienbestand.fuegeMediumEin(medium2);
        _medienListe = medienbestand.getMedien();
        _service = new VerleihServiceImpl(kundenstamm, medienbestand, new ArrayList<Verleihkarte>());
        

    }

    @Test
    public void testeNachInitialisierungIstNichtsVerliehen() throws Exception
    {
        assertTrue(_service.getVerleihkarten()
            .isEmpty());
        assertFalse(_service.istVerliehen(_medienListe.get(0)));
        assertFalse(_service.sindAlleVerliehen(_medienListe));
        assertTrue(_service.sindAlleNichtVerliehen(_medienListe));
    }

    @Test
    public void testeVerleihUndRueckgabeVonMedien() throws Exception
    {
        // Lege eine Liste mit nur verliehenen und eine Liste mit ausschließlich
        // nicht verliehenen Medien an
        List<Medium> verlieheneMedien = _medienListe.subList(0, 2);
        List<Medium> nichtVerlieheneMedien = _medienListe.subList(2, 4);
        _service.verleiheAn(_kunde, verlieheneMedien, _datum);

        // Prüfe, ob alle sondierenden Operationen für das Vertragsmodell
        // funktionieren
        assertTrue(_service.istVerliehen(verlieheneMedien.get(0)));
        assertTrue(_service.istVerliehen(verlieheneMedien.get(1)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(0)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(1)));
        assertTrue(_service.sindAlleVerliehen(verlieheneMedien));
        assertTrue(_service.sindAlleNichtVerliehen(nichtVerlieheneMedien));
        assertFalse(_service.sindAlleNichtVerliehen(verlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(nichtVerlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(_medienListe));
        assertFalse(_service.sindAlleNichtVerliehen(_medienListe));
        assertTrue(_service.istVerliehenAn(_kunde, verlieheneMedien.get(0)));
        assertTrue(_service.istVerliehenAn(_kunde, verlieheneMedien.get(1)));
        assertFalse(
                _service.istVerliehenAn(_kunde, nichtVerlieheneMedien.get(0)));
        assertFalse(
                _service.istVerliehenAn(_kunde, nichtVerlieheneMedien.get(1)));
        assertTrue(_service.sindAlleVerliehenAn(_kunde, verlieheneMedien));
        assertFalse(
                _service.sindAlleVerliehenAn(_kunde, nichtVerlieheneMedien));

        // Prüfe alle sonstigen sondierenden Methoden
        assertEquals(2, _service.getVerleihkarten()
            .size());

        _service.nimmZurueck(verlieheneMedien, _datum);
        // Prüfe, ob alle sondierenden Operationen für das Vertragsmodell
        // funktionieren
        assertFalse(_service.istVerliehen(verlieheneMedien.get(0)));
        assertFalse(_service.istVerliehen(verlieheneMedien.get(1)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(0)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(1)));
        assertFalse(_service.sindAlleVerliehen(verlieheneMedien));
        assertTrue(_service.sindAlleNichtVerliehen(nichtVerlieheneMedien));
        assertTrue(_service.sindAlleNichtVerliehen(verlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(nichtVerlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(_medienListe));
        assertTrue(_service.sindAlleNichtVerliehen(_medienListe));
        assertTrue(_service.getVerleihkarten()
            .isEmpty());
 }
    
    @Test
    public void testMerkeVor() throws Exception
    {
    
        _service.merkeVor(_vormerkkunde1, neuesMedium);
        _service.merkeVor(_vormerkkunde2, neuesMedium);
        _service.merkeVor(_vormerkkunde3, neuesMedium);
        
        assertTrue(_service.istVorgemerktVon(_vormerkkunde2, neuesMedium));
        assertTrue(_service.istVorgemerktVon(_vormerkkunde1, neuesMedium));
        assertFalse(_service.istVorgemerktVon(_vormerkkunde1, medium2));
        assertFalse(_service.istVerleihenMoeglich(_vormerkkunde3, _medienListe));
        assertTrue(_service.istVerleihenMoeglich(_vormerkkunde1, _medienListe));
        
    }
    @Test
    public void testEntferneVormerkung() 
    {
    	_service.merkeVor(_vormerkkunde1, neuesMedium);
        _service.merkeVor(_vormerkkunde2, neuesMedium);
        _service.merkeVor(_vormerkkunde3, neuesMedium);
         
        _service.entferneVormerkung(_vormerkkunde2, neuesMedium);
        assertFalse(_service.istVorgemerktVon(_vormerkkunde2, neuesMedium));
        assertTrue(_service.istVormerkenMoeglich(_vormerkkunde2, neuesMedium));
        _service.entferneVormerkung(_vormerkkunde3, neuesMedium);
        _service.entferneVormerkung(_vormerkkunde1, neuesMedium);
        assertEquals(null, _service.getVormerkkarte(neuesMedium));

    }
    @Test
    public void testExistiertVormerkkarte()
    {
        assertEquals(null, _service.getVormerkkarte(neuesMedium));
        assertFalse(_service.existiertVormerkkarte(neuesMedium));
        _service.merkeVor(_vormerkkunde1, neuesMedium);
        assertTrue(_service.existiertVormerkkarte(neuesMedium));
    }
    
    @Test
    public void testIstVormerkenMoeglich() throws Exception
    {
    	_service.merkeVor(_vormerkkunde1, neuesMedium);
        _service.merkeVor(_vormerkkunde2, neuesMedium);
        _service.merkeVor(_vormerkkunde3, neuesMedium);
        
        assertFalse(_service.istVormerkenMoeglich(_vormerkkunde3, neuesMedium));
        _service.verleiheAn(_vormerkkunde1, _medienListe, _datum);
        assertTrue(_service.istVormerkenMoeglich(_kunde, neuesMedium));
        assertEquals(_service.getVormerkkarte(neuesMedium).getVormerker(0), _vormerkkunde2);
        
        
    }

    @Test
    public void testVerleihEreignisBeobachter() throws ProtokollierException
    {
        final boolean ereignisse[] = new boolean[1];
        ereignisse[0] = false;
        ServiceObserver beobachter = new ServiceObserver()
        {
            @Override
            public void reagiereAufAenderung()
            {
                ereignisse[0] = true;
            }
        };
        _service.verleiheAn(_kunde, Collections.singletonList(_medienListe.get(0)), _datum);
        assertFalse(ereignisse[0]);

        _service.registriereBeobachter(beobachter);
        _service.verleiheAn(_kunde, Collections.singletonList(_medienListe.get(1)), _datum);
        assertTrue(ereignisse[0]);

        _service.entferneBeobachter(beobachter);
        ereignisse[0] = false;
        _service.verleiheAn(_kunde, Collections.singletonList(_medienListe.get(2)), _datum);
        assertFalse(ereignisse[0]);
    }

}
