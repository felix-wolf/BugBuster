package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 * 
 */
public class KonsolenVideospielTest extends AbstractVideospielTest
{

    private static final String BEZEICHNUNG = "KonsolenVideospiel";

    @Test
    @Override
    public void testBerechneMietgebuehr()
    {
        assertEquals(new Geldbetrag(200), _videoSpiel.berechneMietgebuehr(1));
        assertEquals(new Geldbetrag(900), _videoSpiel.berechneMietgebuehr(3));
        assertEquals(new Geldbetrag(900), _videoSpiel.berechneMietgebuehr(5));
        assertEquals(new Geldbetrag(1600), _videoSpiel.berechneMietgebuehr(7));
    }

    @Override
    @Test
    public void testGetMedienBezeichnung()
    {
        assertEquals(BEZEICHNUNG, _videoSpiel.getMedienBezeichnung());
    }

    @Override
    protected KonsolenVideospiel getMedium()
    {
        return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
    }

}
