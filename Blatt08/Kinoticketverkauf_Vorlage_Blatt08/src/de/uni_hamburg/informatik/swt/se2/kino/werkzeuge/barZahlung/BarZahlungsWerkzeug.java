package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

//TODO: Kommentare
public class BarZahlungsWerkzeug 
{
	private int _preis;
	//der eingegebene betrag
	private int _betrag;
	private BarZahlungsWerkzeugUI _barZahlungsWerkzeugUI;


	public BarZahlungsWerkzeug(int preis)
	{
		_preis = preis;
		_barZahlungsWerkzeugUI = new BarZahlungsWerkzeugUI(_preis);

		registriereUIAktionen();
		_barZahlungsWerkzeugUI.zeigeFenster();
	}


	private void registriereUIAktionen() 
	{
		_barZahlungsWerkzeugUI.getOkButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_barZahlungsWerkzeugUI.schliesseFenster();
			}
		});

		_barZahlungsWerkzeugUI.getAbbrechenButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_barZahlungsWerkzeugUI.schliesseFenster();
			}
		});
		
		
		_barZahlungsWerkzeugUI.getBezahltTextfield().addKeyListener(new KeyListener() {
						
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
				{
					e.consume();            				
				}	
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() != 10)
				{
					_barZahlungsWerkzeugUI.aktiviereOKButton(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		_barZahlungsWerkzeugUI.getBezahltTextfield().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				reagiereAufEinzahlung();
			}
			
		});
		
	}

	private void reagiereAufEinzahlung()
	{
		_betrag = (int) _barZahlungsWerkzeugUI.getBezahlt();
		_barZahlungsWerkzeugUI.aktiviereOKButton(istBezahlenMoeglich());		
		aktualisiereRestbetragAnzeige(_betrag);
	}


	private boolean istBezahlenMoeglich() 
	{
		return berechneRestbetrag(_betrag) >= 0; 
	}


	private void aktualisiereRestbetragAnzeige(int betrag) 
	{
		_barZahlungsWerkzeugUI.setzeRestlabel(berechneRestbetrag(betrag));
	}


	private int berechneRestbetrag(int betrag) 
	{
		return betrag - _preis;
	}


	public boolean istBezahlt()
	{
		return _barZahlungsWerkzeugUI.okButtonGedrückt();
	}
}
