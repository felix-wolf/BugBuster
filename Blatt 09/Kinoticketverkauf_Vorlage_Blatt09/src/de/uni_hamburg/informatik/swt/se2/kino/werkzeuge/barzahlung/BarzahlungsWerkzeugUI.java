package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * TODO für Blatt 8: Löschen
 * 
 * Klasse BarzahlungsWerkzeugUI ist die GUI für die Barbezahlung. Sie besteht
 * aus drei großen Eingabefeldern, von denen das mittlere zur Eingabe gedacht
 * ist.
 * 
 * @author SE2-Team
 * @version SoSe 2016
 */
class BarzahlungsWerkzeugUI
{
    private static final String OK_BUTTON_TEXT = "Geld erhalten";
    private static final String BARZAHLUNG_TITEL = "Barzahlung";
    private static final String NOCH_ZU_ZAHLEN = "Noch zu zahlen";
    private static final String RUECKGELD = "Rückgeld";

    private static final Color SCHRIFTFARBE_NORMAL = new Color(95, 247, 0);
    private static final Color SCHRIFTFARBE_FEHLER = new Color(255, 148, 148);
    private static final Color HINTERGRUNDFARBE = new Color(30, 30, 30);
    private static final Font SCHRIFTART_GROSS = new Font("Monospaced",
            Font.BOLD, 28);
    private static final Font SCHRIFTART_KLEIN = new Font("Monospaced",
            Font.BOLD, 22);
    private static final int TEXTFELDBREITE = 12;

    private JDialog _dialog;
    private JTextField _preisTextfield;
    private JTextField _gezahltTextfield;
    private JTextField _restbetragTextfield;
    private JButton _geldErhaltenButton;
    private JButton _abbrechenButton;
    private JLabel _restBetragPanel;

    /**
     * Konstruktor der Klasse BarzahlungsWerkzeugUI.java
     */
    public BarzahlungsWerkzeugUI()
    {
        initDialog();
    }

    /**
     * Zeigt das Fenster an
     */
    public void zeigeAn()
    {
        _dialog.setLocationRelativeTo(null);
        _dialog.setVisible(true);
    }

    /**
     * Verbirgt das Fenster.
     */
    public void verberge()
    {
        _dialog.setVisible(false);
    }

    /**
     * Markiert das Gezahlt-Textfeld als ausreichend (grün) oder als
     * nichtausrechend (rot)
     * 
     * @param ausreichend true, wenn es als ausreichend markiert werden soll,
     *            sonst false
     */
    public void markiereGezahltTextfield(boolean ausreichend)
    {
        markiereTextfield(_gezahltTextfield, ausreichend);
    }

    /**
     * Markiert das Restbetrag-Textfeld als Rückgeld-Feld (grün), wenn
     * ausreichend gezahlt wurde, oder als "Noch zu zahlen"-Feld (rot)
     * 
     * @param ausreichend true, wenn es als fehlerhaft markiert werden soll,
     *            sonst false
     */
    public void markiereRestbetragTextfield(boolean ausreichend)
    {
        if (ausreichend)
        {
            _restBetragPanel.setText(RUECKGELD);
        }
        else
        {
            _restBetragPanel.setText(NOCH_ZU_ZAHLEN);
        }
        markiereTextfield(_restbetragTextfield, ausreichend);
    }

    /**
     * Markiert ein spezifiziertes Textfield.
     * 
     * @param textfield ein zu markierendes Textfield
     * @param normal true, wenn ein normaler Textfeldstatus vorliegt, sonst
     *            false.
     */
    private void markiereTextfield(JTextField textfield, boolean normal)
    {
        if (normal)
        {
            textfield.setForeground(BarzahlungsWerkzeugUI.SCHRIFTFARBE_NORMAL);
        }
        else
        {
            textfield.setForeground(BarzahlungsWerkzeugUI.SCHRIFTFARBE_FEHLER);
        }
    }

    /**
     * @return Textfeld mit dem gezahlten Betrag
     */
    public JTextField getGezahltTextfield()
    {
        return _gezahltTextfield;
    }

    /**
     * @return Textfeld für den Preis
     */
    public JTextField getPreisTextfield()
    {
        return _preisTextfield;
    }

    /**
     * @return Textfeld für den Restbetrag-Text
     */
    public JTextField getRestbetragTextfield()
    {
        return _restbetragTextfield;
    }

    /**
     * @return GeldErhalten-Button
     */
    public JButton getGeldErhaltenButton()
    {
        return _geldErhaltenButton;
    }

    /**
     * @return Abbrechen-Button
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    /**
     * @return das Dialogfenster, um zum Beispiel einen WindowListener daran zu
     *         registrieren.
     */
    public JDialog getDialog()
    {
        return _dialog;
    }

