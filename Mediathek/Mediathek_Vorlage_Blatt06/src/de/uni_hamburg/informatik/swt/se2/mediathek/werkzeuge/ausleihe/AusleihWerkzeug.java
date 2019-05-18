package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.ausleihe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.ProtokollierException;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihService;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.SubWerkzeugObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister.AusleiheMedienauflisterWerkzeug;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister.KundenauflisterWerkzeug;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundendetailanzeiger.KundenDetailAnzeigerWerkzeug;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.mediendetailanzeiger.MedienDetailAnzeigerWerkzeug;

/**
 * Ein AusleihWerkzeug stellt die Funktionalität der Ausleihe für die
 * Benutzungsoberfläche bereit. Die UI wird durch die AusleiheUI gestaltet.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class AusleihWerkzeug
{

    /**
     * Die UI-Komponente der Ausleihe.
     */
    private final AusleiheUI _ausleiheUI;

    /**
     * Der Service zum Ausleihen von Medien.
     */
    private final VerleihService _verleihService;

    /**
     * Das Sub-Werkzeug zum darstellen und selektieren der Kunden.
     */
    private KundenauflisterWerkzeug _kundenAuflisterWerkzeug;

    /**
     * Das Sub-Werkzeug zum darstellen und selektieren der Medien.
     */
    private AusleiheMedienauflisterWerkzeug _medienAuflisterWerkzeug;

    /**
     * Das Sub-Werkzeug zum anzeigen der Details der selektieten Medien.
     */
    private MedienDetailAnzeigerWerkzeug _medienDetailAnzeigerWerkzeug;

    /**
     * Das Sub-Werkzeug zum anzeigen der Details des selektieten Kunden.
     */
    private KundenDetailAnzeigerWerkzeug _kundenDetailAnzeigerWerkzeug;

    /**
     * Initialisiert ein neues AusleihWerkzeug. Es wird die Benutzungsoberfläche
     * mit den Ausleihaktionen erzeugt, Beobachter an den Services registriert
     * und die anzuzeigenden Materialien gesetzt.
     * 
     * @param medienbestand Der Medienbestand.
     * @param kundenstamm Der Kundenstamm.
     * @param verleihService Der Verleih-Service.
     * 
     * @require medienbestand != null
     * @require kundenstamm != null
     * @require verleihService != null
     */
    public AusleihWerkzeug(MedienbestandService medienbestand,
            KundenstammService kundenstamm, VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _verleihService = verleihService;

        // Subwerkzeuge erstellen
        _kundenAuflisterWerkzeug = new KundenauflisterWerkzeug(kundenstamm);
        _medienAuflisterWerkzeug = new AusleiheMedienauflisterWerkzeug(
                medienbestand, verleihService);
        _medienDetailAnzeigerWerkzeug = new MedienDetailAnzeigerWerkzeug();
        _kundenDetailAnzeigerWerkzeug = new KundenDetailAnzeigerWerkzeug();

        // UI erzeugen
        _ausleiheUI = new AusleiheUI(_kundenAuflisterWerkzeug.getUIPanel(),
                _medienAuflisterWerkzeug.getUIPanel(),
                _kundenDetailAnzeigerWerkzeug.getUIPanel(),
                _medienDetailAnzeigerWerkzeug.getUIPanel());

        // Beobachter erzeugen und an den Services registrieren
        registriereServiceBeobachter();

        // Beobachter erzeugen und an den Subwerkzeugen registrieren
        registriereSubWerkzeugBeobachter();

        // Die Ausleihaktionen erzeugen und an der UI registrieren
        registriereUIAktionen();
    }

    /**
     * Registriert die Aktionen, die bei benachrichtigungen der Services
     * ausgeführt werden.
     */
    private void registriereServiceBeobachter()
    {
        registriereAusleihButtonAktualisierenAktion();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten Änderungen in Subwerkzeugen
     * ausgeführt werden.
     */
    private void registriereSubWerkzeugBeobachter()
    {
        registriereKundenAnzeigenAktion();
        registriereMedienAnzeigenAktion();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
     */
    private void registriereUIAktionen()
    {
        registriereAusleihAktion();
    }

    /**
     * Registriert die Aktion zur Aktualisierung des Ausleihbuttons, wenn eine
     * Benachrichtigung vom Verleihservice auftaucht.
     */
    private void registriereAusleihButtonAktualisierenAktion()
    {
        _verleihService.registriereBeobachter(new ServiceObserver()
        {

            @Override
            public void reagiereAufAenderung()
            {
                aktualisiereAusleihButton();
            }
        });
    }

    /**
     * Registriert die Aktion, die ausgeführt wird, wenn auf den Ausleih-Button
     * gedrückt wird.
     */
    private void registriereAusleihAktion()
    {
        _ausleiheUI.getAusleihButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    leiheAusgewaehlteMedienAus();
                }
            });
    }

    /**
     * Registiert die Aktion, die ausgeführt wird, wenn ein Kunde ausgewählt
     * wird.
     */
    private void registriereKundenAnzeigenAktion()
    {
        _kundenAuflisterWerkzeug.registriereBeobachter(new SubWerkzeugObserver()
        {
            @Override
            public void reagiereAufAenderung()
            {
                zeigeAusgewaehltenKunden();
                aktualisiereAusleihButton();
            }
        });
    }

    /**
     * Registiert die Aktion, die ausgeführt wird, wenn ein Medium ausgewählt
     * wird.
     */
    private void registriereMedienAnzeigenAktion()
    {
        _medienAuflisterWerkzeug.registriereBeobachter(new SubWerkzeugObserver()
        {

            @Override
            public void reagiereAufAenderung()
            {
                zeigeAusgewaehlteMedien();
                aktualisiereAusleihButton();
            }
        });
    }

    /**
     * Überprüft, ob die selektierten Medien ausgeleihen werden können und ob
     * ein Kunde selektiert ist, an den ausgeliehen werden könnte.
     * 
     * @return true, wenn ausleihen möglich ist, sonst false.
     */
    private boolean istAusleihenMoeglich()
    {
        List<Medium> medien = _medienAuflisterWerkzeug.getSelectedMedien();
        Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        // TODO für Aufgabenblatt 6 (nicht löschen): So ändern, dass vorgemerkte
        // Medien nur vom ersten Vormerker ausgeliehen werden können, gemäß
        // Anforderung d).
        boolean ausleiheMoeglich = (kunde != null) && !medien.isEmpty()
                && _verleihService.sindAlleNichtVerliehen(medien);

        return ausleiheMoeglich;
    }

    /**
     * Leiht die ausgewählten Medien aus. Diese Methode wird über einen Listener
     * angestoßen, der reagiert, wenn der Benutzer den Ausleihen-Button drückt.
     */
    private void leiheAusgewaehlteMedienAus()
    {
        List<Medium> selectedMedien = _medienAuflisterWerkzeug
            .getSelectedMedien();
        Kunde selectedKunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        try
        {
            Datum heute = Datum.heute();
            _verleihService.verleiheAn(selectedKunde, selectedMedien, heute);
        }
        catch (ProtokollierException exception)
        {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Zeigt die Details der ausgewählten Medien.
     */
    private void zeigeAusgewaehlteMedien()
    {
        List<Medium> selectedMedien = _medienAuflisterWerkzeug
            .getSelectedMedien();
        _medienDetailAnzeigerWerkzeug.setMedien(selectedMedien);
    }

    /**
     * Zeigt die Details des ausgewählten Kunden (rechts im Fenster)
     */
    private void zeigeAusgewaehltenKunden()
    {
        Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        _kundenDetailAnzeigerWerkzeug.setKunde(kunde);
    }

    /**
     * Setzt den Ausleihbutton auf benutzbar (enabled) falls die gerade
     * selektierten Medien alle ausgeliehen werden können und ein Kunde
     * ausgewählt ist.
     * 
     * Wenn keine Medien selektiert sind oder wenn mindestes eines der
     * selektierten Medien bereits ausgeliehen ist oder wenn kein Kunde
     * ausgewählt ist, wird der Button ausgegraut.
     */
    private void aktualisiereAusleihButton()
    {
        boolean istAusleihenMoeglich = istAusleihenMoeglich();
        _ausleiheUI.getAusleihButton()
            .setEnabled(istAusleihenMoeglich);
    }

    /**
     * Gibt das Panel, dass die UI-Komponente darstellt zurück.
     * 
     * @return Das Panel, dass die UI-Komponente darstellt.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ausleiheUI.getUIPanel();
    }
}
