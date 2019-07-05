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
	
	public static Geldbetrag get(int euro, int cent) {
		
		int eurocentKey = concatEuroCent(euro, cent);
		
		Geldbetrag geld; 
		
		if(!GELDBETRAEGE.containsKey(eurocentKey)) {
			geld = new Geldbetrag (euro, cent, false);
		}
		else {
			geld = GELDBETRAEGE.get(eurocentKey);
		}
		
		return geld;
	}
	
	public static Geldbetrag get(int eurocent) {
		
		int eurocentKey = eurocent;
		
		Geldbetrag geld; 
		
		int euro = eurocent/100;
		int cent = eurocent-(eurocent/100);
		
		if(!GELDBETRAEGE.containsKey(eurocentKey)) {
			geld = new Geldbetrag (euro, cent, false);
		}
		else {
			geld = GELDBETRAEGE.get(eurocentKey);	
		}
		
		return geld;
	}
	
	public static Geldbetrag get(String euroString) {
		int betrag = stringToEuroCent(euroString);
		return Geldbetrag.get(betrag);
		
	}
	
	public Geldbetrag addiere(Geldbetrag geldbetrag) {
		int betrag1 = this.toEuroCent();
		int betrag2 = geldbetrag.toEuroCent();
		int ergebnis = betrag1 + betrag2;
		return Geldbetrag.get(ergebnis);
	}
	
	public Geldbetrag substrahiere(Geldbetrag geldbetrag) {
		int betrag1 = this.toEuroCent();
		int betrag2 = geldbetrag.toEuroCent(); 
		int ergebnis = betrag1 - betrag2;
		return Geldbetrag.get(ergebnis);
	}
	
	public Geldbetrag multipliziere(int zahl) {
		int betrag1 = this.toEuroCent(); 
		int ergebnis = betrag1*zahl;
		return Geldbetrag.get(ergebnis);	}
	
	private static int concatEuroCent(int euro, int cent)
	{
		return euro*100+cent;
	}
	
	private int toEuroCent()
	{
		return _euroAnteil*100+_centAnteil;
	}
	/**
	 * 
	 * @param EuroString
	 * @return
	 * 
	 * @require String euroString hat Form EE,CC
	 */
	
	private static int stringToEuroCent(String euroString) {
		System.out.println(Integer.valueOf(euroString.replaceAll(",", "")));
		return Integer.valueOf(euroString.replaceAll(",", "").toString());
	}
	
}
