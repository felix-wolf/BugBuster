package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.ArrayList;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Vormerkkarte werden erstellt um zu verfolgen, für
 * welches Medium welche Kunden vorgemerkt haben
 * 
 * Sie beantwortet folgende Fragen: Welches Medium wurde vorgemerkt? Wie viele 
 * Kunden haben es vorgemerkt?
 * 
 * @author Gruppe BugBuster
 * @version SoSe 2019
 */
public class Vormerkkarte
{
    /**
     * Das Medium, welches vorgemerkt wird
     */
    private final Medium _medium;

    /**
     * eine Liste von Kunden, welche das Medium vorgemerkt haben
     */
    private ArrayList<Kunde> _vormerkern;

    /**
     * Initialisiert eine neue Vormerkkarte.
     * 
     * @param kunde Der Kunde der vormerkt
     * @param medium Das Medium, welches vorgemerkt wird
     * 
     * @require kunde != null
     * 
     */
    public Vormerkkarte(Kunde vormerker, Medium medium)
    {
        //assert vormerker != null : "Vorbedingung verletzt: vormerker != null";
        assert medium != null : "Vorbedingung verletzt: medium != null";

        _medium = medium;
        _vormerkern = new ArrayList<Kunde>();
    }

    /**
     * entfernt ersten Kunden aus der Vormerkerliste
     */
    public void RemoveErsteVormerk()
    {
        _vormerkern.remove(0);
    }

    /**
     * Điền vô
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Fügt einen Kunden zur Vormerkerliste hinzu.
     * 
     * @param kunde
     * 
     * @require kunde != null
     */
    public void AddVormerker(Kunde kunde)
    {
        if (gibAnzahlVormerker() == 2)
        {
            _vormerkern.add(2, kunde);
        }

        if (gibAnzahlVormerker() == 1)
        {
            _vormerkern.add(1, kunde);
        }

        if (gibAnzahlVormerker() == 0)
        {
            _vormerkern.add(0, kunde);
        }

    }

    /**
     * Gibt die momentane Anzahl an Vormerkern zurück.
     * 
     * @return die Anzahl der Vormerker
     */
    public int gibAnzahlVormerker()
    {
        return _vormerkern.size();
    }

    /**
     * Gibt Kunde in der 1. Stelle von Vormerkerkarte
     * @return der erste Vormerker
     */
    public Kunde getErsteVormerker()
    {
        if (gibAnzahlVormerker() == 0)
            return null;
        else
            return _vormerkern.get(0);
    }

    /**
     * Gibt Kunde in der 2. Stelle von Vormerkerkarte
     * @return der zweite Vormerker
     */
    public Kunde getZweiteVormerker()
    {
        return _vormerkern.get(1);
    }

    /**
     * Gibt Kunde in der 3. Stelle von Vormerkerkarte
     * @return der dritte Vormerker
     */
    public Kunde getDritteVormerker()
    {
        return _vormerkern.get(2);
    }

    /**
     * Prüfen, ob der Kunde ein Vormerker ist
     * @param: Der geprüfte Kunde
     */
    public boolean istDerKundeVormerker(Kunde kunde)
    {
        if (_vormerkern.indexOf(kunde) == -1)
        {
            return false;
        }
        else
            return true;
    }

    /**
     * Prüfen, ob der dritte Platz zum Vormerken schon besetzt ist
     * @return : true, wenn es noch Platz zum vormerken gibt
     */
    public boolean istDerDritteVormerkerSchonBesetzt()
    {
        if (_vormerkern.size() < 3)
        {
            return false;
        }
        else
            return true;
    }

}