    private void initDialog()
    {
        _dialog = new JDialog((Frame) null, BARZAHLUNG_TITEL);
        _dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        _dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        _dialog.setLayout(new BorderLayout());
        _dialog.add(erstelleBetragsPanel(), BorderLayout.CENTER);
        _dialog.add(erstelleButtonPanel(), BorderLayout.SOUTH);

        _dialog.pack();
        _dialog.setResizable(false);
    }

    /**
     * Erstellt das ButtonPanel mit Ok und Abbrechen
     * 
     * @return das ButtonPanel
     */
    private JPanel erstelleButtonPanel()
    {
        JPanel rueckgabePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        _geldErhaltenButton = new JButton(OK_BUTTON_TEXT);
        rueckgabePanel.add(_geldErhaltenButton);
        _abbrechenButton = new JButton("Abbrechen");
        rueckgabePanel.add(_abbrechenButton);
        return rueckgabePanel;
    }

    /**
     * Erstellt das Hauptpanel mit den drei Anzeigen für die Beträge und
     * beschriftet diese sinnvoll.
     * 
     * @return das Panel mit den Anzeigen für die Beträge
     */
    private JPanel erstelleBetragsPanel()
    {
        JPanel betraege = new JPanel();
        betraege.setLayout(new BoxLayout(betraege, BoxLayout.Y_AXIS));
        initPreisTextfeld();
        betraege.add(erstelleLayoutPanel("Preis", _preisTextfield));
        initGezahltTextfield();
        betraege.add(erstelleLayoutPanel("Gezahlt", _gezahltTextfield));
        initRestbetragTextfield();
        betraege.add(erstelleRestbetragPanel());

        return betraege;
    }

    private void initRestbetragTextfield()
    {
        _restbetragTextfield = new JTextField(TEXTFELDBREITE);
        _restbetragTextfield.setHorizontalAlignment(JTextField.RIGHT);
        _restbetragTextfield.setBackground(HINTERGRUNDFARBE);
        _restbetragTextfield.setForeground(SCHRIFTFARBE_NORMAL);
        _restbetragTextfield.setEditable(false);
        _restbetragTextfield.setFocusable(false);
        _restbetragTextfield.setFont(SCHRIFTART_KLEIN);
    }

    private void initGezahltTextfield()
    {
        _gezahltTextfield = new JTextField(TEXTFELDBREITE);
        _gezahltTextfield.setCaretColor(SCHRIFTFARBE_NORMAL);
        _gezahltTextfield.setHorizontalAlignment(JTextField.RIGHT);
        _gezahltTextfield.setMargin(new Insets(5, 5, 5, 5));
        _gezahltTextfield.setBackground(HINTERGRUNDFARBE);
        _gezahltTextfield.setForeground(SCHRIFTFARBE_NORMAL);
        _gezahltTextfield.setFont(SCHRIFTART_GROSS);
        _gezahltTextfield.requestFocus();
    }

    private void initPreisTextfeld()
    {
        _preisTextfield = new JTextField(TEXTFELDBREITE);
        _preisTextfield.setHorizontalAlignment(JTextField.RIGHT);
        _preisTextfield.setBackground(HINTERGRUNDFARBE);
        _preisTextfield.setForeground(SCHRIFTFARBE_NORMAL);
        _preisTextfield.setFont(SCHRIFTART_KLEIN);
        _preisTextfield.setEditable(false);
        _preisTextfield.setFocusable(false);
    }

    /**
     * Erzeugt ein Textfeld mit Beschriftung in besonderer Anordnung-
     * 
     * @param titel Beschriftung des Textfeldes
     * @param component das Textfeld (oder eine andere Komponente)
     * @return ein Panel mit dem beschrifteten Textfeld
     */
    private Component erstelleLayoutPanel(String titel, JComponent component)
    {
        JPanel rueckgabePanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(titel);
        label.setHorizontalAlignment(JLabel.LEFT);
        rueckgabePanel.add(label, BorderLayout.NORTH);
        rueckgabePanel.add(component, BorderLayout.CENTER);

        return rueckgabePanel;
    }

    private JPanel erstelleRestbetragPanel()
    {
        JPanel restbetragPanel = new JPanel(new BorderLayout());

        _restBetragPanel = new JLabel(NOCH_ZU_ZAHLEN);
        _restBetragPanel.setHorizontalAlignment(JLabel.LEFT);
        restbetragPanel.add(_restBetragPanel, BorderLayout.NORTH);
        restbetragPanel.add(_restbetragTextfield, BorderLayout.CENTER);
        return restbetragPanel;
    }

}
