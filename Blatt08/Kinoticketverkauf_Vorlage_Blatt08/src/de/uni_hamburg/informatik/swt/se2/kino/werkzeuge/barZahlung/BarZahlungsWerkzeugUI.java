package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		_dialog = new JDialog();
		_dialog.setModal(true);
		_dialog.setTitle("Barzahlung");
		_dialog.setLayout(new GridLayout(4, 2, 20, 20));
		_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		createFields(betrag);
		createButtons();		
		_dialog.pack();
		_dialog.setVisible(true);
	}

	public JLabel getBetragLabel() {
		return _betragLabel;
	}
	
	public JButton getOkButton() {
		return _okButton;
	}
	
	public JButton getAbbrechenButton() {
		return _abbrechenButton;
	}
	
	public JDialog getDialog() {
		return _dialog;
	}
	
	public JTextField getBezahltTextfield() {
		return _bezahltTextfield;
	}
	
	public JLabel getRestLabel() {
		return _restLabel;
	}

	private void createFields(int betrag) {
		_betragTextLabel = new	JLabel("Gesamtbetrag:");
		_betragLabel = new JLabel();
		
		_restTextLabel = new JLabel("Restbetrag:");
		_restLabel = new JLabel();
		_restLabel.setText("0");
		
		_bezahltLabel = new JLabel("Bezahlt:");
		_bezahltTextfield = new JFormattedTextField();
		
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
//	private void createFields(int betrag) {
//	_betragTextLabel = new	JLabel("Gesamtbetrag:");
//	_betragLabel = new JLabel();
//	
//	_betragLabel.setText(String.valueOf(betrag));
//	
//	_restTextLabel = new JLabel("Restbetrag:");
//	_restLabel = new JLabel();
//	_restLabel.setText("0");
//	
//	_bezahltLabel = new JLabel("Bezahlt:");
//	_bezahltTextfield = new JFormattedTextField();
//	
//	_dialog.add(_bezahltTextfield, 0,0);
//	_dialog.add(_restLabel,2,1);
//	_dialog.add(_bezahltTextfield, 0,1);
//	_dialog.add(_betragTextLabel, 1,0);
//	_dialog.add(_betragLabel, 1,1);
//	_dialog.add(_restTextLabel,2,0);
//	
//}
}
