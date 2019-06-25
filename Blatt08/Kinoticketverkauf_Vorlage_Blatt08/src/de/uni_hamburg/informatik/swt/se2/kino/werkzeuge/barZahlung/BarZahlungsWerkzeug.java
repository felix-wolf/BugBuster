package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

public class BarZahlungsWerkzeug extends ObservableSubwerkzeug {
	
	private BarZahlungsWerkzeugUI _barZahlungsWerkzeugUI;
	private int _preis;
	private boolean _istBezahl;
	
	/**
	 * Kontrustor
	 */
	public BarZahlungsWerkzeug(int preis)
	{
		_preis = preis;
		_barZahlungsWerkzeugUI = new BarZahlungsWerkzeugUI(_preis);
		
		registriereUIAktionen();
	}
	
	/**
	 * @param betrag: der eingegebene Betrag auf dem Textfield
	 * 
	 */
	public void aktualisiereRestbetragAnzeige(int betrag)
	{
		String restBetrag = Integer.toString(_preis - betrag);
		_barZahlungsWerkzeugUI.get_restLabel().setText(restBetrag);
	}
	
	
	/**
	 * Hier wird die Aktionen von UI Komponenten angemeldet
	 */
	public void registriereUIAktionen()
	{
	    _barZahlungsWerkzeugUI.get_bezahltTextfield().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String eingegebeneBetragInString = _barZahlungsWerkzeugUI.get_bezahltTextfield().getText();
            int eingegebeneBetrag = Integer.parseInt(eingegebeneBetragInString);
            aktualisiereRestbetragAnzeige(eingegebeneBetrag);
            
                
            }
        });

	    _barZahlungsWerkzeugUI.get_okButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (istBezahlenMoeglich())
                {
                    _istBezahl = true;
                    informiereUeberAenderung();
                }
                _barZahlungsWerkzeugUI.get_dialog().dispose();
                
            }
        });
	    
	    _barZahlungsWerkzeugUI.get_abbrechenButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _barZahlungsWerkzeugUI.get_dialog().dispose();
            }
        });
	}
	
	/**
	 * Prüfen, ob der eingegebene Betrag genug ist
	 * @return true, wenn es genug ist
	 */
	public boolean istBezahlenMoeglich()
	{
	    String eingegebeneBetragInString = _barZahlungsWerkzeugUI.get_bezahltTextfield().getText();
        int eingegebeneBetrag = Integer.parseInt(eingegebeneBetragInString);
		if (eingegebeneBetrag >= _preis)
		{
		    return true;
		}
		else return false;
	}
	
	
	//Diese Methode wird nicht benutzt
	public boolean istBezahlt()
	{
		return _istBezahl;
	}
	//Diese Methode wird nicht benutzt
	   public void reagiereAufEinzahlung(int betrag)
	    {
	        
	    }
	   
	 //Diese Methode wird nicht benutzt
	    public void berechneRestbetrag(int betrag)
	    {
	        
	    }
	

}
