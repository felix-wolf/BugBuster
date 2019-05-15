package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 * Testklasse für AbstractMediumTest. Testklassen für konkrete Medienklassen
 * (Beispielsweise DVDTest) sollen von dieser Testklasse erben. So werden für
 * jede konkrete Medienklasse die hier definieren Tests ausgeführt.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public abstract class AbstractMediumTest
{

    protected static final String KOMMENTAR = "Kommentar";

    protected static final int MIETGEBUEHR_PRO_TAG = 300;

    protected static final String TITEL = "Titel";

    @Test
    public final void testAbstractMedium()
    {
        Medium medium = getMedium();
        assertEquals(TITEL, medium.getTitel());
        assertEquals(KOMMENTAR, medium.getKommentar());
    }

    @Test
    public void testBerechneMietgebuehr()
    {
        Medium medium = getMedium();
        assertEquals(new Geldbetrag(MIETGEBUEHR_PRO_TAG),
                medium.berechneMietgebuehr(1));
        assertEquals(new Geldbetrag(2 * MIETGEBUEHR_PRO_TAG),
                medium.berechneMietgebuehr(2));
        assertEquals(new Geldbetrag(7 * MIETGEBUEHR_PRO_TAG),
                medium.berechneMietgebuehr(7));
    }

    @Test
    /*
     * Von ein und demselben Medium kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden können.
     */
    public void testEquals()
    {
        Medium medium = getMedium();
        Medium medium2 = getMedium();
        assertFalse("Mehrere Exemplare des gleichen Mediums sollten ungleich",
                medium.equals(medium2));
        assertTrue("Dieselben Exemplare des gleichen Mediums sollten gleich",
                medium.equals(medium));
    }

    @Test
    public final void testGetFormatiertenString()
    {
        Medium medium = getMedium();
        assertNotNull(medium.getFormatiertenString());
    }

    @Test
    public final void testMediumSetter()
    {
        Medium medium = getMedium();
        medium.setTitel("Titel2");
        assertEquals(medium.getTitel(), "Titel2");
        medium.setKommentar("Kommentar2");
        assertEquals(medium.getKommentar(), "Kommentar2");
    }

    @Test
    public abstract void testGetMedienBezeichnung();

    /**
     * Gibt ein neues konkretes Medium-Objekt zurück. Beispielsweise eine DVD in
     * DVDTest
     */
    protected abstract Medium getMedium();
}
