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
	
	@Override
	public String toString() {
		String euroString = Integer.toString(_euroAnteil);
		String centString = Integer.toString(_centAnteil);
		if (_centAnteil < 10) {
			centString = "0" + centString;
		}
		return euroString + "," + centString;
	}
	
    @Override
    public boolean equals(Object o)
    {
        return (o instanceof Geldbetrag) && equals((Geldbetrag) o);
    }

    private boolean equals(Geldbetrag andererGeldbetrag)
    {
        return (_euroAnteil == andererGeldbetrag._euroAnteil) && (_centAnteil == andererGeldbetrag._centAnteil)
        		&& (_istNegativ == andererGeldbetrag._istNegativ);
    }

    @Override
    public int hashCode()
    {
        return !istNegativ() ? 10004 * _euroAnteil + _centAnteil : -1 * (10004 * _euroAnteil + _centAnteil) ;
    }
	
}
