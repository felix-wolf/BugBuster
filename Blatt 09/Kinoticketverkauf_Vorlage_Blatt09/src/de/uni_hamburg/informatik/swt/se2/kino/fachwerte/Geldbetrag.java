package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;

final public class Geldbetrag {

	//HashMap, welche erzeugte Geldbeträge hält
	private static HashMap<Integer, Geldbetrag> GELDBETRAEGE = new HashMap<Integer, Geldbetrag>();
	//interne Darstellung eines Geldbetrags
	private final int _eurocent;

	
	/**
	 * Privater Konstruktor, fügt "sich selbst" in die HashMap ein
	 * 
	 * @param eurocent der Betrag des Geldbetrags
	 * @require istGueltig(eurocent)
	 * @ensure GELDBETRAEGE.contains(eurocent)
	 */
	private Geldbetrag(int eurocent) {

		assert istGueltig(eurocent): "Vorbedingung verletzt: istGueltig(eurocent)";
		_eurocent = eurocent;

		GELDBETRAEGE.put(eurocent, this);
	}

	/**
	 * Liefert Geldbetrag aus einem int
	 * 
	 * @param eurocent die Höhe des Geldbetrags
	 * @return der Geldbetrag
	 * @require istGueltig(eurocent)
	 */
	public static Geldbetrag get(int eurocent)
	{
		assert istGueltig(eurocent) : "Vorbedingung verletzt: istGueltig(eurocent)";

		return (!GELDBETRAEGE.containsKey(eurocent) ? new Geldbetrag(eurocent) : GELDBETRAEGE.get(eurocent));
	}

	/**
	 * Liefert Geldbetrag aus einem String
	 * 
	 * @param euroString die Höhe des Geldbetrags
	 * @return der Geldbetrag
	 * @require euroString != null
	 * @require istGueltig(euroString)
	 */
	public static Geldbetrag get(String euroString) {

		assert euroString != null : "Vorbedingung verletzt: euroString != null";
		assert istGueltig(euroString) : "Vorbedingung verletzt: istGueltig(euroString)";

		return Geldbetrag.get(stringToEuroCent(euroString));
	}
	
	/**
	 * Überprüft, ob das addieren möglich ist
	 * 
	 * @param geldbetrag der zu addierende Geldbetrag
	 * @return ob addieren möglich ist
	 * @require geldbetrag != null
	 */
	public boolean istAddierenMoeglich(Geldbetrag geldbetrag) 
	{
		assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null";
		return istGueltig((long) getEurocent() + geldbetrag.getEurocent());
	}
	
	/**
	 * Überprüft, ob das subtrahierende möglich ist
	 * 
	 * @param geldbetrag der zu subtrahierende Geldbetrag
	 * @return ob subtrahieren möglich ist
	 * @require geldbetrag != null
	 */
	public boolean istSubtrahierenMoeglich(Geldbetrag geldbetrag) 
	{
		assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null";
		return istGueltig((long) getEurocent() - geldbetrag.getEurocent());
	}
	
	/**
	 * Überprüft, ob das multiplizieren möglich ist
	 * 
	 * @param geldbetrag der zu subtrahieren Geldbetrag
	 * @return ob multiplizieren möglich ist
	 */
	public boolean istMultiplizierenMoeglich(int zahl) 
	{
		return istGueltig((long) getEurocent() * zahl);
	}

	/**
	 * addiert einen Geldbetrag zu einem anderen
	 * 
	 * @param geldbetrag der zu addierende Geldbetrag
	 * @return den neuen Geldbetrag
	 * @require geldbetrag != null
	 * @require istGueltig(this.getEurocent() + geldbetrag.getEurocent())
	 */
	public Geldbetrag addiere(Geldbetrag geldbetrag) {
		assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null";
		assert istAddierenMoeglich(geldbetrag) : "Vorbedingung verletzt:"
		+ " istAddierenMoeglich(geldbetrag)";

		int betrag1 = this.getEurocent();
		int betrag2 = geldbetrag.getEurocent();
		int ergebnis = betrag1 + betrag2;

		return Geldbetrag.get(ergebnis);
	}

	/**
	 * Subtrahiert einen Geldbetrag von einem anderen
	 * 
	 * @param geldbetrag der Geldbetrag, der abgezogen werden soll
	 * @return der neue Geldbetrag
	 * @require geldbetrag != null
	 * @require istGueltig(this.getEurocent() - geldbetrag.getEurocent())
	 */
	public Geldbetrag subtrahiere(Geldbetrag geldbetrag) {

		assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null";
		assert istSubtrahierenMoeglich(geldbetrag) : "Vorbedingung verletzt:"
		+ " istSubtrahierenMoeglich(geldbetrag)";

		int betrag1 = getEurocent();
		int betrag2 = geldbetrag.getEurocent(); 
		int ergebnis = betrag1 - betrag2;
		return Geldbetrag.get(ergebnis);
	}

	/**
	 * Multipliziert Geldbetrag mit einem int zu einem Geldbetrag
	 * 
	 * @param zahl, mit der dieser Geldbetrag multipliziert wird
	 * @return der neue Geldbetrag
	 * @require istGueltig(this.getEurocent() * zahl)
	 */
	public Geldbetrag multipliziere(int zahl) {

		assert istMultiplizierenMoeglich(zahl) : "Vorbedingung verletzt:"
		+ " istMultplizierenMoeglich(zahl)";

		int ergebnis = _eurocent * zahl;

		return Geldbetrag.get(ergebnis);	
	}

