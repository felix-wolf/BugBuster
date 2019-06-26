package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Mit diesem Werkzeug wird die Barzahlung für die Tickets abgewickelt.
 * Dabei wird ein kleines UI-Fenster erzeugt, in dem der zu zahlende
 * Betrag angezeigt wird und der bezahlte Betrag von Kunden eingetragen werden
 * kann. Darauf hin wird nach drücken der Enter-Taste das Rückgeld berechnet 
 * und der OK-Button aktiviert, sollte der Kunde ausreichend Geld bezahlt haben.
 * 
 * @author BugBuster-Team
 * @version SoSe 2019
 */
public class BarZahlungsWerkzeug 
{
	//ob der ok-Button gedrückt wurde
	private boolean _okButtonGedrückt = false;
	//der zu zahlende Preis der Tickets
	private int _preis;
	//der eingegebene Betrag
	private int _betrag;
	//Die UI
	private BarZahlungsWerkzeugUI _barZahlungsWerkzeugUI;

	/**
	 * Initialisiert das BarZahlungsWerkzeug mit einem Preis
	 * 
	 * @param preis der zu zahlende Preis der Tickets
	 * 
	 * @require preis > 0
	 * @ensure preis != null
	 */
	public BarZahlungsWerkzeug(int preis)
	{
		assert preis > 0 : "Vorbedingung verletzt: preis > 0";

		_preis = preis;
		_barZahlungsWerkzeugUI = new BarZahlungsWerkzeugUI(_preis);
		registriereUIAktionen();

	}

	/**
	 * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
	 * Funktionalität: Beim drücken der Knöpfe wird das Fenster geschlossen,
	 * Es können nur Zahlen in das Textfield eingegeben werden, während der 
	 * Nutzer Zahlen einträgt ist der Ok-Button immer disabled, bis Enter
	 * bei einem gültigen Betrag gedrückt wurde
	 */
	private void registriereUIAktionen() 
	{
		_barZahlungsWerkzeugUI.getOkButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_okButtonGedrückt = true;
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

	/**
	 * Verarbeitet die Einzahlung, indem der OK-Button abhängig davon,
	 * ob genug Geld bezahlt wurde, freigeschalten/ausgeschalten wird
	 * und die Restbetragsanzeige aktualisiert wird
	 */
	private void reagiereAufEinzahlung()
	{
		_betrag = (int) _barZahlungsWerkzeugUI.getBezahlt();
		_barZahlungsWerkzeugUI.aktiviereOKButton(istBezahlenMoeglich());		
		aktualisiereRestbetragAnzeige(_betrag);
	}

	/**
	 * gibt zurück, ob der Restbetrag >= 0 ist, also genug Geld bezahlt wurde
	 * 
	 * @return ob Restbetrag >= 0 
	 */
	private boolean istBezahlenMoeglich() 
	{
		return berechneRestbetrag(_betrag) >= 0; 
	}

	/**
	 * aktualisiert die Restbetragsanzeige der UI
	 * 
	 * @param betrag der neue Restbetrag
	 */
	private void aktualisiereRestbetragAnzeige(int betrag) 
	{
		_barZahlungsWerkzeugUI.setzeRestlabel(berechneRestbetrag(betrag));
	}

	/**
	 * berechnet den neuen Restbetrag
	 * 
	 * @require betrag >= 0
	 * @param betrag der eingezahlte Betrag
	 * @return der neue Restbetrag
	 */
	private int berechneRestbetrag(int betrag) 
	{
		assert betrag >= 0 : "Vorbedingung verletzt: betrag >= 0";
		
		return betrag - _preis;
	}

	/**
	 * Gibt zurück, ob der OKButton in der UI gedrückt wurde,
	 * das Geld also bezahlt wurde
	 * 
	 * @return ob bezahlt wurde
	 */
	public boolean istBezahlt()
	{
		return _okButtonGedrückt;
	}
}
