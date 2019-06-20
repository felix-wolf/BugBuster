package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 

public class BarZahlungsWerkzeug 
{
	private int _preis;
	//der eingegebene betrag
	private int _betrag;
	private boolean _bezahlStatus;
	private BarZahlungsWerkzeugUI _barZahlungsWerkzeugUI;

	
	public BarZahlungsWerkzeug(int preis)
	{
		_preis = preis;
		_betrag = 0;
		_bezahlStatus = false;
		
		_barZahlungsWerkzeugUI = new BarZahlungsWerkzeugUI();
		_barZahlungsWerkzeugUI.getBetragLabel().setText(
				_preis + " Eurocent");
		
		//evtl ok button disablen, weiß nicht ob in der ui zuerst enabled oder disabled
		
		registriereUIAktionen();
	}


	private void registriereUIAktionen() 
	{
		_barZahlungsWerkzeugUI.getOkButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                   	_bezahlStatus = true;
                   	_barZahlungsWerkzeugUI.getDialog().dispose();
            }
        });
		
		_barZahlungsWerkzeugUI.getAbbrechenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            		_barZahlungsWerkzeugUI.getDialog().dispose();
            }
        });
		
		_barZahlungsWerkzeugUI.getBezahltTextfield().addKeyListener(new KeyListener()
                {
					@Override
					public void keyPressed(KeyEvent e) {
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						
					}

					@Override
					public void keyTyped(KeyEvent e) {
						reagiereAufEinzahlung(e);
					}
                });
	}
	
	//wird nicht benutzt?
	protected boolean istBezahlenMoeglich() 
	{
		if(berechneRestbetrag(_betrag) >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	protected void reagiereAufEinzahlung(KeyEvent e) 
	{
		if(e.getKeyCode() >= 48 && e.getKeyCode() <= 57)
		{
			_betrag = _betrag * 10 + (e.getKeyCode() - 48);
		}
		else if(e.getKeyCode() == 8)
		{
			_betrag = _betrag / 10;
		}

		_barZahlungsWerkzeugUI.getOkButton().setEnabled(berechneRestbetrag(_betrag) >= 0);
		
		aktualisiereRestbetragAnzeige(_betrag);
	}
	
	
	private void aktualisiereRestbetragAnzeige(int betrag) 
	{
		_barZahlungsWerkzeugUI.getRestLabel().setText(
				berechneRestbetrag(betrag) + " Eurocent");
	}
	
	
	private int berechneRestbetrag(int betrag) 
	{
		return betrag - _preis;
	}
	
	
	private boolean istBezahlt()
	{
		return _bezahlStatus;
	}
}
