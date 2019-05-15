package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 */
public class PCVideospielTest extends AbstractVideospielTest
{
    private static final String BEZEICHNUNG = "PCVideospiel";

    @Test
    @Override
    public void testBerechneMietgebuehr()
    {
        assertEquals(new Geldbetrag(200), _videoSpiel.berechneMietgebuehr(1));
        assertEquals(new Geldbetrag(200), _videoSpiel.berechneMietgebuehr(7));
        assertEquals(new Geldbetrag(700), _videoSpiel.berechneMietgebuehr(8));
        assertEquals(new Geldbetrag(700), _videoSpiel.berechneMietgebuehr(12));
        assertEquals(new Geldbetrag(1200), _videoSpiel.berechneMietgebuehr(13));
    }

    @Override
    @Test
    public void testGetMedienBezeichnung()
    {
        assertEquals(BEZEICHNUNG, _videoSpiel.getMedienBezeichnung());
    }

    @Override
    protected PCVideospiel getMedium()
    {
        return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
    }

}
