package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class BarZahlungsWerkzeugUI {
	private JDialog _dialog;
	private JButton _okButton;
	private JButton _abbrechenButton;
	private JLabel _betragTextLabel;
	private JLabel _betragLabel;
	private JLabel _restTextLabel;
	private JLabel _restLabel;
	private JLabel _bezahltLabel;
	private JFormattedTextField _bezahltTextfield ;
	
	private boolean _okGeklickt = false;

	public BarZahlungsWerkzeugUI(int betrag) {
		_dialog = new JDialog(_dialog, true);
		_dialog.setTitle("Barzahlung");
		_dialog.setLayout(new GridLayout(4, 2, 20, 20));
		_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		createFields(betrag);
		createButtons();		
		_dialog.pack();
	}

	public JButton getOkButton() {
		return _okButton;
	}
	
	public JButton getAbbrechenButton() {
		return _abbrechenButton;
	}

	public JFormattedTextField getBezahltTextfield() {
		return _bezahltTextfield;
	}

	public JLabel getRestLabel() {
		return _restLabel;
	}
	
	public void setzeRestlabel(int betrag) {
		_restLabel.setText(betrag + " Eurocent");
	}
	
	public void aktiviereOKButton(Boolean bool) {
		_okButton.setEnabled(bool);
	}
	
	public void zeigeFenster() {
		_dialog.setVisible(true);
	}
	
	public void schliesseFenster() {
		_dialog.dispose();
	}

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

	private void createButtons() {
		_okButton = new JButton("OK");
		_okButton.setEnabled(false);
		_abbrechenButton = new JButton("Abbrechen");
		_abbrechenButton.addActionListener(event -> _dialog.dispose());
		_dialog.add(_abbrechenButton);
		_dialog.add(_okButton);

		_okButton.addActionListener(event -> {
			_okGeklickt = true;
			_dialog.setVisible(false);

		});
	}

	public boolean okButtonGedrückt() {
		return _okGeklickt;
	}
	
	public long getBezahlt() {
		return _bezahltTextfield.getValue() != null ? (long) _bezahltTextfield.getValue() : 0;
				
}
}