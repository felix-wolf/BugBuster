package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barZahlung;

import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BarZahlungsWerkzeugUI {

	private JDialog _dialog;
	private GridLayout _gridlayout;
	private JFrame _hauptfenster;
	
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
	//	_hauptfenster = new JFrame();
	//	_dialog = new JDialog(_hauptfenster, true);
		_dialog = new JDialog();
		_dialog.setModal(true);
		_dialog.setTitle("Barzahlung");
		_dialog.setLayout(new GridLayout(3, 2, 20, 20));
		
		createButtons();
		createFields(betrag);
		
		_dialog.pack();
		
	}

	private void createFields(int betrag) {
		_betragTextLabel = new	JLabel("Gesamtbetrag:");
		_betragLabel = new JLabel();
		
		_betragLabel.setText(String.valueOf(betrag));
		
		_restTextLabel = new JLabel("Restbetrag:");
		_restLabel = new JLabel();
		_restLabel.setText("0");
		
		_bezahltLabel = new JLabel("Bezahlt:");
		_bezahltTextfield = new JFormattedTextField();
		
		_dialog.add(_bezahltTextfield, 0,0);
		_dialog.add(_bezahltTextfield, 0,1);
		_dialog.add(_betragTextLabel, 1,0);
		_dialog.add(_betragLabel, 1,1);
		_dialog.add(_restTextLabel,2,0);
		_dialog.add(_restLabel,2,1);
		
	}

	private void createButtons() {
		_okButton = new JButton("OK");
		_abbrechenButton = new JButton("Abbrechen");
		
		_dialog.add(_okButton, 1, 0);
		_dialog.add(_abbrechenButton, 1, 1);
		
		_okButton.addActionListener(event -> {
			_okGeklickt = true;
			_dialog.setVisible(false);
	});

	}
}