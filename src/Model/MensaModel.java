package Model;

import edu.kit.aifb.atks.mensascraper.lib.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Modell, welches auf die Daten der {@link KITMensaScraper}-API zugreift und diese verwaltet.
 * Stellt eine Methode bereit, um den Speiseplan bei der API abzurufen {@link #getMeals(MensaLocation, LocalDate)}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class MensaModel {

    private final KITMensaScraper scraper;

    /**
     * Konstruktor der Klasse {@link MensaModel}.
     */

    public MensaModel() {
        this.scraper = new KITMensaScraper();
    }

    /**
     * Liefert den Speiseplan einer spezifizierten Mensa zu einem übergebenen Datum zurück.
     *
     * @param location der Ort der Mensa, dessen Speiseplan abgerufen werden soll.
     * @param date das Datum, an dem der Speiseplan abgerufen werden soll.
     * @return eine Liste mit den {@link MensaMeal}-Gerichten eines Tages.
     * @throws MensaScraperException Exception, welche auftreten kann, wenn z.B.
     * keine Daten für das übergebene Datum verfügbar sind.
     */

    public List<MensaMeal> getMeals(MensaLocation location, LocalDate date) throws MensaScraperException {
        return scraper.fetchMeals(location, date);
    }

}
