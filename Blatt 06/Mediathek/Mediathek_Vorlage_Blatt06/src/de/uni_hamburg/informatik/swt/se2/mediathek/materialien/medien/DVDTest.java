package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DVDTest extends AbstractMediumTest
{
    private static final String BEZEICHNUNG = "DVD";
    private static final int LAENGE = 100;
    private static final String REGISSEUR = "DVD Regisseur";
    private DVD _dvd1;

    public DVDTest()
    {
        _dvd1 = getMedium();
    }

    @Test
    public void testDVD()
    {
        assertEquals(LAENGE, _dvd1.getLaufzeit());
        assertEquals(REGISSEUR, _dvd1.getRegisseur());
    }

    @Test
    public final void testDVDSetter()
    {
        _dvd1.setRegisseur("Regisseur2");
        assertEquals("Regisseur2", _dvd1.getRegisseur());
        _dvd1.setLaufzeit(99);
        assertEquals(99, _dvd1.getLaufzeit());
    }

    @Override
    @Test
    public void testGetMedienBezeichnung()
    {
        assertEquals(BEZEICHNUNG, _dvd1.getMedienBezeichnung());
    }

    @Override
    protected DVD getMedium()
    {
        return new DVD(TITEL, KOMMENTAR, REGISSEUR, LAENGE);
    }

}
