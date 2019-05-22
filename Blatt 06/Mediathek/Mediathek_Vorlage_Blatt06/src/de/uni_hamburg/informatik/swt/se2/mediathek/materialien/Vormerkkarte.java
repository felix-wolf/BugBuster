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
 * Eine Vormerkkarte wird erst erstellt, wenn sie gebraucht wird.
 * Eine Vormerkkarte wird gelöscht, wenn kein Kunde das Medium mehr vorgemerkt hat
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
	 * 
	 */
	public static final int MaxAnzahlVormerker = 3;
	
	/**
	 * Initialisiert eine neue Vormerkkarte.
	 * 
	 * @param kunde Der Kunde der vormerkt
	 * @param medium Das Medium, welches vorgemerkt wird
	 * 
	 * @require kunde != null
	 * @require medium != null
	 */
	public Vormerkkarte(Kunde kunde, Medium medium)
	{
		assert medium != null : "Vorbedingung verletzt: medium != null";
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		
		_vormerker = new ArrayList<>();
		addVormerker(kunde);
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
	 * @require kunde != null
	 * @require !istVorgemerktVon(Kunde)
	 * @ensure istVorgemerktVon(Kunde)
	 */
	public void addVormerker(Kunde kunde)
	{
		assert getAnzahlVormerker() < MaxAnzahlVormerker : "Vorbededingung verletzt: getAnzahlVormerker() < MaxAnzahlVormerker";
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
	 * entfernt einen Kunden aus der Vormerkerliste
	 * 
	 * @require kunde != null
	 * @require istVorgemerktVon(Kunde)
	 * @ensure !istVorgemerktVon(Kunde)
	 * @param kunde
	 */
	public void removeVormerker(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		assert istVorgemerktVon(kunde) : "Vorbedingung verletzt: istVorgemerktVon(Kunde) == true";
		
		_vormerker.remove(kunde);
	}
	
	/**
	 * Diese Methode gibt den Vormerker am Index index zurück
	 * 
	 * @require 0 <= index < Vormerkkarte.MaxAnzahlVormerker
	 * @ensure kunde != null
	 * @param index
	 * @return der Kunde am Index index
	 */
	public Kunde getVormerker(int index)
	{

		assert 0 <= index && index < MaxAnzahlVormerker : "Vorbedingung verletzt: 0 <= index && index < MaxAnzahlVormerker"
				+ "(index nicht gueltig)";
		return _vormerker.get(index);
	}
	
	
	/**
	 * Diese Methode gibt zurück ob das Medium von kunde vorgemerkt ist
	 * 
	 * @param kunde
	 * @return wahr, wenn kunde vorgemerkt ist, sonst falsch
	 * @require kunde != null
	 */
	public boolean istVorgemerktVon(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung Verletzt: kunde != null";
		
		return _vormerker.contains(kunde);
	}
	
	
}