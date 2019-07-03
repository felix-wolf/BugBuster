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
	
}
