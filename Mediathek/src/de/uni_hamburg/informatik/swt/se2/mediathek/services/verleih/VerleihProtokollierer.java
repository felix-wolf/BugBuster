package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;

/**
 * Ein Verleihprotokollierer schreibt alle Verleihvorgänge in eine Datei.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
class VerleihProtokollierer
{
    /**
     * Textrepräsentation für das Ereignis Ausleihe
     */
    public static final String EREIGNIS_AUSLEIHE = "Ausleihe";

    /**
     * Textrepräsentation für das Ereignis Rückgabe
     */
    public static final String EREIGNIS_RUECKGABE = "Rückgabe";

    /**
     * Pfad der Datei, in die das Verleihprotokoll geschrieben wird
     */
    private static final String DATEIPFAD = "./verleihProtokoll.txt";

    /**
     * Schreibt eine übergebene Verleihkarte ins Protokoll.
     * 
     * @param ereignis Der Name des Verleihereignis: mögliche Namen sind durch
     *            die Konstanten EREIGNIS_AUSLEIHE und EREIGNIS_RUECKGABE
     *            definiert.
     * @param verleihkarte eine Verleihkarte, die das Verleihereignis betrifft.
     * 
     * @require EREIGNIS_AUSLEIHE.equals(ereignis) ||
     *          EREIGNIS_RUECKGABE.equals(ereignis)
     * @require verleihkarte != null
     * 
     * @throws ProtokollierException wenn das Protokollieren nicht geklappt hat.
     */
    public void protokolliere(String ereignis, Verleihkarte verleihkarte)
            throws ProtokollierException
    {
        assert EREIGNIS_AUSLEIHE.equals(ereignis) || EREIGNIS_RUECKGABE.equals(
                ereignis) : "Precondition failed: EREIGNIS_AUSLEIHE.equals(ereignis) || EREIGNIS_RUECKGABE.equals(ereignis)";
        assert verleihkarte != null : "Precondition failed: verleihkarte != null";

        String eintrag = Calendar.getInstance()
            .getTime()
            .toString() + ": " + ereignis + "\n"
                + verleihkarte.getFormatiertenString();

        try (FileWriter writer = new FileWriter(DATEIPFAD, true))
        {
            writer.write(eintrag);
        }
        catch (IOException e)
        {
            throw new ProtokollierException(
                    "Beim Schreiben des Verleihprotokolls ist ein Fehler aufgetreten.");
        }
    }

    /************************************************
     * Alternative entsprechend Zusatzaufgabe 5.1.7 *
     ************************************************/

    public enum VerleihEreignis
    {
        AUSLEIHE, RUECKGABE;
    }

    /**
     * 
     * 
     * Schreibt eine übergebene Verleihkarte ins Protokoll.
     * 
     * @param ereignis Das Verleihereignis
     * @param verleihkarte eine Verleihkarte, die das Verleihereignis betrifft.
     * 
     * @require ereignis != null
     * @require verleihkarte != null
     * 
     * @throws ProtokollierException wenn das Protokollieren nicht geklappt hat.
     */
    public void protokolliere(VerleihEreignis ereignis,
            Verleihkarte verleihkarte) throws ProtokollierException
    {
        assert ereignis != null : "Precondition failed: ereignis != null";
        assert verleihkarte != null : "Precondition failed: verleihkarte != null";

        protokolliere(ereignis.name(), verleihkarte);
    }
}
