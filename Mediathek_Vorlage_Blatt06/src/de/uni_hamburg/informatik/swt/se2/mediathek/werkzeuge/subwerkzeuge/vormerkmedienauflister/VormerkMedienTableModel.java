package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Ein VormerkMedienTableModel hält Medien, und gibt für jede Spalte im
 * VormerkWerkzeug die dort benötigte Information über das Medium in einer Zeile
 * zurück.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VormerkMedienTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = new String[] {"Medientyp",
            "Titel", "Ausleiher", "Vormerker 1", "Vormerker 2", "Vormerker 3"};

    private List<VormerkMedienFormatierer> _medienListe;

    /**
     * Initialisiert ein VormerkMedienTableModel.
     */
    public VormerkMedienTableModel()
    {
        _medienListe = new ArrayList<VormerkMedienFormatierer>();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }

    @Override
    public int getColumnCount()
    {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getRowCount()
    {
        return _medienListe.size();
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        VormerkMedienFormatierer formatierer = getMedienFormatierer(row);
        Object ergebnis = null;
        switch (column)
        {
        case 0:
            ergebnis = formatierer.getMedienBezeichnung();
            break;
        case 1:
            ergebnis = formatierer.getTitel();
            break;
        case 2:
            ergebnis = formatierer.getEntleiher();
            break;
        case 3:
            ergebnis = formatierer.getVormerker1();
            break;
        case 4:
            ergebnis = formatierer.getVormerker2();
            break;
        case 5:
            ergebnis = formatierer.getVormerker3();
            break;
        }
        return ergebnis;
    }

    /**
     * Liefert ein Medium, das in der Zeile mit einer gegebenen Nummer
     * dargestellt wird.
     * 
     * @param zeile Eine Nummer einer Tabellenzeile
     * 
     * @require zeileExistiert(zeile)
     */
    public Medium getMediumFuerZeile(int zeile)
    {
        assert zeileExistiert(
                zeile) : "Vorbedingung verletzt: zeileExistiert(zeile)";
        return getMedienFormatierer(zeile).getMedium();
    }

    /**
     * Prüft, ob für die gegebene Tabellen-Zeile ein Medium in dem TableModel
     * existiert.
     * 
     * @param zeile Die Nummer der Tabellenzeile
     */
    public boolean zeileExistiert(int zeile)
    {
        boolean result = false;
        if ((zeile < _medienListe.size()) && (zeile >= 0))
        {
            result = true;
        }
        return result;
    }

    /**
     * Setze die anzuzeigenden Medien. Nach dem Setzen wird die Tabelle
     * aktualisiert. Es wird auf eine Kopie der Liste gearbeitet.
     * 
     * @require medien != null
     */
    public void setMedien(List<VormerkMedienFormatierer> medien)
    {
        assert medien != null : "Vorbedingung verletzt: medien != null";
        _medienListe = new ArrayList<VormerkMedienFormatierer>(medien);

        // sortiere Medien-Liste
        Collections.sort(_medienListe,
                new VormerkMedienFormatiererComparator());

        fireTableDataChanged();
    }

    /**
     * Liefert den Medien-Formatierer für einen angegebenen Index. Die
     * Gültigkeit des Index wird nicht überprüft.
     * 
     */
    private VormerkMedienFormatierer getMedienFormatierer(int index)
    {
        return _medienListe.get(index);
    }

}
