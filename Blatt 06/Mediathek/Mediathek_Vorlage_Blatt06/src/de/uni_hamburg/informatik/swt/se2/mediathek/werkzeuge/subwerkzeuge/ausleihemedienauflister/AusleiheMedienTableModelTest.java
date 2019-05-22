package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class AusleiheMedienTableModelTest
{

    private Medium _cd1;
    private Medium _cd2;
    private Medium _cd3;
    private Kunde _vormerker;
    private AusleiheMedienTableModel _model;

    public AusleiheMedienTableModelTest()
    {
        _cd1 = new CD("CD1-Titel", "CD1-Kommentar", "CD1-Interpret", 42);
        _cd2 = new CD("CD2-Titel", "CD2-Kommentar", "CD2-Regisseur", 120);
        _cd3 = new CD("CD1-Titel", "CD1-Kommentar", "CD1-Interpret", 42);
        List<AusleiheMedienFormatierer> medien = new ArrayList<AusleiheMedienFormatierer>();
        medien.add(new AusleiheMedienFormatierer(_cd1, true, _vormerker));
        medien.add(new AusleiheMedienFormatierer(_cd2, false, _vormerker));
        medien.add(new AusleiheMedienFormatierer(_cd3, true, _vormerker));
        _model = new AusleiheMedienTableModel();
        _model.setMedien(medien);
    }

    @Test
    public void testeLeereMedienListe() throws Exception
    {
        AusleiheMedienTableModel leer = new AusleiheMedienTableModel();
        // erwartet: es gibt trotzdem alle Spalten, aber keine Zeilen
        assertEquals(3, leer.getColumnCount());
        assertEquals(0, leer.getRowCount());
    }

    @Test
    public void testeMedienMitZeilenVerknuepft() throws Exception
    {
        assertTrue(_model.zeileExistiert(0));
        assertEquals(_cd1, _model.getMediumFuerZeile(0));
        assertTrue(_model.zeileExistiert(1));
        assertEquals(_cd3, _model.getMediumFuerZeile(1));
        assertTrue(_model.zeileExistiert(2));
        assertEquals(_cd2, _model.getMediumFuerZeile(2));
    }

    @Test
    public void testeSpaltenDefinition() throws Exception
    {
        assertEquals(3, _model.getColumnCount());
        assertEquals("Medientyp", _model.getColumnName(0));
        assertEquals("Titel", _model.getColumnName(1));
        assertEquals("ausleihbar", _model.getColumnName(2));
        assertEquals(String.class, _model.getColumnClass(0));
        assertEquals(String.class, _model.getColumnClass(1));
        assertEquals(String.class, _model.getColumnClass(2));
    }

    @Test
    public void testeWerte() throws Exception
    {
        // Die Icons in der dritten Spalte werden von diesem Test nicht
        // überprüft.
        assertEquals("CD", _model.getValueAt(0, 0));
        assertEquals("CD1-Titel", _model.getValueAt(0, 1));
        assertEquals("CD", _model.getValueAt(1, 0));
        assertEquals("CD1-Titel", _model.getValueAt(1, 1));
        assertEquals("CD", _model.getValueAt(2, 0));
        assertEquals("CD2-Titel", _model.getValueAt(2, 1));
    }

    @Test
    public void testeZeilenAnzahl() throws Exception
    {
        assertEquals(3, _model.getRowCount());
    }
}
