package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister;

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
 * Ein VormerkMedienauflisterWerkzeug ist ein Werkzeug zum Auflisten von Medien
 * mit ihren für das Vormerken relevanten Verleihinformationen.
 * 
 * Das Werkzeug ist beobachtbar und informiert darüber, wenn sich die Selektion
 * in der Medienliste ändert.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VormerkMedienauflisterWerkzeug extends ObservableSubWerkzeug
{
    private VormerkMedienauflisterUI _ui;
    private MedienbestandService _medienbestand;
    private final VerleihService _verleihService;

    /**
     * Initialisiert ein neues VormerkMedienauflisterWerkzeug. Es wird die
     * Benutzungsoberfläche zum Darstellen der Medien erzeugt.
     * 
     * @param medienbestand Der Medienbestand.
     * @param verleihService Der Verleih-Service.
     * 
     * @require medienbestand != null
     * @require verleihService != null
     */
    public VormerkMedienauflisterWerkzeug(MedienbestandService medienbestand,
            VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _medienbestand = medienbestand;
        _verleihService = verleihService;

        // UI wird erzeugt.
        _ui = new VormerkMedienauflisterUI();

        // Die Ausleihaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();

        // Die Beobachter werden erzeugt und an den Services registriert.
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
        List<VormerkMedienFormatierer> medienFormatierer = new ArrayList<VormerkMedienFormatierer>();
        for (Medium medium : medienListe)
        {
            // TODO für Aufgabenblatt 6 (nicht löschen): Die
            // VormerkMedienFormatierer müssen noch mit einem möglichen
            // Entleiher und möglichen Vormerkern ausgestattet werden.
            // Ist dies korrekt implementiert, erscheinen in der Vormerkansicht
            // die Namen des Entleihers und der möglichen 3 Vormerker.
            Kunde entleiher = null;
            Kunde vormerker1 = null;
            Kunde vormerker2 = null;
            Kunde vormerker3 = null;

            medienFormatierer.add(new VormerkMedienFormatierer(medium,
                    entleiher, vormerker1, vormerker2, vormerker3));
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
        VormerkMedienTableModel medienTableModel = _ui
            .getMedienAuflisterTableModel();
        for (int zeile : selectedRows)
        {
            if (medienTableModel.zeileExistiert(zeile))
            {
                Medium medium = medienTableModel.getMediumFuerZeile(zeile);
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
