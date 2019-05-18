package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CDTest extends AbstractMediumTest
{

    private static final String CD_BEZEICHNUNG = "CD";
    private static final String INTERPRET = "CD Interpret";
    private static final int LAENGE = 100;
    private CD _cd1;

    public CDTest()
    {
        _cd1 = getMedium();
    }

    @Override
    @Test
    public void testGetMedienBezeichnung()
    {
        assertEquals(CD_BEZEICHNUNG, _cd1.getMedienBezeichnung());
    }

    @Test
    public void testCD()
    {
        assertEquals(LAENGE, _cd1.getSpiellaenge());
        assertEquals(INTERPRET, _cd1.getInterpret());
    }

    @Test
    public final void testCDSetter()
    {
        _cd1.setInterpret("Interpret2");
        assertEquals("Interpret2", _cd1.getInterpret());
        _cd1.setSpiellaenge(99);
        assertEquals(99, _cd1.getSpiellaenge());
    }

    @Override
    protected CD getMedium()
    {
        return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
    }

}
