package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Ein Formatierer für Medien in einer Vormerkliste.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VormerkMedienFormatierer
{

    private final Kunde _entleiher;
    private final Medium _medium;
    private final Kunde _vormerker3;
    private final Kunde _vormerker2;
    private final Kunde _vormerker1;

    /**
     * Initialisiert einen neuen VormerkMedienFormatierer für das angegebene
     * Medium.
     * 
     * @param medium Ein adaptiertes Medium.
     * @param entleiher Der aktuelle Entleiher, kann null sein.
     * @param vormerker1 Der erste Vormerker, kann null sein.
     * @param vormerker2 Der zweite Vormerker, kann null sein.
     * @param vormerker3 Der dritte Vormerker, kann null sein.
     * 
     * @require medium != null
     */
    public VormerkMedienFormatierer(Medium medium, Kunde entleiher,
            Kunde vormerker1, Kunde vormerker2, Kunde vormerker3)
    {
        assert medium != null : "@require (medium != null)";
        _medium = medium;
        _entleiher = entleiher;
        _vormerker1 = vormerker1;
        _vormerker2 = vormerker2;
        _vormerker3 = vormerker3;
    }

    /**
     * Liefert das Medium des Formatierers zurück.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Gibt die Medienbezeichnung zurück.
     * 
     * @ensure result != null
     */
    public String getMedienBezeichnung()
    {
        return _medium.getMedienBezeichnung();
    }

    /**
     * Gibt den Medientitel zurück.
     * 
     * @ensure result != null
     */
    public String getTitel()
    {
        return _medium.getTitel();
    }

    /**
     * Gibt den Namen des dritten Vormerkers zurück. Der String ist leer, wenn
     * kein dritter Vormerker existiert.
     * 
     * @ensure result != null
     */
    public String getVormerker3()
    {
        return erzeugeKundendarstellung(_vormerker3);
    }

    /**
     * Gibt den Namen des zweiten Vormerkers zurück. Der String ist leer, wenn
     * kein zweiter Vormerker existiert.
     * 
     * @ensure result != null
     */
    public String getVormerker2()
    {
        return erzeugeKundendarstellung(_vormerker2);
    }

    /**
     * Gibt den Namen des ersten Vormerkers zurück. Der String ist leer, wenn
     * kein erster Vormerker existiert.
     * 
     * @ensure result != null
     */
    public String getVormerker1()
    {
        return erzeugeKundendarstellung(_vormerker1);
    }

    /**
     * Gibt den Namen des Entleihers zurück. Der String ist leer, wenn kein
     * Entleiher existiert.
     * 
     * @ensure result != null
     */
    public String getEntleiher()
    {
        return erzeugeKundendarstellung(_entleiher);
    }

    /**
     * Gibt eine Stringdarstellung des Kunden zurück. Der String ist leer, wenn
     * der übergebene Kunde null ist.
     * 
     * @param kunde Der Kunde. Kann null sein.
     * 
     * @ensure result != null
     */
    private String erzeugeKundendarstellung(Kunde kunde)
    {
        String result = "";
        if (kunde != null)
        {
            result = kunde.getVorname() + " " + kunde.getNachname();
        }
        return result;
    }
}
