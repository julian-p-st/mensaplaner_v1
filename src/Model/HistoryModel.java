package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Modell, welches die HISTORY enthält und die darin gespeicherten Daten verwaltet. Besitzt eine innere Klasse, welche
 * einen Historieneintrag darstellt {@link HistoryEntry}. Stellt Methoden bereit,
 * um der HISTORY einen {@link HistoryEntry} hinzuzufügen {@link #addToHistory(MensaMeal, LocalDate)} und um einen
 * {@link HistoryEntry} wieder aus der HISTORY zu entfernen {@link #removeFromHistory(MensaMeal, LocalDate)}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryModel {

    /**
     * Innere Klasse, welche einen Eintrag in der HISTORY darstellt.
     * Enthält Methoden, um eine String-Darstellung eines {@link HistoryEntry} für eine CSV-Datei zu erhalten
     * {@link #toString()} und um einen Header-String für eine CSV-Datei zu erzeugen {@link #getHeaderForCSV()}.
     * Überschreibt außerdem die Methoden aus dem Interface {@link Comparable}, um zwei {@link HistoryEntry}-Objekte
     * anhand ihres Datums vergleichen zu können.
     */

    public static class HistoryEntry implements Comparable<HistoryEntry> {

        private final LocalDate date;
        private final MensaMeal meal;

        private HistoryEntry(LocalDate date, MensaMeal meal) {
            this.date = date;
            this.meal = meal;
        }

        /**
         * Erzeugt einen Header für eine CSV-Datei, welcher alle Attribute eines {@link MensaMeal}-Gerichts mit ";"
         * separiert auflistet.
         *
         * @return einen CSV-Header als String-Darstellung.
         */

        public static String getHeaderForCSV(){
            return String.join(";",
                    "date",
                    "name",
                    "line",
                    "price",
                    "type",
                    "kcal",
                    "proteins",
                    "carbs",
                    "sugar",
                    "fat",
                    "saturated",
                    "salt",
                    "scoreCo2",
                    "scoreWater",
                    "scoreAnimals",
                    "scoreRainforest",
                    "co2Emissions",
                    "waterConsumption",
                    "additives"
            );
        }

        /**
         * Vergleicht zwei {@link HistoryEntry}-Objekte anhand ihres Datums.
         *
         * @param o das {@link HistoryEntry}-Objekt, welches verglichen werden soll.
         * @return ein negativer Integer, 0, oder ein positiver Integer, je nachdem ob das
         * {@link HistoryEntry}-Objekt ein kleineres, das gleiche, oder ein größeres Datum
         * als das spezifizierte {@link HistoryEntry}-Objekt besitzt.
         */

        @Override
        public int compareTo(HistoryEntry o) {
            return date.compareTo(o.date);
        }

        /**
         * Überprüft zwei {@link HistoryEntry}-Objekte auf Gleichheit.
         *
         * @param o das {@link HistoryEntry}-Objekt, welches verglichen werden soll.
         * @return true bzw. false, je nachdem ob das {@link HistoryEntry}-Objekt gleich bzw. ungleich
         * zum spezifizierten {@link HistoryEntry}-Objekt ist.
         */

        @Override
        public boolean equals(Object o) {
            if(o == null){
                return false;
            }
            if(o == this){
                return true;
            }
            if(!(o instanceof HistoryEntry)){
                return false;
            }
            HistoryEntry that = (HistoryEntry) o;
            return date.equals(that.date) && meal.getName().equals(that.meal.getName());
        }

        /**
         * Liefert einen Hash-Code Wert für das Objekt zurück.
         *
         * @return einen Hash-Code Wert für das Objekt.
         */

        @Override
        public int hashCode() {
            return Objects.hash(date, meal);
        }

        /**
         * Erzeugt eine String Darstellung eines {@link HistoryEntry}-Objekts für eine CSV-Datei,
         * welche alle Attribute eines {@link HistoryEntry} mit ";" separiert auflistet.
         *
         * @return eine String-Darstellung für eine CSV-Datei.
         */

        @Override
        public String toString() {
            return String.join(";",
                    date.toString(),
                    meal.getName(),
                    meal.getLine().toString(),
                    String.valueOf(meal.getPrice()),
                    meal.getType().toString(),
                    String.valueOf(meal.getKcal()),
                    String.valueOf(meal.getProteins()),
                    String.valueOf(meal.getCarbs()),
                    String.valueOf(meal.getSugar()),
                    String.valueOf(meal.getFat()),
                    String.valueOf(meal.getSaturated()),
                    String.valueOf(meal.getSalt()),
                    String.valueOf(meal.getScoreCo2()),
                    String.valueOf(meal.getScoreWater()),
                    String.valueOf(meal.getScoreAnimals()),
                    String.valueOf(meal.getScoreRainforest()),
                    String.valueOf(meal.getCo2Emissions()),
                    String.valueOf(meal.getWaterConsumption()),
                    meal.getAdditives().toString()
            );

        }

        /**
         * Standard-Getter für das im {@link HistoryEntry} gespeicherte {@link LocalDate}
         *
         * @return das gespeicherte Datum.
         */

        public LocalDate getDate() {
            return date;
        }

        /**
         * Standard-Getter für das im {@link HistoryEntry} gespeicherte {@link MensaMeal}-Gericht.
         *
         * @return das gespeicherte Gericht.
         */

        public MensaMeal getMeal() {
            return meal;
        }

    }

    /*
    Die zentrale Historie des Programms.
     */

    private final ArrayList<HistoryEntry> HISTORY = new ArrayList<>();

    /**
     * Fügt einen aus den übergebenen Parametern erstellten {@link HistoryEntry} der HISTORY hinzu.
     *
     * @param meal ein Gericht.
     * @param date das zum Gericht gehörende Datum.
     */

    public void addToHistory(MensaMeal meal, LocalDate date) {
        HISTORY.add(new HistoryEntry(date, meal));
        Collections.sort(HISTORY);
    }

    /**
     * Entfernt einen {@link HistoryEntry}, welcher die spezifizierten Parameter besitzt, aus der HISTORY.
     *
     * @param meal ein Gericht.
     * @param date das zum Gericht gehörende Datum.
     */

    public void removeFromHistory(MensaMeal meal, LocalDate date) {
        HISTORY.remove(new HistoryEntry(date, meal));
        Collections.sort(HISTORY);
    }

    /**
     * Standard-Getter, um die HISTORY zu erhalten.
     *
     * @return die HISTORY des Programms.
     */

    public ArrayList<HistoryEntry> getHistory() {
        return HISTORY;
    }

}
