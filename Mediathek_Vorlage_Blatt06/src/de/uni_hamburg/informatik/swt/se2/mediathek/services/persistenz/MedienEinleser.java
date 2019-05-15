package de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.DVD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.KonsolenVideospiel;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.PCVideospiel;

/**
 * Liest Medien aus einer Textdatei ein. Der MedienEinleser kann CDs, DVDs,
 * KonsolenVideospiele und PCVideospiele einlesen.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
class MedienEinleser
{

    // Dieses Pattern dient der Überprüfung eines Datums
    private static final Pattern DATUM_PATTERN = Pattern
        .compile("([0-9]{1,2})\\.([0-9]{1,2})\\.([0-9]{4})");

    // Dieses pattern dient der Überprüfung einer kundennumer
    private static final Pattern KUNDENNUMEER_PATTERN = Pattern
        .compile("([0-9]{6})");

    private static String LEERSTRING = "";

    private static String LEERZEICHEN = " ";

    /**
     * Dekodiert den übergebenen String.
     * 
     * @param text Ein String der dekodiert werden soll.
     * @require text != null
     * @return Ein dekodierter String.
     */
    private static String dekodiere(String text)
    {
        String ergebnis = text;
        if (text.equals(LEERZEICHEN))
        {
            ergebnis = LEERSTRING;
        }
        return ergebnis;
    }

    /**
     * Versucht aus dem übergebenen String ein Datum zu extrahieren.
     * 
     * @return Ein ermitteltes Datum oder null, wenn kein Datum extrahiert
     *         werden konnte.
     */
    private static Datum ermittleAusleihdatum(String datumString)
    {
        Datum ergebnis = null;
        Matcher m = DATUM_PATTERN.matcher(datumString);
        if (m.matches())
        {
            int tag = Integer.parseInt(m.group(1), 10);
            int monat = Integer.parseInt(m.group(2), 10);
            int jahr = Integer.parseInt(m.group(3), 10);
            if (Datum.istGueltig(tag, monat, jahr))
            {
                ergebnis = new Datum(tag, monat, jahr);
            }
        }
        return ergebnis;
    }

    /**
     * Versucht aus dem übergebenen String eine Kundennummer zu extrahieren.
     * 
     * @return Eine ermittelte Kundennummer oder null, wenn keine Kundennummer
     *         extrahiert werden konnte.
     */
    private static Kundennummer ermittleKundennummer(String kundennummerString)
    {
        Kundennummer ergebnis = null;
        Matcher m = KUNDENNUMEER_PATTERN.matcher(kundennummerString);
        if (m.matches())
        {
            int nummer = Integer.parseInt(m.group(1), 10);
            if (Kundennummer.istGueltig(nummer))
            {
                ergebnis = new Kundennummer(nummer);
            }
        }
        return ergebnis;
    }

    /**
     * Liest Medien aus einer Textdatei ein und gibt alle eingelesenen Medien
     * und eventuell dazugehörende Verleihkarten zurück.
     * 
     * @param kundenstamm Ein Kundenstamm, um Kunden anhand ihrer Kundennummer
     *            zu finden.
     * @param medienDatei Die Datei in der die Medien gespeichert sind.
     * @return Eine Map der Medien und zugehöriger Verleihkarten (falls
     *         existent).
     * @throws DateiLeseException wenn der Medien-Datenbestand nicht gelesen
     *             werden konnte.
     * 
     * @require kundenstamm != null
     * @require medienDatei != null
     * 
     * @ensure result != null
     */
    public Map<Medium, Verleihkarte> leseMedienEin(List<Kunde> kundenstamm,
            File medienDatei) throws DateiLeseException
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";
        assert medienDatei != null : "Vorbedingung verletzt: medienDatei != null";
        Map<Medium, Verleihkarte> eingeleseneMedien = new HashMap<Medium, Verleihkarte>();

        BufferedReader reader = null;
        try
        {
            Map<Kundennummer, Kunde> kundenMap = new HashMap<Kundennummer, Kunde>();
            for (Kunde kunde : kundenstamm)
            {
                kundenMap.put(kunde.getKundennummer(), kunde);
            }
            reader = new BufferedReader(new FileReader(medienDatei));

            String line = null;
            // liest die Datei Zeile für Zeile
            while ((line = reader.readLine()) != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");

                Datum ausleihDatum = ermittleAusleihdatum(
                        naechsterToken(tokenizer));
                Kundennummer kundennummer = ermittleKundennummer(
                        naechsterToken(tokenizer));

                Medium medium = leseMediumEin(tokenizer);
                Verleihkarte verleihkarte = null;
                if (medium != null)
                {
                    if (kundennummer != null)
                    {
                        Kunde kunde = kundenMap.get(kundennummer);
                        verleihkarte = new Verleihkarte(kunde, medium,
                                ausleihDatum);
                    }
                    eingeleseneMedien.put(medium, verleihkarte);
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            throw new DateiLeseException(
                    "Der Medien-Datenbestand konnte nicht eingelesen werden, da die Datei nicht gefunden wurde.");
        }
        catch (IOException e)
        {
            throw new DateiLeseException(
                    "Der Medien-Datenbestand konnte nicht eingelesen werden, da die Datei nicht gelesen werden konnte.");
        }
        finally
        {
            // Abschließend wird versucht, die Datei erneut zu schließen, falls
            // dies vorher nicht gemacht werden konnte, da eine Exception vor
            // der close() Anweisung geworfen wurde.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    // Diese Exception wird geschluckt.
                }
            }
        }

        return eingeleseneMedien;
    }

    /**
     * Liest die Daten für ein Medium aus dem übergebenen StringTokenizer aus
     * und erzeugt ein konkretes Objekt eines Subtyps von Medium.
     * 
     * @param tokenizer Ein StringTokenizer, der die Daten liefert.
     * @return ein neu erzeugtes Medium oder null, wenn kein Medium erzeugt
     *         werden konnte.
     */
    private static Medium leseMediumEin(StringTokenizer tokenizer)
    {
        String medienBezeichnung = naechsterToken(tokenizer);
        String titel = naechsterToken(tokenizer);
        String kommentar = naechsterToken(tokenizer);

        Medium medium = null;
        if (medienBezeichnung.equals("CD"))
        {
            String interpret = naechsterToken(tokenizer);
            String spiellaenge = naechsterToken(tokenizer);

            medium = new CD(titel, kommentar, interpret,
                    Integer.parseInt(spiellaenge));
        }
        else if (medienBezeichnung.equals("DVD"))
        {
            String regisseur = naechsterToken(tokenizer);
            int laufzeit = Integer.valueOf(naechsterToken(tokenizer));

            medium = new DVD(titel, kommentar, regisseur, laufzeit);
        }
        else if (medienBezeichnung.equals("KonsolenVideospiel")
                || medienBezeichnung.equals("PCVideospiel"))
        {
            String system = naechsterToken(tokenizer);

            if (medienBezeichnung.equals("KonsolenVideospiel"))
            {
                medium = new KonsolenVideospiel(titel, kommentar, system);
            }
            else if (medienBezeichnung.equals("PCVideospiel"))
            {
                medium = new PCVideospiel(titel, kommentar, system);
            }
        }
        return medium;
    }

    /**
     * Holt das nächste Token vom Tokenizer und dekodiert es.
     * 
     * @param tokenizer Ein Tokenizer.
     * @return Das nächste dekodierte Token.
     */
    private static String naechsterToken(StringTokenizer tokenizer)
    {
        return dekodiere(tokenizer.nextToken());
    }
}
