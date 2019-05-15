package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.ArrayList;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
/**
 * Verleihkarten werden erstellt um zu verfolgen, für
 * welches Medium welche Kunden vorgemerkt haben
 * 
 * Sie beantwortet folgende Fragen: Welches Medium wurde vorgemerkt? Wie viele 
 * Kunden haben es vorgemerkt?
 * 
 * @author Gruppe BugBuster
 * @version SoSe 2019
 */
public class VormerkKarte 
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
	 * @require medium != null
	 * 
	 */
	public VormerkKarte(Kunde kunde, Medium medium)
	{
		assert medium != null : "Vorbedingung verletzt: medium != null";
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		_vormerker = new ArrayList<>();
		_vormerker.add(kunde);
		_medium = medium;
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
		assert kunde != null: "Vorbedingung verltzt: Kunde != null";
		
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
	 * entfernt einen Kunden aus der Vormerkerliste
	 * @require kunde != null
	 * @param kunde
	 */
	public void removeVormerker(Kunde kunde)
	{
		
		_vormerker.remove(kunde);
	}
	
	public Kunde getVormerker(int index)
	{
		return _vormerker.get(index);
	}
	
	public boolean istVorgemerktVon(Kunde kunde)
	{
		return _vormerker.contains(kunde);
	}
	
	//TESTKOMMENTAR
	
}
