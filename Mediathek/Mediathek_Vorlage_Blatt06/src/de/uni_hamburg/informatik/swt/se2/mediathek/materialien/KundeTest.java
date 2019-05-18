package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.PLZ;

public class KundeTest
{
    private Kunde kunde;

    @Test
    public void getKundennummerTest()
    {
        Kundennummer kundennummer1 = new Kundennummer(123456);
        Kundennummer kundennummer2 = new Kundennummer(654321);

        assertEquals(kundennummer1, kunde.getKundennummer());
        assertNotSame(kundennummer2, kunde.getKundennummer());
    }

    @Test
    public void getNachnameTest()
    {
        assertEquals("Schmidt", kunde.getNachname());
        assertNotSame("Müller", kunde.getNachname());
    }

    @Test
    public void getPLZTest()
    {
        PLZ plz1 = new PLZ("22761");
        PLZ plz2 = new PLZ("12345");

        assertEquals(plz1, kunde.getPLZ());
        assertNotSame(plz2, kunde.getPLZ());
    }

    @Test
    public void getStrasseTest()
    {
        String strasse1 = "Sonnenallee 20";
        String strasse2 = "Adlerweg 2";

        assertEquals(strasse1, kunde.getStrasse());
        assertNotSame(strasse2, kunde.getStrasse());
    }

    @Test
    public void getWohnortTest()
    {
        String ort1 = "Hamburg";
        String ort2 = "Berlin";

        assertEquals(ort1, kunde.getWohnort());
        assertNotSame(ort2, kunde.getWohnort());
    }

    @Test
    public void gettTelefonnummerTest()
    {
        String telNr1 = "123/456789";
        String telNr2 = "321/987654";

        assertEquals(telNr1, kunde.getTelefonnummer());
        assertNotSame(telNr2, kunde.getTelefonnummer());
    }

    public KundeTest()
    {
        kunde = new Kunde(new Kundennummer(123456), "Klaus", "Schmidt");
        kunde.setPLZ(new PLZ("22761"));
        kunde.setStrasse("Sonnenallee 20");
        kunde.setWohnort("Hamburg");
        kunde.setTelefonnummer("123/456789");
    }

    @Test
    public void testeKonstruktor()
    {
        assertEquals(new Kundennummer(123456), kunde.getKundennummer());
        assertEquals("Klaus", kunde.getVorname());
        assertEquals("Schmidt", kunde.getNachname());
    }
}
