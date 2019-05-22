package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Vormerkkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.AbstractObservableService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;

/**
 * Diese Klasse implementiert das Interface VerleihService. Siehe dortiger
 * Kommentar.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class VerleihServiceImpl extends AbstractObservableService
        implements VerleihService
{
	
	/**
	 * Diese Map speichert für jedes eingefügte Medium die Vormerkkarte. 
	 * Ein Zugriff auf die VormerkKarte ist dadurch leicht über
     * die Angabe des Mediums möglich. Beispiel: _vormerkKarten.get(medium)
	 */
	private Map<Medium, Vormerkkarte> _vormerkkarten;
    /**
     * Diese Map speichert für jedes eingefügte Medium die dazugehörige
     * Verleihkarte. Ein Zugriff auf die Verleihkarte ist dadurch leicht über
     * die Angabe des Mediums möglich. Beispiel: _verleihkarten.get(medium)
     */
    private Map<Medium, Verleihkarte> _verleihkarten;

    /**
     * Der Medienbestand.
     */
    private MedienbestandService _medienbestand;

    /**
     * Der Kundenstamm.
     */
    private KundenstammService _kundenstamm;

    /**
     * Der Protokollierer für die Verleihvorgänge.
     */
    private VerleihProtokollierer _protokollierer;

    /**
     * Konstruktor. Erzeugt einen neuen VerleihServiceImpl.
     * 
     * @param kundenstamm Der KundenstammService.
     * @param medienbestand Der MedienbestandService.
     * @param initialBestand Der initiale Bestand.
     * 
     * @require kundenstamm != null
     * @require medienbestand != null
     * @require initialBestand != null
     */
    public VerleihServiceImpl(KundenstammService kundenstamm,
            MedienbestandService medienbestand,
            List<Verleihkarte> initialBestand)
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm  != null";
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand  != null";
        assert initialBestand != null : "Vorbedingung verletzt: initialBestand  != null";
        _verleihkarten = erzeugeVerleihkartenBestand(initialBestand);
        _vormerkkarten = new HashMap<>();
        _kundenstamm = kundenstamm;
        _medienbestand = medienbestand;
        _protokollierer = new VerleihProtokollierer();
    }

    /**
     * Erzeugt eine neue HashMap aus dem Initialbestand.
     */
    private HashMap<Medium, Verleihkarte> erzeugeVerleihkartenBestand(
            List<Verleihkarte> initialBestand)
    {
        HashMap<Medium, Verleihkarte> result = new HashMap<Medium, Verleihkarte>();
        for (Verleihkarte verleihkarte : initialBestand)
        {
            result.put(verleihkarte.getMedium(), verleihkarte);
        }
        return result;
    }

    @Override
    public List<Verleihkarte> getVerleihkarten()
    {
        return new ArrayList<Verleihkarte>(_verleihkarten.values());
    }

    @Override
    public boolean istVerliehen(Medium medium)
    {
        assert mediumImBestand(medium) : "Vorbedingung verletzt: mediumExistiert(medium)";
        return _verleihkarten.get(medium) != null;
    }

    @Override
    public boolean istVerleihenMoeglich(Kunde kunde, List<Medium> medien)
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienImBestand(medien)";

                
        for (Medium medium: medien)
        {                
        	if (existiertVormerkkarte(medium) && !kunde.equals(_vormerkkarten.get(medium).getVormerker(0)))
        	{
        			return false;
        	}
        }
        return sindAlleNichtVerliehen(medien);
    }

    @Override
    public void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
            throws ProtokollierException
    {
        assert sindAlleVerliehen(medien) : "Vorbedingung verletzt: sindAlleVerliehen(medien)";
        assert rueckgabeDatum != null : "Vorbedingung verletzt: rueckgabeDatum != null";

        for (Medium medium : medien)
        {
            Verleihkarte verleihkarte = _verleihkarten.get(medium);
            _verleihkarten.remove(medium);
            _protokollierer.protokolliere(
                    VerleihProtokollierer.EREIGNIS_RUECKGABE, verleihkarte);
        }

        informiereUeberAenderung();
    }

    @Override
    public boolean sindAlleNichtVerliehen(List<Medium> medien)
    {
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (istVerliehen(medium))
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean sindAlleVerliehenAn(Kunde kunde, List<Medium> medien)
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (!istVerliehenAn(kunde, medium))
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean istVerliehenAn(Kunde kunde, Medium medium)
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert mediumImBestand(medium) : "Vorbedingung verletzt: mediumImBestand(medium)";

        return istVerliehen(medium) && getEntleiherFuer(medium).equals(kunde);
    }

    @Override
    public boolean sindAlleVerliehen(List<Medium> medien)
    {
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (!istVerliehen(medium))
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
            throws ProtokollierException
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert sindAlleNichtVerliehen(medien) : "Vorbedingung verletzt: sindAlleNichtVerliehen(medien) ";
        assert ausleihDatum != null : "Vorbedingung verletzt: ausleihDatum != null";
        assert istVerleihenMoeglich(kunde, medien) : "Vorbedingung verletzt:  istVerleihenMoeglich(kunde, medien)";

        for (Medium medium : medien)
        {
        	Verleihkarte verleihkarte = new Verleihkarte(kunde, medium, ausleihDatum);
            _verleihkarten.put(medium, verleihkarte);
            _protokollierer.protokolliere(VerleihProtokollierer.EREIGNIS_AUSLEIHE, verleihkarte);
            
            if (existiertVormerkkarte(medium))
            {
            	entferneVormerkung(kunde, medium);   
            }
        }
        // Was passiert wenn das Protokollieren mitten in der Schleife
        // schief geht? informiereUeberAenderung in einen finally Block?
        informiereUeberAenderung();
    }

    @Override
    public boolean kundeImBestand(Kunde kunde)
    {
        return _kundenstamm.enthaeltKunden(kunde);
    }

    @Override
    public boolean mediumImBestand(Medium medium)
    {
        return _medienbestand.enthaeltMedium(medium);
    }

    @Override
    public boolean medienImBestand(List<Medium> medien)
    {
        assert medien != null : "Vorbedingung verletzt: medien != null";
        assert !medien.isEmpty() : "Vorbedingung verletzt: !medien.isEmpty()";
        
        boolean result = true;
        for (Medium medium : medien)
        {
            if (!mediumImBestand(medium))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Medium> getAusgelieheneMedienFuer(Kunde kunde)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        List<Medium> result = new ArrayList<Medium>();
        for (Verleihkarte verleihkarte : _verleihkarten.values())
        {
            if (verleihkarte.getEntleiher()
                .equals(kunde))
            {
                result.add(verleihkarte.getMedium());
            }
        }
        return result;
    }

    @Override
    public Kunde getEntleiherFuer(Medium medium)
    {
        assert istVerliehen(
                medium) : "Vorbedingung verletzt: istVerliehen(medium)";
        Verleihkarte verleihkarte = _verleihkarten.get(medium);
        return verleihkarte.getEntleiher();
    }

    @Override
    public Verleihkarte getVerleihkarteFuer(Medium medium)
    {
        assert istVerliehen(
                medium) : "Vorbedingung verletzt: istVerliehen(medium)";
        return _verleihkarten.get(medium);
    }

    @Override
    public List<Verleihkarte> getVerleihkartenFuer(Kunde kunde)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        List<Verleihkarte> result = new ArrayList<Verleihkarte>();
        for (Verleihkarte verleihkarte : _verleihkarten.values())
        {
            if (verleihkarte.getEntleiher()
                .equals(kunde))
            {
                result.add(verleihkarte);
            }
        }
        return result;
    }
    
    @Override
    public Vormerkkarte getVormerkkarte(Medium medium)
    {
    	assert medium != null : "Vorbedingung verletzt: medium != null";

    	return _vormerkkarten.get(medium);
    }
    
    @Override
    public void merkeVor(Kunde kunde, Medium medium)
    {
    	assert medium != null : "Vorbedingung verletzt: medium != null";
    	assert kunde != null : "Vorbedingung verletzt: kunde != null";
    	assertTrue("Vorbedingung verletzt: vormerken nicht möglich", istVormerkenMoeglich(kunde, medium));
    
    	if (existiertVormerkkarte(medium))
    	{
    		_vormerkkarten.get(medium).addVormerker(kunde);
    	}
    	else 
    	{
    		Vormerkkarte _karte = new Vormerkkarte(kunde, medium);
        	_vormerkkarten.put(medium, _karte);
    	}
    	
    	informiereUeberAenderung();
    }
    
    @Override
    public boolean existiertVormerkkarte(Medium medium)
    {
    	assert medium != null : "Vorbedingung verletzt: medium != null";
    	
    	return _vormerkkarten.containsKey(medium);
    }
    
    @Override
    public boolean istVormerkenMoeglich(Kunde kunde, Medium medium)
    {
    	if (existiertVormerkkarte(medium))
    	{
    		return !istVorgemerktVon(kunde, medium) && _vormerkkarten.get(medium).getAnzahlVormerker() < 3;
    	}
    	return true;
    }
    @Override
    public int getAnzahlVormerker(Medium medium)
    {
    	assert medium != null : "Vorbedingung verletzt: medium != null";

    	return _vormerkkarten.get(medium).getAnzahlVormerker();
    }

    
    @Override
    public boolean istVormerkenMoeglich(Kunde kunde, List<Medium> medien)
    {
    	assert medien != null : "Vorbedingung verletzt: medien != null";
    	assert kunde != null : "Vorbedingung verletzt: kunde != null";

    	for (Medium medium : medien)
    	{
    		if (!istVormerkenMoeglich(kunde, medium))
    		{
    			return false;
   			}
    	}
    	return true;
    }
    
    @Override
    public void entferneVormerkung(Kunde kunde, Medium medium)
    {
    	assert medium != null : "Vorbedingung verletzt: medium != null";
    	assert kunde != null : "Vorbedingung verletzt: kunde != null";
    	assert existiertVormerkkarte(medium) : "existiertVormerkkarte(medium)";
    	
    	if (existiertVormerkkarte(medium) && istVorgemerktVon(kunde, medium))
    	{
    		_vormerkkarten.get(medium).removeVormerker(kunde);
    	}
    	
    	if (_vormerkkarten.get(medium).getAnzahlVormerker() == 0)
    	{
    		_vormerkkarten.remove(medium);
    	}	
    }
    
    @Override
    public Kunde getVormerker(Medium medium, int index)
    {
    	_vormerkkarten.get(medium);
		assert index <= 0 && index < Vormerkkarte.MaxAnzahlVormerker: 
    		"Vorbedingung verletzt: index <= 0 && index < _vormerkkarten.get(medium).MaxAnzahlVormerker";
		
		return _vormerkkarten.get(medium).getVormerker(index);
    }
    
    @Override
    public boolean istVorgemerktVon(Kunde kunde, Medium medium)
    {
    	assert medium != null : "Vorbedingung verletzt: medium != null";
    	assert kunde != null : "Vorbedingung verletzt: kunde != null";
    	
    	if (existiertVormerkkarte(medium))
    	{
    		return _vormerkkarten.get(medium).istVorgemerktVon(kunde);
    	}
    	return false;
    }
    
}
