package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.rueckgabe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;

/**
 * Dieses TableModel hält die Verleihkarten, die an der Oberfläche angezeigt
 * werden sollen. Für jedes Medium wird zusätzlich angezeigt, ob es verliehen
 * ist oder nicht. Aufgabe eines Tablemodels ist es u.a., für die jeweiligen
 * Spalten und Zeilen einer GUI-Tabelle die anzuzeigenden Einträge zu liefern.
 * Für jede Zeile weiß dieses Modell, welche Verleihkarte dort dargestellt wird.
 * 
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VerleihkartenTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_IDENTIFIERS = new String[] {"Kunde",
            "Mediumtyp", "Titel", "Ausleihdatum", "Ausleihdauer (Tage)",
            "Mietgebühr (€)"};

    /**
     * Die Liste, die die Verleihkarten zwischenspeichert/cached und die
     * Sortierreihenfolge repräsentiert.
     */
    private List<Verleihkarte> _verleihkartenListe;

    /**
     * Konstruktor. Initialisiert ein neues VerleihkartenTableModel.
     */
    public VerleihkartenTableModel()
    {
        _verleihkartenListe = new ArrayList<Verleihkarte>();
    }

    @Override
    public int getColumnCount()
    {
        return COLUMN_IDENTIFIERS.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return COLUMN_IDENTIFIERS[column];
    }

    @Override
    public int getRowCount()
    {
        return _verleihkartenListe.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        String ergebnis = null;
        Verleihkarte verleihkarte = _verleihkartenListe.get(rowIndex);
        switch (columnIndex)
        {
        case 0:
            ergebnis = verleihkarte.getEntleiher()
                .getVorname() + " "
                    + verleihkarte.getEntleiher()
                        .getNachname();
            break;
        case 1:
            ergebnis = verleihkarte.getMedium()
                .getMedienBezeichnung();
            break;
        case 2:
            ergebnis = verleihkarte.getMedium()
                .getTitel();
            break;
        case 3:
            ergebnis = verleihkarte.getAusleihdatum()
                .toString();
            break;
        case 4:
            ergebnis = Integer.toString(verleihkarte.getAusleihdauer());
            break;
        case 5:
            ergebnis = verleihkarte.getMietgebuehr()
                .getFormatiertenString();
        }
        return ergebnis;
    }

    /**
     * Liefert die Verleihkarte, die in der Zeile mit der gegebenen Nummer
     * dargestellt wird.
     * 
     * @param zeile Die Nummer der Tabellenzeile
     * 
     * @require zeileExistiert(zeile)
     * 
     * @ensure result != null
     */
    public Verleihkarte getVerleihkartenFuerZeile(int zeile)
    {
        assert zeileExistiert(
                zeile) : "Vorbedingung verletzt: zeileExistiert(zeile)";
        return _verleihkartenListe.get(zeile);
    }

    /**
     * Setzt die anzuzeigenden Verleihkarten.
     * 
     * @param verleihkarten Eine Liste der zu setzenden Verleihkarten.
     *            Nachträgliche Änderungen der Liste wirken sich auch auf das
     *            Model aus.
     * 
     * @require verleihkarten != null
     */
    public void setVerleihkarten(List<Verleihkarte> verleihkarten)
    {
        assert verleihkarten != null : "Vorbedingung verletzt: verleihkarten != null";
        _verleihkartenListe = verleihkarten;
        sortiereVerleihkarten();

        fireTableDataChanged();
    }

    /**
     * Prüft, ob für die gegebene Tabellen-Zeile eine Verleihkarte in dem
     * TableModel existiert.
     * 
     * @param zeile Die Nummer der Tabellenzeile
     */
    public boolean zeileExistiert(int zeile)
    {
        boolean result = false;
        if ((zeile < _verleihkartenListe.size()) && (zeile >= 0))
        {
            result = true;
        }
        return result;
    }

    /**
     * Sortiert die Verleihkarten nach der im VerleihkartenComparator
     * angegebenen Sortierreihenfolge.
     */
    private void sortiereVerleihkarten()
    {
        Collections.sort(_verleihkartenListe, new VerleihkartenComparator());
    }
}
