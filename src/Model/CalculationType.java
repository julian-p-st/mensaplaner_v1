package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

/**
 * Enumeration, welche die statistisch im {@link StatisticsModel} auswertbaren Kennzahlen eines
 * {@link MensaMeal}-Gerichts als Konstanten enthält ("Berechnungstyp"). Außerdem werden Methoden bereitgestellt,
 * um eine String-Darstellung des Berechnungstyps zu erhalten {@link #getTypeToString(CalculationType)}
 * und um die jeweilige Einheit abzufragen {@link #getTypeUnit(CalculationType)}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public enum CalculationType {
    PRICE, KCAL, PROTEINS, CARBS, SUGAR, FAT, SATURATED, SALT;

    /**
     * Liefert eine String-Darstellung des übergebenen {@link CalculationType} zurück.
     * @param type der {@link CalculationType}, dessen String-Darstellung zurückgegeben werden soll.
     * @return eine String-Darstellung.
     */

    public static String getTypeToString(CalculationType type) {
        return switch (type) {
            case PRICE: yield "Preis";
            case KCAL: yield "Kalorien";
            case PROTEINS: yield "Proteine";
            case CARBS: yield "Kohlenhydrate";
            case SUGAR: yield "Zucker";
            case FAT: yield "Fette";
            case SATURATED: yield "Gesättigte Fette";
            case SALT: yield "Salz";
        };
    }

    /**
     * Liefert die Einheit eines übergebenen {@link CalculationType} als String-Darstellung zurück.
     * @param type der {@link CalculationType}, dessen Einheit als String-Darstellung zurückgegeben werden soll.
     * @return die Einheit als String-Darstellung.
     */

    public static String getTypeUnit(CalculationType type) {
        return switch(type){
            case PRICE -> "€";
            case KCAL -> "kcal";
            case PROTEINS,CARBS,SUGAR,FAT,SATURATED,SALT -> "g";

        };
    }

}
