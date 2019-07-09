package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;

import javax.swing.JOptionPane;

public class Geldbetrag {

	private static HashMap<Integer, Geldbetrag> GELDBETRAEGE = new HashMap<Integer, Geldbetrag>();
	private final int _eurocent;
	
	/**
	 * wird von get aufgerufen
	 * 
	 * @param euroAnteil
	 * @param centAnteil
	 * @param istNegativ
	 */
	private Geldbetrag(int eurocent) {
		_eurocent = eurocent;
		
		GELDBETRAEGE.put(eurocent, this);
	}
	
	public static Geldbetrag get(long eurocent) throws Exception
	{
	    
		try
		{
			istGueltig(eurocent);
		}
		catch(Exception e)
		{	
		    throw new Exception(e.getMessage());
		}
		
		int betrag = (int) eurocent;
		if(!GELDBETRAEGE.containsKey(betrag)) {
			return new Geldbetrag(betrag);
		}
		else
		{
		 return GELDBETRAEGE.get(betrag);	
		}
	}
	
	public static Geldbetrag get(String euroString) {
		
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
	
	public Geldbetrag addiere(Geldbetrag geldbetrag) {
		
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
	
	public Geldbetrag subtrahiere(Geldbetrag geldbetrag) {
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
	
	public Geldbetrag multipliziere(int zahl) {
		long betrag1 = getEurocent(); 
		long ergebnis = betrag1*zahl;
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
	 * Prüfen, ob die Eingage in Interger gültig für Geldbetrag ist.
	 * 
	 * @param euroAnteil : Euro Anteil in int
	 * @param centAnteil : Cent Anteil in int
	 * 
	 * @return true, wenn es gültig ist
	 */
	public static void istGueltig (long eurocent) throws Exception
	{
		if (!(eurocent <= Integer.MAX_VALUE && eurocent >= Integer.MIN_VALUE)) 
		{
			throw new Exception("FEHLER! Betrag zu gross");   
		}
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
	    if (!pruefeObStringInFormat(geldbetragInString) && pruefeAnzahlKommaInString(geldbetragInString))
	    {
	        throw new Exception("Fehler: String nicht im richtigen Format");
	    }
	    	
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
            
            if ( !istCharEinNummer && charInString != ',') 
            {
                return false;
            }
        }
        return returnWert;
	    
	}
	
	   /**
     * Prüfe, ob das eingegebene String maximal ein ',' Symbol hat
     * @return true, wenn das String maximal ein ',' oder Symbol hat
     */
    private static boolean pruefeAnzahlKommaInString (String geldbetragInString)
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
