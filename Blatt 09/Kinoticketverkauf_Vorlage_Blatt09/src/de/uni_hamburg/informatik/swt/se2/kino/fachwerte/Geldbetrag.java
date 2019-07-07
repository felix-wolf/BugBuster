package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;

public class Geldbetrag {

	private static HashMap<Integer, Geldbetrag> GELDBETRAEGE;
	private final boolean _istNegativ;
	private final int _centAnteil;
	private final int _euroAnteil;
	
	/**
	 * wird von get aufgerufen
	 * 
	 * @param euroAnteil
	 * @param centAnteil
	 * @param istNegativ
	 */
	private Geldbetrag(int euroAnteil, int centAnteil, boolean istNegativ) {
		_euroAnteil = euroAnteil;
		_centAnteil = centAnteil;
		_istNegativ = istNegativ;
	}
	
	/**
	 * Prüfen, ob die Eingage in Interger gültig für Geldbetrag ist.
	 * 
	 * @param euroAnteil : Euro Anteil in int
	 * @param centAnteil : Cent Anteil in int
	 * 
	 * @return true, wenn es gültig ist
	 */
	public static boolean istGueltig (int euroAnteil, int centAnteil)
	{
	    // Nur euroAnteil darf negativ sein
	    if (centAnteil < 0) return false;
	    
	    //höchste centAnteil ist 99 cent
	    if (centAnteil > 99) return false;
	    
	    return true;
	}
	
	
	   /**
     * Prüfen, ob die Eingage in String gültig für Geldbetrag ist.
     * 
     * @param geldbetragInString : ein String beschreibt Geldbetrag z.B: -12,43 oder 23.45
     * 
     * @return true, wenn es gültig ist
     */
	public static boolean istGueltig (String geldbetragInString)
	{
	    if (pruefeObStringInFormat(geldbetragInString) && pruefeAnzahlKommaInString(geldbetragInString))
	    {
	        // Hier wird String zu 2 Int konvertiert, und dann ruft Methode istGueltig zum prüfen, ob 2 Int in Array rich für EuroAnteil und CentAnteil stehen
	        int[] ArrayEuroundCentBetrag = StringTo2Int(geldbetragInString);
	        if ( istGueltig(ArrayEuroundCentBetrag[0], ArrayEuroundCentBetrag[1]))
	        {
	            return  true;
	        }
	        else return false;
	    }
	    else return false;
	}
	
	/**
	 * Prüfe, ob das eingegebene String in Format:  wenn es '-' gibt, dann muss '-' am Anfang steht. Die nächsten Symbole sind aus Nummer oder ',' oder '.'
	 * @return true, wenn es den Format hat.
	 */
	private static boolean pruefeObStringInFormat (String geldbetragInString)
	{
	    boolean returnWert = true;
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
            
            if ( !istCharEinNummer && charInString != ',' && charInString != '.') 
            {
                return false;
            }
        }
        return returnWert;
	    
	}
	
	   /**
     * Prüfe, ob das eingegebene String maximal ein ',' oder '.' Symbol hat
     * @return true, wenn das String maximal ein ',' oder '.' Symbol hat
     */
    private static boolean pruefeAnzahlKommaInString (String geldbetragInString)
    {
        int AnzahlVorkommen = 0;
        
        for (int i=0; i<geldbetragInString.length(); i++)
        {
            char charInString = geldbetragInString.charAt(i);
            
            if (charInString == ',' || charInString == '.') 
            {
                AnzahlVorkommen++;
            }
        }
        
        if (AnzahlVorkommen > 1) 
            {
                return false;
            }
        else 
            {
                return true;
            }
        
    }
    
  /**
  * Konvertiere das gegebene String zu 2 int
  * 
  * @return Ein Array von 2 int
  */
 private static int[] StringTo2Int (String geldbetragInString)
 {
     boolean negativ = false;
     int[] euroUndCentAnteil = new int[2];
     String euroAnteil = new String();
     String centAnteil = new String();
     
     if (geldbetragInString.charAt(0) == '-')
     {
         negativ = true;
     }
     
     int EsteStelleZurParse = 0;
     if (negativ)
     {
         EsteStelleZurParse = 1;
     }
     
     boolean kommaHatEntscheint = false;
     for (int i= EsteStelleZurParse ; i < geldbetragInString.length() ; i++)
     {
         char aktuelleChar = geldbetragInString.charAt(i);
         if (aktuelleChar == ',' || aktuelleChar == '.')
         {
             kommaHatEntscheint = true;
         }
         
         if (Character.isDigit(aktuelleChar) && !kommaHatEntscheint)
         {
             euroAnteil += aktuelleChar;
         }
         
         if (Character.isDigit(aktuelleChar) && kommaHatEntscheint)
         {
             centAnteil += aktuelleChar;
         }
     }
     
     if (negativ) 
         {
         euroUndCentAnteil[0] = 0 - (Integer.parseInt(euroAnteil));
         }
     else 
     {
         euroUndCentAnteil[0] = Integer.parseInt(euroAnteil);
     }
     
     euroUndCentAnteil[1] = Integer.parseInt(centAnteil);
     
     return euroUndCentAnteil;
     
 }
 
 
 /**
* Gib zurück, ob der Geldbetrag negativ ist
* @return true, der Geldbetrag negativ ist
*/
public boolean istNegativ ()
{
  return _istNegativ;
   
}

/**
* Konvertiert Geldbetrag zu int
* @return euroCentInInt
*/
private int toEuroCent ()
{
    int euroCentInInt;
    if (_istNegativ)
    {
        return 0 - (_euroAnteil*100 + _centAnteil);
    }
    else return _euroAnteil*100 + _centAnteil;
  
}

    
    
}