	/**
	 * Getter für Feld mit int Betrag
	 * 
	 * @return den int-Wert des Geldbetrags
	 */
	private int getEurocent()
	{
		return _eurocent;
	}

	/**
	 * Wandelt String zu Eurocent um, entfernt ggf. Komma
	 * 
	 * @param euroString der String
	 * @return den eurocentbetrag
	 * @require euroString != null
	 */
	private static int stringToEuroCent(String euroString) {

		assert euroString != null : "Vorbedingung verletzt: euroString != null";

		if (euroString.contains(","))
		{
			return Integer.valueOf(euroString.replaceAll(",", "").toString());

		}
		return Integer.valueOf(euroString) * 100;
	}



	/**
	 * Prüft, ob die Eingabe im Int-Wertbereich liegt
	 * 
	 * @return true, wenn es gültig ist
	 */
	public static boolean istGueltig (long eurocent)
	{
		return (eurocent <= Integer.MAX_VALUE && eurocent >= Integer.MIN_VALUE);
	}

	/**
	 * Prüfen, ob die Eingabe ein gültiger String ist
	 * 
	 * @param geldbetragInString : ein String beschreibt Geldbetrag z.B: -12,43
	 * 
	 * @return true, wenn es gültig ist
	 */
	public static boolean istGueltig(String geldbetragInString)
	{
		assert geldbetragInString != null : "Vorbedingung verletzt: geldbetragInString != null";

		return (gueltigesStringformat(geldbetragInString) && gueltigeKommaAnzahl(geldbetragInString)
				&& gueltigeNachkommastellen(geldbetragInString) && istGueltig(Long.valueOf(geldbetragInString.replace(",", ""))));
	}

	/**
	 * Prüfe, ob das eingegebene String in Format:  wenn es '-' gibt, dann muss '-' am Anfang steht. Die nächsten Symbole sind aus Nummer oder ',' oder '.'
	 * @return true, wenn es den Format hat.
	 */
	private static boolean gueltigesStringformat (String geldbetragInString)
	{
		char ersteBuchstabe = geldbetragInString.charAt(0);
		int anfangPositionZumParse;

		//Wenn es ein Vorzeichen - gibt, dann fängt in der Stelle 1 zu parsen
		if (ersteBuchstabe == '-') 
		{
			anfangPositionZumParse = 1;
		}
		else anfangPositionZumParse = 0;

		for (int i = anfangPositionZumParse; i < geldbetragInString.length(); i++)
		{
			char charInString = geldbetragInString.charAt(i);
			boolean istCharEinNummer = Character.isDigit(charInString);

			if ( !istCharEinNummer && charInString != ',') 
			{
				return false;
			}
		}
		return true;

	}

	/**
	 * Prüfe, ob das eingegebene String maximal ein ',' Symbol hat
	 * 
	 * @param geldbetragInString
	 * @require geldbetragInString != null
	 * @return true, wenn das String maximal ein ',' oder Symbol hat
	 */
	private static boolean gueltigeKommaAnzahl (String geldbetragInString)
	{
		assert geldbetragInString != null : "Vorbedingung verletzt: geldbetragInString != null";
		
		int AnzahlVorkommen = 0;
		for (int i = 0; i < geldbetragInString.length(); i++)
		{
			char charInString = geldbetragInString.charAt(i);

			if (charInString == ',') 
			{
				AnzahlVorkommen++;
			}
		}
		return (AnzahlVorkommen < 2); 

	}

	/**
	 * Prüft ob, wenn es Komma gibt, nach dem Komma maximal zwei Stellen kommen
	 * 
	 * @param geldbetragInString Geldbetrag als Sting
	 * @require geldbetragInString != null
	 * @return True, wenn maximal 2 Nachkommastellen
	 */
	private static boolean gueltigeNachkommastellen (String geldbetragInString)
	{
		assert geldbetragInString != null : "Vorbedingung verletzt: geldbetragInString != null";
		
		int count = 0;
		if (geldbetragInString.contains(","))
		{
			int indexVonKomma = geldbetragInString.indexOf(',');
			for (int i = indexVonKomma; i < geldbetragInString.length() ;i++)
			{
				count++;
			}
		}
		return count < 4;
	}


	/**
	 * gibt zurück, ob der Geldbetrag negativ ist
	 * 
	 * @return ob negativ 
	 */
	public boolean istNegativ()
	{
		return _eurocent < 0;
	}

	@Override
	public String toString() {

		int euro = Math.abs(_eurocent / 100);
		int cent = Math.abs(_eurocent % 100);
		String centString = "" + cent;
		if (cent < 10) {
			centString = "0" + centString;
		}

		String minus = "";
		if (istNegativ())
		{
			minus = "-";
		}
		return minus + euro + "," + centString;
	}

	@Override
	public boolean equals(Object o)
	{
		return (o instanceof Geldbetrag) && equals((Geldbetrag) o);
	}

	private boolean equals(Geldbetrag andererGeldbetrag)
	{
		return (_eurocent == andererGeldbetrag._eurocent);
	}

	@Override
	public int hashCode()
	{
		return 10004 * _eurocent;
	}

}