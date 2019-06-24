package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent; 
//import java.awt.event.KeyListener; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

//TODO: Kommentare
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
		_bezahlStatus = false;
		
		_barZahlungsWerkzeugUI = new BarZahlungsWerkzeugUI(_preis);
		
		//gehört in UI, ist schon in UI?
		//_barZahlungsWerkzeugUI.getBetragLabel().setText(
		//		_preis + " Eurocent");	 
		
		//gehört in UI. ist schon?
		//_barZahlungsWerkzeugUI.getOkButton().setEnabled(false);
		
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
//		System.out.println("test");
//		_barZahlungsWerkzeugUI.getBezahltTextfield().addKeyListener(new KeyListener() {
//		
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				System.out.println("test");
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					System.out.println("test");
//				}
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				System.out.println("test");				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println("test");				
//			}
//		});
		_barZahlungsWerkzeugUI.getBezahltTextfield().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					reagiereAufEinzahlung();
					System.out.println("test");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("test");
				}
			}
			
		});
		
//		_barZahlungsWerkzeugUI.getBezahltTextfield().addKeyListener(new KeyListener()
//                {
//					@Override
//					public void keyPressed(KeyEvent e) {
//						
//					}
//
//					@Override
//					public void keyReleased(KeyEvent e) {
//						
//					}
//
//					@Override
//					public void keyTyped(KeyEvent e) {
//						
////						reagiereAufEinzahlung(e);
//					}
//                });
	}
	
	
	private void reagiereAufEinzahlung() throws Exception
	{
		//TODO: getKeyCode ersetzen durch Textfeldinhalt
		//Bei formattedTextfield: getValue()!!
		
		int inhalt = (int)_barZahlungsWerkzeugUI.getBezahlt();
		
		try {
		    _betrag = inhalt;
		  } catch (NumberFormatException e) {
			  JOptionPane.showMessageDialog(null, "Digga, gib die Zahl vernünftig ein...");
			throw new Exception("Digga gib mal Zahl vern�nftig ein...");
		  }
		

		//TODO Hierfür funktion in UI!
		_barZahlungsWerkzeugUI.getOkButton().setEnabled(istBezahlenMoeglich());
		
		aktualisiereRestbetragAnzeige(_betrag);
	}
	
	
	private boolean istBezahlenMoeglich() 
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
	
	
	private void aktualisiereRestbetragAnzeige(int betrag) 
	{
		_barZahlungsWerkzeugUI.getRestLabel().setText(
				berechneRestbetrag(betrag) + " Eurocent");
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
