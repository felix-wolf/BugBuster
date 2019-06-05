package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

/**
 * Ein Button, mit dem ein Sitzplatz in der Benutzeroberfläche dargestellt wird.
 * Der Sitzplatz kann ausgewählt und als frei oder als verkauft gekennzeichnet
 * werden.
 * 
 * @author SE2-Team
 * @version SoSe 2018
 */
class JPlatzButton extends JButton
{
    private static final long serialVersionUID = 1498799618824175365L;
    private static final Color FARBE_FREI = Color.GREEN;
    private static final Color FARBE_VERKAUFT = Color.RED;
    private static final Color FARBE_AUSGEWAEHLT = Color.YELLOW;

    private final Border defaultBorder = new BevelBorder(BevelBorder.RAISED);
    private final Border loweredBorder = new BevelBorder(BevelBorder.LOWERED);
    private Border currentBorder = defaultBorder;

    private Platz _platz;
    private boolean _verkauft;
    private boolean _ausgewaehlt;

    /**
     * Erzeugt einen neuen Button. Der Button wird mit der Nummer des Sitzes in
     * seiner Sitzreihe beschriftet.
     * 
     * @param platz der Platz, auf den sich dieser Button bezieht.
     * 
     * @require platz != null
     */
    public JPlatzButton(Platz platz)
    {
        super("" + platz.getSitz());
        _platz = platz;
        _verkauft = false;
        _ausgewaehlt = false;
    }

    @Override
    protected void paintBorder(Graphics g)
    {
        if (getModel().isArmed() || _ausgewaehlt)
        {
            currentBorder = loweredBorder;
        }
        else
        {
            currentBorder = defaultBorder;
        }
        currentBorder.paintBorder(this, g, 0, 0, getWidth(), getHeight());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        /*
         * Der Button zeichnet sich selbst, weil das Einfaerben basierend auf
         * seinem Zustand mit dem normalen JButton nicht plattformuebergreifend
         * fuer alle Look&Feels funktioniert.
         */

        // Diese Methode soll den Zustand von g nicht veraendern, deshalb wird
        // hier eine Kopie erstellt, mit der dann gearbeitet wird.
        Graphics graphics = g.create();

        try
        {
            // Hintergrund einfaerben
            Color color = farbeFuerAktuellenZustand();
            graphics.setColor(color);
            Insets borderInsets = currentBorder.getBorderInsets(this);
            Rectangle viewRect = new Rectangle();
            viewRect.x = borderInsets.left;
            viewRect.y = borderInsets.top;
            viewRect.width = getWidth() - borderInsets.left
                    - borderInsets.right;
            viewRect.height = getHeight() - borderInsets.top
                    - borderInsets.bottom;
            graphics.fillRect(viewRect.x, viewRect.y, viewRect.width,
                    viewRect.height);

            // Beschriftung des Buttons zeichnen
            graphics.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            graphics.drawString(getText(), x, y);
        }
        finally
        {
            graphics.dispose();
        }
    }

    /**
     * Gibt die Farbe zurück, in der der Button-Hintergrund gezeichnet werden
     * soll, basierend auf dem aktuellen Zustand dieses Buttons.
     */
    private Color farbeFuerAktuellenZustand()
    {
        if (_ausgewaehlt)
        {
            return FARBE_AUSGEWAEHLT;
        }
        else
        {
            if (_verkauft)
            {
                return FARBE_VERKAUFT;
            }
            else
            {
                return FARBE_FREI;
            }
        }
    }

    /**
     * Gibt den Platz zurück, auf den sich dieser Button bezieht.
     */
    public Platz getPlatz()
    {
        return _platz;
    }

    /**
     * Kennzeichnet den Sitzplatz, den dieser Button anzeigt, als verkauft oder
     * frei. Nach Aufruf dieser Methode wird der Button nicht mehr als
     * ausgewählt angezeigt.
     * 
     * @param verkauft <code>true</code>, wenn der Sitzplatz als verkauft
     *            gekennzeichnet werden soll, <code>false</code>, wenn er als
     *            frei angezeigt werden soll.
     */
    public void setVerkauft(boolean verkauft)
    {
        _verkauft = verkauft;
        repaint();
    }

    /**
     * Zeigt diesen Button als ausgewählt oder nicht ausgewählt an.
     * 
     * @param ausgewaehlt <code>true</code>, wenn der Button als ausgewählt
     *            angezeigt werden soll, <code>false</code> sonst.
     */
    public void setAusgewaehlt(boolean ausgewaehlt)
    {
        _ausgewaehlt = ausgewaehlt;
        repaint();
    }
}
