package de.uni_hamburg.informatik.swt.se2.mediathek.services;

/**
 * Das interface für beobachtbare Services. Definiert Operationen zum An- und
 * Abmelden von Beobachtern.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public interface ObservableService
{

    /**
     * Meldet den gegebenen Beoabachter beim an.
     * 
     * @param beobachter Ein Beobachter, der angemeldet werden soll.
     * 
     * @require beobachter != null
     */
    void registriereBeobachter(ServiceObserver beobachter);

    /**
     * Meldet den gegebenen Beoabachter beim ab.
     * 
     * @param beobachter Ein Beobachter, der abgemeldet werden soll.
     * 
     * @require beobachter != null
     */
    void entferneBeobachter(ServiceObserver beobachter);
}
