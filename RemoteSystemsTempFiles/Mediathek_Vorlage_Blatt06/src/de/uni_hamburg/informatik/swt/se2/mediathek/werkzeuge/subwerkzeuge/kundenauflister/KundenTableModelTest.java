package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

public class KundenTableModelTest
{
    private Kunde _kundeSchmidt;
    private Kunde _kundeSchmitz;
    private KundenTableModel _model;

    public KundenTableModelTest()
    {
        // Die Kunden werden in nicht alphabetischer Reihenfolge eingefügt,
        // um die Sortierfunktion zu testen.
        _kundeSchmitz = new Kunde(new Kundennummer(123456), "Susi", "Schmitz");
        _kundeSchmidt = new Kunde(new Kundennummer(456789), "Klaus", "Schmidt");
        List<Kunde> kunden = new ArrayList<Kunde>();
        kunden.add(_kundeSchmitz);
        kunden.add(_kundeSchmidt);
        _model = new KundenTableModel();
        _model.setKunden(kunden);
    }

    @Test
    public void testeKundenMitZeilenVerknuepft() throws Exception
    {
        // Das KundenTableModel sortiert die Kunden alphabetisch nach Nachname,
        // deshalb muss Schmidt vor Schmitz in der Tabelle auftauchen.
        assertTrue(_model.zeileExistiert(0));
        assertEquals(_kundeSchmidt, _model.getKundeFuerZeile(0));
        assertTrue(_model.zeileExistiert(1));
        assertEquals(_kundeSchmitz, _model.getKundeFuerZeile(1));
    }

    @Test
    public void testeLeereKundenliste() throws Exception
    {
        KundenTableModel leer = new KundenTableModel();
        // erwartet: es gibt trotzdem alle drei Spalten, aber keine Zeilen
        assertEquals(3, leer.getColumnCount());
        assertEquals(0, leer.getRowCount());
    }

    @Test
    public void testeSpaltenDefinition() throws Exception
    {
        assertEquals(3, _model.getColumnCount());
        assertEquals("Kundennummer", _model.getColumnName(0));
        assertEquals("Vorname", _model.getColumnName(1));
        assertEquals("Nachname", _model.getColumnName(2));
    }

    @Test
    public void testeWerte() throws Exception
    {
        // Auch hier wird die Sortierung mit getestet.
        assertEquals("456789", _model.getValueAt(0, 0));
        assertEquals("Klaus", _model.getValueAt(0, 1));
        assertEquals("Schmidt", _model.getValueAt(0, 2));
        assertEquals("123456", _model.getValueAt(1, 0));
        assertEquals("Susi", _model.getValueAt(1, 1));
        assertEquals("Schmitz", _model.getValueAt(1, 2));
    }

    @Test
    public void testeZeilenAnzahl() throws Exception
    {
        assertEquals(2, _model.getRowCount());
    }

    @Test
    public void testeZellenSindNichtEditierbar() throws Exception
    {
        assertFalse(_model.isCellEditable(0, 0));
        assertFalse(_model.isCellEditable(0, 1));
        assertFalse(_model.isCellEditable(0, 2));
        assertFalse(_model.isCellEditable(1, 0));
        assertFalse(_model.isCellEditable(1, 1));
        assertFalse(_model.isCellEditable(1, 2));
    }
}
