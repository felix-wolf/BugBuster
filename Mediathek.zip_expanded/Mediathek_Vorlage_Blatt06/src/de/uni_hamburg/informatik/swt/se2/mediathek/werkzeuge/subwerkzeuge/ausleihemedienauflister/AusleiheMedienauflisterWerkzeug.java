package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihService;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.ObservableSubWerkzeug;

/**
 * Ein AusleiheMedienauflisterWerkzeug ist ein Werkzeug zum auflisten von Medien
 * mit ihren Verleihinformationen.
 * 
 * Das Werkzeug ist beobachtbar und informiert darüber, wenn sich die Selektion
 * in der Medienliste ändert.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class AusleiheMedienauflisterWerkzeug extends ObservableSubWerkzeug
{
    private AusleiheMedienauflisterUI _ui;
    private MedienbestandService _medienbestand;
    private final VerleihService _verleihService;

    /**
     * Initialisiert ein neues AusleiheMedienauflisterWerkzeug. Es wird die
     * Benutzungsoberfläche zum Darstellen der Medien erzeugt.
     * 
     * @param medienbestand Der Medienbestand.
     * @param verleihService Der Verleih-Service.
     * 
     * @require medienbestand != null
     * @require verleihService != null
     */
    public AusleiheMedienauflisterWerkzeug(MedienbestandService medienbestand,
            VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _medienbestand = medienbestand;
        _verleihService = verleihService;

        // UI wird erzeugt.
        _ui = new AusleiheMedienauflisterUI();

        // Die Ausleihaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();

        // Die Beobachter werden erzeugt und an den Servies registriert.
        registriereServiceBeobachter();

        // Die anzuzeigenden Materialien werden in den UI-Widgets gesetzt.
        setzeAnzuzeigendeMedien();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
     */
    private void registriereUIAktionen()
    {
        registriereMedienAnzeigenAktion();
    }

    /**
     * Holt und setzt die Medieninformationen.
     */
    private void setzeAnzuzeigendeMedien()
    {
        List<Medium> medienListe = _medienbestand.getMedien();
        List<AusleiheMedienFormatierer> medienFormatierer = new ArrayList<AusleiheMedienFormatierer>();
        for (Medium medium : medienListe)
        {
            boolean istVerliehen = _verleihService.istVerliehen(medium);
            // TODO für Aufgabenblatt 6 (nicht löschen): Falls ein Vormerker für
            // ein Medium existiert, muss dieser hier ermittelt werden.
            // Ist dies korrekt implementiert, erscheint in der Ausleiheansicht
            // der Name des Vormerkers, an den ein Medium ausgeliehen werden
            // darf, gemäß Anforderung d).
            Kunde ersterVormerker = null;

            medienFormatierer.add(new AusleiheMedienFormatierer(medium,
                    istVerliehen, ersterVormerker));
        }
        _ui.getMedienAuflisterTableModel()
            .setMedien(medienFormatierer);
    }

    /**
     * Registiert die Aktion, die ausgeführt wird, wenn ein Medium ausgewählt
     * wird.
     */
    private void registriereMedienAnzeigenAktion()
    {
        _ui.getMedienAuflisterTable()
            .getSelectionModel()
            .addListSelectionListener(new ListSelectionListener()
            {
                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    informiereUeberAenderung();
                }
            });
    }

    /**
     * Registriert die Beobacheter für die Services.
     */
    private void registriereServiceBeobachter()
    {
        ServiceObserver beobachter = new ServiceObserver()
        {
            @Override
            public void reagiereAufAenderung()
            {
                // Wenn ein Service eine Änderung mitteilt, dann wird
                // die angezeigte Liste aller Medien aktualisiert:
                setzeAnzuzeigendeMedien();
            }
        };
        _medienbestand.registriereBeobachter(beobachter);
        _verleihService.registriereBeobachter(beobachter);
    }

    /**
     * Gibt die Liste der vom Benutzer selektierten Medien zurück.
     * 
     * @return Die Liste der vom Benutzer selektierten Medien.
     * 
     * @ensure result != null
     */
    public List<Medium> getSelectedMedien()
    {
        List<Medium> result = new ArrayList<Medium>();
        int[] selectedRows = _ui.getMedienAuflisterTable()
            .getSelectedRows();
        AusleiheMedienTableModel ausleiheMedienTableModel = _ui
            .getMedienAuflisterTableModel();
        for (int zeile : selectedRows)
        {
            if (ausleiheMedienTableModel.zeileExistiert(zeile))
            {
                Medium medium = ausleiheMedienTableModel
                    .getMediumFuerZeile(zeile);
                result.add(medium);
            }
        }
        return result;
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
}
