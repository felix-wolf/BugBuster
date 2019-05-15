package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.ArrayList;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
/**
 * Verleihkarten werden erstellt um zu verfolgen, für
 * welches Medium welche Kunden vorgemerkt haben
 * @author Gruppe BugBuster
 *
 */
public class VormerkKarte 
{
	
	private final Medium _medium;
	
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
	 * @require getAnzahlVormerker < 3
	 */
	public void addVormerker(Kunde kunde)
	{
		assert kunde != null: "Vorbedingung verltzt: Kunde != null";
		assert getAnzahlVormerker() < 3: "Vorbedingung verletzt: Anzahl Vormerker < 3.";
		
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
	
	
	
}
