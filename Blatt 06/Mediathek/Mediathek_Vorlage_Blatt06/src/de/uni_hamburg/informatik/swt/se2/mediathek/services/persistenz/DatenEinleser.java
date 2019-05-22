package de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Ein DatenEinleser kann verwendet werden um Kunden, Medien und Verleihkarten
 * einzulesen.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class DatenEinleser
{

    /**
     * Die Datei in der die Medien gespeichert sind.
     */
    private final File _medienDatei;

    /**
     * Die Datei in der die Kunden gespeichert sind.
     */
    private final File _kundenDatei;

    /**
     * Die eingelesenen Kunden, ist null wenn noch nicht eingelesen wurde.
     */
    private List<Kunde> _kunden;

    /**
     * Die eingelesenen Medien, ist null wenn noch nicht eingelesen wurde.
     */
    private List<Medium> _medien;

    /**
     * Die eingelesenen Verleihkarten, ist null wenn noch nicht eingelesen
     * wurde.
     */
    private List<Verleihkarte> _verleihkarten;

    /**
     * Initialisiert einen neuen DatenEinleser, der aus den angebenen Dateien
     * einliest.
     * 
     * @param medienDatei Die Datei in der die Medien gespeichert sind.
     * @param kundenDatei Die Datei in der die Kunden gespeichert sind.
     * 
     * @require medienDatei != null
     * @require kundenDatei != null
     */
    public DatenEinleser(File medienDatei, File kundenDatei)
    {
        assert medienDatei != null : "Vorbedingung verletzt: medienDatei != null";
        assert kundenDatei != null : "Vorbedingung verletzt: kundenDatei != null";

        _medienDatei = medienDatei;
        _kundenDatei = kundenDatei;
    }

    /**
     * Liest die Daten aus den, beim Konstruktoraufruf angegebenen Dateien aus.
     * 
     * Das Ergebnis des Einlesens kann mit #getKunden(), #getMedien() und
     * #getVerleihkarten() abgefragt werden.
     * 
     * @throws DateiLeseException Wenn ein Fehler beim Lesen der Dateien
     *             auftritt.
     * 
     * @ensure wurdeEingelesen() == true
     */
    public void leseDaten() throws DateiLeseException
    {
        KundenEinleser kundenEinleser = new KundenEinleser();
        _kunden = kundenEinleser.leseKundenEin(_kundenDatei);
        MedienEinleser medienEinleser = new MedienEinleser();
        Map<Medium, Verleihkarte> medienMap = medienEinleser
            .leseMedienEin(_kunden, _medienDatei);
        _medien = new ArrayList<Medium>(medienMap.keySet());
        _verleihkarten = new ArrayList<Verleihkarte>();
        for (Entry<Medium, Verleihkarte> entry : medienMap.entrySet())
        {
            Verleihkarte verleihkarte = entry.getValue();
            if (verleihkarte != null)
            {
                _verleihkarten.add(verleihkarte);
            }
        }
    }

    /**
     * Prüft ob eingelesen wurde.
     * 
     * @return true wenn eingelesen wurde, sonst false .
     */
    public boolean wurdeEingelesen()
    {
        return _kunden != null;
    }

    /**
     * Gibt die eingelesenen Kunden zurück.
     * 
     * @return Die eingelesenen Kunden.
     * 
     * @require wurdeEingelesen()
     */
    public List<Kunde> getKunden()
    {
        assert wurdeEingelesen() : "Vorbedingung verletzt: wurdeEingelesen()";
        return _kunden;
    }

    /**
     * Gibt die eingelesenen Medien zurück.
     * 
     * @return Die eingelesenen Medien.
     * 
     * @require wurdeEingelesen()
     */
    public List<Medium> getMedien()
    {
        assert wurdeEingelesen() : "Vorbedingung verletzt: wurdeEingelesen()";
        return _medien;
    }

    /**
     * Gibt die eingelesenen Verleihkarten zurück.
     * 
     * @return Die eingelesenen Verleihkarten.
     * 
     * @require wurdeEingelesen()
     */
    public List<Verleihkarte> getVerleihkarten()
    {
        assert wurdeEingelesen() : "Vorbedingung verletzt: wurdeEingelesen()";
        return _verleihkarten;
    }

}
