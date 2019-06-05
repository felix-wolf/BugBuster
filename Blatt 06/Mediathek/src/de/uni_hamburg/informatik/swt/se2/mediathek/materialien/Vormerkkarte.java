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
    private ArrayList<Kunde> _vormerker;

    /**
     * Initialisiert eine neue Vormerkkarte.
     * 
     * @param kunde Der Kunde der vormerkt
     * @param medium Das Medium, welches vorgemerkt wird
     * 
     * @require kunde != null
     * 
     */
    public Vormerkkarte(Medium medium)
    {
        assert medium != null : "Vorbedingung verletzt: medium != null";
        _medium = medium;
        _vormerker = new ArrayList<Kunde>();
    }

	/**
	 * entfernt einen Kunden aus der Vormerkerliste
	 * @require kunde != null
	 * @param kunde
	 */
	public void removeVormerker(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung Verletzt: kunde != null";
		
		_vormerker.remove(kunde);
	}
	
	/**
	 * entfernt einen Kunden aus der Vormerkerliste
	 * @require kunde != null
	 * @param kunde
	 */
	public void removeVormerker(int index)
	{
		
		_vormerker.remove(index);
	}
	/**
	 * Gibt das Medium der Vormerkkarte zurück
	 * 
	 * @return das Medium der Vormerkkarte
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
	public void addVormerker(Kunde kunde)
	{
		assert kunde != null: "Vorbedingung verletzt: Kunde != null";
		
		_vormerker.add(kunde);
		
	}

    /**
     * Gibt die momentane Anzahl an Vormerkern zurück.
     * 
     * @return die Anzahl der Vormerker
     */
    public int getAnzahlVormerker()
    {
        return _vormerker.size();
    }

	/**
	 * Diese Methode gibt den Vormerker am Index index zurück
	 * 
	 * @param index
	 * @return der Kunde am Index index
	 * @require index >= 0
	 * @require index <= 2
	 */
	public Kunde getVormerker(int index)
	{
		assert index >= 0 : "Vorbedingung Verletzt: index >= 0";
		assert index <= 2 : "Vorbedingung Verletzt: index <= 2";
		if (_vormerker.size() > 0)
		{
			return _vormerker.get(index);
		}
		return null;
	}
	
    /**
     * Prüfen, ob der Kunde ein Vormerker ist
     * @param: Der geprüfte Kunde
     */
    public boolean istVorgemerktVon(Kunde kunde)
    {
        return _vormerker.indexOf(kunde) != -1;
    }

}
