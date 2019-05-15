package de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

public class KundenstammServiceImplTest
{

    private KundenstammService _kundenstamm;
    private Kunde _klaus;
    private Kunde _susi;

    public KundenstammServiceImplTest()
    {
        _kundenstamm = new KundenstammServiceImpl(new ArrayList<Kunde>());
        _klaus = new Kunde(new Kundennummer(123456), "Klaus", "Schmidt");
        _susi = new Kunde(new Kundennummer(654321), "Susi", "Sonnenschein");
    }

    @Test
    public void testEntferneKunde()
    {
        assertTrue(_kundenstamm.getKunden()
            .isEmpty());
        _kundenstamm.fuegeKundenEin(_klaus);
        _kundenstamm.fuegeKundenEin(_susi);

        _kundenstamm.entferneKunden(_klaus);
        assertFalse(_kundenstamm.enthaeltKunden(_klaus));

        _kundenstamm.entferneKunden(_susi);
        assertFalse(_kundenstamm.enthaeltKunden(_susi));

        try
        {
            // Versuch einen nicht mehr vorhandenen Kunden zu entfernen
            _kundenstamm.entferneKunden(_susi);
            fail("Kunde nicht vorhanden");
        }
        catch (AssertionError e)
        {
            // tue nichts, Fehler erwartet.
        }
    }

    @Test
    public void testEnthaeltKunden()
    {
        assertTrue(_kundenstamm.getKunden()
            .isEmpty());
        _kundenstamm.fuegeKundenEin(_klaus);
        assertTrue(_kundenstamm.enthaeltKunden(_klaus));

        _kundenstamm.fuegeKundenEin(_susi);
        assertTrue(_kundenstamm.enthaeltKunden(_susi));

        _kundenstamm.entferneKunden(_klaus);
        assertFalse(_kundenstamm.enthaeltKunden(_klaus));

        _kundenstamm.entferneKunden(_susi);
        assertFalse(_kundenstamm.enthaeltKunden(_susi));
    }

    @Test
    public void testFuegeKundeEin()
    {

        assertTrue(_kundenstamm.getKunden()
            .isEmpty());

        _kundenstamm.fuegeKundenEin(_klaus);
        assertTrue(_kundenstamm.enthaeltKunden(_klaus));

        _kundenstamm.fuegeKundenEin(_susi);
        assertTrue(_kundenstamm.enthaeltKunden(_susi));
    }

    @Test
    public void testgetKunden()
    {
        assertTrue(_kundenstamm.getKunden()
            .isEmpty());
        _kundenstamm.fuegeKundenEin(_klaus);
        assertEquals(1, _kundenstamm.getKunden()
            .size());
        assertTrue(_kundenstamm.enthaeltKunden(_klaus));

        _kundenstamm.fuegeKundenEin(_susi);
        assertEquals(2, _kundenstamm.getKunden()
            .size());
        assertTrue(_kundenstamm.enthaeltKunden(_susi));
    }

    @Test
    public void testgetKundenFuerKundennummer()
    {
        assertTrue(_kundenstamm.getKunden()
            .isEmpty());
        _kundenstamm.fuegeKundenEin(_klaus);
        _kundenstamm.fuegeKundenEin(_susi);

        Kundennummer kundennummerKlaus = _klaus.getKundennummer();
        Kundennummer kundennummerSusi = _susi.getKundennummer();

        Kunde kunde = _kundenstamm.getKunden(kundennummerKlaus);
        assertEquals(_klaus, kunde);

        kunde = _kundenstamm.getKunden(kundennummerSusi);
        assertEquals(_susi, kunde);

        kunde = _kundenstamm.getKunden(new Kundennummer(987654));
        assertNull(kunde);
    }

}
