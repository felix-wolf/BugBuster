package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

/**
 * Ein KundenTableModel hält Kunden, und gibt für jede Spalte im AusleihWerkzeug
 * die dort benötigte Information über den Kunde n in einer Zeile zurück.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class KundenTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = new String[] {"Kundennummer",
            "Vorname", "Nachname"};

    /**
     * Eine Liste, die die Kunden zwischenspeichert/cached und die
     * Sortierreihenfolge repräsentiert.
     */
    private List<Kunde> _kundenListe;

    /**
     * Initialisiert ein KundenTableModel.
     * 
     */
    public KundenTableModel()
    {
        _kundenListe = new ArrayList<Kunde>();
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
        return _kundenListe.size();
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        Kunde kunde = _kundenListe.get(row);
        String ergebnis = "";
        switch (column)
        {
        case 0:
            ergebnis = kunde.getKundennummer()
                .toString();
            break;
        case 1:
            ergebnis = kunde.getVorname();
            break;
        case 2:
            ergebnis = kunde.getNachname();
            break;
        }
        return ergebnis;
    }

    /**
     * Liefert den Kunden, der in der Zeile mit der gegebenen Nummer dargestellt
     * wird.
     * 
     * @param zeile Die Nummer der Tabellenzeile
     * 
     * @require zeileExistiert(zeile)
     * @ensure result != null
     */
    public Kunde getKundeFuerZeile(int zeile)
    {
        assert zeileExistiert(
                zeile) : "Vorbedingung verletzt: zeileExistiert(zeile)";
        return _kundenListe.get(zeile);
    }

    /**
     * Prüft, ob für eine gegebene Tabellen-Zeile ein Kunde in dem TableModel
     * existiert.
     * 
     * @param zeile Die Nummer der Tabellenzeile
     * @return true, wenn die angegebene Zeile existiert, sonst false.
     */
    public boolean zeileExistiert(int zeile)
    {
        boolean result = false;
        if ((zeile < _kundenListe.size()) && (zeile >= 0))
        {
            result = true;
        }
        return result;
    }

    /**
     * Setze die anzuzeigenden Kunden. Nach dem Setzen wird die Tabelle
     * aktualisiert.
     * 
     * @param kunden Eine Liste aller Kunden auf der gearbeitet wird.
     *            Nachträgliche Änderungen der Liste wirken sich auch auf das
     *            Model aus.
     * 
     * @require kunden != null
     */
    public void setKunden(List<Kunde> kunden)
    {
        assert kunden != null : "Vorbedingung verletzt: kunden != null";
        _kundenListe = kunden;
        sortiereKunden();

        fireTableDataChanged();
    }

    /**
     * Sortiert Kunden nach der im KundenComparator angegebenen
     * Sortierreihenfolge.
     */
    private void sortiereKunden()
    {
        Collections.sort(_kundenListe, new KundenComparator());
    }
}
