package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkMedienTableModelTest
{

    private Medium _cd1;
    private Medium _cd2;
    private Medium _cd3;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;
    private Kunde _kunde4;
    private VormerkMedienTableModel _model;

    public VormerkMedienTableModelTest()
    {
        _cd1 = new CD("CD1-Titel", "CD1-Kommentar", "CD1-Interpret", 42);
        _cd2 = new CD("CD2-Titel", "CD2-Kommentar", "CD2-Regisseur", 120);
        _cd3 = new CD("CD3-Titel", "CD3-Kommentar", "CD3-Interpret", 42);
        _kunde1 = new Kunde(new Kundennummer(111111), "Vorname1", "Nachname1");
        _kunde2 = new Kunde(new Kundennummer(222222), "Vorname2", "Nachname2");
        _kunde3 = new Kunde(new Kundennummer(333333), "Vorname3", "Nachname3");
        _kunde4 = new Kunde(new Kundennummer(444444), "Vorname4", "Nachname4");
        List<VormerkMedienFormatierer> medien = new ArrayList<VormerkMedienFormatierer>();
        medien.add(new VormerkMedienFormatierer(_cd1, _kunde1, _kunde2, _kunde3,
                _kunde4));
        medien.add(new VormerkMedienFormatierer(_cd2, _kunde2, _kunde3, _kunde4,
                _kunde1));
        medien.add(new VormerkMedienFormatierer(_cd3, _kunde3, _kunde4, _kunde1,
                _kunde2));
        _model = new VormerkMedienTableModel();
        _model.setMedien(medien);
    }

    @Test
    public void testeLeereMedienListe() throws Exception
    {
        VormerkMedienTableModel leer = new VormerkMedienTableModel();
        // erwartet: es gibt trotzdem alle Spalten, aber keine Zeilen
        assertEquals(6, leer.getColumnCount());
        assertEquals(0, leer.getRowCount());
    }

    @Test
    public void testeMedienMitZeilenVerknuepft() throws Exception
    {
        assertTrue(_model.zeileExistiert(0));
        assertEquals(_cd1, _model.getMediumFuerZeile(0));
        assertTrue(_model.zeileExistiert(1));
        assertEquals(_cd2, _model.getMediumFuerZeile(1));
        assertTrue(_model.zeileExistiert(2));
        assertEquals(_cd3, _model.getMediumFuerZeile(2));
    }

    @Test
    public void testeSpaltenDefinition() throws Exception
    {
        assertEquals("Medientyp", _model.getColumnName(0));
        assertEquals("Titel", _model.getColumnName(1));
        assertEquals("Ausleiher", _model.getColumnName(2));
        assertEquals("Vormerker 1", _model.getColumnName(3));
        assertEquals("Vormerker 2", _model.getColumnName(4));
        assertEquals("Vormerker 3", _model.getColumnName(5));
        for (int i = 0; i < 6; i++)
        {
            assertEquals(String.class, _model.getColumnClass(i));
        }
    }

    @Test
    public void testeWerte() throws Exception
    {
        assertEquals("CD", _model.getValueAt(0, 0));
        assertEquals("CD1-Titel", _model.getValueAt(0, 1));
        assertEquals("Vorname1 Nachname1", _model.getValueAt(0, 2));
        assertEquals("Vorname2 Nachname2", _model.getValueAt(0, 3));
        assertEquals("Vorname3 Nachname3", _model.getValueAt(0, 4));
        assertEquals("Vorname4 Nachname4", _model.getValueAt(0, 5));
        assertEquals("CD", _model.getValueAt(1, 0));
        assertEquals("CD2-Titel", _model.getValueAt(1, 1));
        assertEquals("Vorname2 Nachname2", _model.getValueAt(1, 2));
        assertEquals("Vorname3 Nachname3", _model.getValueAt(1, 3));
        assertEquals("Vorname4 Nachname4", _model.getValueAt(1, 4));
        assertEquals("Vorname1 Nachname1", _model.getValueAt(1, 5));
        assertEquals("CD", _model.getValueAt(2, 0));
        assertEquals("CD3-Titel", _model.getValueAt(2, 1));
        assertEquals("Vorname3 Nachname3", _model.getValueAt(2, 2));
        assertEquals("Vorname4 Nachname4", _model.getValueAt(2, 3));
        assertEquals("Vorname1 Nachname1", _model.getValueAt(2, 4));
        assertEquals("Vorname2 Nachname2", _model.getValueAt(2, 5));
    }

    @Test
    public void testeZeilenAnzahl() throws Exception
    {
        assertEquals(3, _model.getRowCount());
    }
}
