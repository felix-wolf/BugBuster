package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;
import javax.swing.JOptionPane;

public class Geldbetrag {

	//HashMap, welche erzeugte Geldbeträge hält
	private static HashMap<Integer, Geldbetrag> GELDBETRAEGE = new HashMap<Integer, Geldbetrag>();
	//interne Darstellung eines Geldbetrags
	private final int _eurocent;

	/**
	 * Privater Konstruktor, fügt "sich selbst" in die HashMap ein
	 * 
	 * @param eurocent der Betrag des Geldbetrags
	 * @throws Exception 
	 * @require istGueltig(eurocent)
	 */
	private Geldbetrag(int eurocent) throws Exception {
		assert istGueltig(eurocent): "Vorbedingung verletzt: istGueltig(eurocent)";
		_eurocent = eurocent;

		GELDBETRAEGE.put(eurocent, this);
	}

	/**
	 * Liefert Geldbetrag aus einem long
	 * 
	 * @param eurocent die Höhe des Geldbetrags
	 * @return der Geldbetrag
	 * @require istGueltig(eurocent)
	 * @throws Exception wenn eurocent ungültig ist (außerhalb Wertebereich int)
	 */
	public static Geldbetrag get(long eurocent) throws Exception
	{

		assert istGueltig(eurocent) : "Vorbedingung verletzt: istGueltig(eurocent)";

		try
		{
			istGueltig(eurocent);
		}
		catch(Exception e)
		{	
			throw new Exception(e.getMessage());
		}
		int betrag = (int) eurocent;

		return (!GELDBETRAEGE.containsKey(betrag) ? new Geldbetrag(betrag) : GELDBETRAEGE.get(betrag));

	}

	/**
	 * Liefert Geldbetrag aus einem String
	 * 
	 * @param euroString die Höhe des Geldbetrags
	 * @return der Geldbetrag
	 * @require euroString != null
	 * @throws Exception wenn euroString ungültig ist
	 */
	public static Geldbetrag get(String euroString) {

		assert euroString != null : "Vorbedingung verletzt: euroString != null";

		try
		{
			istGueltig(euroString);
			return Geldbetrag.get(stringToEuroCent(euroString));

		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}

	/**
	 * addiert einen Geldbetrag zu einem anderen
	 * 
	 * @param geldbetrag der zu addierende Geldbetrag
	 * @return den neuen Geldbetrag
	 * @require geldbetrag != null
	 */
	public Geldbetrag addiere(Geldbetrag geldbetrag) {

		assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null";

		long betrag1 = this.getEurocent();
		long betrag2 = geldbetrag.getEurocent();
		long ergebnis = betrag1 + betrag2;

		try
		{
			istGueltig(ergebnis);
			return Geldbetrag.get(ergebnis);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		return null;
	}

	/**
	 * Subtrahiert einen Geldbetrag von einem anderen
	 * 
	 * @param geldbetrag der Geldbetrag, der abgezogen werden soll
	 * @return der neue Geldbetrag
	 * @require geldbetrag != null
	 */
	public Geldbetrag subtrahiere(Geldbetrag geldbetrag) {

		assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null";

		long betrag1 = getEurocent();
		long betrag2 = geldbetrag.getEurocent(); 
		long ergebnis = betrag1 - betrag2;

		try
		{
			istGueltig(ergebnis);
			return Geldbetrag.get(ergebnis);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		return null;
	}

	/**
	 * Multipliziert Geldbetrag mit einem int zu einem Geldbetrag
	 * @param zahl, mit der dieser Geldbetrag multipliziert wird
	 * @return der neue Geldbetrag
	 */
	public Geldbetrag multipliziere(int zahl) {

		long betrag1 = getEurocent(); 
		long ergebnis = betrag1 * zahl;
		try
		{
			istGueltig(ergebnis);
			return Geldbetrag.get(ergebnis);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		return null;	
	}

	/**
	 * Getter für Feld mit int Betrag
	 * @return den int-Wert des Geldbetrags
	 */
	private int getEurocent()
	{
		return _eurocent;
	}

	/**
	 * 
	 * @param EuroString
	 * @return
	 * 
	 */
	private static long stringToEuroCent(String euroString) {
		if (euroString.contains(","))
		{
			return Long.valueOf(euroString.replaceAll(",", "").toString());

		}
		return Long.valueOf(euroString) * 100;

	}


	/**
	 * Prüft, ob die Eingage in Interger gültig für Geldbetrag ist.
	 * 
	 * @param euroAnteil : Euro Anteil in int
	 * @param centAnteil : Cent Anteil in int
	 * 
	 * @return true, wenn es gültig ist
	 */
	public static boolean istGueltig (long eurocent) throws Exception
	{
		if (!(eurocent <= Integer.MAX_VALUE && eurocent >= Integer.MIN_VALUE)) 
		{
			throw new Exception("FEHLER! Betrag zu gross"); 
		}
		return true;
	}

	/**
	 * Prüfen, ob die Eingage in String gültig für Geldbetrag ist.
	 * 
	 * @param geldbetragInString : ein String beschreibt Geldbetrag z.B: -12,43
	 * 
	 * @return true, wenn es gültig ist
	 */
	public static void istGueltig(String geldbetragInString) throws Exception
	{
		if (!gueltigesStringformat(geldbetragInString) || !gueltigeKommaAnzahl(geldbetragInString)
				|| !gueltigeNachkommastellen(geldbetragInString))
		{
			throw new Exception("Fehler: String nicht im richtigen Format");
		}

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

		for (int i=anfangPositionZumParse; i<geldbetragInString.length(); i++)
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
	 * @return true, wenn das String maximal ein ',' oder Symbol hat
	 */
	private static boolean gueltigeKommaAnzahl (String geldbetragInString)
	{
		int AnzahlVorkommen = 0;

		for (int i=0; i<geldbetragInString.length(); i++)
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
	 * @param geldbetragInString
	 * @return True, wenn Nachkommabereich gültig ist
	 */
	private static boolean gueltigeNachkommastellen (String geldbetragInString)
	{
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


	public boolean istNegativ()
	{
		return _eurocent < 0;
	}

	/**
	 * Wandelt diesen Geldbetrag in einen Strin um
	 * 
	 * @return String des Geldbetrags
	 */

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