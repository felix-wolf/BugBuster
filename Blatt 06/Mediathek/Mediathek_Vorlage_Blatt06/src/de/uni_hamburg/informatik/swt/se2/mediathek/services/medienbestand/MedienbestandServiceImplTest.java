package de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class MedienbestandServiceImplTest
{

    private Medium _cd1;

    private Medium _cd2;

    private MedienbestandService _medienbestand;

    public MedienbestandServiceImplTest()
    {
        _medienbestand = new MedienbestandServiceImpl(new ArrayList<Medium>());
        _cd1 = new CD("Mein CD1-Titel", "Mein CD1-Kommentar",
                "Mein Interpret 1", 100);
        _cd2 = new CD("Mein CD2-Titel", "Mein CD2-Kommentar",
                "Mein Interpret 2", 71);
    }

    @Test
    public void entferneMediumTest()
    {
        assertTrue(_medienbestand.getMedien()
            .isEmpty());
        _medienbestand.fuegeMediumEin(_cd1);
        _medienbestand.fuegeMediumEin(_cd2);

        _medienbestand.entferneMedium(_cd1);
        assertEquals(1, _medienbestand.getMedien()
            .size());

        assertFalse(_medienbestand.getMedien()
            .contains(_cd1));
        assertTrue(_medienbestand.getMedien()
            .contains(_cd2));

        _medienbestand.entferneMedium(_cd2);
        assertTrue(_medienbestand.getMedien()
            .isEmpty());
    }

    @Test
    public void enthaeltMediumTest()
    {
        assertTrue(_medienbestand.getMedien()
            .isEmpty());
        _medienbestand.fuegeMediumEin(_cd1);
        _medienbestand.fuegeMediumEin(_cd2);

        assertEquals(2, _medienbestand.getMedien()
            .size());

        List<Medium> medien = _medienbestand.getMedien();
        boolean medienbestandEnthaeltCD1 = false;
        boolean medienbestandEnthaeltCD2 = false;

        for (Medium m : medien)
        {
            CD gefundeneCD = (CD) m;
            if (gefundeneCD.equals(_cd1))
            {
                medienbestandEnthaeltCD1 = true;
            }
            else if (gefundeneCD.equals(_cd2))
            {
                medienbestandEnthaeltCD2 = true;
            }
        }

        assertTrue(medienbestandEnthaeltCD1);
        assertTrue(medienbestandEnthaeltCD2);

        CD myCd = new CD("Titel", "Kommentar", "Interpret", 53);
        assertFalse(_medienbestand.enthaeltMedium(myCd));
    }

    @Test
    public void fuegeMediumEinTest()
    {
        assertTrue(_medienbestand.getMedien()
            .isEmpty());
        _medienbestand.fuegeMediumEin(_cd1);
        assertFalse(_medienbestand.getMedien()
            .isEmpty());
        assertEquals(1, _medienbestand.getMedien()
            .size());
        assertTrue(_medienbestand.enthaeltMedium(_cd1));

        _medienbestand.fuegeMediumEin(_cd2);
        assertEquals(2, _medienbestand.getMedien()
            .size());
        assertTrue(_medienbestand.enthaeltMedium(_cd2));
    }
}
