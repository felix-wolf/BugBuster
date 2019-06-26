package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class BarZahlungsWerkzeugUI {

	//UI-Elemente
	private JDialog _dialog;
	private JButton _okButton;
	private JButton _abbrechenButton;
	private JLabel _betragTextLabel;
	private JLabel _betragLabel;
	private JLabel _restTextLabel;
	private JLabel _restLabel;
	private JLabel _bezahltLabel;
	private JFormattedTextField _bezahltTextfield ;

	/**
	 * Initialisiert die BarZahlungsoberfläche
	 * 
	 * @param betrag der zu zahlende Betrag
	 */
	public BarZahlungsWerkzeugUI(int betrag) {
		_dialog = new JDialog(_dialog, true);
		_dialog.setTitle("Barzahlung");
		_dialog.setLayout(new GridLayout(4, 2, 20, 20));
		_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		createFields(betrag);
		createButtons();		
		_dialog.pack();
	}

	/**
	 * Gibt den OKButton zurück
	 * 
	 * @return OKButton
	 */
	public JButton getOkButton() {
		return _okButton;
	}

	/**
	 * Gibt den AbbrechenButton zurück
	 * 
	 * @return AbbrechenButton
	 */
	public JButton getAbbrechenButton() {
		return _abbrechenButton;
	}

	/**
	 * Gibt das Textfeld zurück
	 * 
	 * @return _bezahltTextfield
	 */
	public JFormattedTextField getBezahltTextfield() {
		return _bezahltTextfield;
	}

	/**
	 * Gibt das RestLabel zurück
	 * 
	 * @return _restLabel
	 */
	public JLabel getRestLabel() {
		return _restLabel;
	}

	public void setzeRestlabel(int betrag) {
		_restLabel.setText(betrag + " Eurocent");
	}

	/**
	 * Setzt den OKButton auf true/false
	 */
	public void aktiviereOKButton(Boolean bool) {
		_okButton.setEnabled(bool);
	}

	/**
	 * zeigt die BarZahlungsoberfläche an
	 */
	public void zeigeFenster() {
		_dialog.setVisible(true);
	}

	/**
	 * schließt die BarZahlungsoberfläche
	 */
	public void schliesseFenster() {
		_dialog.dispose();
	}

	/**
	 * Erzeugt alle Label und setzt die Texte.
	 * Fügt die Labels zum Dialog hinzu
	 * 
	 * @param betrag der zu zahlende Betrag
	 */
	private void createFields(int betrag) {
		_betragTextLabel = new	JLabel("Gesamtbetrag:");
		_betragLabel = new JLabel();

		_restTextLabel = new JLabel("Restbetrag:");
		_restLabel = new JLabel();
		_restLabel.setText("0");

		_bezahltLabel = new JLabel("Bezahlt:");
		_bezahltTextfield = new JFormattedTextField(new DecimalFormat());

		_dialog.add(_betragTextLabel);
		_dialog.add(_betragLabel);
		_dialog.add(_bezahltLabel);
		_dialog.add(_bezahltTextfield);
		_dialog.add(_restTextLabel);
		_dialog.add(_restLabel);
		_betragLabel.setText(String.valueOf(betrag));
	}

	/**
	 * erzeugt die Buttons und setzt die Texte
	 * Fügt die Buttons zum Dialog hinzu
	 */
	private void createButtons() {
		_okButton = new JButton("OK");
		_okButton.setEnabled(false);
		_abbrechenButton = new JButton("Abbrechen");
		_dialog.add(_abbrechenButton);
		_dialog.add(_okButton);

	}

	/**
	 * Gibt den Inhalt des Textfields zurück
	 * Wenn es null ist, gibt 0 zurück
	 * 
	 * @return Inhalt des Textfields oder 0
	 * 
	 */
	public long getBezahlt() {
		return _bezahltTextfield.getValue() != null ? (long) _bezahltTextfield.getValue() : 0;

	}
}