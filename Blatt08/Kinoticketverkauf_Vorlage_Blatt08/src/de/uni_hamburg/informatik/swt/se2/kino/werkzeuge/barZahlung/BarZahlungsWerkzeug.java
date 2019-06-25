package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		_barZahlungsWerkzeugUI.getBetragLabel().setText(
				_preis + " Eurocent");		
//		_barZahlungsWerkzeugUI.getOkButton().setEnabled(false);
		
		registriereUIAktionen();
		_barZahlungsWerkzeugUI.getDialog().setVisible(true);
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
	}
	
	private void reagiereAufEinzahlung() throws Exception
	{
		//TODO: getKeyCode ersetzen durch Textfeldinhalt
		
		String inhalt = _barZahlungsWerkzeugUI.getBezahltTextfield().getText();
		
		try {
		    _betrag = Integer.parseInt(inhalt);
		  } catch (NumberFormatException e) {
			  JOptionPane.showMessageDialog(null, "Digga, gib die Zahl vernünftig ein...");
			throw new Exception("Digga gib mal Zahl vern�nftig ein...");
		  }

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
